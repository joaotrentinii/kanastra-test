package application.ports.outbound

import application.domain.events.GeneratedBill

interface Cache {

    suspend fun send(event: GeneratedBill)
}
