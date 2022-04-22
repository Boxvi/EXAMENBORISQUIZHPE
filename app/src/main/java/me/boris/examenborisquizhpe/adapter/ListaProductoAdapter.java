package me.boris.examenborisquizhpe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.boris.examenborisquizhpe.R;
import me.boris.examenborisquizhpe.modelo.Producto;

import java.util.ArrayList;

public class ListaProductoAdapter extends RecyclerView.Adapter<ListaProductoAdapter.ProductoViewHolder> {

    ArrayList<Producto> productoArrayList;
    ArrayList<Producto> productoArrayListOriginal;

    public ListaProductoAdapter(ArrayList<Producto> productoArrayList) {
        this.productoArrayList = productoArrayList;
        productoArrayListOriginal = new ArrayList<>();
        productoArrayListOriginal.addAll(productoArrayList);
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null, false);
        return new ProductoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewID.setText(String.valueOf(productoArrayList.get(position).getCodigo()));
        holder.viewNombre.setText(productoArrayList.get(position).getNombre());
        holder.viewPrecio.setText("\t\t" + productoArrayList.get(position).getPrecio() + "$");
        holder.viewStock.setText("Stock: " + productoArrayList.get(position).getStock());
        holder.viewDescripcion.setText(productoArrayList.get(position).getDescripcion());
        holder.viewID2.setText(String.valueOf(productoArrayList.get(position).getCodigo()));
        holder.viewFecha.setText(String.valueOf(productoArrayList.get(position).getFecha()));
    }

    @Override
    public int getItemCount() {
        return productoArrayList.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView viewID, viewID2, viewNombre, viewFecha, viewStock, viewPrecio, viewDescripcion;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewID = itemView.findViewById(R.id.viewID);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewStock = itemView.findViewById(R.id.viewStock);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);
            viewID2 = itemView.findViewById(R.id.viewID2);
            viewFecha = itemView.findViewById(R.id.viewFecha);


        }
    }
}
