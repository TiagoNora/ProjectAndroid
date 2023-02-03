package com.example.biblioteca.UI;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.R;

public class FuncionarioActivity extends AppCompatActivity {
    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);
        Button bt1 = (Button) findViewById(R.id.criarLivro);
        Button bt2 = (Button) findViewById(R.id.alterarLivro);
        Button bt3 = (Button) findViewById(R.id.listarLivros);
        Button bt4 = (Button) findViewById(R.id.alterarBiblioteca);
        Button bt5 = (Button) findViewById(R.id.checkOut);
        Button bt6 = (Button) findViewById(R.id.checkIn);
        Button bt7 = (Button) findViewById(R.id.estenderPrazo);
        Button bt8 = (Button) findViewById(R.id.listarComentarios);
        Button bt9 = (Button) findViewById(R.id.quantidadePositivos);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,CriarLivro.class);
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,AlterarLivroActivity.class);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,ListarLivrosActivity.class);
                startActivity(intent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,AlterarInformacoesBibliotecaActivity.class);
                startActivity(intent);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,CheckOutActivity.class);
                startActivity(intent);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,CheckInActivity.class);
                startActivity(intent);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,EstenderPrazoActivity.class);
                startActivity(intent);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,InformacoesListarComentarios.class);
                startActivity(intent);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this,QuantidadePositivosActivity.class);
                startActivity(intent);
            }
        });
}}