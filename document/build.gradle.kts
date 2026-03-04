import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.kotlin.jvm)
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


tasks.withType<KotlinCompile> {
  compilerOptions {
    jvmTarget = JVM_21
  }
}
