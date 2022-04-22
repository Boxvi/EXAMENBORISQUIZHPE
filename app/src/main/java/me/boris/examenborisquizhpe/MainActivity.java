package me.boris.examenborisquizhpe;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.boris.examenborisquizhpe.adapter.ListaProductoAdapter;
import me.boris.examenborisquizhpe.modelo.ModeloProducto;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listProducts;
    private ListaProductoAdapter listaProductoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listProducts = findViewById(R.id.listProductos);
        listProducts.setLayoutManager(new LinearLayoutManager(this));

        ModeloProducto modeloProducto = new ModeloProducto();

        listaProductoAdapter = new ListaProductoAdapter(modeloProducto.readProducto(MainActivity.this));
        listProducts.setAdapter(listaProductoAdapter);

    }

    public void irAdministracion(View view) {
        Intent intent = new Intent(this, administacionActivity.class);
        startActivity(intent);
        finish();
    }
}