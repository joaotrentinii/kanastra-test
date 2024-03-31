<?php

namespace App\Services\Impl;

use App\Dto\Bill;
use App\Services\BankApiInterface;
use Ramsey\Uuid\Uuid;

class BankApi implements BankApiInterface
{
    function generateBill(array $data): Bill
    {
        /** Aqui seria disparado o request para a api do banco para realizar a geração do boleto */

        $pathPdf = "./storage/app/bill.pdf";

        /** Após a geração do boleto no banco, o boleto seria retornado */
        return new Bill(
            debtId: Uuid::fromString($data['debtId']),
            name: $data['name'],
            email: $data['email'],
            value: $data['debtAmount'],
            dueDate: $data['debtDueDate'],
            governmentId: $data['governmentId'],
            pathPdf: $pathPdf
        );
    }
}
