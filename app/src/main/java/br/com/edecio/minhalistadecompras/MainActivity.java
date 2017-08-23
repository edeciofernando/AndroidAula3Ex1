package br.com.edecio.minhalistadecompras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spnLocal;
    private EditText edtProduto;
    private EditText edtQuant;
    private Button btnSalvar;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnLocal = (Spinner) findViewById(R.id.spnLocal);
        edtProduto = (EditText) findViewById(R.id.edtProduto);
        edtQuant = (EditText) findViewById(R.id.edtQuant);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnListar = (Button) findViewById(R.id.btnListar);

        btnSalvar.setOnClickListener(this);
        btnListar.setOnClickListener(this);

// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.locais, android.R.layout.simple_spinner_item);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);

        adapter.add("Treichel Macromercado");
        adapter.add("Feira da Dom Joaquim");
        adapter.add("Krollow");
        adapter.add("Atacadão");

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spnLocal.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSalvar) {
            String produto = edtProduto.getText().toString();
            String local = spnLocal.getSelectedItem().toString();
            String quant = edtQuant.getText().toString();

            if (produto.trim().isEmpty() || quant.trim().isEmpty()) {
                Toast.makeText(this, "Preencha os dados", Toast.LENGTH_SHORT).show();
                edtProduto.requestFocus();
                return;
            }

            String filename = "minhasCompras.txt";
            FileOutputStream outputStream;

            try {
                // MODE_APPEND: adiciona dados ao arquivo
                outputStream = openFileOutput(filename, Context.MODE_APPEND);
                outputStream.write((produto+";"+local+";"+quant+"\n").getBytes());
                outputStream.close();
                Toast.makeText(this, "Ok! Produto Cadastrado", Toast.LENGTH_SHORT).show();
                edtProduto.setText("");
                edtQuant.setText("");
                spnLocal.setSelection(0);
                edtProduto.requestFocus();
            } catch (Exception e) {
                Toast.makeText(this, "Erro: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Intent intent = new Intent(this, ListaActivity.class);
            startActivity(intent);
        }
    }
}
