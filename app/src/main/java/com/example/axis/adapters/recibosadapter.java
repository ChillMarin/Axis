package com.example.axis.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.axis.Models.Transaccion;
import com.example.axis.R;

import java.util.ArrayList;

public class recibosadapter extends RecyclerView.Adapter<recibosadapter.ViewHolder>  implements View.OnClickListener{
    private int resource;
    private ArrayList<Transaccion> transaccions;
    private View.OnClickListener listener;

    public recibosadapter(ArrayList<Transaccion> transaccions, int resource){
        this.transaccions = transaccions;
        this.resource = resource;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {

            holder.cedula.setText("Cédula: "+ transaccions.get(position).getIdCliente());
             holder.monto.setText("Monto: "+String.valueOf(transaccions.get(position).getMonto()));
            holder.estatus.setText("Estatus: "+transaccions.get(position).getIdEstatus());
            holder.fecha.setText("Fecha: "+String.valueOf(transaccions.get(position).getFecha()));
            holder.tarjeta.setText("Número de Tarjeta: "+String.valueOf(transaccions.get(position).getIdTarjeta()));
            holder.ref.setText(String.valueOf(transaccions.get(position).getIdTransaccion()));

        }catch (Exception e){
            Log.d("Error",e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return transaccions.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener =listener;

    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView cedula,monto,estatus,fecha,ref,tarjeta;
            public View view;
            public ViewHolder(View view){
                super(view);
                this.view = view;
                this.cedula = (TextView) view.findViewById(R.id.TextViewCedula);
                this.monto = (TextView) view.findViewById(R.id.textViewMonto);
                this.estatus = (TextView) view.findViewById(R.id.textViewEstatus);
                this.fecha = (TextView) view.findViewById(R.id.textViewFecha);
                this.ref = (TextView) view.findViewById(R.id.textViewRef);
                this.tarjeta = (TextView) view.findViewById(R.id.textViewIdTarjeta);
            }
        }
}
