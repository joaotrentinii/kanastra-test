package application.ports.outbound

import application.domain.events.GeneratedBill
import application.domain.models.Bill

interface Bank {

    suspend fun generate(bills: List<Bill>): List<GeneratedBill>
}
