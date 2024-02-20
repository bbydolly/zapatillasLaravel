<?php

namespace App\Http\Controllers;

use App\Models\Zapatilla;
use Illuminate\Http\Request;

/**
 * Class ZapatillaController
 * @package App\Http\Controllers
 */
class ZapatillaController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $zapatillas = Zapatilla::paginate();

        return view('zapatilla.index', compact('zapatillas'))
            ->with('i', (request()->input('page', 1) - 1) * $zapatillas->perPage());
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $zapatilla = new Zapatilla();
        return view('zapatilla.create', compact('zapatilla'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        request()->validate(Zapatilla::$rules);

        $zapatilla = Zapatilla::create($request->all());

        return redirect()->route('zapatilla.index')
            ->with('success', 'Zapatilla creada correctamente');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $zapatilla = Zapatilla::find($id);

        return view('zapatilla.show', compact('zapatilla'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $zapatilla = Zapatilla::find($id);

        return view('zapatilla.edit', compact('zapatilla'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  Zapatilla $zapatilla
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Zapatilla $zapatilla)
    {
        request()->validate(Zapatilla::$rules);

        $zapatilla->update($request->all());

        return redirect()->route('zapatilla.index')
            ->with('success', 'Zapatilla actualizada correctamente');
    }

    /**
     * @param int $id
     * @return \Illuminate\Http\RedirectResponse
     * @throws \Exception
     */
    public function destroy($id)
    {
        $zapatilla = Zapatilla::find($id)->delete();

        return redirect()->route('zapatilla.index')
            ->with('success', 'Zapatilla borrada correctamente');
    }
}
