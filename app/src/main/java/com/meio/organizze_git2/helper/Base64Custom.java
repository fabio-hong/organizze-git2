package com.meio.organizze_git2.helper;

import android.util.Base64;

public class Base64Custom {

    public static String codificarBase64(String textoParaCodificar){

        //Base64.encodeToString(array de bytes, flag que define o tipo de codificacao)
        //.replaceAll (valor que quero substituir, valor que será qual será substituido)
        //esse método evita que o encodeToString dê caracteres inválidos
        //vou substituir \\n e \\r por nulo
        return Base64.encodeToString(textoParaCodificar.getBytes(), Base64.DEFAULT)
                .replaceAll("(\\n|\\r)","");
    }

    public static String decodificarBase64 (String textoParaDecodificar){

        //o método decode não retorna uma string e como precisamos de string...
        return new String (Base64.decode(textoParaDecodificar,Base64.DEFAULT));

    }
}
