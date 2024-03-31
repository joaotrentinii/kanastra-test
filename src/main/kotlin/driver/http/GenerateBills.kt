package driver.http

import application.commands.GenerateBills
import application.domain.models.File
import application.ports.inbound.CommandHandler
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput

@Path("/createBills")
class GenerateBills(private val handler: CommandHandler<GenerateBills>) {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun generate(parts: MultipartFormDataInput): Response {
        return try {
            val form = parts.values.firstNotNullOf { it.value }.first()
            val lines = form.fileItem.inputStream.reader().readLines()
            val headers = lines.removeFirst().split(",")

            val file = File(name = form.fileName, hash = lines.hashCode())
            val command = GenerateBills(data = lines, headers = headers, file = file)

            handler.handle(command = command)

            return Response.status(200).entity("File ${form.fileName} processed successfully.").build()
        } catch (throwable: Throwable) {
            val message = "There was an error processing the file: ${throwable.message}"
            Response.status(500).entity(message).build()
        }
    }
}
