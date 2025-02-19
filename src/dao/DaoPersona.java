package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Entity;
import models.Persona;
import utils.Database;

//DAO: Data Access Object nello specifico di oggetti di tipo Persona
//ha il compito di accedere ai dati delle persone e manipolarli
public class DaoPersona implements IDao {

    // proprietà
    // dipendenza che permette alla classe DaoPersona di funzionare e avere le info
    // necessarie
    private Database databse; // al DAO serve un oggetto di tipo Database per leggere i dati sul db e
                              // inviargli i comandi CUD

    // costruttore
    public DaoPersona(String nomeSchema) {
        databse = new Database(nomeSchema);
    }

    // metodi
    @Override
    public void addPersona(Entity persona) {
        // per aggiungere una persona nel db, nella tabella persone la connessione deve
        // essere aperta
        databse.apriConnessione();
        String comando = "INSERT INTO persone (nome, cognome, data_di_nascita, residenza, genere, altezza) " +
                "values (?,?,?,?,?,?)";

        // uso l'oggetto PreparedStatement per inviare il comando al server, al db
        // e CONTROLLARE che tutti i valori e i nomi delle colonne, il nome della
        // tabella siano corrette
        // che tutta la query sia corretta
        try {

            PreparedStatement ps = databse.getConnection().prepareStatement(comando);
            // uso il preparedstatement per cambiare i segnaposto con i valori delle
            // proprietà
            // dell'oggetto persona preso in input
            // cosi che possa controllarli
            if (persona instanceof Persona p) { // Entity persona = new Persona()
                ps.setString(1, p.getNome());
                ps.setString(2, p.getCognome());
                ps.setDate(3, Date.valueOf(p.getData_di_nascita()));
                ps.setString(4, p.getResidenza());
                ps.setString(5, String.valueOf(p.getGenere()));
                ps.setInt(6, p.getAltezza());

                // "INSERT INTO persone (nome, cognome, data_di_nascita, residenza, genere,
                // altezza)"
                // "values ("Marco", "Verdi", "1980-09-07", "Milano",?,?)"
                ps.executeUpdate();
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("sono nel catch di addPersona");
        } finally {
            databse.chiudiConnessione();
        }

    }

    // ORM: Object Relational Mapping
    @Override
    public List<Persona> listaPersone() {
        // apro la connessione
        databse.apriConnessione();
        List<Persona> ris = new ArrayList<>();

        // query da inviare al db
        String query = "SELECT * FROM persone";

        // la query viene gestita dal PreparedStatement che la controlla e la invia al
        // db
        try {
            PreparedStatement ps = databse.getConnection().prepareStatement(query);
            // chiedo a ps di portare la query al db e fargliela eseguire

            ResultSet rs = ps.executeQuery(); // una volta eseguita ottengo un oggetto
            // ResultSet che è un oggetto in cui i dati sono salvati in formato tabellare
            // in Java per operare sugli oggetti ho bisogno di trasformare ogni record della
            // tabella
            // in un ogetti di tipo Persona
            // ogni record salvato in ResultSet deve diventare una Persona

            while (rs.next()) {// ad ogni iterazione in rs ci sarà un record, una riga della tabella
                // man mano che l'iterazione va avanti scorro le righe una per volta
                // prendo una riga, i cui dati corrispondono alla tabella generata dal server
                // per cui le colonne e i dati associati rispecchiano la tabella in sql
                // quei dati li passerò al costruttore di Persona per creare un oggetto
                Persona p = new Persona(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getString(5),
                        rs.getString(6).charAt(0), // prendo il primo char (indice 0)
                        // del contenuto della colonna 6, quindi genere
                        rs.getInt(7));
                if (p != null)
                    ris.add(p);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databse.chiudiConnessione();
        }
        return ris;
    }

    @Override
    public Persona personaById(int id) {

        databse.apriConnessione();
        String query = "SELECT * FROM persone WHERE id = ?";

        Persona p = null;

        try {
            PreparedStatement ps = databse.getConnection().prepareStatement(query);

            // sostituisco il place holder con il valore dell'id passato come input
            ps.setInt(1, id);
            // a questo punto la query dovrebbe essere:
            // SELECT * FROM persone WHERE id = 5
            // ps.toString(); in una stampa permette di vedere il contenuto della query
            // modificata

            // eseguo la query
            ResultSet rs = ps.executeQuery();
            // questa tabella conterrà im spòp recprd, quello associato all'id indicato
            if (rs.next()) {
                p = new Persona(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getString(5),
                        rs.getString(6).charAt(0), // prendo il primo char (indice 0)
                        // del contenuto della colonna 6, quindi genere
                        rs.getInt(7));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databse.chiudiConnessione();
        }
        return p;

    }

    @Override
    public void updatePersona(Persona p) {
        databse.apriConnessione();
        String comando = "UPDATE persone SET nome = ?, cognome = ?, data_di_nascita = ?, residenza = ?,\n" + //
                        "genere = ?, altezza = ? WHERE id = ?";

        try {
            //database.getConnection() -> ritorna un oggetto di tipo Connection c
            //c..prepareStatement(comando) -> ritorna un oggetto di tipo PreparedStatement
            //a cui ho passato un comando, un'istruzione da controllare e nel caso
            //settare se trova ? cioè dei place holder
            PreparedStatement ps = databse.getConnection().prepareStatement(comando);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCognome());
            ps.setDate(3, Date.valueOf(p.getData_di_nascita()));;
            ps.setString(4, p.getResidenza());
            ps.setString(5, String.valueOf(p.getGenere()));
            ps.setInt(6, p.getAltezza());
            ps.setInt(7, p.getId());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            databse.chiudiConnessione();
        }
    }

    @Override
    public void delete(int id) {
        databse.apriConnessione();
        String comando = "DELETE FROM persone WHERE id = ?";
        try {
            PreparedStatement ps = databse.getConnection().prepareStatement(comando);

            ps.setInt(1, id);
            // DELETER FROM persone WHERE id = 2;
            int righe = ps.executeUpdate();
            System.out.println("righe modificate: " + righe);

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databse.chiudiConnessione();
        }
    }
}
