@extends('layouts.app')

@section('template_title')
    {{ $zapatilla->name ?? "{{ __('Show') Zapatilla" }}
@endsection

@section('content')
    <section class="content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <div class="float-left">
                            <span class="card-title">Show Zapatilla</span>
                        </div>
                        <div class="float-right">
                            <a class="btn btn-primary" href="{{ route('zapatilla.index') }}"> {{ __('Atrás') }}</a>
                        </div>
                    </div>

                    <div class="card-body">

                        <div class="form-group">
                            <strong>Talla:</strong>
                            {{ $zapatilla->talla }}
                        </div>
                        <div class="form-group">
                            <strong>Modelo:</strong>
                            {{ $zapatilla->modelo }}
                        </div>
                        <div class="form-group">
                            <strong>Marca:</strong>
                            {{ $zapatilla->marca }}
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>
@endsection
