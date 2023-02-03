package com.example.biblioteca.UI;

import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Adapter.ListViewAdapterLivros;
import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

import java.util.ArrayList;
import java.util.List;

public class ListarLivrosActivity extends AppCompatActivity {
    private ListView lv;
    private ProgressBar pdRing;
    private List<Livro> livros;
    private ListViewAdapterLivros adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        lv = (ListView) findViewById(R.id.listView);
        livros = new ArrayList();
        adapter = new ListViewAdapterLivros(this, livros);
        lv.setAdapter(adapter);
        getLivrosFromWs();

    }
    private void getLivrosFromWs() {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                List<Livro> livrosDTOS = RequestsService.getLivros(ListarLivrosActivity.this);
                livros.clear();
                livros.addAll(livrosDTOS);
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