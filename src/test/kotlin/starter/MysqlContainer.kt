package starter

import io.vertx.mutiny.mysqlclient.MySQLPool
import io.vertx.mysqlclient.MySQLConnectOptions
import io.vertx.sqlclient.PoolOptions
import org.flywaydb.core.Flyway
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

object MysqlContainer {
    private val image = DockerImageName
        .parse("mysql:8.0")
        .asCompatibleSubstituteFor("mysql")
        .withTag("8.0")

    private val container = MySQLContainer<Nothing>(image).apply {
        withDatabaseName("payments_adm")
    }

    val CLIENT: MySQLPool by lazy {

        container.start()

        Flyway
            .configure()
            .createSchemas(true)
            .schemas("payments_adm")
            .dataSource(container.jdbcUrl, container.username, container.password)
            .load()
            .migrate()

        MySQLConnectOptions().also {
            it.host = container.host
            it.port = container.getMappedPort(3306)
            it.user = container.username
            it.password = container.password
            it.database = container.databaseName
            it.properties = mapOf("search_path" to "payments_adm")
        }.let { MySQLPool.pool(it, PoolOptions()) }
    }
}
