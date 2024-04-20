package isa.ejercicio;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        MongoDBConnection dbConnection = new MongoDBConnection("mongodb://localhost:27017", "miBaseDatos");
        PersonaCRUD personaCRUD = new PersonaCRUD(dbConnection.getDatabase(), "personas");

    }
}
