import java.util.ArrayList;
import java.util.Scanner;

public class Equipo {
    String nombre;
    ArrayList<Personaje> party;

    public Equipo(String nombre, ArrayList<Personaje> party){
        this.nombre = nombre;
        this.party = party;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarParty(){
        for(int i = 0; i < party.size(); i++){
            System.out.print(party.get(i).getNombre() + ", ");
        }
    }

    public Personaje getPjDeParty(String pj){
        Personaje ret = null;
        boolean ok = false;
        int i = 0;
        while (!ok && i < party.size()){
            if(party.get(i).getNombre().equals(pj)) {
                ret = party.get(i);
                ok = true;
            }
            i++;
        }
        System.out.println("\nAhora el equipo esta así: ");
        mostrarParty();
        System.out.println();
        return ret;
    }

    public void añadirPjParty(Personaje pjAñadir){
        party.add(pjAñadir);
        System.out.println("\nAhora el equipo esta así: ");
        mostrarParty();
        System.out.println();
    }

    public void eliminarPjParty(Personaje pjElim){
        boolean ok = false;
        int i = 0;
        while(!ok && i < party.size()){
            if(party.get(i).equals(pjElim)){
                party.remove(i);
            }
            i++;
        }
        System.out.println("Ahora el equipo esta así: ");
        mostrarParty();

    }

    public void cambiarNombre(){

        Scanner sc = new Scanner(System.in);
        String nombre = null;

        System.out.println("Escribe el nombre nuevo: ");
        setNombre(sc.nextLine());

        System.out.println("Ahora el nombre es este: " + getNombre() + "\n");


    }

    public void añadirMasillaParty(Personaje pjAñadir){
        party.add(pjAñadir);
    }


}
