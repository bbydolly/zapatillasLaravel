<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Marca
 *
 * @property $id
 * @property $zapatillas_id
 * @property $nombre
 * @property $created_at
 * @property $updated_at
 *
 * @property Zapatilla $zapatilla
 * @package App
 * @mixin \Illuminate\Database\Eloquent\Builder
 */
class Marca extends Model
{
    
    static $rules = [
		'zapatillas_id' => 'required',
		'nombre' => 'required',
    ];

    protected $perPage = 20;

    /**
     * Attributes that should be mass-assignable.
     *
     * @var array
     */
    protected $fillable = ['zapatillas_id','nombre'];


    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasOne
     */
    public function zapatilla()
    {
        return $this->hasOne('App\Models\Zapatilla', 'id', 'zapatillas_id');
    }
    

}
