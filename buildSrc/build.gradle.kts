plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
//    implementation(libs.kotlin.gradle.plugin)
//    implementation(libs.android.gradle.plugin)
//    implementation(libs.kotlin.gradle.plugin)
    implementation(kotlin("stdlib"))  // Зависимость для работы с Kotlin
    implementation(gradleApi())      // Доступ к Gradle API
}