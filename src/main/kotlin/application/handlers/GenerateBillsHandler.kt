package application.handlers

import application.commands.GenerateBills
import application.domain.events.ProcessedFile
import application.domain.models.Bill
import application.ports.inbound.CommandHandler
import application.ports.outbound.Bank
import application.ports.outbound.Cache
import application.ports.outbound.Mail
import application.ports.outbound.Persistence
import jakarta.inject.Singleton
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Singleton
class GenerateBillsHandler(
    private val mail: Mail,
    private val bank: Bank,
    private val cache: Cache,
    private val persistence: Persistence,
) : CommandHandler<GenerateBills> {

    override suspend fun handle(command: GenerateBills) = coroutineScope {
        val bills = command.data
            .map { command.headers.zip(it.split(",")).toMap() }
            .map { Bill.from(line = it) }

        val generated = bank.generate(bills = bills)
        val processedFile = ProcessedFile.from(command.file)

        /**
         * Envia os eventos de boleto gerado de forma paralela com a utilização de coroutines para o Redis
         * A ideia é que todas as requisições de IO sejam executadas dessa forma
         * Fiz apenas aqui, pois foi o único adaptador implementado que faz várias requisições
         * */
        generated.forEach { launch { cache.send(event = it) } }

        mail.send(events = generated)
        persistence.execute(events = listOf(processedFile))
    }
}
