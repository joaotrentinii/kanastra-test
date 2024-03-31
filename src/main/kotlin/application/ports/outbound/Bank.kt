package application.ports.outbound

interface Bank {

    suspend fun generateBill();
}
