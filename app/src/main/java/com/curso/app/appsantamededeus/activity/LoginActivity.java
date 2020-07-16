package com.curso.app.appsantamededeus.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.curso.app.appsantamededeus.MenuIgreja;
import com.curso.app.appsantamededeus.R;
import com.curso.app.appsantamededeus.config.ConfiguracaoFirebase;
import com.curso.app.appsantamededeus.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText campoEmail;
    private TextInputEditText campoSenha;
    private FirebaseAuth auth;
    private NavigationView sair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = ConfiguracaoFirebase.getAuth();

        campoEmail = findViewById(R.id.editLoginEmail);
        campoSenha = findViewById(R.id.editLoginSenha);

        sair = (NavigationView) findViewById(R.id.nav_sair);
    }


    public void logarUsuario(Usuario usuario){
        auth.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha() )
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                verificaSeUsuarioEstaLogado(task);
                            }
                        });
    }

    private void verificaSeUsuarioEstaLogado(Task task){
        if (task.isSuccessful()) {
            abrirTelaPrincipal();
        } else {
            String excecao = "";
            try {
                throw task.getException();
            } catch (FirebaseAuthInvalidUserException e) {
                excecao = "Usuário não cadastrado!";
            } catch (FirebaseAuthInvalidCredentialsException e) {
                excecao = "E-mail ou senha não correspondem a um usuário cadastrado!";
            } catch (Exception e) {
                excecao = "Erro ao logar usuário!" + e.getMessage();
                e.printStackTrace();
            }
            Toast.makeText(LoginActivity.this,
                    excecao, Toast.LENGTH_SHORT).show();
        }
    }


    public void validaUsuario(View view){

        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        if(!textoEmail.isEmpty()){// Remover para um método essas validações
            if(!textoSenha.isEmpty()){
                Usuario usuario = new Usuario();
                usuario.setEmail(textoEmail);
                usuario.setSenha(textoSenha);
                logarUsuario(usuario);
            }else{
                Toast.makeText(LoginActivity.this,
                        "Preencha sua senha!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginActivity.this,
                    "Preencha seu e-mail!",
                    Toast.LENGTH_SHORT).show();
        }


    }
    public void abrirTelaPrincipal(){

        Intent intent =  new Intent(
                LoginActivity.this, MenuIgreja.class);
        startActivity(intent);

    }


    @Override // autenticar usuario caso ele já esteja logado
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = auth.getCurrentUser();
        if(usuarioAtual != null){
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaCadastro(View view){

      Intent intent =  new Intent(
              LoginActivity.this, CadastroActivity.class);
      startActivity(intent);

    }

}
