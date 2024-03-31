<?php

namespace App\Services;

use Illuminate\Database\Eloquent\Collection;

interface FileServiceInterface
{
    function saveProcessedFile(string $name, string $hash);
    function findAllProcessedFiles(): Collection;
    function isAlreadyProcessedFile(string $hash): bool;
}
