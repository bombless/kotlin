/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.platform

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ServiceManager

interface DefaultIdeTargetPlatformKindProvider {
    val defaultPlatform: IdePlatform<*, *>

    companion object {
        val defaultPlatform: IdePlatform<*, *>
            get() {
                if (ApplicationManager.getApplication() == null) {
                    // TODO support passing custom platforms in JPS
                    error("Application is 'null', are you calling this code from JPS?")
                }
                return ServiceManager.getService(DefaultIdeTargetPlatformKindProvider::class.java).defaultPlatform
            }
    }
}