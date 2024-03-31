<?php

namespace Tests\Unit\app\Services\Impl;

use App\Services\Impl\BankApi;
use PHPUnit\Framework\TestCase;
use Ramsey\Uuid\Uuid;

class BankApiTest extends TestCase
{
    public function test_generate_bill()
    {
        $service = new BankApi();

        $data = [
            'debtId' => Uuid::uuid4(),
            'name' => 'test.csv',
            'email' => 'joao_trentini@hotmail.com',
            'debtAmount' => 120.00,
            'debtDueDate' => '2024/03/29',
            'governmentId' => '12312',
            'pathPdf' => './storage/app/bill.pdf'
        ];

        $result = $service->generateBill(data: $data);

        static::assertEquals($result->debtId, $data['debtId']);
        static::assertEquals($result->name, $data['name']);
        static::assertEquals($result->email, $data['email']);
        static::assertEquals($result->value, $data['debtAmount']);
        static::assertEquals($result->dueDate, $data['debtDueDate']);
        static::assertEquals($result->governmentId, $data['governmentId']);
        static::assertEquals($result->pathPdf, $data['pathPdf']);
    }
}
