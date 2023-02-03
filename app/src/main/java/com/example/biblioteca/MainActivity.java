package com.example.biblioteca;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.UI.FingerActivity;
import com.example.biblioteca.UI.FuncionarioActivity;
import com.example.biblioteca.UI.SettingsMain;
import com.example.biblioteca.Service.RequestsService;
import com.example.biblioteca.UI.UtilizadorActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pdRing;
    private int i=0;
    private TextView caixa1,caixa2;
    private Button bt1, bt2;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        caixa2.setText("URL:"+ Utils.getWSAddress(MainActivity.this));
                    }
                });
        pdRing = (ProgressBar) findViewById(R.id.progressBar);
        pdRing.setVisibility(ProgressBar.INVISIBLE);
        Button bt1 = (Button)findViewById(R.id.funcionarios);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FuncionarioActivity.class);
                someActivityResultLauncher.launch(i);
            }
        });
        Button bt2 = (Button)findViewById(R.id.utilizadores);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UtilizadorActivity.class);
                someActivityResultLauncher.launch(i);
            }
        });
        caixa1 = findViewById(R.id.caixaTexto1);
        caixa2= findViewById(R.id.caixaTexto2);
        caixa2.setText("Base url:"+ Utils.getWSAddress(MainActivity.this));
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


                BibliotecaInfo bibliotecaInfo = RequestsService.getBibliotecaInfo(getApplicationContext());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(bibliotecaInfo != null) {
                            caixa1.setText(bibliotecaInfo.toString());
                            caixa2.setText("");
                        }else{
                            caixa2.setText("No response from WebServer in  URL:"+ RequestsService.lastUrl);
                            caixa1.setText("");
                        }
                        pdRing.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        }.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = (MenuInflater) getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reload:
                getInfoDataFromWS();
                return true;
            case R.id.settings:
                openSettingsMainActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openSettingsMainActivity(){
        Intent intent = new Intent(this, FingerActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

}