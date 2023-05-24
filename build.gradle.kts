import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("java")
    id("org.springframework.boot") version "2.7.8"
    id("org.springframework.cloud.contract") version "3.0.6"
}

apply {
    plugin("java")
    plugin("io.spring.dependency-management")
    plugin("org.springframework.boot")
    plugin("org.springframework.cloud.contract")
    plugin("org.jetbrains.kotlin.plugin.spring")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-dependencies:2.7.8")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.8")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.4")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.4")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.13.4")

    testImplementation(kotlin("test"))
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:3.1.5") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
    }
    testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin:3.1.5") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-scripting-compiler-embeddable")
    }
    testImplementation("io.rest-assured:spring-web-test-client:4.5.1")
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.6.21")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier:3.1.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.8")
    testImplementation("io.projectreactor:reactor-test:3.4.18")
}


configure<org.springframework.cloud.contract.verifier.plugin.ContractVerifierExtension> {
    testMode.set(org.springframework.cloud.contract.verifier.config.TestMode.WEBTESTCLIENT)
    baseClassForTests.set("com.test.AbstractContractTest")
}

tasks.test {
    useJUnitPlatform {
        excludeTags("contract")
    }

    testLogging {
        events(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED, org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED, org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
    }
}

val contractTest = tasks.named("contractTest", Test::class.java) {
    useJUnitPlatform {
        includeTags("contract")
    }
    shouldRunAfter(tasks.test)

    testLogging {
        events(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED, org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED, org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
    }
}

tasks.check {
    dependsOn(contractTest)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.languageVersion = "1.6"
    kotlinOptions.apiVersion = "1.6"
}
