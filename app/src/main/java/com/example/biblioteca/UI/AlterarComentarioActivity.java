package com.example.biblioteca.UI;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Dto.BibliotecaInfoDTO;
import com.example.biblioteca.Dto.ComentarioCriarDTO;
import com.example.biblioteca.Dto.Mapper;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.Model.ComentarioCriar;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

public class AlterarComentarioActivity extends AppCompatActivity {
    private Button add, cancel;
    private EditText etIsbn, etUserId,etComentario;
    private ToggleButton tbtipoComentario;
    private String isbn, userId, comentario;
    private Boolean tipoComentario;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_comentario);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etIsbn = (EditText) findViewById(R.id.etIsbn);
        etUserId = (EditText) findViewById(R.id.etUserId);
        etComentario = (EditText) findViewById(R.id.etComentario);
        tbtipoComentario = (ToggleButton) findViewById(R.id.tipoComentario) ;
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        isbn = null;
        userId = null;
        comentario = null;
        tipoComentario = null;
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
                    userId = etUserId.getText().toString();
                    isbn = etIsbn.getText().toString();
                    comentario = etComentario.getText().toString();
                    if(tbtipoComentario.isChecked()){
                        tipoComentario = true;
                    }
                    else{
                        tipoComentario = false;
                    }
                    ComentarioCriar comentarioCriar = new ComentarioCriar(tipoComentario,etComentario.getText().toString());
                    ComentarioCriarDTO comentarioCriarDTO = Mapper.comentarioCriar2ComentarioCriarDTO(comentarioCriar);
                    putComentarioInfo2WS(comentarioCriarDTO,userId,isbn);

                } catch (Exception e) {
                    exceptionMessage = e.getMessage();
                    exception = true;
                }
            }
        });
    }
    private void checkOperationResult(){
        if(exception == false) {
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(AlterarComentarioActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
        }
    }
    private void putComentarioInfo2WS(ComentarioCriarDTO comentarioCriarDTO,String userId,String isbn) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                String id = RequestsService.getIdComentario(isbn,userId,AlterarComentarioActivity.this);
                RequestsService.updateComentario(id,userId,isbn,comentarioCriarDTO,AlterarComentarioActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.INVISIBLE);
                        checkOperationResult();
                    }
                });
            }
        }.start();
    }
}