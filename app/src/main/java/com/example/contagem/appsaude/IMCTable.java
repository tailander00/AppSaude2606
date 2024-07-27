package com.example.contagem.appsaude;

import android.provider.BaseColumns;

public class IMCTable implements BaseColumns {
        public static final String TABLE_NAME = "imc";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUNA_ALTURA = "altra";
        public static final String COLUNA_PESO = "peso";
        public static final String COLUNA_IMC = "imc";
        public static final String COLUNA_DATA = "dataAtual";

        public static  final String SQL_CREATE_IMC_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_ALTURA + " REAL NOT NULL, "
                + COLUNA_PESO + " REAL NOT NULL, "
                + COLUNA_IMC + " REAL NOT NULL ,"
                + COLUNA_DATA + " DATE NOT NULL);";
}



