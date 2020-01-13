package com.example.axis.Models;

public class Persona {
    private String idPersona;
    private String personaCedula;

    public Persona() {
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getPersonaCedula() {
        return personaCedula;
    }

    public void setPersonaCedula(String personaCedula) {
        this.personaCedula = personaCedula;
    }

    public String getPersonaTipo() {
        return personaTipo;
    }

    public void setPersonaTipo(String personaTipo) {
        this.personaTipo = personaTipo;
    }

    private String personaTipo;
}
