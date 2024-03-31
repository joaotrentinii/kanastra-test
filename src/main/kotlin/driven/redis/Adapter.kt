package driven.redis

import application.domain.events.GeneratedBill
import application.ports.outbound.Cache
import io.lettuce.core.api.async.RedisAsyncCommands
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

@Singleton
class Adapter(private val client: RedisAsyncCommands<String, String>) : Cache {

    override suspend fun send(event: GeneratedBill): Unit = withContext(IO) {
        val key = event.governmentId
        val value = event.toString()
        client.setex(key, 1200L, value)
    }
}
