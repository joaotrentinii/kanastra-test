package driven.mysql.builders

import application.domain.events.Event
import application.domain.events.GeneratedBill
import application.domain.events.ProcessedFile
import driven.mysql.statements.Statement
import driven.mysql.statements.bills.Insert as GeneratedBillInsert
import driven.mysql.statements.file.Insert as ProcessedFileInsert

fun Event.statements(): List<Statement> = when (this) {
    is ProcessedFile -> statements()
    is GeneratedBill -> statements()
}

fun ProcessedFile.statements(): List<Statement> {
    val insert = ProcessedFileInsert.from(event = this)
    return listOf(insert)
}

fun GeneratedBill.statements(): List<Statement> {
    val insert = GeneratedBillInsert.from(event = this)
    return listOf(insert)
}
