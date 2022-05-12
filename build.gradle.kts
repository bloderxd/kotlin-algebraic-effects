import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    application
}

group = "me.pichau"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.arrow-kt:arrow-core:1.1.2")
    implementation("io.arrow-kt:arrow-optics:1.1.2")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.1.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
}

application {
    mainClass.set("MainKt")
}