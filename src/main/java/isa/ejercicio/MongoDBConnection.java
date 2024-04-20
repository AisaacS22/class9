package isa.ejercicio;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private MongoClientSettings settings;
    private MongoDatabase database;

    public MongoDBConnection(String connectionString, String databaseName) {
        this.settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        this.database = MongoClients.create(settings).getDatabase(databaseName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
