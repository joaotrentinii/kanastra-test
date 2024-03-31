<?php

namespace Tests\Unit\app\Http\Controllers;

use App\Http\Controllers\PaymentController;
use App\Models\File;
use App\Services\BillServiceInterface;
use App\Services\FileServiceInterface;
use Illuminate\Database\Eloquent\Collection;
use Illuminate\Http\Request;
use Illuminate\Http\UploadedFile;
use PHPUnit\Framework\MockObject\Exception;
use PHPUnit\Framework\TestCase;
use RuntimeException;

class PaymentControllerTest extends TestCase
{

    /**
     * @throws Exception
     */
    public function testWhenFileIsSuccessfullyProcessed()
    {
        $billService = static::createMock(BillServiceInterface::class);
        $billService->expects(static::once())->method('generate');

        $fileService = static::createMock(FileServiceInterface::class);
        $fileService->expects(static::once())->method('saveProcessedFile');
        $fileService->expects(static::once())->method('isAlreadyProcessedFile');

        $controller = new PaymentController($billService, $fileService);

        $result = $controller->createBills($this->requestFake());

        static::assertEquals(200, $result->status());
        static::assertEquals("File test.csv processed successfully.", $result->content());
    }

    /**
     * @throws Exception
     */
    public function testWhenFileHasAlreadyBeenProcessed()
    {
        $billService = static::createMock(BillServiceInterface::class);
        $fileService = static::createMock(FileServiceInterface::class);
        $fileService->method('isAlreadyProcessedFile')->willReturn(true);

        $controller = new PaymentController($billService, $fileService);

        $result = $controller->createBills($this->requestFake());

        static::assertEquals(409, $result->status());
        static::assertEquals("File already processed.", $result->content());
    }

    /**
     * @throws Exception
     */
    public function testWhenAnExceptionHappened()
    {
        $exception = new RuntimeException("An error occurs");

        $fileService = static::createMock(FileServiceInterface::class);
        $billService = static::createMock(BillServiceInterface::class);
        $billService->method('generate')->willThrowException($exception);

        $controller = new PaymentController($billService, $fileService);

        $result = $controller->createBills($this->requestFake());

        static::assertEquals(500, $result->status());
        static::assertEquals($exception->getMessage(), $result->content());
    }

    /**
     * @throws Exception
     */
    public function testFindProcessedFiles()
    {
        $expected = new Collection(
            [
                new File(["name" => "test1.csv", "hash" => "hash1"]),
                new File(["name" => "test2.csv", "hash" => "hash2"]),
                new File(["name" => "test3.csv", "hash" => "hash3"])
            ]
        );

        $billService = static::createMock(BillServiceInterface::class);
        $fileService = static::createMock(FileServiceInterface::class);
        $fileService->method('findAllProcessedFiles')->willReturn($expected);

        $controller = new PaymentController($billService, $fileService);

        $result = $controller->findProcessedFiles();

        static::assertEquals(200, $result->status());
        static::assertEquals($expected, $result->content());
    }

    private function requestFake(): Request
    {
        $file = new UploadedFile(__DIR__ . '/test.csv', 'test.csv', null, null, true);
        $request = new Request();
        $request->files->set('csv', $file);

        return $request;
    }
}
