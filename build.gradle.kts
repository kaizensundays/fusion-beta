plugins {
    kotlin("jvm") version "1.5.31" apply false
    kotlin("plugin.spring") version "1.5.31" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

subprojects {
    plugins.apply("io.spring.dependency-management")

    repositories {
        maven {
            url = uri(project.properties["artifactory.url"]!!)
            isAllowInsecureProtocol = true
            credentials {
                username = project.properties["artifactory.username"] as String
                password = project.properties["artifactory.password"] as String
            }
        }
    }

    val log4jVersion = "2.17.1"
    val jacksonVersion = "2.12.6"
    val ktorVersion = "1.6.7"
    val igniteVersion = "2.11.0"
    val postgreSqlVersion = "42.3.5"

    dependencyManagement {
        dependencies {
            dependency("org.apache.logging.log4j:log4j-api:$log4jVersion")
            dependency("org.apache.logging.log4j:log4j-core:$log4jVersion")
            dependency("org.apache.logging.log4j:log4j-jcl:$log4jVersion")
            dependency("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")

            dependency("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
            dependency("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
            dependency("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

            dependency("io.ktor:ktor-client-cio:$ktorVersion")
            dependency("io.ktor:ktor-client-websockets:$ktorVersion")
            dependency("io.ktor:ktor-client-auth-basic-jvm:$ktorVersion")

            dependency("org.postgresql:postgresql:$postgreSqlVersion")

            dependency("org.apache.ignite:ignite-core:$igniteVersion")
            dependency("org.apache.ignite:ignite-slf4j:$igniteVersion")
            dependency("org.apache.ignite:ignite-spring:$igniteVersion")
            dependency("com.h2database:h2:1.4.197")
        }
    }
}