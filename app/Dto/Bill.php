<?php

namespace App\Dto;

use Ramsey\Uuid\UuidInterface;

class Bill
{
    public function __construct(
        public readonly UuidInterface $debtId,
        public readonly String $name,
        public readonly String $email,
        public readonly float $value,
        public readonly String $dueDate,
        public readonly String $governmentId,
        public readonly String $pathPdf
    )
    {
    }
}
