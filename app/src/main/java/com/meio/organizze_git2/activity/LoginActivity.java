package com.meio.organizze_git2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import com.meio.organizze_git2.R;
import com.meio.organizze_git2.config.ConfiguracaoFirebase;
import com.meio.organizze_git2.model.Usuario;


public class LoginActivity extends AppCompatActivity {

    private EditText campoSenha, campoEmail;
    private Button buttonEntrar;
    private Usuario objUsuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {

                        objUsuario = new Usuario();
                        objUsuario.setSenha(textoSenha);
                        objUsuario.setEmail(textoEmail);
                        validarLogin();

                    } else {
                        Toast.makeText(LoginActivity.this, R.string.toast_senha, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, R.string.toast_email, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void validarLogin() {
        //Recuperar instancia de autenticacao do firebase
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                objUsuario.getEmail(), objUsuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    abrirTelaPrincipal();

                } else {

                    String excecao = "";

                    try {

                        throw task.getException();

                    } catch (FirebaseAuthInvalidUserException e) {

                        excecao = getString(R.string.excecao_nao_cadastrado);

                        Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();

                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        excecao = getString(R.string.excecao_email_ou_senha);

                        Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {

                        excecao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }


                }
            }
        });
    }

    public void abrirTelaPrincipal (){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();//fecha activity login
    }


}