@extends('layouts.app')

@section('template_title')
    Zapatilla
@endsection

@section('content')
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header">
                        <div style="display: flex; justify-content: space-between; align-items: center;">

                            <span id="card_title">
                                {{ __('Zapatilla') }}
                            </span>

                             <div class="float-right">
                                <a href="{{ route('zapatilla.create') }}" class="btn btn-primary btn-sm float-right"  data-placement="left">
                                  {{ __('Crear nueva') }}
                                </a>
                              </div>
                        </div>
                    </div>
                    @if ($message = Session::get('success'))
                        <div class="alert alert-success">
                            <p>{{ $message }}</p>
                        </div>
                    @endif

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead class="thead">
                                    <tr>
                                        <th>No</th>

										<th>Talla</th>
										<th>Modelo</th>
										<th>Marca</th>

                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @foreach ($zapatillas as $zapatilla)
                                        <tr>
                                            <td>{{ ++$i }}</td>

											<td>{{ $zapatilla->talla }}</td>
											<td>{{ $zapatilla->modelo }}</td>
											<td>{{ $zapatilla->marca }}</td>

                                            <td>
                                                <form action="{{ route('zapatilla.destroy',$zapatilla->id) }}" method="POST">
                                                    <a class="btn btn-sm btn-primary " href="{{ route('zapatilla.show',$zapatilla->id) }}"><i class="fa fa-fw fa-eye"></i> {{ __('Mostrar') }}</a>
                                                    <a class="btn btn-sm btn-success" href="{{ route('zapatilla.edit',$zapatilla->id) }}"><i class="fa fa-fw fa-edit"></i> {{ __('Editar') }}</a>
                                                    @csrf
                                                    @method('DELETE')
                                                    <button type="submit" class="btn btn-danger btn-sm"><i class="fa fa-fw fa-trash"></i> {{ __('Borrar') }}</button>
                                                </form>
                                            </td>
                                        </tr>
                                    @endforeach
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                {!! $zapatillas->links() !!}
            </div>
        </div>
    </div>
@endsection
