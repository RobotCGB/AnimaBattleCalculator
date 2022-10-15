import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Personaje> personajes = new ArrayList<>();
    
    public static ArrayList<Equipo> equipos = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Personaje Alexei = new Personaje("Alexei", 160, 160, 80, 60);
        Personaje Manya = new Personaje("Manya", 120, 120, 120, 90);
        Personaje Yassir = new Personaje("Yassir", 120, 140, 100, 80);
        Personaje Ziri = new Personaje("Ziri", 140, 180, 80, 100);

        personajes.add(Alexei);
        personajes.add(Manya);
        personajes.add(Yassir);
        personajes.add(Ziri);



        char opcion = 'n';
        
        System.out.println("Bienvenido a Anima Battle Calculator (ABC): ");
        while(opcion != 's') {
            switch (elegirUso()) {
                case 0 -> System.out.println();
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
            System.out.println("¿Has terminado?");
            opcion = sc.next().charAt(0);
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

        System.out.println("Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("HA actual: ");
        int HAbase = sc.nextInt();

        System.out.println("HD actual: ");
        int HDbase = sc.nextInt();

        System.out.println("Turno actual: ");
        int turnoBase = sc.nextInt();

        System.out.println("Daño actual: ");
        int danoBase = sc.nextInt();

        Personaje pj = new Personaje(nombre, HAbase, HDbase, turnoBase, danoBase);
        personajes.add(pj);

    }

    private static void crearCombate(){
        System.out.println("Lo siento, esto aun no esta hecho");
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
            System.out.println("Personajes elegibles: ");
            mostrarPersonajes();
            System.out.println("Elige personaje que añadir: ");
            sc.nextLine();
            select = sc.nextLine();
            aux = getPersonajePorNombre(select);
            if (aux != null) {
                party.add(aux);
            }
        System.out.println("¿Quieres añadir alguien mas?(s/n): ");
            c = sc.next().charAt(0);
        } while(c != 'n');

        Equipo eq = new Equipo(nombre, party);
        
        equipos.add(eq);

    }

    private static void mostrarPersonajes(){
        System.out.println(personajes.toString());
    }
    
    private static void mostrarEquipos(){

        if(equipos.size() > 1) {
            for (int i = 0; i < equipos.size(); i++) {
                System.out.print(equipos.get(i).getNombre() + ": ");
                equipos.get(i).mostrarParty();

            }
        } else if(equipos.size() == 1){
            System.out.println(equipos.get(0).getNombre() + ": ");
            equipos.get(0).mostrarParty();
        } else{
            System.out.println("Aun no se han creado equipos");
        }

        System.out.println("\nPulsa enter para continuar");
        sc.nextLine();

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
        boolean ok = false;
        int i = 0;
        Equipo ret = null;
        while(!ok && i < personajes.size()){
            if(equipos.get(i).getNombre().equals(nombre)){
                ok = true;
                ret = equipos.get(i);
            }
            i++;
        }
        return ret;
    }

    public static void editarEquipos(){

        System.out.println("Estos son los equipos a editar: ");
        mostrarEquipos();
        System.out.println("¿Cual quieres?: ");
        Equipo eqSelec = getEquipoPorNombre(sc.nextLine());
        System.out.println("¿Y que quieres hacer?: ");
        System.out.println("1. Añadir personaje");
        System.out.println("2. Eliminar personaje");
        int op = sc.nextInt();
        switch (op) {
            case 1 -> eqSelec.añadirPjParty(pjAAñadir());
            case 2 -> eqSelec.eliminarPjParty(pjAEliminar(eqSelec));
            default -> {
                System.out.println("Error");
            }
        }

    }

    public static Personaje pjAEliminar(Equipo eq){

        Personaje ret = null;
        System.out.println("Pjs en el equipo: ");
        eq.mostrarParty();
        System.out.println("Elige pj a eliminar: ");
        String pjSelec = sc.nextLine();
        ret = eq.getPjDeParty(pjSelec);
        return ret;

    }


    public static Personaje pjAAñadir(){
        Personaje ret = null;
        System.out.println("Personajes disponibles: ");
        mostrarPersonajes();
        System.out.println("Elige pj a añadir: ");
        String pjSelec = sc.nextLine();
        ret = getPersonajePorNombre(pjSelec);

        return ret;
    }

}