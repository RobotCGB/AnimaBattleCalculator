import java.io.*;
import java.util.ArrayList;

public class Serializar {

    String nomFilePj = "./saveAnimaPj.dat";
    String nomFileEq = "./saveAnimaEq.dat";

    public Serializar() {

    }

    public void serializarPj(ArrayList<Personaje> pj) throws FileNotFoundException {
        try {
            FileOutputStream file = new FileOutputStream(nomFilePj);
            ObjectOutputStream objStr = new ObjectOutputStream(file);
            objStr.writeObject(pj);

        } catch (IOException e) {

        }
    }

    public ArrayList<Personaje> deSerializarPj() throws FileNotFoundException {
        try {
            FileInputStream file = new FileInputStream(nomFilePj);
            ObjectInputStream objStr = new ObjectInputStream(file);
            return (ArrayList<Personaje>) objStr.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<Personaje>();
    }

    public void serializarEq(ArrayList<Equipo> eq) throws FileNotFoundException {
        try {
            FileOutputStream file = new FileOutputStream(nomFileEq);
            ObjectOutputStream objStr = new ObjectOutputStream(file);
            objStr.writeObject(eq);

        } catch (IOException e) {

        }
    }

    public ArrayList<Equipo> deSerializarEq() throws FileNotFoundException {
        try {
            FileInputStream file = new FileInputStream(nomFileEq);
            ObjectInputStream objStr = new ObjectInputStream(file);
            return (ArrayList<Equipo>) objStr.readObject();

        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<Equipo>();
    }

}
