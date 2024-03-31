package application.ports.inbound

interface CommandHandler<T> {

    suspend fun handle(command: T)
}
