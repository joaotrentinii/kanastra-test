package driven.smtp

import application.domain.events.GeneratedBill
import application.ports.outbound.Mail
import jakarta.inject.Singleton

@Singleton
class Adapter : Mail {

    override suspend fun send(events: List<GeneratedBill>) {
        // TODO: Implementar envio para uma fila assíncrona
    }
}
