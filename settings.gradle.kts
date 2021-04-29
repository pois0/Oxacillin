rootProject.name = "oxacillin"
include(":core")
include(":extensions")
include(":defaults")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id) {
                "org.jetbrains.kotlin.multiplatform", "org.jetbrains.kotlin.plugin.serialization" -> {
                    useVersion("1.4.32")
                }
            }
        }
    }
}
