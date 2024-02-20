@extends('layouts.app')

@section('template_title')
    {{ $marca->name ?? "{{ __('Mostrar') Marca" }}
@endsection

@section('content')
    <section class="content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <div class="float-left">
                            <span class="card-title">Mostrar Marca</span>
                        </div>
                        <div class="float-right">
                            <a class="btn btn-primary" href="{{ route('marca.index') }}"> {{ __('Atrás') }}</a>
                        </div>
                    </div>

                    <div class="card-body">

                        <div class="form-group">
                            <strong>Zapatillas Id:</strong>
                            {{ $marca->zapatillas_id }}
                        </div>
                        <div class="form-group">
                            <strong>Nombre:</strong>
                            {{ $marca->nombre }}
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>
@endsection
