package me.boris.examenborisquizhpe;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import me.boris.examenborisquizhpe.modelo.ModeloProducto;

public class administacionActivity extends AppCompatActivity {

    private EditText txtNombre, txtStock, txtPrecio, txtDescripcion, txtSearch;
    private Button btnCrear, btnEditar, btnEliminar;
    private TextView txtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administacion);

        txtNombre = findViewById(R.id.txtNombre);
        txtStock = findViewById(R.id.txtStock);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        btnCrear = findViewById(R.id.btnCreate);
        btnCrear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtStock.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")) {
                    crearProducto(txtNombre.getText().toString(), txtDescripcion.getText().toString(), txtPrecio.getText().toString(), txtStock.getText().toString(), "imagen");
                    Toast.makeText(administacionActivity.this, "REGISTRO GUARDADO CON EXITO", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(administacionActivity.this, administacionActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(administacionActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void crearProducto(String nombre, String descripcion, String precio, String stock, String imagen) {
        ModeloProducto modeloProducto = new ModeloProducto(nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(stock), null);
        modeloProducto.createProducto(administacionActivity.this);
    }

    private void editarProducto(String codigo, String nombre, String descripcion, double precio, int stock, String imagen) {
        ModeloProducto modeloProducto = new ModeloProducto(nombre, descripcion, precio, stock, null);
        modeloProducto.updateProducto(administacionActivity.this, codigo);
    }


    private void elimiarProducto(String codigo) {
        ModeloProducto modeloProducto = new ModeloProducto();
        modeloProducto.deleteProducto(administacionActivity.this, codigo);
    }

    public void irLista(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}