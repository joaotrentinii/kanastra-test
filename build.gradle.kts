import org.gradle.kotlin.dsl.libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kover)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.quarkus)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.kotlin.jvm)
}

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://packages.confluent.io/maven")
}

dependencies {
    implementation(libs.flyway.mysql)
    implementation(libs.redis.lettuce)
    implementation(libs.kotlinx.coroutines)

    implementation(enforcedPlatform(libs.quarkus.bom))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-flyway")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-jdbc-mysql")
    implementation("io.quarkus:quarkus-reactive-mysql-client")
    implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")

    testImplementation(libs.mockk)
    testImplementation(libs.kotest.core)
    testImplementation(libs.junit.pioneer)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.test.containers.mysql)
}

tasks.withType<Test> {
    jvmArgs = listOf("--add-opens", "java.base/java.time=ALL-UNNAMED")
    useJUnitPlatform()
    environment(
        "NEW_RELIC_LICENSE_KEY" to "INVALID_LICENSE_KEY",
    )
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjdk-release=21")
        allWarningsAsErrors = true
    }
}
