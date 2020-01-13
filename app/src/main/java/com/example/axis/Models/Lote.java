package com.example.axis.Models;

import java.util.List;

public class Lote {
    private int idLote;
    private int loteEstatus;
    private List<Transaccion> transaccions;
    private String lote_fecha;
    public Lote() {
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getLoteEstatus() {
        return loteEstatus;
    }

    public void setLoteEstatus(int loteEstatus) {
        this.loteEstatus = loteEstatus;
    }

    public List<Transaccion> getTransaccions() {
        return transaccions;
    }

    public void setTransaccions(List<Transaccion> transaccions) {
        this.transaccions = transaccions;
    }

    public String getLote_fecha() {
        return lote_fecha;
    }

    public void setLote_fecha(String lote_fecha) {
        this.lote_fecha = lote_fecha;
    }
}
