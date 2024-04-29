import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.noarg") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    kotlin("kapt") version "1.9.22"

    id("java")
    id("io.spring.dependency-management") version "1.1.4"
    id("org.springframework.boot") version "3.1.7" apply(false)
    id("com.epages.restdocs-api-spec") version "0.18.2" apply(false)
    id("org.hidetake.swagger.generator") version "2.18.2" apply(false)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    repositories {
        mavenCentral()
    }
}


subprojects {
    group = "kyonggiyo"
    version = "1.2"

    apply {
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("kotlin-spring")
        plugin("java")
        plugin("java-test-fixtures")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    val springCloudVersion = "2022.0.3"

    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }


    dependencies {
        // Spring
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test" ) {
            exclude("com.vaadin.external.google")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
