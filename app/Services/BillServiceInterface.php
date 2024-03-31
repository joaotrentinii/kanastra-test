<?php

namespace App\Services;

use Illuminate\Http\UploadedFile;

interface BillServiceInterface
{
    function generate(UploadedFile $file);
}
