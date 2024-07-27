package com.example.contagem.appsaude;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class AguaActivity extends AppCompatActivity {
    private SaudeDbHelper mdbHelper;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua);
        editText = findViewById(R.id.agua_editText);
        mdbHelper = new SaudeDbHelper(this);
        mostra();

    }

    public void enviar(View v){
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date(System.currentTimeMillis());


        try {
            SQLiteDatabase db = mdbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(AguaTable.COLUNA_LITROS, Float.parseFloat(String.valueOf(editText.getText())));
            values.put(AguaTable.COLUNA_DATA, String.valueOf(data));

            long retorno = db.insert(AguaTable.TABLE_NAME, null, values);

            if (retorno == -1) {
                Toast.makeText(this, "Erro ao inserir " + editText, Toast.LENGTH_SHORT).show();
            }

        }catch (SQLiteConstraintException e){ }

        mdbHelper.close();
        this.finish();
    }

    private void mostra(){
        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        Cursor cursor = db.query(AguaTable.TABLE_NAME, null, null, null, null, null, null);

        if(cursor!=null){

            cursor.moveToFirst();

            int x=cursor.getCount();
            DataPoint[] ponto = new DataPoint[x];

            for (int i=0;i<x;i++) {
               float litros = cursor.getFloat(cursor.getColumnIndexOrThrow(AguaTable.COLUNA_LITROS));
               long data = cursor.getLong(cursor.getColumnIndexOrThrow(AguaTable.COLUNA_DATA));
                ponto[i]= new DataPoint(data, litros);

                cursor.moveToNext();
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(ponto);
            GraphView graph = (GraphView)findViewById(R.id.grafico);
            graph.addSeries(series);
        }
    }
}
