package com.curso.app.appsantamededeus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.curso.app.appsantamededeus.R;
import com.curso.app.appsantamededeus.config.ConfiguracaoFirebase;
import com.curso.app.appsantamededeus.model.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner sp_hora_inicial;
    private Spinner sp_hora_final;
    private Button btnBanner;
    private Button btnSalvar;
    private EditText txt_descricao;
    private AlertDialog alerta;
    public static final int IMAGEM_INTERNA = 12;
    public static Date date = new Date();
    private ListView listViewEventos;
    private LinearLayout linearLayoutEvento;
    private ArrayAdapter<String> adapterListaEventos;
    private ArrayList<String> listaEventosString;
    private List<Evento> listaEventosObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent entrandaIntent = getIntent();
        final String data = entrandaIntent.getStringExtra("date");

        final TextView textoData = findViewById(R.id.dataEvento);
        sp_hora_inicial = (Spinner) findViewById(R.id.horaInicial);
        sp_hora_final = (Spinner) findViewById(R.id.horaFinal);
        txt_descricao = (EditText) findViewById(R.id.descEvento);
        btnSalvar = (Button) findViewById(R.id.botaoSalvar);
        textoData.setText(data);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           String emailUsuario = ConfiguracaoFirebase.getAuth().getCurrentUser().getEmail();
                    Evento evento = new Evento();
                    evento.setUsuario(emailUsuario);
                    evento.setDataSalva(textoData.getText().toString());
                    evento.setHoraInicio(sp_hora_inicial.getSelectedItem().toString());
                    evento.setHoraFim(sp_hora_final.getSelectedItem().toString());
                    evento.setDecricao(txt_descricao.getText().toString());

                    ConfiguracaoFirebase.getDatabase().child("calendario_eventos")
                            .push()
                            .setValue(evento);

            }
        });








    }

    public void teste(View view){

        Toast.makeText(MainActivity.this,
                "Boa noite!!!!",
                Toast.LENGTH_LONG).show();
        finish();

    }
}
