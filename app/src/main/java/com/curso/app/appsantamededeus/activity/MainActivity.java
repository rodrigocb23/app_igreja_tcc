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
import com.curso.app.appsantamededeus.model.Base64Custon;
import com.curso.app.appsantamededeus.model.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Spinner sp_hora_inicial;
    private Spinner sp_hora_final;
    private Button btnBanner;
    private Button btnSalvar;
    private EditText txtDescricao;
    private AlertDialog alerta;
    public static final int IMAGEM_INTERNA = 12;
    public static Date date = new Date();
    private ListView listViewEventos;
    private LinearLayout linearLayoutEvento;
    private ArrayAdapter<String> adapterListaEventos;
    private ArrayList<String> listaEventosString;
    private List<Evento> listaEventosObj;
    private EditText textoLocal;
    private TextView dataSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent entrandaIntent = getIntent();
        final String data = entrandaIntent.getStringExtra("date");

        dataSalvar = findViewById(R.id.dataEvento);
        sp_hora_inicial = (Spinner) findViewById(R.id.horaInicial);
        sp_hora_final = (Spinner) findViewById(R.id.horaFinal);
        txtDescricao = (EditText) findViewById(R.id.descEvento);
        //btnSalvar = (Button) findViewById(R.id.botaoSalvar);
        textoLocal = (EditText) findViewById(R.id.adpterLocal);
        dataSalvar.setText(data);
    }

    public void validarDadosEventos(View view) throws ParseException {

        String local = textoLocal.getText().toString();
        String descricao = txtDescricao.getText().toString();
        String hrInicial = sp_hora_inicial.getSelectedItem().toString();
        String hrFinal = sp_hora_final.getSelectedItem().toString();
        String validarData = dataSalvar.getText().toString();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Date data = sdf1.parse(validarData);

        final Date date = new Date();

        if (!descricao.isEmpty()) {
                if (!local.isEmpty()) {
                    String emailUsuario = ConfiguracaoFirebase.getAuth().getCurrentUser().getEmail();
                    Evento evento = new Evento();
                    evento.setId(UUID.randomUUID().toString());
                    evento.setLocal(textoLocal.getText().toString());
                    evento.setUsuario(Base64Custon.encodeBase64(emailUsuario));
                    evento.setDataSalva(Base64Custon.encodeBase64(dataSalvar.getText().toString()));
                    evento.setHoraInicio(Base64Custon.encodeBase64(sp_hora_inicial.getSelectedItem().toString()));
                    evento.setHoraFim(Base64Custon.encodeBase64(sp_hora_final.getSelectedItem().toString()));
                    evento.setDecricao(txtDescricao.getText().toString());
                    cadastrarEvento(evento);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Informe um local!",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(MainActivity.this,
                        "Digite a Descrição para o Evento!",
                        Toast.LENGTH_SHORT).show();
            }
    }

    public void cadastrarEvento(Evento evento){
        ConfiguracaoFirebase.getDatabase().child("calendario_eventos")
                .child(evento.getId())
                .setValue(evento);
        mensagemSucessoEvento();
    }



    public void mensagemSucessoEvento(){
        Toast.makeText(MainActivity.this,
                "Evento Cadastrado com Sucesso!",
                Toast.LENGTH_SHORT).show();
        finish();
    }




    public void teste(View view){

        Toast.makeText(MainActivity.this,
                "Boa noite!!!!",
                Toast.LENGTH_LONG).show();
        finish();

    }
}
