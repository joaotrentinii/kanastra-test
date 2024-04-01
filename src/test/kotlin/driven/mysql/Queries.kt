package driven.mysql

import io.vertx.mutiny.sqlclient.Row
import starter.MysqlContainer.CLIENT

object Queries {

    private const val FIND_ALL = "SELECT * FROM file"

    fun count(): Int = CLIENT
        .preparedQuery(FIND_ALL)
        .executeAndAwait()
        .count()

    fun first(): Row = CLIENT
        .preparedQuery(FIND_ALL)
        .executeAndAwait()
        .first()
}
