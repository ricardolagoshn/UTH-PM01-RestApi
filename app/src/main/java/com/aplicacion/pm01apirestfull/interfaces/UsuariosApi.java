package com.aplicacion.pm01apirestfull.interfaces;

import com.aplicacion.pm01apirestfull.Models.Usuarios;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;

public interface UsuariosApi
{
    String Ruta = "/posts";

    @GET(Ruta)
    Call<List<Usuarios>> getUsuarios();
}
