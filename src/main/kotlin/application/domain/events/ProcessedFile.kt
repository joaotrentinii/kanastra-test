package application.domain.events

import application.domain.models.File

data class ProcessedFile(val name: String, val hash: Int) : Event {

    companion object {
        fun from(file: File) = ProcessedFile(
            name = file.name,
            hash = file.hash,
        )
    }
}
