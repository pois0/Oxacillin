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

    sourceSets.all {
        dependencies {
            api(project(":core"))
            api(project(":extensions"))
        }
    }
}

tasks {
    addKdocTask(dokkaHtml)
}

publishing {
    setRepository(this@Build_gradle::uri, tasks, rootProject.name, project.name)
}
