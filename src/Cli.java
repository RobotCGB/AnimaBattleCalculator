import java.util.ArrayList;
import java.util.Scanner;

public class Cli {

    Scanner sc = new Scanner(System.in);
    View view = new View();
    ArrayList<Personaje> personajes = new ArrayList<>();
    ArrayList<Equipo> equipos = new ArrayList<>();

    public Cli(){

    }

    public void run(){
        char opcion = 'n';

        view.showIntroduccion();
        while(opcion != 's') {
            switch (elegirUso()) {
                case 0 -> opcion = 's';
                case 1 -> empezarCombate();
                case 2 -> crearPersonaje();
                case 3 -> crearEquipo();
                case 4 -> view.showTodosPersonajes();
                case 5 -> view.showTodosEquipos();
                case 6 -> editarEquipos();
                default -> view.showError();
            }
            view.clear();
        }
    }

    private int elegirUso(){
        Scanner sc = new Scanner(System.in);
        view.showOpcionesUso();
        return sc.nextInt();
    }

    private void empezarCombate(){
        Equipo eq1 = pedirEquipoUno();
        Equipo eq2 = pedirEquipoDos();

        Combate combate = new Combate(eq1, eq2);
        combate.continuarCombate();

    }

    private Equipo pedirEquipoUno(){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        Equipo eq = null;
        while(!ok) {
            view.showMostrarYPedirEquipos();
            eq = getEquipoPorNombre(sc.nextLine());
            if(eq == null){
                view.showError();
            } else{
                for (int i = 0; i < eq.party.size(); i++) {
                    eq.getParty().get(i).setColor(Colores.AZUL);
                }
                ok = true;
            }
        }
        return eq;
    }

    private Equipo pedirEquipoDos(){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        Equipo eq = null;
        while(!ok) {
            view.showMostrarYPedirEquipos();
            eq = getEquipoPorNombre(sc.nextLine());
            if(eq == null){
                view.showError();
            } else{
                for (int i = 0; i < eq.party.size(); i++) {
                    eq.getParty().get(i).setColor(Colores.ROJO);
                }
                ok = true;
            }
        }
        return eq;
    }

    private void crearPersonaje(){

        Scanner sc = new Scanner(System.in);

        view.showPedirNombre();
        String nombre = sc.nextLine();

        view.showPedirSalud();
        int salud = sc.nextInt();

        view.showPedirHA();
        int HAbase = sc.nextInt();

        view.showPedirHD();
        int HDbase = sc.nextInt();

        view.showPedirTurno();
        int turnoBase = sc.nextInt();

        view.showPedirDano();
        int danoBase = sc.nextInt();

        Personaje pj = new Personaje(nombre,salud, HAbase, HDbase, turnoBase, danoBase);
        personajes.add(pj);

    }

    private void crearEquipo(){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        ArrayList<Personaje> party = new ArrayList<>();
        char opMasilla = 'n';

        view.showEligeNombre();
        String nombre = sc.nextLine();
        char c;
        String select = null;

        Personaje aux = null;

        do {
            sc = new Scanner(System.in);
            view.showMostrarYPedirPersonajes();
            select = sc.nextLine();
            aux = getPersonajePorNombre(select);
            if (aux != null) {
                party.add(aux);
            }
            view.showPreguntarSiMas();
            c = sc.next().toLowerCase().charAt(0);
        } while(c != 'n');

        Equipo eq = new Equipo(nombre, party);

        view.showPreguntarSiMasillas();
        opMasilla = sc.next().toLowerCase().charAt(0);

        if(opMasilla == 's'){
            while(opMasilla == 's') {
                añadirMasillas(eq);
                view.showPreguntarSiMas();
                opMasilla = sc.next().toLowerCase().charAt(0);
            }
        }

        equipos.add(eq);

    }

    public Personaje getPersonajePorNombre(String nombre){
        boolean ok = false;
        int i = 0;
        Personaje ret = null;
        while(!ok && i < personajes.size()){
            if(personajes.get(i).getNombre().equals(nombre)){
                ok = true;
                ret = personajes.get(i);
                view.showPjSelecCorrecto(ret);
            }
            i++;
        }
        if(ret == null)
            view.showError();
        return ret;
    }

    public Personaje getPersonajePorNombreSilent(String nombre){
        boolean ok = false;
        int i = 0;
        Personaje ret = null;
        while(!ok && i < personajes.size()){
            if(personajes.get(i).getNombre().equals(nombre)){
                ok = true;
                ret = personajes.get(i);
            }
            i++;
        }
        if(ret == null)
            view.showError();
        return ret;
    }

    public Equipo getEquipoPorNombre(String nombre){

        if(equipos.size() > 0) {
            boolean ok = false;
            int i = 0;
            Equipo ret = null;
            while (!ok && i < personajes.size()) {
                if (nombre.equals(equipos.get(i).getNombre())) {
                    ok = true;
                    ret = equipos.get(i);
                }
                i++;
            }
            return ret;
        } else {
            view.showError();
            return null;
        }
    }

    public void editarEquipos(){

        Scanner sc = new Scanner(System.in);

        view.showMostrarYPedirEquiposEditar();
        Equipo eqSelec = getEquipoPorNombre(sc.nextLine());
        view.showCorrectoSeleccionEquipo(eqSelec);
        view.showMostrarOpcionesEditar();
        int op = sc.nextInt();
        switch (op) {
            case 0 -> view.showSaliendo();
            case 1 -> eqSelec.añadirPjParty(pjAAñadir());
            case 2 -> eqSelec.eliminarPjParty(pjAEliminar(eqSelec));
            case 3 -> eqSelec.cambiarNombre();
            case 4 -> añadirMasillas(eqSelec);
            default -> view.showError();
        }

    }

    public Personaje pjAEliminar(Equipo eq){

        Scanner sc = new Scanner(System.in);
        String pjSelec = null;
        Personaje ret = null;

        view.showMostrarYPedirPjEliminar(eq);
        pjSelec = sc.nextLine();
        ret = eq.getPjDeParty(pjSelec);

        return ret;

    }

    public Personaje pjAAñadir(){

        Scanner sc = new Scanner(System.in);
        Personaje ret = null;
        String pjSelec = null;

        view.showMostrarYPedirPersonajes();
        pjSelec = sc.nextLine();
        ret = getPersonajePorNombre(pjSelec);

        return ret;
    }

    public void añadirMasillas(Equipo eq){

        Scanner sc = new Scanner(System.in);
        Personaje ret = null;
        String pjSelec = null;
        int veces = 0;
        boolean ok = false;
        String pjNomAux = null;
        String pjNomBase = null;

        view.showMostrarYPedirMasillas();
        pjSelec = sc.nextLine();
        ret = getPersonajePorNombreSilent(pjSelec);
        if(ret != null) {
            view.showPreguntaCantidad();
            veces = sc.nextInt();
            pjNomBase = ret.getNombre();

            for (int i = 0; i < veces; i++) {
                pjNomAux = pjNomBase + "#" + (i+1);
                eq.añadirMasillaParty(ret.clona());
                eq.setNombreIndex(i,pjNomAux);
            }
            view.showPjSelecCorrecto(getPersonajePorNombre(pjSelec));
        }
        view.showEquipoEstadoActual(eq);
    }

}
