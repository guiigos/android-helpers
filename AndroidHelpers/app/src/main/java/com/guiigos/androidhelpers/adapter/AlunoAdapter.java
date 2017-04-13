package com.guiigos.androidhelpers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guiigos.androidhelpers.R;
import com.guiigos.androidhelpers.model.Aluno;

import java.util.List;

public class AlunoAdapter
        extends BaseAdapter {

    private Context context;
    private List<Aluno> lstAlunos;

    public AlunoAdapter(Context context, List<Aluno> lstAlunos) {
        this.context = context;
        this.lstAlunos = lstAlunos;
    }

    @Override
    public int getCount() {
        return lstAlunos.size();
    }

    @Override
    public Object getItem(int position) {
        return lstAlunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lstAlunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = lstAlunos.get(position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.component_adapter_alunos, null);

        TextView tvNome = (TextView)view.findViewById(R.id.nome);
        tvNome.setText(aluno.getNome());

        TextView tvEndereco = (TextView)view.findViewById(R.id.endereco);
        tvEndereco.setText(aluno.getEndereco());

        return view;
    }
}
