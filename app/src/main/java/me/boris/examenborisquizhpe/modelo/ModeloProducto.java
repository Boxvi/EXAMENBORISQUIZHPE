package me.boris.examenborisquizhpe.modelo;

import android.content.Context;
import android.database.Cursor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloProducto extends Producto implements CrudProducto {

    DBHelper dbHelper;

    public ModeloProducto() {
    }

    public ModeloProducto(String nombre, String descripcion, double precio, int stock, byte[] imagen) {
        super(nombre, descripcion, precio, stock, imagen);
    }

    @Override
    public void createProducto(Context context) {
        dbHelper = new DBHelper(context);
        String nsql = "INSERT INTO productos (nombre, descripcion, precio, stock,fecha,imagen) " +
                "values ('" + getNombre() + "','" + getDescripcion() + "','" + getPrecio() + "','" + getStock() + "', DATE('now'),'" + getImagen() + "')";
        System.out.println(nsql);
        dbHelper.noQuery(nsql);
        dbHelper.close();

    }

    @Override
    public ArrayList<Producto> readProducto(Context context) {
        dbHelper = new DBHelper(context);
        String sql = "SELECT * FROM productos";

        Cursor cursor = dbHelper.query(sql);

        ArrayList<Producto> productoArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Producto producto = new Producto();
            producto.setCodigo(cursor.getString(0));
            producto.setNombre(cursor.getString(1));
            producto.setDescripcion(cursor.getString(2));
            producto.setPrecio(cursor.getDouble(3));
            producto.setStock(cursor.getInt(4));
            producto.setFecha(cursor.getString(5));
            producto.setImagen(cursor.getBlob(6));
            productoArrayList.add(producto);
        }
        dbHelper.close();
        System.out.println();
        return productoArrayList;
    }

    @Override
    public void updateProducto(Context context, String codigo) {
        dbHelper = new DBHelper(context);
        String nsql = "UPDATE productos set nombre = '" + getNombre() + "', descripcion  = '" + getDescripcion() + "' , precio = '" + getPrecio() + "', stock= '" + getStock() + "', fecha = DATE('now'),  imagen = '" + getImagen() + "' where codigo='" + codigo + "'";
        dbHelper.noQuery(nsql);
        dbHelper.close();
    }

    @Override
    public void deleteProducto(Context context, String codigo) {
        dbHelper = new DBHelper(context);
        String nsql = "DELETE FROM productos where codigo='" + codigo + "'";
        dbHelper.noQuery(nsql);
        dbHelper.close();
    }

    @Override
    public List<Producto> searchProducto(Context context, String codigo) {
        dbHelper = new DBHelper(context);
        String sql = "SELECT * FROM productos where codigo='" + codigo + "'";

        Producto producto = null;
        Cursor cursor = dbHelper.query(sql);

        ArrayList<Producto> productoArrayList = new ArrayList<>();

        while (cursor.moveToFirst()) {
            producto = new Producto();
            producto.setCodigo(cursor.getString(0));
            producto.setNombre(cursor.getString(1));
            producto.setDescripcion(cursor.getString(2));
            producto.setPrecio(cursor.getDouble(3));
            producto.setStock(cursor.getInt(4));
            producto.setFecha(cursor.getString(5));
            producto.setImagen(cursor.getBlob(6));
            productoArrayList.add(producto);
        }
        dbHelper.close();
        return productoArrayList;
    }

    @Override
    public byte[] readImagenProducto(String file) {

        ByteArrayOutputStream baos = null;

        try {
            File imgFile = new File(file);
            FileInputStream fis = new FileInputStream(imgFile);
            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1; ) {
                baos.write(buffer, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos != null ? baos.toByteArray() : null;
    }
}
