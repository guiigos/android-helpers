package com.guiigos.androidhelpers.herlper;

import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.RatingBar;

import com.guiigos.androidhelpers.R;
import com.guiigos.androidhelpers.activity.FormularioActivity;
import com.guiigos.androidhelpers.model.Aluno;

public class FormularioHelper {

    private long id;
    private EditText edNome;
    private EditText edEndereco;
    private EditText edTelefone;
    private RatingBar rtNota;
    private FloatingActionButton fab;
    private FloatingActionButton fabVoltar;

    public FormularioHelper(FormularioActivity activity) {
        id = 0;

        edNome = (EditText)activity.findViewById(R.id.et_nome);
        edEndereco = (EditText)activity.findViewById(R.id.et_endereco);
        edTelefone = (EditText)activity.findViewById(R.id.et_telefone);
        rtNota = (RatingBar)activity.findViewById(R.id.rb_pontos);

        fab = (FloatingActionButton)activity.findViewById(R.id.fab);
        fabVoltar = (FloatingActionButton)activity.findViewById(R.id.fab_voltar);

        fab.setOnClickListener(activity);
        fabVoltar.setOnClickListener(activity);
    }

    public Aluno PegaAluno(){
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(String.valueOf(edNome.getText()));
        aluno.setEndereco(String.valueOf(edEndereco.getText()));
        aluno.setTelefone(String.valueOf(edTelefone.getText()));
        aluno.setNota(Double.valueOf(rtNota.getProgress()));
        return aluno;
    }

    public void PreencheAluno(Aluno aluno) {
        id = aluno.getId();
        edNome.setText(aluno.getNome());
        edEndereco.setText(aluno.getEndereco());
        edTelefone.setText(aluno.getTelefone());
        rtNota.setProgress((int)aluno.getNota());
    }
}
