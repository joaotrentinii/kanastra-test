package query.files.findAll.json

import io.vertx.mutiny.sqlclient.Row
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class File(val name: String, val instant: String) {

    companion object {
        fun from(row: Row) = File(
            name = row.getString("name"),
            instant = row.getLocalDateTime("dat_added")
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
        )
    }
}
