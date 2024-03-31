<?php

namespace App\Listeners;

use App\Events\BillGenerated;
use App\Mail\SendBillEmail;
use Illuminate\Support\Facades\Mail;

class BillGeneratedListener
{
    public function handle(BillGenerated $event): void
    {
        Mail::send(new SendBillEmail(bill: $event->bill));
    }
}
