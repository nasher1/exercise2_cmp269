plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.7"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.7")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.7")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.7")
    implementation("io.ktor:ktor-server-cors-jvm:2.3.7")
    
    implementation("com.google.zxing:core:3.5.3")
    implementation("com.google.zxing:javase:3.5.3")
    
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.10.1")
    
    // Logging dependencies
    implementation("ch.qos.logback:logback-classic:1.4.11")
    implementation("org.slf4j:slf4j-api:2.0.9")
    
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}