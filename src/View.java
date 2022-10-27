import java.util.ArrayList;
import java.util.Scanner;

public class View {

    public View(){

    }

    public void showError(){
        System.out.println("Error, algo ha salido mal");
    }

    public void showIntroduccion(){
        System.out.println("Bienvenido a Anima Battle Calculator (ABC): ");
    }

    public void showTodosEquipos(ArrayList<Equipo> equipos){

        if(equipos.size() > 1) {
            for (int i = 0; i < equipos.size(); i++) {
                System.out.println("");
                System.out.print(equipos.get(i).getNombre() + ": ");
                showMostrarParty(equipos.get(i).getParty());
                System.out.println("");

            }
        } else if(equipos.size() == 1){
            System.out.println("");
            System.out.println(equipos.get(0).getNombre() + ": ");
            showMostrarParty(equipos.get(0).getParty());
            System.out.println("");
        } else{
            System.out.println("Aun no se han creado equipos");
        }


    }

    public void showTodosPersonajes(ArrayList<Personaje> personajes){
        System.out.println(personajes.toString());
    }

    public void showOpcionesUso(){
        System.out.println("Dime, ¿que quieres hacer?");
        System.out.println("0. Salir");
        System.out.println("1. Calcular combate");
        System.out.println("2. Crear personaje");
        System.out.println("3. Crear equipo");
        System.out.println("4. Mostrar personajes");
        System.out.println("5. Mostrar equipos");
        System.out.println("6. Editar equipos");
        System.out.println("Elige: ");
    }

    public void showPedirBase(){
        System.out.println("Introduce valor base con todos los posibles bonos: ");
    }

    public void showSumaTirada(int base, int tirada){
        System.out.println(base + " + " + tirada + " = " + (base+tirada));
    }

    public void showTurnoActualPj(int i, ArrayList<Personaje> alPjOrd, Equipo ambosEquipos, int posMay, int turnoAux){
        System.out.println(i+1 + ". " + alPjOrd.get(i).getColor().getPigmento() + ambosEquipos.getParty().get(posMay).getNombre() + " (" + turnoAux + ")" + Colores.VACIO.getPigmento());
    }

    public void showMostrarYPedirEquipos(ArrayList<Equipo> equipos){
        System.out.println("Equipos a elegir: ");
        showTodosEquipos(equipos);
        System.out.println("Elige un equipo: ");
    }

    public void showMostrarYPedirPersonajes(ArrayList<Personaje> personajes){
        System.out.println("Personajes elegibles: ");
        showTodosPersonajes(personajes);
        System.out.println("Elige personaje que añadir: ");
    }

    public void showMostrarYPedirMasillas(ArrayList<Personaje> personajes){
        System.out.println("Personajes elegibles: ");
        showTodosPersonajes(personajes);
        System.out.println("Elige que tipo de masillas añadir: ");
    }

    public void showPreguntaCantidad(){
        System.out.println("Dime la cantidad que quieres introducir: ");
    }

    public void showMostrarParty(ArrayList<Personaje> party) {
        for (int i = 0; i < party.size(); i++) {
            System.out.print(party.get(i).getNombre());
            if (i != party.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public void showEligeNombre(){
        System.out.println("Elige el nombre del equipo: ");
    }

    public void showPreguntarSiMas(){
        System.out.println("¿Quieres añadir alguien mas?(s/n): ");
    }

    public void showPreguntarSiMasillas(){
        System.out.println("¿Quieres meter masillas?(s/n): ");
    }

    public void showEquipoEstadoActual(Equipo eq){
        System.out.println("Ahora el equipo esta así: ");
        showMostrarParty(eq.getParty());
    }

    public void showNombreEstadoActual(String nombre){
        System.out.println("Ahora el nombre es este: " + nombre + "\n");
    }

    public void showPjSelecCorrecto(Personaje pjSelec){
        System.out.println("Personaje: " + pjSelec + " seleccionado");
    }

    public void showMostrarYPedirEquiposEditar(ArrayList<Equipo> equipos){
        System.out.println("Estos son los equipos a editar: ");
        showTodosEquipos(equipos);
        System.out.println("¿Cual quieres?: ");
    }

    public void showErrorPjNoEncontrado(){
        System.out.println("No se ha encontrado el personaje, vuelve a insertar pj.");
    }

    public void showSaludRestantePj(Personaje pj){
        System.out.println("A " + pj.getNombre() + " le quedan " + pj.getSalud() + " puntos de vida");
    }

    public void showMostrarOpcionesEditar(){
        System.out.println("¿Y que quieres hacer?: ");
        System.out.println("0. Salir");
        System.out.println("1. Añadir personaje");
        System.out.println("2. Eliminar personaje");
        System.out.println("3. Cambiar nombre");
        System.out.println("4. Añadir masilla");
    }

    public void showCorrectoSeleccionEquipo(Equipo eqSelec){
        System.out.println("Has seleccionado: " + eqSelec.getNombre());
    }

    public void showMostrarYPedirPjEliminar(Equipo eq){
        System.out.println("Pjs en el equipo: ");
        showMostrarParty(eq.getParty());
        System.out.println("\nElige pj a eliminar: ");
    }

    public void showPedirNombre(){
        System.out.println("Nombre: ");
    }

    public void showPedirSalud(){
        System.out.println("Salud actual: ");
    }

    public void showPedirHA(){
        System.out.println("HA actual: ");
    }

    public void showPedirHD(){
        System.out.println("HD actual: ");
    }

    public void showPedirTurno(){
        System.out.println("Turno actual: ");
    }

    public void showPedirDano(){
        System.out.println("Daño actual: ");
    }

    public void showSaliendo(){
        System.out.println("Saliendo... ");
    }

    public void showGuardando(){
        System.out.println("Guardando...");
    }

    public void showPreguntarOtroTurno(){
        System.out.println("¿Otro turno?(s/n): ");
    }

    public void showMostrarOpcionesAccion(){
        System.out.println("0. Nada");
        System.out.println("1. Atacar");
        System.out.println("2. Tirada de habilidad");
    }

    public void showErrorRangoCeroDos(){
        System.out.println("Haz seleccion en el rango [0-2]");
    }

    public void showContadorTurno(int numeroDeTurnos){
        System.out.println("Turno " + numeroDeTurnos);
        System.out.println("Tiramos turno...");
    }

    public void showPreguntarAccionPj(String nombre){
        System.out.println("¿Que va a hacer " + nombre + "?");
    }

    public void showInaccionPj(String nombre){
        System.out.println(nombre + " no hizo nada");
    }

    public void showPreguntarHApj(int HA){
        System.out.println("Tu habilidad de ataque es: " + HA);
        System.out.println("¿Es correcto?(s/n): ");
    }

    public void clear(){
        System.out.println();
    }

}
