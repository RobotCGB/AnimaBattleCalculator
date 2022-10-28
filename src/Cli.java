import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cli {

    Scanner sc = new Scanner(System.in);
    View view = new View();
    ArrayList<Personaje> personajes;
    ArrayList<Equipo> equipos;
    Serializar serializar = new Serializar();
    final int cantTA = 7;

    public Cli() {
        this.personajes = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    public Cli(ArrayList<Personaje> personajes, ArrayList<Equipo> equipos) {
        this.personajes = personajes;
        this.equipos = equipos;
    }

    public void run() throws FileNotFoundException {
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
                case 6 -> editarPj();
                case 7 -> editarEquipos();
                case 8 -> eliminarPj();
                case 9 -> eliminarEq();
                case 10 -> guardar();
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
        if(eq1 != null && eq2 != null) {
            Combate combate = new Combate(eq1, eq2);
            combate.continuarCombate();
        }
    }

    private Equipo pedirEquipoUno() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        Equipo eq = null;
        Equipo eqAux = null;
        String eqEle;
        while (!ok) {
            do {
                view.showMostrarYPedirEquipos(equipos);
                eqEle = sc.nextLine();
                eqAux = getEquipoPorNombre(eqEle);
                if(eqAux == null && !eqEle.equals("salir"))
                    view.showErrorEqNoEncontrado();
            } while(eqAux == null || eqEle.equals("salir"));
            if (eqAux == null) {
                view.showError();
            } else {
                eq = eqAux.clona();
                for (int i = 0; i < eq.getParty().size(); i++) {
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
        String eqEle;
        while (!ok) {
            do {
                view.showMostrarYPedirEquipos(equipos);
                eqEle = sc.nextLine();
                eqAux = getEquipoPorNombre(eqEle);
                if(eqAux == null && !eqEle.equals("salir"))
                    view.showErrorEqNoEncontrado();
            } while(eqAux == null || eqEle.equals("salir"));
            if (eqAux == null) {
                view.showError();
            } else {
                eq = eqAux.clona();
                for (int i = 0; i < eq.getParty().size(); i++) {
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

        int[] TA = preguntarNuevaTA();

        int critico;

        do {
            view.showPedirCritico();
            critico = sc.nextInt();
        } while(critico < 0 || critico > 6);

        personajes.add(new Personaje(nombre, salud, HAbase, HDbase, turnoBase, danoBase, TA, critico));

    }

    public int[] preguntarNuevaTA(){
        int[] TA = new int[cantTA];
        int cuentaTA = 0;

        view.showPedirFIL();
        TA[cuentaTA] = sc.nextInt();
        cuentaTA++;

        view.showPedirCON();
        TA[cuentaTA] = sc.nextInt();
        cuentaTA++;

        view.showPedirPEN();
        TA[cuentaTA] = sc.nextInt();
        cuentaTA++;

        view.showPedirCAL();
        TA[cuentaTA] = sc.nextInt();
        cuentaTA++;

        view.showPedirELE();
        TA[cuentaTA] = sc.nextInt();
        cuentaTA++;

        view.showPedirFRI();
        TA[cuentaTA] = sc.nextInt();
        cuentaTA++;

        view.showPedirENE();
        TA[cuentaTA] = sc.nextInt();

        return TA;
    }

    private void crearEquipo() {
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
        String select;
        Personaje aux;
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

        view.showMostrarYPedirPjEliminar(personajes);
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
                case 7 -> cambiarTA(pjSelec);
                case 8 -> cambiarCritico(pjSelec);
                default -> view.showError();
            }

        }
    }

    public void cambiarCritico(Personaje pj){
        Scanner sc = new Scanner(System.in);
        int var;
        do {
            view.showPedirCritico();
            var = sc.nextInt();
            if(var != -1)
                pj.setCritico(var);
            else
                view.showError();
        }while(var < 0 || var > 6);
    }

    public void cambiarTA(Personaje pj){
        Scanner sc = new Scanner(System.in);
        int[] var;
        do {
            var = preguntarNuevaTA();
            if(var != null)
                pj.setTA(var);
            else
                view.showError();
        }while(var == null);
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

    public void eliminarPj(){
        Scanner sc = new Scanner(System.in);

        view.showMostrarYPedirPersonajesEliminar(personajes);
        String op = sc.nextLine();
        Personaje pjSelec = getPersonajePorNombre(op);
        if (pjSelec != null) {
            view.showCorrectoEliminarPj(pjSelec);
            personajes.remove(pjSelec);
        }
    }

    public void eliminarEq(){
        Scanner sc = new Scanner(System.in);

        view.showMostrarYPedirEquiposEliminar(equipos);
        String op = sc.nextLine();
        Equipo eqSelec = getEquipoPorNombre(op);
        if (eqSelec != null) {
            view.showCorrectoEliminarEq(eqSelec);
            equipos.remove(eqSelec);
        }
    }

    public void guardar() throws FileNotFoundException {
        serializar.serializarPj(personajes);
        serializar.serializarEq(equipos);
    }

}
