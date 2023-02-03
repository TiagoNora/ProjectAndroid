package com.example.biblioteca.UI;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Dto.Mapper;
import com.example.biblioteca.Dto.StockDTO;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.Model.Stock;
import com.example.biblioteca.R;

public class InformacoesListarComentarios extends AppCompatActivity {
    private Button add, cancel;
    private EditText etIsbn;
    private String isbn;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_listar_comentarios);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etIsbn = (EditText) findViewById(R.id.etIsbn);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        isbn = null;
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
                    Intent intent = new Intent(InformacoesListarComentarios.this,ListarComentariosActivity.class);
                    intent.putExtra(Utils.isbn,isbn);
                    startActivity(intent);

            }
        });
    }
}