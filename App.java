import java.time.LocalDate;

import dao.DaoPersona;
import dao.IDao;
import models.Entity;
import models.Persona;

public class App {
    public static void main(String[] args) throws Exception {
        IDao daoPersona = new DaoPersona("anagrafica");

        /*Entity persona = new Persona(0, "Mario", "Alighieri", 
                            LocalDate.of(1996, 03, 04), 
                            "Venezia", 'M', 179);

        daoPersona.addPersona(persona);*/
        //System.out.println(daoPersona.personaById(9));
        //daoPersona.delete(11);
        //System.out.println(daoPersona.listaPersone());
        Persona p = daoPersona.personaById(10);
        System.out.println(daoPersona.personaById(10));
        //cambio lo stato dell'oggetto persona dal punto di vista Java ma non dal punto di vista del server, del db
        p.setCognome("Maradona");
        p.setData_di_nascita(LocalDate.parse("1999-05-03"));

        //se voglio apportare i cambiamenti in modo permanente devo modificarli anche sul db
        daoPersona.updatePersona(p);
        System.out.println(daoPersona.personaById(10));
    }
}
