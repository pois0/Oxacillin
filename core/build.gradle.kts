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

    sourceSets {
        commonMain {
            dependencies {
                api(Libraries.KtorClientCore)
                api(Libraries.uuid)
                api(Libraries.KotlinxSerializationJson)
                api(Libraries.KotlinxDatetime)
                api(Libraries.KotlinLogging)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        named("jvmMain") {}

        named("jvmTest") {
            dependencies {
                implementation(kotlin("test"))

                implementation(Libraries.KtorClientApache)
                implementation(Libraries.KtorClientCIO)
                implementation(Libraries.KtorClientJetty)
                implementation(Libraries.KtorClientOkhttp)
                implementation(Libraries.KtorClientMockJvm)

                implementation(kotlin("test-junit5"))
                implementation(Libraries.JUnitJupiter)

                implementation(Libraries.TwitterText)
                implementation(Libraries.Guava)

                implementation(Libraries.LogbackCore)
                implementation(Libraries.LogbackClassic)
                implementation(Libraries.Jansi)
            }
        }

        named("jsMain") {
            dependencies {
                api(Libraries.KtorClientJs)

                implementation(npm("crypto-js", Versions.crypto_js))
            }
        }
        named("jsTest") {
            dependencies {
                implementation(kotlin("test-js"))

                implementation(Libraries.KtorClientMockJs)
            }
        }
    }
}
