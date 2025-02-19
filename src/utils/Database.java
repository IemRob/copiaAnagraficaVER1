package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*questa classe ha il compito di gestire la connessione con il DataBase, ovvero il server
 * che contiene i dati salvati al suo interno
 */
public class Database {

    // proprità
    private String user;
    private String password;
    private String percorso;
    // le proprietà qui sopra servono per creare una connessione, un ponte
    // che viene gestito da un oggetto specifico che ha il compito di arrivare al
    // database e aprire la porta a cui si trova
    private Connection connection; // ho aggiunto una proprietà che è una dipendenza
    // ovvero alla classe Database per poter funzionare serve un oggetto di tipo
    // Connection

    // mi serve la classe Driver per aprire la connessione, qui sotto il percorso
    // per
    // raggiungerla all'interno della libreria
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // costruttore
    public Database(String nomeSchema) {
        this.user = "root";
        this.password = "root";
        setPercorso(nomeSchema);
    }

    // get e set
    public String getPercorso() {
        return percorso;
    }

    // con il set imposto il percorso per raggiungere il db
    public void setPercorso(String nomeSchema) {
        // creo una variabile che contenga dei comandi per utilizzare il tempo
        // universale
        String timezone = "?useSSL=false&serverTimezone=UTC";
        // contiene il percorso per arrivare al server e quindi alla porta in cui
        String url = "jdbc:mysql://localhost:3306/";
        this.percorso = url + nomeSchema + timezone;

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // metodi
    // 1. apre la connessione
    public void apriConnessione() {
        try {
            // chiamo la classe Driver, ovvero faccio si che possa prendere
            // i metodi che mi servono
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(percorso, user, password);
        } catch (ClassNotFoundException e) {
            // se non trova la classe Driver
            System.out.println("riga: 58 -> controlla il percorso per arrivare alla classe Driver"
                    + " oppure non c'è il connettore, controlla il jar");
        } catch (Exception e) {
            System.out.println("riga 59 -> controlla lo user, la password e il percorso()");
            System.out.println(e.getMessage());
        }
    }

    // 2. chiude la connessione
    public void chiudiConnessione() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("stai provando a chiudere la connessione ma ci sono degli errori");
        } catch (Exception e) {
            System.out.println("errore nella chiusura");
        }
    }

}
