plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.0-Beta"
    application
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.application")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    }

    application {
        mainClass.set("aoc22.AppKt")
    }
}
