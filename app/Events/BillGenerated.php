<?php

namespace App\Events;

use App\Dto\Bill;
use Illuminate\Broadcasting\InteractsWithSockets;
use Illuminate\Foundation\Events\Dispatchable;
use Illuminate\Queue\SerializesModels;

class BillGenerated
{
    use Dispatchable, InteractsWithSockets, SerializesModels;

    public function __construct(public readonly Bill $bill)
    {
    }
}
