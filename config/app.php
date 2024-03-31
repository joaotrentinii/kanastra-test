<?php

use Illuminate\Support\Facades\Facade;
use Illuminate\Support\ServiceProvider;

return [

    'url' => env('APP_URL', 'http://localhost'),
    'key' => env('APP_KEY'),
    'env' => env('APP_ENV', 'production'),
    'name' => env('APP_NAME', 'Laravel'),
    'debug' => (bool) env('APP_DEBUG', true),
    'asset_url' => env('ASSET_URL'),

    'cipher' => 'AES-256-CBC',
    'locale' => 'en',
    'timezone' => 'UTC',
    'faker_locale' => 'en_US',
    'fallback_locale' => 'en',

    'maintenance' => ['driver' => 'file'],

    'providers' => ServiceProvider::defaultProviders()->merge([
        App\Providers\AppServiceProvider::class,
        App\Providers\EventServiceProvider::class,
        App\Providers\RouteServiceProvider::class,
    ])->toArray(),

    'aliases' => Facade::defaultAliases()->merge([])->toArray(),
];
