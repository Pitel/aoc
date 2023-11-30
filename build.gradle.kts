plugins {
    kotlin("jvm") version "2.0.0-Beta1"
    application
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.application")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    }

    application {
        mainClass.set("aoc.AppKt")
    }
}
