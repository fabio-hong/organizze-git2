package com.meio.organizze_git2.model;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.meio.organizze_git2.config.ConfiguracaoFirebase;

public class Usuario {

    private String nome;
    private String email;
    private String senha;

    private String idUsuario;
    private Double receitaTotal = 0.00;
    private Double despesaTotal = 0.00;



    //getter and setter
    //construtor vazio



    public Usuario() {


    }

    //Método que vai salvar o usuario
    public void salvar(){

        //Criar objeto que permite eu salvar dados no firebase
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();

        //Criar nós
        //firebase.child(primeiro nó = usuarios)
        //                .child(id do usuario)
        //                .setValue(objeto Usuario, que contém nome, email, senha, idUsuari, receita e despesa)
        firebase.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }

    //getter and setter (receitaTotal e despesaTotal)

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public Double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }




    //Não preciso salvar a senha e o idUsuario
    //@Exclude
    //public String getIdUsuario() {
    //        return idUsuario;
    //    }
    //@Exclude
    //public String getSenha() {
    //        return senha;
    //    }


    //getter and setter de Usuario

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
