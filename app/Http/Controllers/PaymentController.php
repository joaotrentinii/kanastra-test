<?php

namespace App\Http\Controllers;

use App\Services\BillServiceInterface;
use App\Services\FileServiceInterface;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Throwable;

class PaymentController extends Controller
{
    public function __construct(
        private readonly BillServiceInterface $billService,
        private readonly FileServiceInterface $fileService
    )
    {
    }

    public function createBills(Request $request): Response
    {
        try {
            $file = $request->file()['csv'];
            $hash = md5_file($file->getPathname(), true);

            /** Garante que um csv com o mesmo conteúdo não seja processado duas vezes */
            if ($this->fileService->isAlreadyProcessedFile(hash: $hash)) {
                return new Response(content: "File already processed.", status: 409);
            }

            $fileName = $file->getClientOriginalName();

            $this->billService->generate(file: $file);
            $this->fileService->saveProcessedFile(name: $fileName, hash: $hash);
        } catch (Throwable $exception) {
            return new Response(content: $exception->getMessage(), status: 500);
        }

        return new Response(content: "File $fileName processed successfully.", status: 200);
    }

    public function findProcessedFiles(): Response
    {
        $files = $this->fileService->findAllProcessedFiles();
        return new Response(content: $files, status: 200);
    }
}
