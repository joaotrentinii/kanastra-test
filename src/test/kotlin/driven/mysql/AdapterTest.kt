package driven.mysql

import application.domain.events.ProcessedFile
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import starter.MysqlContainer.CLIENT

class AdapterTest {

    private val adapter = Adapter(CLIENT)

    @Test
    fun `When the file is processed`(): Unit = runBlocking {
        /** @Dado que exista um evento de arquivo processado */
        val event = ProcessedFile(name = "Test.csv", hash = 123321)

        /** @Quando o adaptador for executado com esse evento */
        adapter.execute(events = listOf(event))

        /** @Então um registro deverá ser persistido no banco de dados */
        val row = Queries.first()

        /** @E o registro deverá conter o mesmo nome de arquivo do evento */
        event.name shouldBe row.getString("name")

        /** @E deverá conter o mesmo hash do evento */
        event.hash shouldBe row.getInteger("hash")
    }

    @Test
    fun `When several files have been processed`(): Unit = runBlocking {
        /** @Dado que exista uma lista com 10 eventos de arquivos processados */
        val events = (1..10).mapIndexed { index, _ -> ProcessedFile(name = "Test$index.csv", hash = index) }

        /** @Quando o adaptador for executado com esses eventos */
        adapter.execute(events = events)

        /** @Então 10 registros deverão ser persistidos no banco de dados */
        Queries.count() shouldBe 10
    }
}