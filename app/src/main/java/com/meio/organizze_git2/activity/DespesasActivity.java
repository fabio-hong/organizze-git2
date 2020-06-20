package com.meio.organizze_git2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.meio.organizze_git2.R;
import com.meio.organizze_git2.config.ConfiguracaoFirebase;
import com.meio.organizze_git2.helper.Base64Custom;
import com.meio.organizze_git2.helper.DateCustom;
import com.meio.organizze_git2.model.Movimentacao;
import com.meio.organizze_git2.model.Usuario;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao objMovimentacao;
    private DatabaseReference firebaseReferencia = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoData= findViewById(R.id.editData);
        campoCategoria= findViewById(R.id.editCategoria);
        campoDescricao= findViewById(R.id.editDescricao);
        campoValor= findViewById(R.id.editValor);

        //Preencher campoData com a data atual
        campoData.setText(DateCustom.dataAtual());

        //Recuperar despesa total
        recuperarDespesaTotal();

        //check
    }



    //Método que será chamado ao clicar no fabSalvar
    public void salvarDespesa(View view){

        //Verificar se algo foi digitado
        if (validarCamposDespesa()){

            objMovimentacao = new Movimentacao();
            String data = campoData.getText().toString();

            //Valor da movimentação
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());
            objMovimentacao.setValor(valorRecuperado);
            objMovimentacao.setCategoria(campoCategoria.getText().toString());
            objMovimentacao.setDescricao(campoDescricao.getText().toString());
            objMovimentacao.setData(data);
            objMovimentacao.setTipoMovimentacao("tipoDespesa");


            //Atualizar valores da movimentacao
            Double despesaAtualizada = despesaTotal + valorRecuperado;
            atualizarDespesa(despesaAtualizada);


            //Salvar movimentacao
            objMovimentacao.salvar(data);

            //check
        }
    }



    //Método para não deixar salvar campos em branco
    public Boolean validarCamposDespesa(){

        String textoValor =campoValor.getText().toString();
        String textoData =campoData.getText().toString();
        String textoCategoria =campoCategoria.getText().toString();
        String textoDescricao =campoDescricao.getText().toString();


        if (!textoValor.isEmpty()) {
            if (!textoData.isEmpty()) {
                if (!textoCategoria.isEmpty()) {
                    if (!textoDescricao.isEmpty()) {
                        return true;

                    }else {
                        Toast.makeText(DespesasActivity.this,
                                R.string.toast_erro_descricao,
                                Toast.LENGTH_LONG).show();
                        return false;
                    }

                }else {
                    Toast.makeText(DespesasActivity.this,
                            R.string.toast_categoria_erro,
                            Toast.LENGTH_LONG).show();
                    return false;
                }

            }else {
                Toast.makeText(DespesasActivity.this,
                        R.string.toast_data_erro,
                        Toast.LENGTH_LONG).show();
                return false;
            }

        }else {

            Toast.makeText(DespesasActivity.this,
                    R.string.toast_valor_erro,
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

    //check


    //Método que recupera despesa total
    public void recuperarDespesaTotal(){ //check

        //Recuperar Referencia do usuario
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);

        DatabaseReference usuarioReferencia =
                firebaseReferencia
                        .child("usuarios")
                        .child(idUsuario);


        //Listener para mudança do databasereference usuarioReferencia
        usuarioReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Usuario objUsuario = dataSnapshot.getValue(Usuario.class)
                //o getValue vai converter o retorno do firebase em um objeto do tipo Usuario
                Usuario objUsuario = dataSnapshot.getValue(Usuario.class);

                //Recuperar a despesa total do usuario
                despesaTotal = objUsuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //Método que atualiza despesa
    public void atualizarDespesa(Double despesa){//check

        //Recuperar referencia do usuario
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioReferencia = firebaseReferencia.child("usuarios").child(idUsuario);


        usuarioReferencia.child("despesaTotal").setValue(despesa);


    }
}