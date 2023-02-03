package com.example.biblioteca.UI;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.R;

public class InformacoesListarLivrosRetirados extends AppCompatActivity {

    private Button add, cancel;
    private EditText etUserId;
    private String userId;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_listar_livros_retirados);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etUserId = (EditText) findViewById(R.id.etUserId);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
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

                userId = etUserId.getText().toString();
                Intent intent = new Intent(InformacoesListarLivrosRetirados.this,ListarLivrosRetiradosActivity.class);
                intent.putExtra(Utils.userId,userId);
                startActivity(intent);

            }
        });
    }
}