package com.example.biblioteca.UI;

import android.content.Intent;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Adapter.ListViewAdapterComentarios;
import com.example.biblioteca.Adapter.ListViewAdapterLivros;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.Model.Comentario;
import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

import java.util.ArrayList;
import java.util.List;

public class ListarComentariosActivity extends AppCompatActivity {
    private ListView lv;
    private ProgressBar pdRing;
    private List<Comentario> comentarios;
    private ListViewAdapterComentarios adapter;
    private String isbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_comentarios);
        Intent intent = getIntent();
        isbn = intent.getStringExtra(Utils.isbn);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        lv = (ListView) findViewById(R.id.listView);
        comentarios = new ArrayList();
        adapter = new ListViewAdapterComentarios(this, comentarios);
        lv.setAdapter(adapter);
        getComentariosFromWs(isbn);
    }
    private void getComentariosFromWs(String isbn) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                List<Comentario> comentariosDTOS = RequestsService.getComentarios(isbn,ListarComentariosActivity.this);
                comentarios.clear();
                comentarios.addAll(comentariosDTOS);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        pdRing.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        }.start();
    }
}