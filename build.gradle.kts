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

    dependencyManagement {
        dependencies {
            dependency("org.apache.logging.log4j:log4j-api:$log4jVersion")
            dependency("org.apache.logging.log4j:log4j-core:$log4jVersion")
            dependency("org.apache.logging.log4j:log4j-jcl:$log4jVersion")
            dependency("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")
        }
    }
}