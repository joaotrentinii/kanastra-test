package driven.mysql.builders

import application.domain.events.Event
import application.domain.events.GeneratedBill
import application.domain.events.ProcessedFile
import driven.mysql.statements.Statement
import driven.mysql.statements.bills.Insert as GeneratedBillInsert
import driven.mysql.statements.file.Insert as ProcessedFileInsert

fun Event.statement(): Statement = when (this) {
    is ProcessedFile -> ProcessedFileInsert.from(event = this)
    is GeneratedBill -> GeneratedBillInsert.from(event = this)
}
