pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "receipt_ofd_android"
include(":app")
include(":feature")
include(":feature:login")
include(":feature:login:api")
include(":feature:login:impl")
include(":feature:login:test")
include(":feature:scanner")
include(":feature:scanner:api")
include(":feature:scanner:impl")
include(":feature:scanner:test")
include(":core")
include(":core:network")
include(":core:network:api")
include(":core:network:impl")
include(":core:network:test")
include(":core:di")
include(":feature:navigation")
include(":feature:navigation:api")
include(":feature:navigation:impl")
include(":feature:navigation:test")
include(":feature:start")
include(":feature:start:api")
include(":feature:start:impl")
include(":feature:start:test")
