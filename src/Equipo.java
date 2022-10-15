import java.util.ArrayList;

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

    public void mostrarParty(){
        for(int i = 0; i < party.size(); i++){
            System.out.println(party.get(i).getNombre());
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
        return ret;
    }

    public void añadirPjParty(Personaje pjAñadir){
        party.add(pjAñadir);
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

    }

}
