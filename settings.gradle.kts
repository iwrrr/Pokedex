pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "Pokedex"
include(":app")
include(":core:common")
include(":core:data")
include(":core:network")
include(":core:database")
include(":core:domain")
include(":core:designsystem")
include(":feature:pokedex")
