<?php

namespace Tests\Unit\app\Services\Impl;

use App\Models\File;
use App\Repositories\FileRepositoryInterface;
use App\Services\Impl\FileService;
use Illuminate\Database\Eloquent\Collection;
use PHPUnit\Framework\MockObject\Exception;
use PHPUnit\Framework\TestCase;

class FileServiceTest extends TestCase
{
    /**
     * @throws Exception
     */
    public function testSaveProcessedFile()
    {
        $repository = static::createMock(FileRepositoryInterface::class);
        $repository->expects(static::once())
            ->method('save')
            ->with('test.csv', 'hash');

        $service = new FileService(fileRepository: $repository);

        $service->saveProcessedFile(name: 'test.csv', hash: 'hash');
    }

    /**
     * @throws Exception
     */
    public function testReturnAllProcessedFiles()
    {
        $expected = new Collection(
            [
                new File(["name" => "test1.csv", "hash" => "hash1"]),
                new File(["name" => "test2.csv", "hash" => "hash2"]),
                new File(["name" => "test3.csv", "hash" => "hash3"])
            ]
        );

        $repository = static::createMock(FileRepositoryInterface::class);
        $repository->method('findAll')->willReturn($expected);

        $service = new FileService(fileRepository: $repository);

        $result = $service->findAllProcessedFiles();

        static::assertEquals($expected, $result);
    }

    /**
     * @throws Exception
     */
    public function testIfAlreadyProcessedFile()
    {
        $repository = static::createMock(FileRepositoryInterface::class);
        $repository->method('exists')
            ->with('hash')
            ->willReturn(true);

        $service = new FileService(fileRepository: $repository);

        $result = $service->isAlreadyProcessedFile(hash: 'hash');

        static::assertTrue($result);
    }
}
