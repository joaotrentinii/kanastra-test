package driven.mysql.statements.file

import application.domain.events.ProcessedFile
import driven.mysql.statements.Statement
import io.vertx.mutiny.sqlclient.Tuple

class Insert(
    private val name: String,
    private val hash: Int,
) : Statement {

    override val template = "INSERT INTO file (name, hash) VALUES (?, ?)"
    override fun arguments(): Tuple = Tuple.of(name, hash)

    companion object {

        fun from(event: ProcessedFile) = Insert(
            name = event.name,
            hash = event.hash,
        )
    }
}
