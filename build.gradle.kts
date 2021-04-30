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
