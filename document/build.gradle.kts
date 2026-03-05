import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.kotlinJvm)
}

group = "document"
version = "0.1"

sourceSets {
  main {
    kotlin.srcDirs("src")
    resources.srcDirs("src", "db").exclude("**/*.kt")
  }
  test {
    kotlin.srcDirs("test")
    resources.srcDirs("test").exclude("**/*.kt")
  }
}

repositories {
  mavenCentral()
  maven { url = uri("https://jitpack.io") }
}

dependencies {
  implementation(libs.poi)
  implementation(libs.poi.ooxml)

  implementation(libs.klite.core)

  testImplementation(kotlin("test"))
  testImplementation(libs.org.junit.jupiter.junit.jupiter.api)
  testImplementation(libs.org.junit.jupiter.junit.jupiter.params)
  testRuntimeOnly(libs.org.junit.jupiter.junit.jupiter.engine)
  testRuntimeOnly(libs.org.junit.platform.junit.platform.launcher)
}
tasks.withType<KotlinCompile> {
  compilerOptions {
    jvmTarget = JVM_21
  }
}

tasks.test {
  useJUnitPlatform()
}
