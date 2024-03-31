package application.domain.models

import java.util.UUID

data class Bill(
    val name: String,
    val governmentId: String,
    val email: String,
    val value: Double,
    val dueDate: String,
    val debtId: UUID,
) {
    companion object {
        fun from(line: Map<String, String>): Bill {
            return Bill(
                name = line.getValue("name"),
                governmentId = line.getValue("governmentId"),
                email = line.getValue("email"),
                value = line.getValue("debtAmount").toDouble(),
                dueDate = line.getValue("debtDueDate"),
                debtId = UUID.fromString(line.getValue("debtId")),
            )
        }
    }
}
