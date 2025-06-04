plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(kotlin("stdlib"))  // Зависимость для работы с Kotlin
    implementation(gradleApi())      // Доступ к Gradle API
}