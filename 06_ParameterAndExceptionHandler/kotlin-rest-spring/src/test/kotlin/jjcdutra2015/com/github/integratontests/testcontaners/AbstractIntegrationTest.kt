package jjcdutra2015.com.github.integratontests.testcontaners

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.MapPropertySource
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.lifecycle.Startables
import java.util.stream.Stream

@ContextConfiguration(initializers = [AbstractIntegrationTest.Initializer::class])
open class AbstractIntegrationTest {

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            startContainers()

            val environment = applicationContext.environment
            val testcontainers = MapPropertySource(
                "testcontainers", createConnectionConfguration()
            )
            environment.propertySources.addFirst(testcontainers)
        }

        companion object {
            private var mysql: MySQLContainer<*> = MySQLContainer("mysql:8.0.28")

            private fun startContainers() {
                Startables.deepStart(Stream.of(mysql)).join()
            }

            private fun createConnectionConfguration(): MutableMap<String, Any> {
                return mutableMapOf(
                    "spring.datasource.url" to mysql.jdbcUrl,
                    "spring.datasource.username" to mysql.username,
                    "spring.datasource.password" to mysql.password
                    )
            }
        }
    }
}