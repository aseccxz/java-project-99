plugins {
	application
	id("org.springframework.boot") version "4.0.6"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.sonarqube") version "7.3.0.8198"
	jacoco
	id("com.github.ben-manes.versions") version "0.54.0"
	checkstyle
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.openapitools:jackson-databind-nullable:0.2.10")
	implementation("org.mapstruct:mapstruct:1.6.3")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.2")
	implementation("net.datafaker:datafaker:2.5.4")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

	testImplementation("org.instancio:instancio-junit:5.5.1")
	testImplementation("net.javacrumbs.json-unit:json-unit-assertj:4.1.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	testImplementation ("org.springframework.boot:spring-boot-test-autoconfigure")
	testCompileOnly("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sonar {
	properties {
		property("sonar.projectKey", "aseccxz_java-project-99")
		property("sonar.organization", "aseccxz")
	}
}

tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
	}
}

application {
	mainClass = "hexlet.code.AppApplication"
}
