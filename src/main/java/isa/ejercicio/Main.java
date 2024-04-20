package isa.ejercicio;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        MongoDBConnection dbConnection = new MongoDBConnection("mongodb://localhost:27017", "miBaseDatos");
        PersonaCRUD personaCRUD = new PersonaCRUD(dbConnection.getDatabase(), "personas");

    }
}
//https://drive.google.com/file/d/1yRRGoampkmqLdNuYzA2dFGrK8ine7M1P/view?usp=drive_link
