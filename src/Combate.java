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
            turnosDesOrden[posMay] = -99999;
            alPjOrd.add(ambosEquipos.getParty().get(posMay));
            alTurnoOrd.add(turnoAux);
            view.showTurnoActualPj(i, alPjOrd, alTurnoOrd, posMay, turnoAux);
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


        view.showPreguntarHApj(pj.getHAbase());
        c = sc.next().toLowerCase().charAt(0);
        if(c == 'n'){
            view.showPreguntaActual();
            pj.setHAbase(sc.nextInt());
            view.showHACorrecto(pj);
        }
        view.showPreguntaDanoActual(pj);
        view.showPreguntaCorrecto();
        c = sc.next().toLowerCase().charAt(0);
        if(c == 'n'){
            view.showPreguntaActual();
            pj.setDanoBase(sc.nextInt());
            view.showDanoCorrecto(pj);
        }
        view.showAtacables(ambosEquipos);

        do {
            sc = new Scanner(System.in);
            view.showPreguntaObjetivoAtaque();
            nombre = sc.nextLine();
            i = 0;
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
        view.showPreguntaDefensaActual(enem);
        view.showPreguntaCorrecto();
        c = sc.next().toLowerCase().charAt(0);
        if(c == 'n'){
            view.showPreguntaActual();
            enem.setHDbase(sc.nextInt());
            view.showHDCorrecto(enem);
        }
        calcularAtaque(pj,enem, 0);

    }

    private void calcularAtaque(Personaje pj, Personaje enem, int bono){
        int tiradaHA;
        int tiradaHD;
        int totalHA;
        int totalHD;
        Scanner sc = new Scanner(System.in);
        char c;

        tiradaHA = d100conAbierta();
        totalHA = tiradaHA + pj.getHAbase() + bono;
        view.showTiradaMasBaseHA(tiradaHA, pj, bono);
        tiradaHD = d100conAbierta();
        totalHD = tiradaHD + enem.getHDbase();
        view.showTiradaMasBaseHD(tiradaHD, enem);

        int dano = calcularDano(totalHA, totalHD, pj.getDanoBase(), enem.getTAindex(pj.getCritico()));
        char opContra = 'n';
        if(dano >= 0) {
            view.showDanoCalculado(dano);
            c = sc.next().toLowerCase().charAt(0);
            sc = new Scanner(System.in);
            if(c == 's'){
                enem.setSalud(enem.getSalud() - dano);
                view.showDanoAplicado();
            } else {
                view.showPreguntaDanoEntonces();
                dano = sc.nextInt();
            }
            view.showSaludRestantePj(enem);
        }
        else {
            view.showContraataque(dano);
            opContra = sc.next().toLowerCase().charAt(0);
            if(opContra == 's'){
                calcularAtaque(enem, pj, -dano);
            }
        }
    }

    private int calcularDano(int HA, int HD, int dano, int TA){
        int dif = HA - HD;
        int danoTot;
        int restaTA = 100 - (TA * 10);
        if(dif > 0){
            danoTot = ((dano * dif)-20) / 100;
            danoTot *= restaTA;
        } else if(dif == 0){
            danoTot = 0;
        } else {
            danoTot = dif / 2;
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
            tirada = -(int) (Math.random() * 100 + 1);
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
            tirada = -75;
        }
        if(tirada == 2){
            tirada = -100;
        }
        if(tirada == 1){
            tirada = -125;
        }

        return tirada;
    }

}
