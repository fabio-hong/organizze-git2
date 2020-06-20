package com.meio.organizze_git2.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = objSimpleDateFormat.format(data);

        return dataString;
    }

    public static String mesAnoDataEscolhida (String data){

        //Separar a data em 3 indices de uma array
        //Ex: a data está no formato 20/01/2019 então, a cada / ele separará
        String arrayData[] = data.split("/");
        String dia = arrayData[0];
        String mes = arrayData[1];
        String ano = arrayData[2];

        String mesAno = mes + ano; //012019
        return mesAno;
    }
}
