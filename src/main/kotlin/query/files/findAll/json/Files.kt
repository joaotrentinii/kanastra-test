package query.files.findAll.json

import kotlinx.serialization.Serializable

@Serializable
data class Files(val values: List<File>)
