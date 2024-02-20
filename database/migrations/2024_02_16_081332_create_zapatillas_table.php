<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        //Creación de la estructura de la tabla
       //Se ha creado primero esta tabla porque no tiene campo relacional
        Schema::create('zapatillas', function (Blueprint $table) {
            $table->id();
            $table->integer("talla");
            $table->string("modelo");
            $table->string("marca");
            // $table->string("color");
            //$table->bigInteger('marca_id')->unsigned();
            //$table->foreign('marca_id')->references('id')->on('marcas')->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('zapatillas');
    }
};
