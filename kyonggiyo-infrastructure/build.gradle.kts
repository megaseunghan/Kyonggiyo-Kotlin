import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false

dependencies {
    implementation(project(":kyonggiyo-core"))
    testImplementation(testFixtures(project(":kyonggiyo-core")))

    // OPEN FEIGN
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // DB
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // AWS S3
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
    testImplementation("io.findify:s3mock_2.13:0.2.6")
}
