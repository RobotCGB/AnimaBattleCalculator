import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cli {

    Scanner sc = new Scanner(System.in);
    View view = new View();
    ArrayList<Personaje> personajes;
    ArrayList<Equipo> equipos;

    public Cli() {
        this.personajes = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    public Cli(ArrayList<Personaje> personajes, ArrayList<Equipo> equipos) {
        this.personajes = personajes;
        this.equipos = equipos;
    }

    public void run(){
        char opcion = 'n';

        view.showIntroduccion();
        while (opcion != 's') {
            switch (elegirUso()) {
                case 0 -> opcion = 's';
                case 1 -> empezarCombate();
                case 2 -> crearPersonaje();
                case 3 -> crearEquipo();
                case 4 -> view.showTodosPersonajes(personajes);
                case 5 -> view.showTodosEquipos(equipos);
                case 6 -> editarEquipos();
                case 7 -> editarPj();
                default -> view.showError();
            }
            view.clear();
        }
    }

    private int elegirUso() {
        Scanner sc = new Scanner(System.in);
        view.showOpcionesUso();
        return sc.nextInt();
    }

    private void empezarCombate() {
        Equipo eq1 = pedirEquipoUno();
        Equipo eq2 = pedirEquipoDos();

        Combate combate = new Combate(eq1, eq2);
        combate.continuarCombate();

    }

    private Equipo pedirEquipoUno() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        Equipo eq = null;
        Equipo eqAux = null;
        while (!ok) {
            do {
                view.showMostrarYPedirEquipos(equipos);
                eqAux = getEquipoPorNombre(sc.nextLine());
                if(eqAux == null)
                    view.showErrorEqNoEncontrado();
            } while(eqAux == null);
            eq = eqAux.clona();
            if (eq == null) {
                view.showError();
            } else {
                for (int i = 0; i < eq.party.size(); i++) {
                    eq.getParty().get(i).setColor(Colores.AZUL);
                }
                ok = true;
            }
        }
        return eq;
    }

    private Equipo pedirEquipoDos() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        Equipo eq = null;
        Equipo eqAux = null;
        while (!ok) {
            do {
                view.showMostrarYPedirEquipos(equipos);
                eqAux = getEquipoPorNombre(sc.nextLine());
                if(eqAux == null)
                    view.showErrorEqNoEncontrado();
            } while(eqAux == null);
            eq = eqAux.clona();
            if (eq == null) {
                view.showError();
            } else {
                for (int i = 0; i < eq.party.size(); i++) {
                    eq.getParty().get(i).setColor(Colores.ROJO);
                }
                ok = true;
            }
        }
        return eq;
    }

    private void crearPersonaje() {

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

        personajes.add(new Personaje(nombre, salud, HAbase, HDbase, turnoBase, danoBase));

    }

    private void crearEquipo() {
        Scanner sc = new Scanner(System.in);

        Equipo eq = new Equipo(preguntarNombre(), preguntarParty());

        preguntarMasilla(eq);

        equipos.add(eq);

    }

    public String preguntarNombre(){
        view.showEligeNombre();
        return sc.nextLine();
    }

    public ArrayList<Personaje> preguntarParty(){

        ArrayList<Personaje> party = new ArrayList<>();
        String select = null;
        Personaje aux = null;
        char c;

        do {
            sc = new Scanner(System.in);
            view.showMostrarYPedirPersonajes(personajes);
            select = sc.nextLine();
            aux = getPersonajePorNombre(select);
            if (aux != null) {
                party.add(aux);
            }
            view.showPreguntarSiMas();
            c = sc.next().toLowerCase().charAt(0);
        } while (c != 'n');
        return party;
    }

    public void preguntarMasilla(Equipo eq){
        char opMasilla;
        view.showPreguntarSiMasillas();
        opMasilla = sc.next().toLowerCase().charAt(0);

        if (opMasilla == 's') {
            while (opMasilla == 's') {
                anadirMasillas(eq);
                view.showPreguntarSiMas();
                opMasilla = sc.next().toLowerCase().charAt(0);
            }
        }
    }

    public Personaje getPersonajePorNombre(String nombre) {
        boolean ok = false;
        int i = 0;
        Personaje ret = null;
        while (!ok && i < personajes.size()) {
            if (personajes.get(i).getNombre().equals(nombre)) {
                ok = true;
                ret = personajes.get(i);
                view.showPjSelecCorrecto(ret);
            }
            i++;
        }
        if (ret == null)
            view.showError();
        return ret;
    }

    public Personaje getPersonajePorNombreSilent(String nombre) {
        boolean ok = false;
        int i = 0;
        Personaje ret = null;
        while (!ok && i < personajes.size()) {
            if (personajes.get(i).getNombre().equals(nombre)) {
                ok = true;
                ret = personajes.get(i);
            }
            i++;
        }
        if (ret == null)
            view.showError();
        return ret;
    }

    public Equipo getEquipoPorNombre(String nombre) {

        if (equipos.size() > 0) {
            boolean ok = false;
            int i = 0;
            Equipo ret = null;
            while (!ok && i < equipos.size()) {
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

    public void editarEquipos() {

        Scanner sc = new Scanner(System.in);

        view.showMostrarYPedirEquiposEditar(equipos);
        String op = sc.nextLine();
        Equipo eqSelec = getEquipoPorNombre(op);
        if (eqSelec != null) {

            view.showCorrectoSeleccionEquipo(eqSelec);
            view.showMostrarOpcionesEditarEquipo();
            int opc = sc.nextInt();
            switch (opc) {
                case 0 -> view.showSaliendo();
                case 1 -> eqSelec.anadirPjParty(pjAAnadir());
                case 2 -> eqSelec.eliminarPjParty(pjAEliminar(eqSelec));
                case 3 -> eqSelec.cambiarNombre();
                case 4 -> anadirMasillas(eqSelec);
                default -> view.showError();
            }

        }
    }

    public Personaje pjAAnadir() {

        Scanner sc = new Scanner(System.in);
        Personaje ret = null;
        String pjSelec = null;

        view.showMostrarYPedirPersonajes(personajes);
        pjSelec = sc.nextLine();
        ret = getPersonajePorNombre(pjSelec);

        return ret;
    }

    public Personaje pjAEliminar(Equipo eq) {

        Scanner sc = new Scanner(System.in);
        String pjSelec = null;
        Personaje ret = null;

        view.showMostrarYPedirPjEliminar(eq);
        pjSelec = sc.nextLine();
        ret = eq.getPjDeParty(pjSelec);

        return ret;

    }

    public void anadirMasillas(Equipo eq) {

        Scanner sc = new Scanner(System.in);
        Personaje ret = null;
        String pjSelec = null;
        int veces = 0;
        boolean ok = false;
        String pjNomAux = null;
        String pjNomBase = null;

        view.showMostrarYPedirMasillas(personajes);
        pjSelec = sc.nextLine();
        ret = getPersonajePorNombreSilent(pjSelec);
        if (ret != null) {
            view.showPreguntaCantidad();
            veces = sc.nextInt();
            pjNomBase = ret.getNombre();

            for (int i = 0; i < veces; i++) {
                pjNomAux = pjNomBase + "#" + (i + 1);
                eq.anadirMasillaParty(ret.clona());
                eq.setNombreIndex(i, pjNomAux);
            }
            view.showPjSelecCorrecto(getPersonajePorNombre(pjSelec));
        }
        view.showEquipoEstadoActual(eq);
    }

    public void editarPj(){
        Scanner sc = new Scanner(System.in);

        view.showMostrarYPedirPersonajesEditar(personajes);
        String op = sc.nextLine();
        Personaje pjSelec = getPersonajePorNombre(op);
        if (pjSelec != null) {

            view.showCorrectoSeleccionPj(pjSelec);
            view.showMostrarOpcionesEditarPersonajes();
            int opc = sc.nextInt();
            switch (opc) {
                case 0 -> view.showSaliendo();
                case 1 -> cambiarNombre(pjSelec);
                case 2 -> cambiarSalud(pjSelec);
                case 3 -> cambiarHA(pjSelec);
                case 4 -> cambiarHD(pjSelec);
                case 5 -> cambiarTurno(pjSelec);
                case 6 -> cambiarDano(pjSelec);
                default -> view.showError();
            }

        }
    }

    public void cambiarNombre(Personaje pj){
        Scanner sc = new Scanner(System.in);
        String var;
        do {
            view.showPedirNombre();
            var = sc.nextLine();
            if(var != null)
                pj.setNombre(var);
            else
                view.showError();
        }while(var == null);
    }

    public void cambiarSalud(Personaje pj){
        Scanner sc = new Scanner(System.in);
        int var;
        do {
            view.showPedirSalud();
            var = sc.nextInt();
            if(var != -1)
                pj.setSalud(var);
            else
                view.showError();
        }while(var == -1);
    }

    public void cambiarHA(Personaje pj){
        Scanner sc = new Scanner(System.in);
        int var;
        do {
            view.showPedirHA();
            var = sc.nextInt();
            if(var != -1)
                pj.setHAbase(var);
            else
                view.showError();
        }while(var == -1);
    }

    public void cambiarHD(Personaje pj){
        Scanner sc = new Scanner(System.in);
        int var;
        do {
            view.showPedirHD();
            var = sc.nextInt();
            if(var != -1)
                pj.setHDbase(var);
            else
                view.showError();
        }while(var == -1);
    }

    public void cambiarTurno(Personaje pj){
        Scanner sc = new Scanner(System.in);
        int var;
        do {
            view.showPedirSalud();
            var = sc.nextInt();
            if(var != -1)
                pj.setTurnoBase(var);
            else
                view.showError();
        }while(var == -1);
    }

    public void cambiarDano(Personaje pj){
        Scanner sc = new Scanner(System.in);
        int var;
        do {
            view.showPedirDano();
            var = sc.nextInt();
            if(var != -1)
                pj.setDanoBase(var);
            else
                view.showError();
        }while(var == -1);
    }



}
