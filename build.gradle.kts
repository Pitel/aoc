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
        val kotest = "6.0.0.M1"
        testImplementation("io.kotest:kotest-runner-junit5:$kotest")
        testImplementation("io.kotest:kotest-assertions-core:$kotest")
    }

    application {
        mainClass.set("aoc.AppKt")
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
