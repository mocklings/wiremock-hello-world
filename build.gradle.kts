plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.mocklings"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(23)
}

java {
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}

dependencies {
    testImplementation("org.wiremock:wiremock:3.9.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}