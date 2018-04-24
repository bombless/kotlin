plugins {
    kotlin("jvm")
    id("jps-compatible")
}

jvmTarget = "1.8"

dependencies {
    compile("com.google.guava:guava:24.1-jre")
    compile("com.beust:jcommander:1.72")
    compile("com.google.protobuf:protobuf-java:3.5.1")
    compile("com.google.code.gson:gson:2.8.2")
    compile("com.google.code.findbugs:jsr305:3.0.2")
    compile("org.ow2.asm:asm-all:6.0_BETA")
    compile("com.google.protobuf:protobuf-java-util:3.5.1")
    compile("com.google.re2j:re2j:1.2")
    compile("com.google.auto.value:auto-value:1.6")
    compile("com.google.auto.value:auto-value-annotations:1.6")
    annotationProcessor("com.google.auto.value:auto-value:1.6")

}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}
