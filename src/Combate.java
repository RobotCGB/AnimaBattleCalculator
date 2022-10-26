import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Combate {
    Equipo equipoUno;
    Equipo equipoDos;
    Equipo ambosEquipos;
    ArrayList<Personaje> alPjOrd = new ArrayList<>();
    ArrayList<Integer> alTurnoOrd = new ArrayList<>();

    public Combate(Equipo equipoUno, Equipo equipoDos){
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
    }

    public void continuarCombate(){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int numeroDeTurnos = 1;
        boolean fin = false;
        char c = 'n';

        HashMap<Integer, Personaje> personajesYTurno;

        do{
            personajesYTurno = getSortedMapTurnoYPj(numeroDeTurnos, equipoUno, equipoDos);

            System.out.println("¿Otro turno?(s/n): ");
            c = sc.next().toLowerCase().charAt(0);
            if(c == 'n')
                fin = true;
            numeroDeTurnos++;
        }while(!fin);



    }

    private int elegirAccionTurno(){
        Scanner sc = new Scanner(System.in);
        int ret = 0;
        char c = 's';
        while(c != 'n') {
            System.out.println("0. Nada");
            System.out.println("1. Atacar");
            System.out.println("2. Tirada de habilidad");
            ret = sc.nextInt();
            if(ret >= 0 && ret <= 2)
                c = 'n';
            else
                System.out.println("Haz seleccion en el rango [0-2]");
        }

        return ret;
    }

    private HashMap<Integer, Personaje> getSortedMapTurnoYPj(int numeroDeTurnos, Equipo eqUno, Equipo eqDos){

        HashMap<Integer, Personaje> ret = new HashMap<>();

        System.out.println("Turno " + numeroDeTurnos);
        System.out.println("Tiramos turno...");
        System.out.println();

        ambosEquipos = new Equipo(eqUno, eqDos);

        int cantidadPjs = ambosEquipos.getPjsEnParty();
        int[] turnosAux = new int[cantidadPjs];
        int tirada = 0;
        int turno_total = 0;
        Personaje aux;

        for (int i = 0; i < cantidadPjs; i++) {
            aux = ambosEquipos.getParty().get(i);
            tirada = d100ParaTurno();
            turno_total = aux.getTurnoBase() + tirada;
            turnosAux[i] = turno_total;
        }

        int turnoOrdenados;
        int posMay;
        Personaje pjMay = null;

        for (int i = 0; i < cantidadPjs; i++) {
            posMay = posDelMayor(turnosAux);
            turnoOrdenados = turnosAux[posMay];
            turnosAux[posMay] = 0;
            alPjOrd.add(ambosEquipos.getParty().get(posMay));
            alTurnoOrd.add(turnoOrdenados);
            ret.put(turnoOrdenados, (ambosEquipos.getParty().get(posMay)));
            System.out.println(i+1 + ". " + alPjOrd.get(i).getColor().getPigmento() + ambosEquipos.getParty().get(posMay).getNombre() + " (" + turnoOrdenados + ")" + Colores.VACIO.getPigmento());

        }
        for (int i = 0; i < cantidadPjs; i++) {
            System.out.println("¿Que va a hacer " + alPjOrd.get(i).getNombre() + "?");
            switch (elegirAccionTurno()){
                case 0 -> System.out.println(alPjOrd.get(i).getNombre() + " no hizo nada");
                case 1 -> realizarAtaque(alPjOrd.get(i));
                case 2 -> realizarAccion();
                default -> {
                    System.out.println("Error");
                }
            }
        }

        return ret;
    }

    private void realizarAtaque(Personaje pj){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        int i = 0;
        Personaje enem = null;
        String nombre = null;
        char c = 's';
        int tiradaHA;
        int tiradaHD;
        int totalHA;
        int totalHD;

        System.out.println("Tu habilidad de ataque es: " + pj.getHAbase());
        System.out.println("¿Es correcto?(s/n): ");
        c = sc.next().toLowerCase().charAt(0);
        if(c == 'n'){
            System.out.println("Entonces, ¿cual es ahora?: ");
            pj.setHAbase(sc.nextInt());
            System.out.println("Correcto, ahora tu HA es: " + pj.getHAbase());
        }
        System.out.println("Tu daño es: " + pj.getDanoBase());
        System.out.println("¿Es correcto?(s/n): ");
        c = sc.next().toLowerCase().charAt(0);
        if(c == 'n'){
            System.out.println("Entonces, ¿cual es ahora?: ");
            pj.setDanoBase(sc.nextInt());
            System.out.println("Correcto, ahora tu daño es: " + pj.getDanoBase());
        }
        System.out.println("Estos son los personajes atacables: ");
        ambosEquipos.mostrarParty();
        sc = new Scanner(System.in);
        do {
            System.out.println("¿A quien atacas?: ");
            nombre = sc.nextLine();
            while (!ok && i < ambosEquipos.getPjsEnParty()) {
                if (ambosEquipos.getParty().get(i).getNombre().equals(nombre)) {
                    ok = true;
                    enem = ambosEquipos.getParty().get(i);
                    System.out.println("Personaje: " + enem.getNombre() + " seleccionado");
                }
                i++;
            }
            if (enem == null)
                System.out.println("No se ha encontrado el personaje, vuelve a insertar pj.");
        } while(enem == null);
        System.out.println("Su habilidad de defensa es: " + enem.getHDbase());
        System.out.println("¿Es correcto?(s/n): ");
        c = sc.next().toLowerCase().charAt(0);
        if(c == 'n'){
            System.out.println("Entonces, ¿cual es ahora?: ");
            enem.setHAbase(sc.nextInt());
            System.out.println("Correcto, ahora tu HD es: " + enem.getHDbase());
        }
        tiradaHA = d100conAbierta();
        totalHA = tiradaHA + pj.getHAbase();
        System.out.println(tiradaHA + " (tirada) + " + pj.getHAbase() + " (base) = " + totalHA);
        tiradaHD = d100conAbierta();
        totalHD = tiradaHD + enem.getHDbase();
        System.out.println(tiradaHD + " (tirada) + " + enem.getHDbase() + " (base) = " + totalHD);

    }

    private void realizarAccion(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce valor base con todos los posibles bonos: ");
        int base = sc.nextInt();
        int tirada = d100conAbierta();
        System.out.println(base + " + " + tirada + " = " + (base+tirada));

    }

    private int posDelMayor(int[] turnos){
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

    private int d100conAbierta(){
        int tirada = (int) (Math.random() * 100 + 1);
        int tiradaAct = tirada;
        do {
            if (tiradaAct >= 90) {
                tiradaAct =  (int) (Math.random() * 100 + 1);
                tirada += tiradaAct;
            }
        }while(tiradaAct >= 90);

        if(tirada <= 3){
            tirada = -tirada;
        }

        return tirada;
    }

    private int d100ParaTurno(){
        int tirada = (int) (Math.random() * 100 + 1);
        int tiradaAct = tirada;
        do {
            if (tiradaAct >= 90) {
                tiradaAct =  (int) (Math.random() * 100 + 1);
                tirada += tiradaAct;
            }
        }while(tiradaAct >= 90);

        if(tirada == 3){
            tirada = -10;
        }
        if(tirada == 2){
            tirada = -20;
        }
        if(tirada == 1){
            tirada = -30;
        }

        return tirada;
    }

}
