description = ""

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    `maven-publish`
    signing

    id("org.jetbrains.dokka")
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

/*
 * Publishing
 */

tasks {
    addKdocTask(dokkaHtml)
}

publishing {
    setRepository(this@Build_gradle::uri, tasks, rootProject.name, project.name)
}

signing {
    commonConfig(gradle, publishing)
}

//publishing {
//    repositories {
//        maven {
//            name = "Sonatype"
//            url = uri(
//                if (System.getenv(Env.Version).orEmpty().endsWith("-SNAPSHOT")) {
//                    Publications.MavenCentralSnapshotRepositoryUrl
//                } else {
//                    Publications.MavenCentralStagingRepositoryUrl
//                }
//            )
//
//            credentials {
//                username = System.getenv(Env.OSSRHUsername)
//                password = System.getenv(Env.OSSRHPassword)
//            }
//        }
//
//        maven {
//            name = "GitHubPackages"
//            url = uri(Publications.GitHubPackagesRepositoryUrl)
//
//            credentials {
//                username = System.getenv(Env.GitHubUsername)
//                password = System.getenv(Env.GitHubPassword)
//            }
//        }
//    }
//
//    publications.withType<MavenPublication> {
//        groupId = Publications.GroupId
//        artifactId = when (name) {
//            "kotlinMultiplatform" -> {
//                rootProject.name
//            }
//            else -> {
//                "${rootProject.name}-$name"
//            }
//        }
//        version = System.getenv(Env.Version)
//
//        pom {
//            name.set(artifactId)
//            description.set(Publications.Description)
//            url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")
//
//            licenses {
//                license {
//                    name.set(Publications.LicenseName)
//                    url.set(Publications.LicenseUrl)
//                }
//            }
//
//            developers {
//                developer {
//                    id.set(Publications.DeveloperId)
//                    name.set(Publications.DeveloperName)
//                    email.set(Publications.DeveloperEmail)
//                    organization.set(Publications.DeveloperOrganization)
//                    organizationUrl.set(Publications.DeveloperOrganizationUrl)
//                }
//            }
//
//            scm {
//                url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")
//            }
//        }
//
//        artifact(tasks["kdocJar"])
//    }
//}
//
