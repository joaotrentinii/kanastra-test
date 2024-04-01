package driven.mysql

import application.domain.events.Event
import application.ports.outbound.Persistence
import driven.mysql.builders.statement
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.awaitSuspending
import io.vertx.mutiny.mysqlclient.MySQLPool
import jakarta.inject.Singleton

@Singleton
class Adapter(private val pool: MySQLPool) : Persistence {

    override suspend fun execute(events: List<Event>) {
        events
            .map { it.statement() }
            .map { pool.preparedQuery(it.template).execute(it.arguments()) }
            .map { item -> item.onItem().transform { it.rowCount() } }
            .map { item -> item.onItem().invoke { rowCount -> require(rowCount > 0) } }
            .let { Uni.join().all(it).andFailFast() }
            .awaitSuspending()
    }
}
