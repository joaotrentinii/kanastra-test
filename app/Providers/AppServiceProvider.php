<?php

namespace App\Providers;

use App\Repositories\FileRepositoryInterface;
use App\Repositories\Impl\FileRepository;
use App\Services\BankApiInterface;
use App\Services\BillServiceInterface;
use App\Services\FileServiceInterface;
use App\Services\Impl\BankApi;
use App\Services\Impl\BillService;
use App\Services\Impl\FileService;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    public function register(): void
    {
    }

    public function boot(): void
    {
        $this->app->singleton(BankApiInterface::class, BankApi::class);
        $this->app->singleton(FileServiceInterface::class, FileService::class);
        $this->app->singleton(BillServiceInterface::class, BillService::class);
        $this->app->singleton(FileRepositoryInterface::class, FileRepository::class);
    }
}
