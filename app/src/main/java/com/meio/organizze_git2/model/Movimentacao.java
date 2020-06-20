package com.meio.organizze_git2.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.meio.organizze_git2.config.ConfiguracaoFirebase;
import com.meio.organizze_git2.helper.Base64Custom;
import com.meio.organizze_git2.helper.DateCustom;

public class Movimentacao {

    private String data;
    private String categoria;
    private String descricao;
    private String tipoMovimentacao;
    private double valor;

    //Construtor vazio
    public Movimentacao() {
    }


    //Salvar no firebase
    public void salvar(String dataEscolhida){

        //COMO RECUPERAR EMAIL E DEFINIR ID DO USUARIO
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        //Recuperar mes e ano
        String mesAno = DateCustom.mesAnoDataEscolhida(dataEscolhida);

        //Preciso da referencia do firebase
        DatabaseReference objFirebaseReferencia = ConfiguracaoFirebase.getFirebaseDatabase();

        objFirebaseReferencia
                .child("movimentacao")  //Quando já tem o nó, ele não cria, apenas seleciona
                .child(idUsuario)
                .child(mesAno)
                .push() //cria id único
                .setValue(this);

    }



    //Getter and setter
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
