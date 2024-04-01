package driven.redis

import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.api.async.RedisAsyncCommands
import jakarta.enterprise.context.Dependent
import jakarta.inject.Singleton
import jakarta.ws.rs.Produces
import java.lang.System.getenv

@Dependent
class Factory {

    @Produces
    @Singleton
    fun client(): RedisAsyncCommands<String, String> {
        val host = getenv("REDIS_HOST")
        val port = getenv("REDIS_PORT").toInt()
        val redisUri = RedisURI.create(host, port)
        val redisClient = RedisClient.create(redisUri)
        val connection = redisClient.connect()
        return connection.async()
    }
}
