import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.kotlin.jvm)
}

group = "acta"
version = "0.1"

repositories {
  mavenCentral()
  maven { url = uri("https://jitpack.io") }
}

dependencies {
  implementation(libs.klite.server)
}

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

tasks.test {
  useJUnitPlatform()
  jvmArgs("-DENV=test", "--add-opens=java.base/java.lang=ALL-UNNAMED", "-XX:-OmitStackTraceInFastThrow")
}

tasks.withType<KotlinCompile> {
  compilerOptions {
    jvmTarget = JVM_21
  }
}

tasks.register<Copy>("deps") {
  description = "helpers"
  group = "helpers"
  into("$buildDir/libs/deps")
  from(configurations.runtimeClasspath)
}

val mainClassName = "LauncherKt"

tasks.jar {
  dependsOn("deps")
  doFirst {
    manifest {
      attributes(
        mapOf(
        "Main-Class" to mainClassName,
        "Class-Path" to File("$buildDir/libs/deps").listFiles()?.joinToString(" ") { "deps/${it.name}" }
      ))
    }
  }
}

tasks.register<JavaExec>("run") {
  description = "helpers"
  group = "helpers"
  workingDir(rootDir)
  jvmArgs("--add-exports=java.base/sun.net.www=ALL-UNNAMED")
  mainClass.set(mainClassName)
  classpath = sourceSets.main.get().runtimeClasspath
}
