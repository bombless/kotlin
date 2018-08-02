// TODO:
// LANGUAGE_VERSION: 1.3

// TARGET_BACKEND: JVM
// WITH_RUNTIME
// WITH_COROUTINES

import helpers.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*
import kotlin.coroutines.jvm.internal.*

fun getLabelValue(c: Continuation<*>): Int {
    val field = c.javaClass.getDeclaredField("label")
    field.setAccessible(true)
    return field.get(c) as Int - 1
}

suspend fun getSourceFileAndLineNumberFromContinuation() = suspendCoroutineUninterceptedOrReturn<Pair<String, Int>> {
    getSourceFileAndLineNumber(it, getLabelValue(it))
}

var continuation: Continuation<*>? = null

suspend fun suspendHere() = suspendCoroutineUninterceptedOrReturn<Unit> {
    continuation = it
    COROUTINE_SUSPENDED
}

suspend fun dummy() {}

suspend fun named(): Pair<String, Int> {
    dummy()
    return getSourceFileAndLineNumberFromContinuation()
}

suspend fun suspended() {
    dummy()
    suspendHere()
}

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

fun box(): String {
    var res: Any? = null
    builder {
        res = named()
    }
    if (res != Pair("runtimeDebugMetadata.kt", 34)) {
        return "" + res
    }
    builder {
        dummy()
        res = getSourceFileAndLineNumberFromContinuation()
    }
    if (res != Pair("runtimeDebugMetadata.kt", 56)) {
        return "" + res
    }

    builder {
        suspended()
    }
    res = getSourceFileAndLineNumber(continuation!!, getLabelValue(continuation!!))
    if (res != Pair("runtimeDebugMetadata.kt", 39)) {
        return "" + res
    }
    return "OK"
}