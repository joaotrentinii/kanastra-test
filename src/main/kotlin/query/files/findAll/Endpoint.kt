package query.files.findAll

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status.OK
import query.files.findAll.database.Repository

@Path("/processedFiles")
class Endpoint(private val repository: Repository) {

    @GET
    suspend fun all(): Response {
        val files = repository.findAll()
        return Response.status(OK).entity(files).build()
    }
}
