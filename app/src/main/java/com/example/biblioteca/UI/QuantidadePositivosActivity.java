package com.example.biblioteca.UI;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Handler.NetworkHandler;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.Model.Comentario;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

public class QuantidadePositivosActivity extends AppCompatActivity {
    private EditText etIsbn;
    private Button add,cancel;
    private String isbn;
    private String valor;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    private TextView caixa1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantidade_positivos);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etIsbn = (EditText) findViewById(R.id.etIsbn);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        caixa1 = (TextView) findViewById(R.id.caixaTexto1);
        etIsbn = null;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    isbn = etIsbn.getText().toString();
                    getRecommendedCountedFromWS(isbn);
                } catch (Exception e) {
                    exceptionMessage = e.getMessage();
                    exception = true;
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });
    }
    private void getRecommendedCountedFromWS(String isbn){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });


                int valor = RequestsService.getRecommendedCount(isbn,QuantidadePositivosActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        caixa1.setText(valor);
                        pdRing.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        }.start();
    }
}
