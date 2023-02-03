package com.example.biblioteca.UI;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Dto.BibliotecaInfoDTO;
import com.example.biblioteca.Dto.Mapper;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

public class CheckOutActivity extends AppCompatActivity {
    private EditText etIsbn, etUserId;
    private String isbn,userId;
    private Button add, cancel;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
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
                    postCheckOut2WS(isbn,userId);


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
            Toast.makeText(CheckOutActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
        }
    }
    private void postCheckOut2WS(String isbn, String userId) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                RequestsService.addCheckOut(isbn,userId, CheckOutActivity.this);
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