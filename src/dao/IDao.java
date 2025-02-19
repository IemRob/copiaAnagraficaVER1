package dao;

import java.util.List;

import models.Entity;
import models.Persona;

public interface IDao {
     
    //firme dei metodi CRUD
    //C -> CREATE
    void addPersona(Entity persona); //permetterà di aggiungere una persona come entità nel db 
    //R -> READ leggo i dati della tabella persone
    List<Persona> listaPersone();
    //Read di una sola persona
    Persona personaById(int id);
    //U -> UPDATE
    void updatePersona(Persona p);
    //D -> DELETE
    void delete(int id);
}
