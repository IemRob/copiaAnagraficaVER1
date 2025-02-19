package models;

import java.time.LocalDate;

public class Persona extends Entity{

    //proprietà
    private String nome;
    private String cognome;
    private LocalDate data_di_nascita;
    private String residenza;
    private char genere;
    private int altezza;
    
    public Persona(){}

    public Persona(int id, String nome, String cognome, LocalDate data_di_nascita, String residenza, char genere,
            int altezza) {
        super(id);
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita = data_di_nascita;
        this.residenza = residenza;
        this.genere = genere;
        this.altezza = altezza;
    }

    // get e set

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(LocalDate data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public char getGenere() {
        return genere;
    }

    public void setGenere(char genere) {
        this.genere = genere;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    @Override
    public String toString() {
        return "Persona [nome=" + nome + ", cognome=" + cognome + ", data_di_nascita=" + data_di_nascita
                + ", residenza=" + residenza + ", genere=" + genere + ", altezza=" + altezza + "]";
    }



    
    

}
