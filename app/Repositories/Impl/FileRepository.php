<?php

namespace App\Repositories\Impl;

use App\Models\File;
use App\Repositories\FileRepositoryInterface;
use Illuminate\Database\Eloquent\Collection;

class FileRepository implements FileRepositoryInterface
{

    function save(string $name, string $hash)
    {
        File::create(['name' => $name, 'hash' => $hash]);
    }

    function exists(string $hash): bool
    {
        return File::where(['hash' => $hash])->exists();
    }

    function findAll(): Collection
    {
        return File::all();
    }
}
