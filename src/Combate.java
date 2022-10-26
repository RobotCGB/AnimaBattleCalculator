import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Combate {
    Equipo equipoUno;
    Equipo equipoDos;

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

            for(Map.Entry<Integer,Personaje> entry : personajesYTurno.entrySet()){
                Integer turnoAct = entry.getKey();
                Personaje pjAct = entry.getValue();
                System.out.println("¿Que va a hacer " + pjAct.getNombre() + "?");
                switch (elegirAccionTurno()){
                    case 0 -> System.out.println(pjAct.getNombre() + " no hizo nada");
                    case 1 -> realizarAtaque(pjAct);
                    case 2 -> realizarAccion(pjAct);
                    default -> {
                        System.out.println("Error");
                    }
                }
            }

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

        Equipo ambosEquipos = new Equipo(eqUno, eqDos);

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

        for (int i = 0; i < cantidadPjs; i++) {
            posMay = posDelMayor(turnosAux);
            turnoOrdenados = turnosAux[posMay];
            turnosAux[posMay] = 0;
            ret.put(turnoOrdenados, (ambosEquipos.getParty().get(posMay)));
            System.out.println(i+1 + ". " + ambosEquipos.getParty().get(posMay).getColor().getPigmento() + ambosEquipos.getParty().get(posMay).getNombre() + " (" + turnoOrdenados + ")" + Colores.VACIO.getPigmento());
        }
        return ret;
    }

    private void realizarAtaque(Personaje pj){

    }

    private void realizarAccion(Personaje pj){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce valor base con los posibles bonos: ");
        int base = sc.nextInt();


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
