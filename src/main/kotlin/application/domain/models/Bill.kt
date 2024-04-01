package application.domain.models

import java.util.UUID

data class Bill(
    val name: String,
    val email: String,
    val value: Double,
    val debtId: UUID,
    val dueDate: String,
    val governmentId: String,
) {
    companion object {

        fun from(line: Map<String, String>): Bill {
            return Bill(
                name = line.getValue("name"),
                email = line.getValue("email"),
                value = line.getValue("debtAmount").toDouble(),
                debtId = UUID.fromString(line.getValue("debtId")),
                dueDate = line.getValue("debtDueDate"),
                governmentId = line.getValue("governmentId"),
            )
        }
    }
}
