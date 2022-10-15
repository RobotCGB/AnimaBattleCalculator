import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Personaje> personajes = new ArrayList<>();
    
    public static ArrayList<Equipo> equipos = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char opcion = 'n';
        
        System.out.println("Bienvenido a Anima Battle Calculator (ABC): ");
        while(opcion != 's') {
            switch (elegirUso()) {
                case 1 -> crearCombate();
                case 2 -> crearPersonaje();
                case 3 -> crearEquipo();
                case 4 -> mostrarPersonajes();
                case 5 -> mostrarEquipos();
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
        System.out.println("1. Calcular combate");
        System.out.println("2. Crear personaje");
        System.out.println("3. Crear equipo");
        System.out.println("4. Mostrar personajes");
        System.out.println("5. Mostrar equipos");
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

        Personaje pj = new Personaje(nombre, HAbase, HDbase, turnoBase);
        personajes.add(pj);

    }

    private static void crearCombate(){
    }

    private static void crearEquipo(){
        boolean ok = false;
        ArrayList<Personaje> party = new ArrayList<>();

        System.out.println("Elige el nombre del equipo: ");
        String nombre = sc.nextLine();
        char c;
        String select;

        do {
            System.out.println("Elige personaje que añadir: ");
            System.out.println(personajes.toString());
            select = sc.nextLine();
            party.add(getPersonajeNombre(select));
            System.out.println("¿Quieres añadir alguien mas?(s/n): ");
            c = sc.next().charAt(0);
        } while(c != 's');

        Equipo eq = new Equipo(nombre, party);
        
        equipos.add(eq);

    }

    private static void mostrarPersonajes(){
        System.out.println(personajes.toString());
    }
    
    private static void mostrarEquipos(){
        System.out.println(equipos.toString());
    }

    public static Personaje getPersonajeNombre(String nombre){
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
        return ret;
    }

}