package com.guiigos.androidhelpers.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.guiigos.androidhelpers.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao
        extends SQLiteOpenHelper {

    public AlunoDao(Context context) {
        super(context, "Aluno", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Aluno (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, nota REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insere(Aluno aluno){
        try{
            SQLiteDatabase db = getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("nome", aluno.getNome());
            cv.put("endereco", aluno.getEndereco());
            cv.put("telefone", aluno.getTelefone());
            cv.put("nota", aluno.getNota());

            db.insert("Aluno", null, cv);
        }catch (Exception ex){
            Log.d("alunoDao", "insere - " + ex.getMessage());
        }
    }

    public void alterar(Aluno aluno){
        try{
            SQLiteDatabase db = getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("id", aluno.getId());
            cv.put("nome", aluno.getNome());
            cv.put("endereco", aluno.getEndereco());
            cv.put("telefone", aluno.getTelefone());
            cv.put("nota", aluno.getNota());

            String[] params = {String.valueOf(aluno.getId())};

            db.update("Aluno", cv, "id = ?", params);
        }catch (Exception ex){
            Log.d("alunoDao", "alterar - " + ex.getMessage());
        }
    }

    public void delete(Aluno aluno){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String[] params = {String.valueOf(aluno.getId()), String.valueOf(aluno.getNome())};
            db.delete("Aluno", "id = ? and nome = ?", params);
        }catch (Exception ex){
            Log.d("alunoDao", "delete - " + ex.getMessage());
        }
    }

    public List<Aluno> buscar(){
        List<Aluno> lstAlunos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Aluno";

            SQLiteDatabase db =  getWritableDatabase();
            Cursor c = db.rawQuery(sql, null);

            while (c.moveToNext()){
                Aluno aluno = new Aluno();
                aluno.setId(c.getLong(c.getColumnIndex("id")));
                aluno.setNome(c.getString(c.getColumnIndex("nome")));
                aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
                aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
                aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

                lstAlunos.add(aluno);
            }
            c.close();
        }catch (Exception ex){
            Log.d("alunoDao", "buscar - " + ex.getMessage());
        }

        return lstAlunos;
    }
}
