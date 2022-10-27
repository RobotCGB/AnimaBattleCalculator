import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Combate {
    Equipo ambosEquipos;
    ArrayList<Personaje> alPjOrd = new ArrayList<>();
    ArrayList<Integer> alTurnoOrd = new ArrayList<>();
    int numeroDeTurnos = 1;
    int cantidadPjs;
    View view = new View();

    public Combate(Equipo equipoUno, Equipo equipoDos){
        this.ambosEquipos = new Equipo(equipoUno, equipoDos);
        this.cantidadPjs = ambosEquipos.getPjsEnParty();
    }

    public void iniciarCombate(){

    }

    public void continuarCombate(){
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        boolean fin = false;
        char c = 'n';

        do{
            nuevoTurno();
            elegirUsoTurno();

            view.showPreguntarOtroTurno();
            c = sc.next().toLowerCase().charAt(0);
            if(c == 'n')
                fin = true;
            numeroDeTurnos++;
        }while(!fin);

    }

    private void nuevoTurno(){

        view.showContadorTurno(numeroDeTurnos);

        int turnoAux;
        int posMay;

        int[] turnosDesOrden = calcularTurnos();

        for (int i = 0; i < cantidadPjs; i++) {
            posMay = posDelMayor(turnosDesOrden);
            turnoAux = turnosDesOrden[posMay];
            turnosDesOrden[posMay] = 0;
            alPjOrd.add(ambosEquipos.getParty().get(posMay));
            alTurnoOrd.add(turnoAux);
            view.showTurnoActualPj(i, alPjOrd, ambosEquipos, posMay, turnoAux);
        }

    }

    private int[] calcularTurnos(){
        int[] turnosAux = new int[cantidadPjs];
        Personaje aux;
        int tirada = 0;
        int turno_total = 0;
        for (int i = 0; i < cantidadPjs; i++) {
            aux = ambosEquipos.getParty().get(i);
            tirada = d100ParaTurno();
            turno_total = aux.getTurnoBase() + tirada;
            turnosAux[i] = turno_total;
        }
        return turnosAux;
    }

    private void elegirUsoTurno(){
        for (int i = 0; i < ambosEquipos.getPjsEnParty(); i++) {
            view.showPreguntarAccionPj(alPjOrd.get(i).getNombre());
            switch (elegirAccionTurno()){
                case 0 -> view.showInaccionPj(alPjOrd.get(i).getNombre());
                case 1 -> realizarAtaque(alPjOrd.get(i));
                case 2 -> realizarAccion();
                default -> {
                    view.showError();
                }
            }
        }
    }

    private int elegirAccionTurno(){
        Scanner sc = new Scanner(System.in);
        int ret = 0;
        char c = 's';
        while(c != 'n') {
            view.showMostrarOpcionesAccion();
            ret = sc.nextInt();
            if(ret >= 0 && ret <= 2)
                c = 'n';
            else
                view.showErrorRangoCeroDos();
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

        view.showPreguntarHApj(pj.getHAbase());
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
        view.showMostrarParty(ambosEquipos.getParty());
        sc = new Scanner(System.in);
        do {
            System.out.println("¿A quien atacas?: ");
            nombre = sc.nextLine();
            while (!ok && i < ambosEquipos.getPjsEnParty()) {
                if (ambosEquipos.getParty().get(i).getNombre().equals(nombre)) {
                    ok = true;
                    enem = ambosEquipos.getParty().get(i);
                    view.showPjSelecCorrecto(enem);
                }
                i++;
            }
            if (enem == null)
                view.showErrorPjNoEncontrado();
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
        int dano = calcularDano(totalHA, totalHD, pj.getDanoBase());

        System.out.println("El daño ejercido ha sido: " + dano);
        System.out.println("¿Quieres que se aplique?: ");
        c = sc.next().toLowerCase().charAt(0);
        sc = new Scanner(System.in);
        if(c == 's'){
            enem.setSalud(enem.getSalud() - dano);
            System.out.println("Daño aplicado");
        } else {
            System.out.println("Indica el daño aplicado entonces: ");
            dano = sc.nextInt();
        }

        view.showSaludRestantePj(enem);

    }

    private int calcularDano(int HA, int HD, int dano){
        int dif = HA - HD;
        int danoTot = 0;
        if(dif >= 0){
            danoTot = (dano * dif) / 100;
        } else {
            danoTot = -1;
        }
            return danoTot;
    }

    private void realizarAccion(){
        Scanner sc = new Scanner(System.in);
        view.showPedirBase();
        int base = sc.nextInt();
        int tirada = d100conAbierta();
        view.showSumaTirada(base, tirada);

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
