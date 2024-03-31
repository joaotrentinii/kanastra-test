package application.commands

import application.domain.models.File

data class GenerateBills(
    val file: File,
    val data: List<String>,
    val headers: List<String>,
)
