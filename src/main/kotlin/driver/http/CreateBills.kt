package driver.http

import application.commands.GenerateBills
import application.domain.models.File
import application.ports.inbound.CommandHandler
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR
import jakarta.ws.rs.core.Response.Status.OK
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput

@Path("/createBills")
class CreateBills(private val handler: CommandHandler<GenerateBills>) {

    @POST
    suspend fun generate(parts: MultipartFormDataInput): Response {
        return try {
            val form = parts.values.firstNotNullOf { it.value }.first()
            val lines = form.fileItem.inputStream.reader().readLines()
            val headers = lines.removeFirst().split(",")

            val file = File(name = form.fileName, hash = lines.hashCode())
            val command = GenerateBills(data = lines, headers = headers, file = file)

            handler.handle(command = command)

            return Response.status(OK).entity("File ${form.fileName} processed successfully.").build()
        } catch (throwable: Throwable) {
            val message = "There was an error processing the file: ${throwable.message}"
            Response.status(INTERNAL_SERVER_ERROR).entity(message).build()
        }
    }
}
