import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static ArrayList<Personaje> personajes = new ArrayList<>();
    
    public static ArrayList<Equipo> equipos = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Personaje Alexei = new Personaje("Alexei", 200, 160, 160, 80, 60);
        Personaje Manya = new Personaje("Manya", 180, 120, 120, 120, 90);
        Personaje Yassir = new Personaje("Yassir", 140, 120, 140, 100, 80);
        Personaje Ziri = new Personaje("Ziri", 100,  140, 180, 80, 100);

        personajes.add(Alexei);
        personajes.add(Manya);
        personajes.add(Yassir);
        personajes.add(Ziri);

        char opcion = 'n';
        
        System.out.println("Bienvenido a Anima Battle Calculator (ABC): ");
        while(opcion != 's') {
            switch (elegirUso()) {
                case 0 -> opcion = 's';
                case 1 -> crearCombate();
                case 2 -> crearPersonaje();
                case 3 -> crearEquipo();
                case 4 -> mostrarPersonajes();
                case 5 -> mostrarEquipos();
                case 6 -> editarEquipos();
                default -> {
                    System.out.println("Error");
                }
            }
            clear();
        }
    }

    private static int elegirUso(){


        System.out.println("Dime, ¿que quieres hacer?");
        System.out.println("0. Salir");
        System.out.println("1. Calcular combate");
        System.out.println("2. Crear personaje");
        System.out.println("3. Crear equipo");
        System.out.println("4. Mostrar personajes");
        System.out.println("5. Mostrar equipos");
        System.out.println("6. Editar equipos");
        System.out.println("Elige: ");
        return sc.nextInt();
    }

    private static void crearPersonaje(){

        Scanner sc = new Scanner(System.in);

        clear();

        System.out.println("Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Salud actual: ");
        int salud = sc.nextInt();

        System.out.println("HA actual: ");
        int HAbase = sc.nextInt();

        System.out.println("HD actual: ");
        int HDbase = sc.nextInt();

        System.out.println("Turno actual: ");
        int turnoBase = sc.nextInt();

        System.out.println("Daño actual: ");
        int danoBase = sc.nextInt();

        Personaje pj = new Personaje(nombre,salud, HAbase, HDbase, turnoBase, danoBase);
        personajes.add(pj);

    }

    private static void crearCombate(){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        Equipo eqUno = null;
        Equipo eqDos = null;
        int numeroDeTurnos = 1;
        boolean fin = false;
        char c = 'n';

        while(!ok) {
            System.out.println("Equipos a elegir: ");
            mostrarEquipos();
            System.out.println("Elige el primer equipo: ");
            eqUno = getEquipoPorNombre(sc.nextLine());
            if(eqUno == null){
                System.out.println("Equipo no valido");
            } else{
                ok = true;
            }
        }
        ok = false;
        while(!ok) {
            System.out.println("Equipos a elegir: ");
            mostrarEquipos();
            System.out.println("Elige el segundo equipo: ");
            eqDos = getEquipoPorNombre(sc.nextLine());
            if(eqDos == null){
                System.out.println("Equipo no valido");
            } else{
                ok = true;
            }
        }

        Combate combate = new Combate(eqUno, eqDos);
        do{
            System.out.println("Turno " + numeroDeTurnos);
            System.out.println("Tiramos turno...");
            System.out.println();

            Equipo ambosEquipos = new Equipo(eqUno, eqDos);

            int cantidadPjs = ambosEquipos.getPjsEnParty();
            int[] turnosAux = new int[cantidadPjs];
            int tirada = 0;
            int turno_total = 0;
            Personaje aux;

            for (int i = 0; i < cantidadPjs; i++) {
                aux = ambosEquipos.getParty().get(i);
                tirada = (int) (Math.random() * 100 + 1);
                turno_total = aux.getTurnoBase() + tirada;
                turnosAux[i] = turno_total;
            }

            int[] turnosOrdenados = new int[cantidadPjs];
            int posMay;
            Equipo ambosEquiposOrdenados = new Equipo();

            for (int i = 0; i < cantidadPjs; i++) {
                posMay = posDelMayor(turnosAux);
                turnosOrdenados[i] = turnosAux[posMay];
                turnosAux[posMay] = 0;
                ambosEquiposOrdenados.getParty().add(ambosEquipos.getParty().get(posMay));
                System.out.println(i+1 + ". " + ambosEquiposOrdenados.getParty().get(i).getNombre() + " (" + turnosOrdenados[i] + ")");
            }

            System.out.println("¿Otro turno?(s/n): ");
            c = sc.next().toLowerCase().charAt(0);
            if(c == 'n')
                fin = true;

            numeroDeTurnos++;
        }while(!fin);
    }

    private static int posDelMayor(int[] turnos){
        int ret = 0;
        int masAlto = 0;
        for (int i = 0; i < turnos.length; i++) {
            if(turnos[i] >= masAlto) {
                masAlto = turnos[i];
                ret = i;
            }
        }
        return ret;
    }

    private static void crearEquipo(){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        ArrayList<Personaje> party = new ArrayList<>();

        System.out.println("Elige el nombre del equipo: ");
        String nombre = sc.nextLine();
        char c;
        String select = null;

        Personaje aux = null;

        do {
            sc = new Scanner(System.in);
            System.out.println("Personajes elegibles: ");
            mostrarPersonajes();
            System.out.println("Elige personaje que añadir: ");
            select = sc.nextLine();
            aux = getPersonajePorNombre(select);
            if (aux != null) {
                party.add(aux);
            }
        System.out.println("¿Quieres añadir alguien mas?(s/n): ");
            c = sc.next().toLowerCase().charAt(0);
        } while(c != 'n');

        Equipo eq = new Equipo(nombre, party);
        
        equipos.add(eq);

    }

    private static void mostrarPersonajes(){
        System.out.println(personajes.toString());
    }
    
    private static void mostrarEquipos(){

        Scanner sc = new Scanner(System.in);

        clear();

        if(equipos.size() > 1) {
            for (int i = 0; i < equipos.size(); i++) {
                System.out.println("");
                System.out.print(equipos.get(i).getNombre() + ": ");
                equipos.get(i).mostrarParty();
                System.out.println("");

            }
        } else if(equipos.size() == 1){
            System.out.println("");
            System.out.println(equipos.get(0).getNombre() + ": ");
            equipos.get(0).mostrarParty();
            System.out.println("");
        } else{
            System.out.println("Aun no se han creado equipos");
        }


    }

    public static Personaje getPersonajePorNombre(String nombre){
        boolean ok = false;
        int i = 0;
        Personaje ret = null;
        while(!ok && i < personajes.size()){
            if(personajes.get(i).getNombre().equals(nombre)){
                ok = true;
                ret = personajes.get(i);
                System.out.println("Personaje: " + ret.getNombre() + " seleccionado");
            }
            i++;
        }
        if(ret == null)
            System.out.println("No se ha encontrado el personaje");
        return ret;
    }

    public static Equipo getEquipoPorNombre(String nombre){

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
            System.out.println("Error: no hay equipos");
            return null;
        }
    }

    public static void editarEquipos(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Estos son los equipos a editar: ");
        mostrarEquipos();
        System.out.println("¿Cual quieres?: ");
        Equipo eqSelec = getEquipoPorNombre(sc.nextLine());
        System.out.println("Has seleccionado: " + eqSelec.getNombre());
        System.out.println("¿Y que quieres hacer?: ");
        System.out.println("0. Salir");
        System.out.println("1. Añadir personaje");
        System.out.println("2. Eliminar personaje");
        System.out.println("3. Cambiar nombre");
        System.out.println("4. Añadir masilla");
        int op = sc.nextInt();
        switch (op) {
            case 0 -> System.out.println();
            case 1 -> eqSelec.añadirPjParty(pjAAñadir());
            case 2 -> eqSelec.eliminarPjParty(pjAEliminar(eqSelec));
            case 3 -> eqSelec.cambiarNombre();
            case 4 -> añadirMasillas(eqSelec);
            default -> {
                System.out.println("Error");
            }
        }

    }

    public static Personaje pjAEliminar(Equipo eq){

        Scanner sc = new Scanner(System.in);
        String pjSelec = null;
        Personaje ret = null;

        System.out.println("Pjs en el equipo: ");
        eq.mostrarParty();
        System.out.println("\nElige pj a eliminar: ");
        pjSelec = sc.nextLine();
        ret = eq.getPjDeParty(pjSelec);

        return ret;

    }

    public static Personaje pjAAñadir(){

        Scanner sc = new Scanner(System.in);
        Personaje ret = null;
        String pjSelec = null;

        System.out.println("Personajes disponibles: ");
        mostrarPersonajes();
        System.out.println("Elige pj a añadir: ");
        pjSelec = sc.nextLine();
        ret = getPersonajePorNombre(pjSelec);

        return ret;
    }

    public static void añadirMasillas(Equipo eq){

        Scanner sc = new Scanner(System.in);
        Personaje ret = null;
        String pjSelec = null;
        int veces = 0;

        System.out.println("Personajes disponibles: ");
        mostrarPersonajes();
        System.out.println("Elige que tipo de masillas añadir: ");
        pjSelec = sc.nextLine();
        System.out.println("Dime la cantidad que quieres introducir: ");
        veces = sc.nextInt();

        for(int i = 0; i < veces; i++){
            eq.añadirMasillaParty(getPersonajePorNombre(pjSelec));
        }
        System.out.println("Ahora el equipo esta así: ");
        eq.mostrarParty();
    }

    private static void clear(){
        /*for(int i = 0; i < 20; i++){
            System.out.println();
        }*/
    }

}