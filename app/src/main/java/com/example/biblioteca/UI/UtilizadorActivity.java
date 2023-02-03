package com.example.biblioteca.UI;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.R;

public class UtilizadorActivity extends AppCompatActivity {
    private Button bt1,bt2,bt3,bt4,bt5,bt6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilizador);
        bt1 = (Button) findViewById(R.id.criarComentario);
        bt2 = (Button) findViewById(R.id.alterarComentario);
        bt3 = (Button) findViewById(R.id.listarComentarios);
        bt4 = (Button) findViewById(R.id.quantidadePositivos);
        bt5 = (Button) findViewById(R.id.listarOpiniao);
        bt6 = (Button) findViewById(R.id.listarLivrosRetirados);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilizadorActivity.this,CriarComentarioActivity.class);
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilizadorActivity.this,AlterarComentarioActivity.class);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilizadorActivity.this,InformacoesListarComentarios.class);
                startActivity(intent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilizadorActivity.this,QuantidadePositivosActivity.class);
                startActivity(intent);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilizadorActivity.this,ListarComentarioActivity.class);
                startActivity(intent);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilizadorActivity.this,InformacoesListarLivrosRetirados.class);
                startActivity(intent);
            }
        });
    }
}