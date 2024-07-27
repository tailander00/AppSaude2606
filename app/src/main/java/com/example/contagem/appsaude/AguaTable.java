package com.example.contagem.appsaude;

import android.provider.BaseColumns;

public class AguaTable implements BaseColumns {
    public static final String TABLE_NAME = "agua";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUNA_LITROS = "litros";
    public static final String COLUNA_DATA = "dataAtual";

    public static  final String SQL_CREATE_AGUA_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_LITROS + " REAL NOT NULL, "
            + COLUNA_DATA + " DATE NOT NULL);";
}
