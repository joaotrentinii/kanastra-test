package application.ports.outbound

import application.domain.events.GeneratedBill

interface Mail {

    suspend fun send(events: List<GeneratedBill>)
}
