package application.ports.outbound

import application.models.statement.Statement

interface Persistence {

    suspend fun execute(statements: List<Statement>)
}
