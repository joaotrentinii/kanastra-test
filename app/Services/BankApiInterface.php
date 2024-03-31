<?php

namespace App\Services;

use App\Dto\Bill;

interface BankApiInterface
{
    function generateBill(array $data): BIll;
}
