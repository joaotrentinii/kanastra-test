<?php

namespace App\Services\Impl;

use App\Repositories\FileRepositoryInterface;
use App\Services\FileServiceInterface;
use Illuminate\Database\Eloquent\Collection;

class FileService implements FileServiceInterface
{
    public function __construct(private readonly FileRepositoryInterface $fileRepository)
    {
    }

    function saveProcessedFile(string $name, string $hash)
    {
        $this->fileRepository->save(name: $name, hash: $hash);
    }

    function findAllProcessedFiles(): Collection
    {
        return $this->fileRepository->findAll();
    }

    function isAlreadyProcessedFile(string $hash): bool
    {
        return $this->fileRepository->exists(hash: $hash);
    }
}
