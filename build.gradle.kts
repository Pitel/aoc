plugins {
    kotlin("jvm") version "2.2.21"
    application
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.application")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
        val kotest = "6.0.7"
        testImplementation("io.kotest:kotest-runner-junit5:$kotest")
        testImplementation("io.kotest:kotest-assertions-core:$kotest")
    }

    application.mainClass = "aoc.AppKt"

    kotlin.jvmToolchain(21)

    tasks.test {
        useJUnitPlatform()
        testLogging.showStandardStreams = true
    }
}
