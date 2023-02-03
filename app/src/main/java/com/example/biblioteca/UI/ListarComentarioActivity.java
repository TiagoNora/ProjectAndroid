package com.example.biblioteca.UI;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.Model.Comentario;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

public class ListarComentarioActivity extends AppCompatActivity {
    private Button add,cancel;
    private EditText etUserId,etIsbn;
    private String userId,isbn;
    private TextView caixa1;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_comentario);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etIsbn = (EditText) findViewById(R.id.etIsbn);
        etUserId = (EditText) findViewById(R.id.etUserId);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        caixa1 = (TextView) findViewById(R.id.caixaTexto1);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        isbn = null;
        userId = null;
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
                isbn = etIsbn.getText().toString();
                userId = etUserId.getText().toString();
                getInfoDataFromWS(isbn,userId);
            }
        });

    }
    private void getInfoDataFromWS(String isbn,String userId){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });


                Comentario comentario = RequestsService.getComentario(isbn,userId,ListarComentarioActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(comentario != null) {
                            String string = "Comentario:"+comentario.getRecomendado()+"\n"+
                                    "Opiniao:"+comentario.getOpiniao()+"\n"+
                                    "UserId:"+comentario.getUserId()+"\n"+
                                    "Isbn:"+comentario.getIsbn();
                            caixa1.setText(string);

                        }
                        pdRing.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        }.start();
    }

}