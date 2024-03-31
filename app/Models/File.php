<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class File extends Model
{
    protected $hidden = ['hash'];
    protected $fillable = ['name', 'hash'];
}
