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
