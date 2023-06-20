import org.springframework.boot.gradle.tasks.bundling.BootJar


tasks.getByName<BootJar>("bootJar") {
	enabled = true
	archiveBaseName.set("gigi")
}

plugins {
	java
	id("org.springframework.boot") version "2.7.12"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.ujo"
java.sourceCompatibility = JavaVersion.VERSION_11


repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.batch:spring-batch-test")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")

	//jsp
	implementation("org.apache.tomcat.embed:tomcat-embed-jasper")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
