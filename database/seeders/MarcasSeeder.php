<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
//uso las fachadas para poder usar los métodos DB
use Illuminate\Support\Facades\DB;

class Marca extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('marcas')->insert([
            'nombre'=>'Nike',
            'id'=>'1'

        ]);

    }
}
