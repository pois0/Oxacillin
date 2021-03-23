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

object Publications {
    const val GroupId = "blue.starry"
    const val OSSRHProfileGroupId = "blue.starry.jsonkt"
    const val Description = "Full-featured Twitter API wrapper for Kotlin"
    const val GitHubUsername = "StarryBlueSky"
    const val GitHubRepository = "Oxacillin"

    const val LicenseName = "The MIT Licence"
    const val LicenseUrl = "https://opensource.org/licenses/MIT"

    const val DeveloperId = "StarryBlueSky"
    const val DeveloperName = "The Starry Blue Sky"
    const val DeveloperEmail = "letter@starry.blue"
    const val DeveloperOrganization = "The Starry Blue Sky"
    const val DeveloperOrganizationUrl = "https://github.com/StarryBlueSky"

    const val MavenCentralStagingRepositoryUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
    const val MavenCentralSnapshotRepositoryUrl = "https://oss.sonatype.org/content/repositories/snapshots"
    const val GitHubPackagesRepositoryUrl = "https://maven.pkg.github.com/$GitHubUsername/$GitHubRepository"
}

object Env {
    const val Version = "VERSION"

    const val OSSRHProfileId = "OSSRH_PROFILE_ID"
    const val OSSRHUsername = "OSSRH_USERNAME"
    const val OSSRHPassword = "OSSRH_PASSWORD"

    const val GitHubUsername = "GITHUB_USERNAME"
    const val GitHubPassword = "GITHUB_PASSWORD"

    const val SigningKeyId = "SIGNING_KEYID"
    const val SigningKey = "SIGNING_KEY"
    const val SigningPassword = "SIGNING_PASSWORD"
}

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

allprojects {
    group = "jp.pois"

    /*
     * Dependencies
     */
    repositories {
        mavenCentral()

        // TODO: For kotlinx-datetime; should remove it by May 01, 2021
        maven(url = "https://kotlin.bintray.com/kotlinx/")
        // TODO: For dokka; should remove it by May 01, 2021
        jcenter()
    }
}

//
///*
// * Tests
// */
//
//ktlint {
//    verbose.set(true)
//    outputToConsole.set(true)
//    reporters {
//        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
//    }
//    ignoreFailures.set(true)
//}
//
//buildtimetracker {
//    reporters {
//        register("summary") {
//            options["ordered"] = "true"
//            options["barstyle"] = "ascii"
//            options["shortenTaskNames"] = "false"
//        }
//    }
//}
//
//tasks.withType<Test> {
//    useJUnitPlatform()
//
//    testLogging {
//        showStandardStreams = true
//        events("passed", "failed")
//    }
//}
//
///*
// * Publishing
// */
//
//tasks {
//    register<Jar>("kdocJar") {
//        from(dokkaHtml)
//        dependsOn(dokkaHtml)
//        archiveClassifier.set("javadoc")
//    }
//}
//
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
//signing {
//    setRequired { gradle.taskGraph.hasTask("publish") }
//    sign(publishing.publications)
//
//    if (System.getenv(Env.SigningKey) != null) {
//        @Suppress("UnstableApiUsage")
//        useInMemoryPgpKeys(
//            System.getenv(Env.SigningKeyId),
//            System.getenv(Env.SigningKey),
//            System.getenv(Env.SigningPassword)
//        )
//    }
//}
//
//nexusStaging {
//    packageGroup = Publications.OSSRHProfileGroupId
//    stagingProfileId = System.getenv(Env.OSSRHProfileId)
//    username = System.getenv(Env.OSSRHUsername)
//    password = System.getenv(Env.OSSRHPassword)
//}
