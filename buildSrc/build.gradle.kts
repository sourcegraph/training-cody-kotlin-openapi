plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.1")
}