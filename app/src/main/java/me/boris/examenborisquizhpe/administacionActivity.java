package me.boris.examenborisquizhpe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import me.boris.examenborisquizhpe.modelo.ModeloProducto;
import me.boris.examenborisquizhpe.modelo.Producto;

import java.util.List;

public class administacionActivity extends AppCompatActivity {

    private EditText txtNombre, txtStock, txtPrecio, txtDescripcion, txtSearch;
    private Button btnCrear, btnEditar, btnEliminar, btnCamara;
    private ImageView imgView;
    private TextView txtID;


    private TextWatcher textWatcher = null;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administacion);

        txtNombre = findViewById(R.id.txtNombre);
        txtStock = findViewById(R.id.txtStock);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imgView);

        btnCamara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });

        btnCrear = findViewById(R.id.btnCreate);
        btnCrear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtStock.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")) {
                    crearProducto(txtNombre.getText().toString(), txtDescripcion.getText().toString(), txtPrecio.getText().toString(), txtStock.getText().toString(), "imagen");
                    Toast.makeText(administacionActivity.this, "REGISTRO GUARDADO CON EXITO", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(administacionActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(administacionActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtSearch = findViewById(R.id.txtSearch);
        txtID = findViewById(R.id.txtID);
        textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtID.setText(txtSearch.getText().toString());

                String codigo = txtID.getText().toString();
                System.out.println(codigo);
                ModeloProducto modeloProducto = new ModeloProducto();
                List<Producto> productoList = modeloProducto.searchProducto(administacionActivity.this, codigo);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    productoList.stream().forEach(p -> {
                        String[] producto = {p.getNombre(), p.getDescripcion(), p.getPrecio() + "", p.getStock() + ""};
                        txtNombre.setText(producto[0]);
                        txtDescripcion.setText(producto[1]);
                        txtPrecio.setText(producto[2]);
                        txtStock.setText(producto[3]);
                        System.out.println(producto[0] + producto[1] + producto[2] + producto[3]);

                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        txtSearch.addTextChangedListener(textWatcher);


        btnEditar = findViewById(R.id.btnUpdate);
        btnEditar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtStock.getText().toString().equals("") && !txtPrecio.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")) {
                    editarProducto(txtID.getText().toString(), txtNombre.getText().toString(), txtDescripcion.getText().toString(), txtPrecio.getText().toString(), txtStock.getText().toString(), "imagen");
                    Toast.makeText(administacionActivity.this, "REGISTRO MODIFICIADO CON EXITO", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(administacionActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(administacionActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }

        });


        btnEliminar = findViewById(R.id.btnDelete);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtID.getText().toString().equals("")) {
                    elimiarProducto(txtID.getText().toString());
                    Toast.makeText(administacionActivity.this, "REGISTRO ELIMINADO CON EXITO", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(administacionActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(administacionActivity.this, "DEBE LLENAR EL CAMPO DE BUSQUEDA PARA ELIMINAR AL PRODUCTO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void crearProducto(String nombre, String descripcion, String precio, String stock, String imagen) {
        ModeloProducto modeloProducto = new ModeloProducto(nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(stock), null);
        modeloProducto.createProducto(administacionActivity.this);
    }

    private void editarProducto(String codigo, String nombre, String descripcion, String precio, String stock, String imagen) {
        ModeloProducto modeloProducto = new ModeloProducto(nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(stock), null);
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

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imageBitmap);
        }
    }

}