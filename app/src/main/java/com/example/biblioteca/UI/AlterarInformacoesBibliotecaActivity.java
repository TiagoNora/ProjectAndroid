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
import com.example.biblioteca.Dto.StockDTO;
import com.example.biblioteca.MainActivity;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.Model.Stock;
import com.example.biblioteca.R;
import com.example.biblioteca.Service.RequestsService;

public class AlterarInformacoesBibliotecaActivity extends AppCompatActivity {
    private Button add, cancel;
    private EditText etAbertura, etFecho;
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String openDays;
    boolean exception = false;
    String exceptionMessage = "";
    private ProgressBar pdRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_informacoes_biblioteca);
        add = (Button) findViewById(R.id.btOp);
        cancel = (Button) findViewById(R.id.btCancel);
        etAbertura = (EditText) findViewById(R.id.etAbertura);
        etFecho = (EditText) findViewById(R.id.etFecho);
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        openTime = null;
        closeTime = null;
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
                    openTime = etAbertura.getText().toString();
                    closeTime = etFecho.getText().toString();
                    BibliotecaInfo bibliotecaInfo = new BibliotecaInfo(name,address,openTime,closeTime,openDays);
                    BibliotecaInfoDTO bibliotecaInfoDTO = Mapper.bibliotecaInfo2BibliotecaInfoDTO(bibliotecaInfo);
                    putBibliotecaInfo2WS(bibliotecaInfoDTO);

                } catch (Exception e) {
                    exceptionMessage = e.getMessage();
                    exception = true;
                }
            }
        });
        getInfoDataFromWS();

    }
    private void getInfoDataFromWS(){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                BibliotecaInfo bibliotecaInfo = RequestsService.getBibliotecaInfoDet(AlterarInformacoesBibliotecaActivity.this);
                name = bibliotecaInfo.getName();
                address = bibliotecaInfo.getAddress();
                openDays = bibliotecaInfo.getOpenDays();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        }.start();
    }
    private void checkOperationResult(){
        if(exception == false) {
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(AlterarInformacoesBibliotecaActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
        }
    }
    private void putBibliotecaInfo2WS(BibliotecaInfoDTO bibliotecaInfoDTO) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pdRing.setVisibility(ProgressBar.VISIBLE);
                    }
                });
                RequestsService.updateBibliotecaInfo(bibliotecaInfoDTO,AlterarInformacoesBibliotecaActivity.this);
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