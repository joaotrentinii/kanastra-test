<?php

namespace App\Services\Impl;

use App\Services\BankApiInterface;
use App\Services\BillServiceInterface;
use Illuminate\Http\UploadedFile;
use InvalidArgumentException;

class BillService implements BillServiceInterface
{
    public function __construct(private readonly BankApiInterface $bankApi)
    {
    }

    function generate(UploadedFile $file)
    {
        if (!$file->isValid()) {
            throw new InvalidArgumentException("The uploaded file is invalid.");
        }

        $lines = file($file->getPathname(), FILE_SKIP_EMPTY_LINES);
        $headers = str_getcsv(array_shift($lines));

        foreach ($lines as $line) {
            $data = str_getcsv($line);
            $record = array_combine($headers, $data);

            /** Chama o serviço que orquestra a geração de boletos de um banco especifico */
            $this->bankApi->generateBill(data: $record);

            /**
             * Não é possível processar o envio de e-mail por meio do redis/mysql de forma síncrona em menos de 60seg.
             * O ideal seria jogar no redis, rabbit ou kafka de forma paralela.
             **/
            //BillGenerated::dispatch($bill);
        }
    }
}
