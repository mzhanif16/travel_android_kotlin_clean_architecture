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
        maven(url = "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("test") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "travel_android_kotlin"
include(":app")
include(":feature:main")
include(":feature:onboarding")
include(":core:common")
include(":feature:favourite")
include(":feature:notification")
include(":feature:profile")
include(":feature:register")
include(":core:network")
include(":core:model")
include(":core:domain")
include(":core:data")
