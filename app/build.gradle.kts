plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("org.openapi.generator") version "7.10.0"
    id("org.owasp.dependencycheck") version "12.1.0"
    application
}

dependencies {
    // Core implementation dependencies
    implementation(project(":utils"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("kotlinx_coroutines_version")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${property("kotlinx_coroutines_version")}")

    // HTTP client dependencies
    implementation("com.squareup.okhttp3:okhttp:${property("okhttp_version")}")
    implementation("com.squareup.okhttp3:logging-interceptor:${property("okhttp_version")}")

    // JSON serialization dependencies
    implementation("com.squareup.moshi:moshi:${property("moshi_version")}")
    implementation("com.squareup.moshi:moshi-kotlin:${property("moshi_version")}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    // Ktor dependencies
    implementation("io.ktor:ktor-server-core:1.6.8")
    implementation("io.ktor:ktor-server-netty:1.6.8")
    implementation("io.ktor:ktor-jackson:1.6.8")

    // Add Logback as the SLF4J implementation
    implementation("ch.qos.logback:logback-classic:1.2.11")

    // Test dependencies - choose one testing framework
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    // Ktor testing dependencies
    testImplementation("io.ktor:ktor-server-test-host:1.6.8")

    // Add MockK for Kotlin-native mocking
    testImplementation("io.mockk:mockk:1.13.5")
}

application {
    mainClass = "com.sourcegraph.demo.app.AppKt"
}

// Configure testing
tasks.withType<Test> {
    useJUnitPlatform()
}

// OpenAPI generator configuration
tasks.openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/src/main/resources/openapi.json")
    outputDir.set("$projectDir")
    packageName.set("com.sourcegraph.demo.openapi.generated")
    apiPackage.set("com.sourcegraph.demo.openapi.generated.api")
    modelPackage.set("com.sourcegraph.demo.openapi.generated.model")
    configOptions.set(mapOf(
        "dateLibrary" to "java8",
        "enumPropertyNaming" to "UPPERCASE",
        "serializationLibrary" to "moshi",
        "useCoroutines" to "true",
        "omitGradleWrapper" to "true"
    ))
}

// Make sure OpenAPI classes are generated before compilation
tasks.compileKotlin {
    dependsOn(tasks.openApiGenerate)
}

tasks.processResources {
    dependsOn(tasks.openApiGenerate)
}

tasks.clean {
    delete(layout.projectDirectory.dir("docs"))
    delete(layout.projectDirectory.dir(".openapi-generator"))
    delete(layout.projectDirectory.dir("src/main/kotlin/com/sourcegraph/demo/openapi/generated"))
    delete(layout.projectDirectory.dir("src/test/kotlin/com/sourcegraph/demo/openapi/generated"))
}