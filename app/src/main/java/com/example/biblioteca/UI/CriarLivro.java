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
import com.example.biblioteca.Dto.Mapper;
import com.example.biblioteca.Dto.StockDTO;
import com.example.biblioteca.Model.Stock;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

public class CriarLivro extends AppCompatActivity {
    private Button add, cancel;
    private EditText etIsbn, etStock;
    private String isbn, stock;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_livro);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etIsbn = (EditText) findViewById(R.id.etIsbn);
        etStock = (EditText) findViewById(R.id.etStock);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        isbn = null;
        stock = null;
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
                    stock = etStock.getText().toString();
                    Stock stock = new Stock(etStock.getText().toString());
                    StockDTO stockDTO = Mapper.stock2stockDTO(stock);
                    postBook2WS(stockDTO, isbn);

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
            Toast.makeText(CriarLivro.this,exceptionMessage,Toast.LENGTH_SHORT).show();
        }
    }
    private void postBook2WS(StockDTO stockDTO, String isbn) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                RequestsService.loadBook(isbn,CriarLivro.this);
                RequestsService.addBook(stockDTO,isbn, CriarLivro.this);
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