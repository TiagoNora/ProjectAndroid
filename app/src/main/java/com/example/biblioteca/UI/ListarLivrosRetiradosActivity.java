package com.example.biblioteca.UI;

import android.content.Intent;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Adapter.ListViewAdapterComentarios;
import com.example.biblioteca.Adapter.ListViewAdapterLivrosRetirados;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.Model.CheckOut;
import com.example.biblioteca.Model.Comentario;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

import java.util.ArrayList;
import java.util.List;

public class ListarLivrosRetiradosActivity extends AppCompatActivity {
    private ListView lv;
    private ProgressBar pdRing;
    private List<CheckOut> livrosRetirados;
    private ListViewAdapterLivrosRetirados adapter;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros_retirados);
        Intent intent = getIntent();
        userId = intent.getStringExtra(Utils.userId);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        lv = (ListView) findViewById(R.id.listView);
        livrosRetirados = new ArrayList();
        adapter = new ListViewAdapterLivrosRetirados(this, livrosRetirados);
        lv.setAdapter(adapter);
        getLivrosRetiradosFromWs(userId);
    }
    private void getLivrosRetiradosFromWs(String userId) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                List<CheckOut> checkOuts = RequestsService.getLivrosRetirados(userId,ListarLivrosRetiradosActivity.this);
                livrosRetirados.clear();
                livrosRetirados.addAll(checkOuts);
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