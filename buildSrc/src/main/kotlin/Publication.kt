import org.gradle.api.invocation.Gradle
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.TaskContainerScope
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URI

object Publications {
    const val GroupId = "jp.pois"
    const val OSSRHProfileGroupId = "jp.pois.oxacillin"
    const val Description = "Twitter API Wrapper for Kotlin Multiplatform forked from Penicillin"
    const val GitHubUsername = "pois0"
    const val GitHubRepository = "Oxacillin"

    const val LicenseName = "The MIT Licence"
    const val LicenseUrl = "https://opensource.org/licenses/MIT"

    const val DeveloperId = "pois0"
    const val DeveloperName = "poispois"
    const val DeveloperEmail = "dev@pois.jp"
    const val DeveloperOrganization = "poispois"
    const val DeveloperOrganizationUrl = "https://github.com/pois0"

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

fun TaskContainerScope.addKdocTask(dokkaHtml: TaskProvider<DokkaTask>) {
    register<Jar>("kdocJar") {
        from(dokkaHtml)
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
    }
}

fun PublishingExtension.setRepository(uriFunc: (Any) -> URI, tasks: TaskContainer, rootProjectName: String, projectName: String) {
    repositories {
        maven {
            name = "Sonatype"
            url = uriFunc(
                if (System.getenv(Env.Version).orEmpty().endsWith("-SNAPSHOT")) {
                    Publications.MavenCentralSnapshotRepositoryUrl
                } else {
                    Publications.MavenCentralStagingRepositoryUrl
                }
            )

            credentials {
                username = System.getenv(Env.OSSRHUsername)
                password = System.getenv(Env.OSSRHPassword)
            }
        }
    }

    publications.withType<MavenPublication> {
        groupId = Publications.GroupId
        artifactId = when (name) {
            "kotlinMultiplatform" -> {
                "${rootProjectName}-${projectName}"
            }
            else -> {
                "${rootProjectName}-${projectName}-$name"
            }
        }
        version = System.getenv(Env.Version)

        pom {
            name.set(artifactId)
            description.set(Publications.Description)
            url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")

            licenses {
                license {
                    name.set(Publications.LicenseName)
                    url.set(Publications.LicenseUrl)
                }
            }

            developers {
                developer {
                    id.set(Publications.DeveloperId)
                    name.set(Publications.DeveloperName)
                    email.set(Publications.DeveloperEmail)
                    organization.set(Publications.DeveloperOrganization)
                    organizationUrl.set(Publications.DeveloperOrganizationUrl)
                }
            }

            scm {
                url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")
            }
        }

        artifact(tasks["kdocJar"])
    }
}

fun SigningExtension.commonConfig(gradle: Gradle, publishing: PublishingExtension) {
    setRequired { gradle.taskGraph.hasTask("publish") }
    sign(publishing.publications)

    if (System.getenv(Env.SigningKey) != null) {
        @Suppress("UnstableApiUsage")
        useInMemoryPgpKeys(
            System.getenv(Env.SigningKeyId),
            System.getenv(Env.SigningKey),
            System.getenv(Env.SigningPassword)
        )
    }
}
