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
        //Cuerpo de la tabla
        //campo relacional

        //NO BORRA EN CASCADA
        Schema::create('marcas', function (Blueprint $table) {
            //borrado de registros en cascada
            $table->engine="InnoDB";
            $table->id();
            $table->bigInteger('zapatillas_id')->unsigned();
            $table->string('nombre');
            $table->timestamps();
            $table->foreign('zapatillas_id')->references('id')->on('zapatillas')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('marcas');
    }
};
