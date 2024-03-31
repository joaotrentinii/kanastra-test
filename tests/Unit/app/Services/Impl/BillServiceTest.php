<?php

namespace Tests\Unit\app\Services\Impl;

use App\Services\BankApiInterface;
use App\Services\Impl\BillService;
use Illuminate\Http\UploadedFile;
use InvalidArgumentException;
use PHPUnit\Framework\MockObject\Exception;
use PHPUnit\Framework\TestCase;

class BillServiceTest extends TestCase
{
    /**
     * @throws Exception
     */
    public function testWhenFileIsInvalid()
    {
        $service = new BillService(static::createMock(BankApiInterface::class));
        $this->expectException(InvalidArgumentException::class);

        $file = static::createMock(UploadedFile::class);
        $file->method('isValid')->willReturn(false);

        $service->generate($file);
    }

    /**
     * @throws Exception
     */
    public function testWhenFileIsSuccessfullyProcessed()
    {
        $api = static::createMock(BankApiInterface::class);
        $api->expects(static::exactly(3))->method('generateBill');

        $service = new BillService($api);

        $file = new UploadedFile(__DIR__ . '/test.csv', 'test.csv', null, null, true);
        $service->generate($file);
    }
}
