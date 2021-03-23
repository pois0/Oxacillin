description = ""

object Versions {
    const val Ktor = "1.5.2"
    const val uuid = "0.2.3"
    const val KotlinxDatetime = "0.1.1"

    const val crypto_js = "4.0.0"

    const val JUnit = "5.7.1"
    const val TwitterText = "3.1.0"
    const val Guava = "30.1-jre"

    const val KotlinLogging = "2.0.5"
    const val Logback = "1.2.3"
    const val jansi = "1.18"
}

object Libraries {
    const val KtorClientCore = "io.ktor:ktor-client-core:${Versions.Ktor}"
    const val uuid = "com.benasher44:uuid:${Versions.uuid}"
    const val KotlinxDatetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KotlinxDatetime}"
    const val KotlinLogging = "io.github.microutils:kotlin-logging:${Versions.KotlinLogging}"

    const val KtorClientApache = "io.ktor:ktor-client-apache:${Versions.Ktor}"
    const val KtorClientCIO = "io.ktor:ktor-client-cio:${Versions.Ktor}"
    const val KtorClientJetty = "io.ktor:ktor-client-jetty:${Versions.Ktor}"
    const val KtorClientOkhttp = "io.ktor:ktor-client-okhttp:${Versions.Ktor}"
    const val KtorClientMockJvm = "io.ktor:ktor-client-mock-jvm:${Versions.Ktor}"

    const val TwitterText = "com.twitter.twittertext:twitter-text:${Versions.TwitterText}"
    const val Guava = "com.google.guava:guava:${Versions.Guava}"

    const val JUnitJupiter = "org.junit.jupiter:junit-jupiter:${Versions.JUnit}"
    const val LogbackCore = "ch.qos.logback:logback-core:${Versions.Logback}"
    const val LogbackClassic = "ch.qos.logback:logback-classic:${Versions.Logback}"
    const val Jansi = "org.fusesource.jansi:jansi:${Versions.jansi}"

    const val KtorClientJs = "io.ktor:ktor-client-js:${Versions.Ktor}"
    const val KtorClientMockJs = "io.ktor:ktor-client-mock-js:${Versions.Ktor}"

    val ExperimentalAnnotations = setOf(
        "kotlin.Experimental",
        "kotlin.time.ExperimentalTime",
        "io.ktor.util.InternalAPI",
        "kotlinx.coroutines.FlowPreview",
        "jp.pois.oxacillin.core.experimental.OxacillinExperimentalApi"
    )
}

plugins {
    kotlin("multiplatform") version "1.4.30"

    `maven-publish`
    signing

    id("org.jetbrains.dokka") version "1.4.20"
}

kotlin {
    explicitApi()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    js(BOTH) {
        nodejs()
        browser {
            testTask {
                useKarma {
                    useChrome()
                }
            }

            dceTask {
                // workaround for https://kotlinlang.org/docs/reference/javascript-dce.html#known-issue-dce-and-ktor.
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
        }
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                apiVersion = "1.4"
                languageVersion = "1.4"
                allWarningsAsErrors = true
                verbose = true

                if (this is org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions) {
                    useIR = true
                }
            }
        }
    }

    sourceSets.all {
        languageSettings.progressiveMode = true

        languageSettings.enableLanguageFeature("InlineClasses")
        Libraries.ExperimentalAnnotations.forEach {
            languageSettings.useExperimentalAnnotation(it)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(Libraries.KtorClientCore)
                api("io.ktor:ktor-client-serialization:${Versions.Ktor}")
                api(Libraries.uuid)
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

        named("jvmMain") {
        }
        named("jvmTest") {
            dependencies {
                implementation(kotlin("reflect"))
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