<?php

use App\Http\Controllers\PaymentController;
use Illuminate\Support\Facades\Route;

Route::post('/createBills', [PaymentController::class, 'createBills']);
Route::get('/findProcessedFiles', [PaymentController::class, 'findProcessedFiles']);

/** Rota auxiliar para certificar que os registros de envio de e-mail foram enviados para o redis */
Route::get('/countEmails', function () {
   $redis = new Redis();
   $redis->connect('redis');
   return $redis->lLen('laravel_database_queues:default');
});
