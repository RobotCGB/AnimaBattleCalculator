import java.util.ArrayList;
import java.util.Scanner;

public class Equipo {
    String nombre;
    ArrayList<Personaje> party;
    View view = new View();

    public Equipo(String nombre, ArrayList<Personaje> party){
        this.nombre = nombre;
        this.party = party;
    }

    public Equipo(Equipo party1, Equipo party2){
        ArrayList<Personaje> aux1 = party1.getParty();
        ArrayList<Personaje> aux2 = party2.getParty();
        ArrayList<Personaje> aux3 = new ArrayList<>();
        aux3.addAll(aux1);
        aux3.addAll(aux2);
        this.party = aux3;
        this.nombre = "auxDosEquipos";
    }



    public Equipo(){
        this.nombre = "vacio";
        this.party = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Personaje> getParty() {
        return party;
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
        view.showEquipoEstadoActual(this);

        return ret;
    }

    public void anadirPjParty(Personaje pjAnadir){
        party.add(pjAnadir);
        view.showEquipoEstadoActual(this);
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
        view.showEquipoEstadoActual(this);

    }

    public void cambiarNombre(){

        Scanner sc = new Scanner(System.in);
        String nombre = null;

        view.showPedirNombre();
        setNombre(sc.nextLine());

        view.showNombreEstadoActual(this.nombre);


    }

    public void anadirMasillaParty(Personaje pjAñadir){
        party.add(pjAñadir);
    }

    public int getPjsEnParty(){
        return party.size();
    }

    public void setNombreIndex(int i, String nom){
        party.get(i).setNombre(nom);
    }

    public Equipo clona(){
        return new Equipo(this.getNombre(), this.getParty());
    }

}
