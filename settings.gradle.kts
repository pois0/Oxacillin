rootProject.name = "oxacillin"
include(":core")
include(":extensions")
include(":defaults")

pluginManagement {
    plugins {
        kotlin("multiplatform") version "1.4.32"
        kotlin("plugin.serialization") version "1.4.32"
    }
}
