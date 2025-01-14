plugins {
    id("java")
    id("application")
}

group = "com.unwize"
version = "1.0-SNAPSHOT"



application {
    mainClass = "com.unwize.cobblebank.Main"
}



repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.javalin:javalin:6.4.0")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("com.alibaba:fastjson:2.0.54")
}

tasks.test {
    useJUnitPlatform()
}