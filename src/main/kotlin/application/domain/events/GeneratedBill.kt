package application.domain.events

data class GeneratedBill(
    val url: String,
    val email: String,
    val value: Double,
    val dueDate: String,
    val governmentId: String,
) : Event
