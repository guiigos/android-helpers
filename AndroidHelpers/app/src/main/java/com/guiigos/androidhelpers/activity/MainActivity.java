package com.guiigos.androidhelpers.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.guiigos.androidhelpers.R;
import com.guiigos.androidhelpers.adapter.AlunoAdapter;
import com.guiigos.androidhelpers.dao.AlunoDao;
import com.guiigos.androidhelpers.model.Aluno;

import java.util.List;

public class MainActivity
        extends AppCompatActivity {

    private ListView lv;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        lv = (ListView)findViewById(R.id.lv_lista);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = new Aluno();
                aluno = (Aluno)lv.getItemAtPosition(position);

                Intent itCadastro = new Intent(MainActivity.this, FormularioActivity.class);
                itCadastro.putExtra("aluno", aluno);
                startActivity(itCadastro);

                finish();
            }
        });

        registerForContextMenu(lv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.adicionar:
                adicionar();
                break;
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem excluir = menu.add("Deletar");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                final Aluno aluno = (Aluno)lv.getItemAtPosition(info.position);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.confirmacao);
                builder.setMessage(R.string.deseja_excluir_o_aluno);
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlunoDao dao = new AlunoDao(MainActivity.this);
                        dao.delete(aluno);
                        dao.close();

                        carregarLista();
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                    }
                });
                alert = builder.create();
                alert.show();

                return true;
            }
        });
    }

    protected void adicionar(){
        Intent  itCadastro = new Intent(MainActivity.this, FormularioActivity.class);
        startActivity(itCadastro);

        finish();
    }

    protected void carregarLista(){
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> lstAluno = dao.buscar();
        dao.close();

        //ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstAluno);
        AlunoAdapter adapter = new AlunoAdapter(this, lstAluno);
        lv.setAdapter(adapter);
    }
}
