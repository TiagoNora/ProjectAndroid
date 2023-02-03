package com.example.biblioteca.UI;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

public class EstenderPrazoActivity extends AppCompatActivity {
    private EditText etIsbn, etUserId;
    private String isbn,userId;
    private Button add, cancel;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estender_prazo);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etIsbn = (EditText) findViewById(R.id.etIsbn);
        etUserId = (EditText) findViewById(R.id.etUserId);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        etIsbn = null;
        etUserId = null;
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
                    userId = etUserId.getText().toString();
                    extendDateBookTo2WS(isbn,userId);

                } catch (Exception e) {
                    exceptionMessage = e.getMessage();
                    exception = true;
                }
            }
        });

    }
    private void extendDateBookTo2WS(String isbn,String userId){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });


                String id = RequestsService.getIdCheckOutBook(isbn,userId,EstenderPrazoActivity.this);
                RequestsService.extendCheckOut(id,EstenderPrazoActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        }.start();
    }
}