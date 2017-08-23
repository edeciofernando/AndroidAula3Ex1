package br.com.edecio.minhalistadecompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.Scanner;

public class ListaActivity extends AppCompatActivity {

    private ListView lvCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvCompras = (ListView) findViewById(R.id.lvCompras);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);

        String filename = "minhasCompras.txt";
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);
            Scanner entrada = new Scanner(inputStream);

            // enquanto houver linhas para serem lidas
            while (entrada.hasNextLine()) {
                // lê uma linha do arquivo
                String linha = entrada.nextLine();
                // separa a linha pela ocorrência do ";"
                String[] partes = linha.split(";");
                // adiciona ao adapter
                adapter.add(partes[0]+" ("+partes[2]+")\n"+partes[1]);
            }
            // fecha o arquivo
            inputStream.close();

            // insere os dados no listView
            lvCompras.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Erro: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
