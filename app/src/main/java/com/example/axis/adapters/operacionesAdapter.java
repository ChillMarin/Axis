package com.example.axis.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.axis.Models.Lote;
import com.example.axis.Models.Transaccion;
import com.example.axis.R;

import java.util.ArrayList;

public class operacionesAdapter extends RecyclerView.Adapter<operacionesAdapter.ViewHolder>  implements View.OnClickListener{
    private int resource;
    private ArrayList<Lote> lotes;
    private View.OnClickListener listener;
    private  ArrayList<Transaccion> transaccions;
    public operacionesAdapter(ArrayList<Lote> lotes, int resource){
        this.lotes = lotes;
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
            holder.loteestatus.setText("Estatus: "+String.valueOf(lotes.get(position).getLoteEstatus()));
            holder.lotefecha.setText("Lote Fecha: " + lotes.get(position).getLote_fecha());
            holder.idlote.setText("Referencia de lote: "+String.valueOf(lotes.get(position).getIdLote()));

        }catch (Exception e){
            Log.d("Error",e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return lotes.size();
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
            private TextView ref,loteestatus,lotefecha, idlote;
            public View view;
            public ViewHolder(View view){
                super(view);
                this.view = view;
                this.ref = (TextView) view.findViewById(R.id.textViewTransacciones);
                this.loteestatus = (TextView) view.findViewById(R.id.textViewLoteEstatus);
                this.lotefecha = (TextView) view.findViewById(R.id.textViewLoteFecha);
                this.idlote = (TextView) view.findViewById(R.id.textViewIdLote);

            }
        }
}
