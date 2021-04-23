description = ""

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    `maven-publish`
    signing

    id("org.jetbrains.dokka") version "1.4.20"
}


kotlin {
    commonConfig()

    sourceSets.all {
        dependencies {
            api(project(":core"))
        }
    }
}
