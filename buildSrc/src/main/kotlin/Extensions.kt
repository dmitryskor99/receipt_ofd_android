import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
//import org.gradle.accessors.dm.LibrariesForLibs
//import org.gradle.kotlin.dsl.the

private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.ksp(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)

fun Project.fullDagger() {
//    val libs = the<LibrariesForLibs>()
//    plugins.apply(libs.plugins.ksp)
    dependencies.implementation("com.google.dagger:dagger:2.56.2")
    dependencies.ksp("com.google.dagger:dagger-compiler:2.56.2")
}