import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class PersonaCRUD {
    private MongoCollection<Document> collection;

    public PersonaCRUD(MongoDatabase database, String collectionName) {
        this.collection = database.getCollection(collectionName);
    }

    public boolean insertarPersona(String nombre, int edad, String ciudad) {
        try {
            Document persona = new Document("nombre", nombre)
                    .append("edad", edad)
                    .append("ciudad", ciudad);

            collection.insertOne(persona);
            System.out.println("Persona insertada exitosamente: " + nombre);
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar persona: " + e.getMessage());
            return false;
        }
    }

    public void mostrarPersonas() {
        System.out.println("Listado de personas:");
        collection.find().forEach(document -> {
            System.out.println(document.toJson());
        });
    }

    public boolean actualizarPersona(String nombreActual, String nuevoNombre) {
        try {
            collection.updateOne(
                    new Document("nombre", nombreActual),
                    new Document("$set", new Document("nombre", nuevoNombre)));

            System.out.println("Persona actualizada exitosamente.");
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar persona: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPersona(String nombre) {
        try {
            collection.deleteOne(new Document("nombre", nombre));
            System.out.println("Persona eliminada exitosamente: " + nombre);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
            return false;
        }
    }
}
