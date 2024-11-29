plugins {
    kotlin("jvm") version "2.1.0"
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
    }

    application {
        mainClass.set("aoc.AppKt")
    }
}
