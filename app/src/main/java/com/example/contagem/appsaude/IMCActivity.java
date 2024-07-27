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
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IMCActivity extends AppCompatActivity {
    private SaudeDbHelper mdbHelper;
    private EditText editTextPeso,editTextAltura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        setContentView(R.layout.activity_imc);
        editTextPeso = findViewById(R.id.peso_editText);
        editTextAltura = findViewById(R.id.altura_editText);
        mdbHelper = new SaudeDbHelper(this);
        mostra();
    }

    public void enviar(View v){
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date(System.currentTimeMillis());

        try {
            SQLiteDatabase db = mdbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            float altura=Float.parseFloat(String.valueOf(editTextAltura.getText()));
            float peso=Float.parseFloat(String.valueOf(editTextPeso.getText()));
            altura /= 100;
            float imc = peso/(altura*altura);

            values.put(IMCTable.COLUNA_ALTURA, altura);
            values.put(IMCTable.COLUNA_PESO, peso);
            values.put(IMCTable.COLUNA_IMC, imc);
            values.put(AguaTable.COLUNA_DATA, data.getTime());

            long retorno = db.insert(IMCTable.TABLE_NAME, null, values);

            if (retorno == -1) {
                Toast.makeText(this, "Erro ao inserir dados", Toast.LENGTH_SHORT).show();
            }

        }catch (SQLiteConstraintException e){ }

        mdbHelper.close();
        this.finish();
    }

    private void mostra(){
        SQLiteDatabase db = mdbHelper.getReadableDatabase();
        Cursor cursor = db.query(IMCTable.TABLE_NAME, null, null, null, null, null, null);

        if(cursor!=null){

            cursor.moveToFirst();

            int x=cursor.getCount();
            DataPoint[] ponto = new DataPoint[x];

            for (int i=0;i<x;i++) {
                float litros = cursor.getFloat(cursor.getColumnIndexOrThrow(IMCTable.COLUNA_IMC));
                long data = cursor.getLong(cursor.getColumnIndexOrThrow(IMCTable.COLUNA_DATA));
                ponto[i]= new DataPoint(data, litros);

                cursor.moveToNext();
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(ponto);
            GraphView graph = (GraphView)findViewById(R.id.graficoImc);
            graph.addSeries(series);
        }
    }
}
