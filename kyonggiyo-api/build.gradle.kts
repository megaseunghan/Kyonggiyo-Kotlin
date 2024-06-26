import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.epages.restdocs-api-spec") version "0.18.2"
    id("org.hidetake.swagger.generator") version "2.18.2"
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = true
bootJar.dependsOn("copyDocs")

val snippetsDir by extra { file("build/generated-snippets")}

dependencies {
    implementation(project(":kyonggiyo-core"))
    implementation(project(":kyonggiyo-infrastructure"))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.2")

    // AWS CloudWatch
    implementation("ca.pjer:logback-awslogs-appender:1.6.0")

    // Documentation
    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.18.3")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
}

openapi3 {
    setServer("http://localhost:8080")
    setServer("https://dev.kyonggiyo.site")
    title = "Kyonggiyo API Document"
    description = "경기요 API 문서"
    version = "${project.version}"
    outputFileNamePrefix = "api"
    outputDirectory = "build/resources/main/static/"
}

tasks.register("copyDocs") {
    dependsOn("openapi3")
    delete("src/main/resources/static/api.json")
    doLast {
        copy {
            from("build/resources/main/static/api.json")
            into("src/main/resources/static/")
        }
    }

}
