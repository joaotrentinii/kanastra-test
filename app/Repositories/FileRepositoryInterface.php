<?php

namespace App\Repositories;

use Illuminate\Database\Eloquent\Collection;

interface FileRepositoryInterface
{
    function save(string $name, string $hash);
    function exists(string $hash): bool;
    function findAll(): Collection;
}
