package driven.http

import application.domain.events.GeneratedBill
import application.domain.models.Bill
import application.ports.outbound.Bank
import jakarta.inject.Singleton

@Singleton
class Adapter : Bank {

    override suspend fun generate(bills: List<Bill>): List<GeneratedBill> {
        // TODO: Implementar geração do boleto no banco e persistência do pdf em um storage
        return bills.map { bill ->
            GeneratedBill(
                email = bill.email,
                value = bill.value,
                dueDate = bill.dueDate,
                governmentId = bill.governmentId,
                url = "https://downloadfile.com/${bill.governmentId}",
            )
        }
    }
}
