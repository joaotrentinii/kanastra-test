package query.files.findAll.database

import io.smallrye.mutiny.coroutines.awaitSuspending
import io.vertx.mutiny.mysqlclient.MySQLPool
import jakarta.inject.Singleton
import query.files.findAll.json.File
import query.files.findAll.json.Files

@Singleton
class Repository(private val pool: MySQLPool) {

    suspend fun findAll(): Files {
        return pool
            .preparedQuery("SELECT name, dat_added FROM file")
            .execute()
            .awaitSuspending()
            .map { File.from(it) }
            .let { Files(values = it) }
    }
}
