package com.aplicacion.pm01apirestfull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aplicacion.pm01apirestfull.Models.Usuarios;
import com.aplicacion.pm01apirestfull.interfaces.UsuariosApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> titulos = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtengaListaUsuarios();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titulos);
        list = (ListView) findViewById(R.id.lista);
        list.setAdapter(arrayAdapter);
    }

    private void obtengaListaUsuarios()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuariosApi usuariosApi = retrofit.create(UsuariosApi.class);

        Call<List<Usuarios>> llamada =usuariosApi.getUsuarios();

        llamada.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                for(Usuarios usuarios : response.body())
                {
                    Log.i( usuarios.getTitle(), "onResponse: ");
                    titulos.add(usuarios.getTitle());

                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {

            }
        });


    }


}