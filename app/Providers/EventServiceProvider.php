<?php

namespace App\Providers;

use App\Events\BillGenerated;
use App\Listeners\BillGeneratedListener;
use Illuminate\Foundation\Support\Providers\EventServiceProvider as ServiceProvider;

class EventServiceProvider extends ServiceProvider
{
    protected $listen = [
        BillGenerated::class => [BillGeneratedListener::class]
    ];
}
