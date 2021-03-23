package com.testing.gomarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="create table tiendas(_ID integer primary key autoincrement, "+
                "nombre text, nit text, direccion text, descripcion text, "+
                "imagen text, latitud text, longitud text, horario text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrir(){
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }

    //metodo para insertar datos en SQLite
    public void insertar(String nombre, String nit, String direccion, String descripcion, String imagen, String latitud, String longitud, String horario) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("nit", nit);
        valores.put("direccion", direccion);
        valores.put("descripcion", descripcion);
        valores.put("imagen", imagen);
        valores.put("latitud", latitud);
        valores.put("longitud", longitud);
        valores.put("horario", horario);
        this.getWritableDatabase().insert("tiendas", null, valores);
    }

    public ArrayList<Tienda> getElementos(){

        ArrayList<Tienda> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from tiendas";
        Cursor registros = database.rawQuery(query, null);
        if (registros.moveToFirst()){
            do{
                String nombre = registros.getString(1);
                String nit = registros.getString(2);
                String direccion = registros.getString(3);
                String descripcion = registros.getString(4);
                String imagen = registros.getString(5);
                String latitud = registros.getString(6);
                Double lat = Double.parseDouble(latitud);
                String longitud = registros.getString(7);
                Double lon = Double.parseDouble(longitud);
                String horario = registros.getString(8);

                Tienda obj = new Tienda(lat,lon,nombre,nit,descripcion,direccion,horario,imagen);

                lista.add(obj);
            }while(registros.moveToNext());
        }
        return  lista;
    }

    public void delete(String nit){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("tiendas", "nit"+ "	= ?", new String[] { String.valueOf(nit)});
    }

}
