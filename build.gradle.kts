plugins {
    java
    kotlin("jvm") version "2.1.0"
    id("io.github.glorrian.cmake-gradle-plugin") version "1.0.1"
}

group = "io.github.glorrian"
version = "1.0-SNAPSHOT"

val kotestVersion = "6.0.0.M1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openpnp:opencv:4.9.0-0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${kotestVersion}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${kotestVersion}")
}

cmake {
    arguments = listOf(
        "-DLIBRARY_PATH=../../src/main/resources",
        "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON"
    )
}

tasks.test {
    useJUnitPlatform()
}
