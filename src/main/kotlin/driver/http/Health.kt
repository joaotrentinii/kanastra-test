package driver.http

import driver.kafka.Consumer
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response

@Path("/health")
class Health(private val consumer: Consumer) {
    @GET
    fun get(): Response = when (consumer.isRunning()) {
        true ->
            Response
                .status(200)
                .build()

        false ->
            Response
                .status(500)
                .build()
    }
}
