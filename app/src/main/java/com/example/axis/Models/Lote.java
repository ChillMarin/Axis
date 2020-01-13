package com.example.axis.Models;

import java.util.List;

public class Lote {
    private String idLote;
    private String loteEstatus;
    private List<Transaccion> transaccions;
    private String lote_fecha;
    public Lote() {
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public String getLoteEstatus() {
        return loteEstatus;
    }

    public void setLoteEstatus(String loteEstatus) {
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
