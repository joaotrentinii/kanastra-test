package application.ports.outbound

import application.domain.events.Event

interface Persistence {

    suspend fun execute(events: List<Event>)
}
