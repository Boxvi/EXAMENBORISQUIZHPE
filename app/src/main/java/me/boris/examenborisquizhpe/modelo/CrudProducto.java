package me.boris.examenborisquizhpe.modelo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public interface CrudProducto {

    public void createProducto(Context context);

    public ArrayList<Producto> readProducto(Context context);

    public void updateProducto(Context context, String codigo);

    public void deleteProducto(Context context, String codigo);

    public List<Producto> searchProducto(Context context, String codigo);

    public byte[] readImagenProducto(String file);
}
