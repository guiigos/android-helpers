package com.guiigos.androidhelpers.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.guiigos.androidhelpers.R;
import com.guiigos.androidhelpers.dao.AlunoDao;
import com.guiigos.androidhelpers.helper.FormularioHelper;
import com.guiigos.androidhelpers.model.Aluno;

public class FormularioActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    private FormularioHelper fh;
    private AlunoDao ad;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fh = new FormularioHelper(this);
        ad = new AlunoDao(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(intent.hasExtra("aluno")){
            Aluno aluno = (Aluno)bundle.getSerializable("aluno");
            fh.PreencheAluno(aluno);
        }

        if(intent.hasExtra("telefone")){
            String celular = bundle.getString("telefone", "");
            String corpo = bundle.getString("corpo", "");

            Aluno aluno = new Aluno();
            aluno.setNome(corpo);
            aluno.setTelefone(celular);

            Log.d("SMS_receiver", "Chegou - " + celular + " - " + corpo);

            fh.PreencheAluno(aluno);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Toast.makeText(this, "Botão gravar.", Toast.LENGTH_SHORT).show();

                Aluno aluno = fh.PegaAluno();

                if(aluno.getId() != 0) ad.alterar(aluno);
                else ad.insere(aluno);

                metodoVoltar();

                break;
            case R.id.fab_voltar:
                Snackbar.make(v, "Botão voltar.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

                metodoVoltar();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Snackbar.make(toolbar, "Utilize o botão voltar!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    @Override
    protected void onDestroy() {
        ad.close();
        super.onDestroy();
    }

    public void metodoVoltar(){
        Intent itVoltar = new Intent(FormularioActivity.this, MainActivity.class);
        startActivity(itVoltar);
        finish();
    }
}
