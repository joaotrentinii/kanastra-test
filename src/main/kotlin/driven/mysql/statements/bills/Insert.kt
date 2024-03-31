package driven.mysql.statements.bills

import application.domain.events.GeneratedBill
import driven.mysql.statements.Statement
import io.vertx.mutiny.sqlclient.Tuple

class Insert(
    private val url: String,
    private val email: String,
    private val value: Double,
    private val dueDate: String,
    private val governmentId: String,
) : Statement {

    override val template = "INSERT INTO bill (url, email, amount, dueDate, governmentId) VALUES (?, ?, ?, ?, ?)"
    override fun arguments(): Tuple = Tuple.of(url, email, value, dueDate, governmentId.toByteArray())

    companion object {

        fun from(event: GeneratedBill) = Insert(
            url = event.url,
            email = event.email,
            value = event.value,
            dueDate = event.dueDate,
            governmentId = event.governmentId,
        )
    }
}
