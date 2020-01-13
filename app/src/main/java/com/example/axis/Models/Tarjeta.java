package com.example.axis.Models;

public class Tarjeta {
    private String idTarjeta;
    private String idCliente;
    private String numerodecuenta;
    private String ccv;
    private String tipodecuenta;
    private String fecha;

    public Tarjeta() {
    }

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumerodecuenta() {
        return numerodecuenta;
    }

    public void setNumerodecuenta(String numerodecuenta) {
        this.numerodecuenta = numerodecuenta;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }



    public String getTipodecuenta() {
        return tipodecuenta;
    }

    public void setTipodecuenta(String tipodecuenta) {
        this.tipodecuenta = tipodecuenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
