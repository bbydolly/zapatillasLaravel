<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
//uso las fachadas para poder usar los métodos DB
use Illuminate\Support\Facades\DB;

class ZapatillasSeeder extends Seeder
{

    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('zapatillas')->insert([
            'talla'=>'36',
            'modelo'=>'ZA',
            'marca'=>'Nike'


        ]);

        DB::table('zapatillas')->insert([
            'talla'=>'38',
            'modelo'=>'retro',
            'marca'=>'Puma'

        ]);

        DB::table('zapatillas')->insert([
            'talla'=>'36',
            'modelo'=>'hight',
            'marca'=>'Jordan'

        ]);
    }
}
