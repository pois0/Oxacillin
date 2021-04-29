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

subprojects {
    group = "jp.pois"

    /*
     * Dependencies
     */
    repositories {
        mavenCentral()
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
