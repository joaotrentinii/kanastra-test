package driven.redis

import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.api.async.RedisAsyncCommands
import jakarta.enterprise.context.Dependent
import jakarta.inject.Singleton
import jakarta.ws.rs.Produces

@Dependent
class Factory {

    @Produces
    @Singleton
    fun client(): RedisAsyncCommands<String, String> {
        val url = RedisURI.create("redis", 6379)
        val redisClient = RedisClient.create(url)
        val connection = redisClient.connect()
        return connection.async()
    }
}
