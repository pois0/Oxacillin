
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension



fun KotlinMultiplatformExtension.commonConfig() {
    explicitApi()

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

            @OptIn(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalDceDsl::class)
            dceTask {
                // workaround for https://kotlinlang.org/docs/reference/javascript-dce.html#known-issue-dce-and-ktor.
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
        }
    }
}
