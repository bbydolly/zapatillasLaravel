<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Zapatilla
 *
 * @property $id
 * @property $talla
 * @property $modelo
 * @property $marca
 * @property $created_at
 * @property $updated_at
 *
 * @property Marca[] $marcas
 * @package App
 * @mixin \Illuminate\Database\Eloquent\Builder
 */
class Zapatilla extends Model
{
    
    static $rules = [
		'talla' => 'required',
		'modelo' => 'required',
		'marca' => 'required',
    ];

    protected $perPage = 20;

    /**
     * Attributes that should be mass-assignable.
     *
     * @var array
     */
    protected $fillable = ['talla','modelo','marca'];


    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function marcas()
    {
        return $this->hasMany('App\Models\Marca', 'zapatillas_id', 'id');
    }
    

}
