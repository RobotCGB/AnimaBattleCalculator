import java.io.Serializable;
import java.util.ArrayList;

public class View implements Serializable {

    public View() {

    }

    public void showError() {
        System.out.println("Error, algo ha salido mal");
    }

    public void showIntroduccion() {
        System.out.println("Bienvenido a Anima Battle Calculator (ABC): ");
    }

    public void showTodosEquipos(ArrayList<Equipo> equipos) {

        if (equipos.size() > 1) {
            for (int i = 0; i < equipos.size(); i++) {
                System.out.println("");
                System.out.print(equipos.get(i).getNombre() + ": ");
                showMostrarParty(equipos.get(i).getParty());
                System.out.println("");

            }
        } else if (equipos.size() == 1) {
            System.out.println("");
            System.out.println(equipos.get(0).getNombre() + ": ");
            showMostrarParty(equipos.get(0).getParty());
            System.out.println("");
        } else {
            System.out.println("Aun no se han creado equipos");
        }


    }

    public void showTodosPersonajes(ArrayList<Personaje> personajes) {
        System.out.println(personajes.toString());
    }

    public void showOpcionesUso() {
        System.out.println("Dime, ¿que quieres hacer?");
        System.out.println("0. Salir");
        System.out.println("1. Calcular combate");
        System.out.println("2. Crear personaje");
        System.out.println("3. Crear equipo");
        System.out.println("4. Mostrar personajes");
        System.out.println("5. Mostrar equipos");
        System.out.println("6. Editar personaje");
        System.out.println("7. Editar equipo");
        System.out.println("8. Eliminar personaje");
        System.out.println("9. Eliminar equipo");
        System.out.println("10. Guardar cambios");
        System.out.println("Elige: ");
    }

    public void showPedirBase() {
        System.out.println("Introduce valor base con todos los posibles bonos: ");
    }

    public void showSumaTirada(int base, int tirada) {
        System.out.println(base + " + " + tirada + " = " + (base + tirada));
    }

    public void showTurnoActualPj(int i, ArrayList<Personaje> alPjOrd, ArrayList<Integer> alTurnosOrd, int posMay, int turnoAux) {
        System.out.println(i + 1 + ". " + alPjOrd.get(i).getColor().getPigmento() + alPjOrd.get(i).getNombre() + " (" + turnoAux + ")" + Colores.VACIO.getPigmento());
    }

    public void showMostrarYPedirEquipos(ArrayList<Equipo> equipos) {
        System.out.println("Equipos a elegir: ");
        showTodosEquipos(equipos);
        System.out.println("Elige un equipo: ");
    }

    public void showMostrarYPedirPersonajes(ArrayList<Personaje> personajes) {
        System.out.println("Personajes elegibles: ");
        showTodosPersonajes(personajes);
        System.out.println("Elige personaje que añadir: ");
    }

    public void showMostrarYPedirMasillas(ArrayList<Personaje> personajes) {
        System.out.println("Personajes elegibles: ");
        showTodosPersonajes(personajes);
        System.out.println("Elige que tipo de masillas añadir: ");
    }

    public void showPreguntaCantidad() {
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

    public void showEligeNombre() {
        System.out.println("Elige el nombre del equipo: ");
    }

    public void showPreguntarSiMas() {
        System.out.println("¿Quieres añadir alguien mas?(s/n): ");
    }

    public void showPreguntarSiMasillas() {
        System.out.println("¿Quieres meter masillas?(s/n): ");
    }

    public void showEquipoEstadoActual(Equipo eq) {
        System.out.println("Ahora el equipo esta así: ");
        showMostrarParty(eq.getParty());
    }

    public void showNombreEstadoActual(String nombre) {
        System.out.println("Ahora el nombre es este: " + nombre + "\n");
    }

    public void showPjSelecCorrecto(Personaje pjSelec) {
        System.out.println("Personaje: " + pjSelec + " seleccionado");
    }

    public void showMostrarYPedirEquiposEditar(ArrayList<Equipo> equipos) {
        System.out.println("Estos son los equipos a editar: ");
        showTodosEquipos(equipos);
        System.out.println("¿Cual quieres?: ");
    }

    public void showMostrarYPedirPersonajesEditar(ArrayList<Personaje> personajes) {
        System.out.println("Estos son los personajes a editar: ");
        showTodosPersonajes(personajes);
        System.out.println("¿Cual quieres?: ");
    }

    public void showMostrarYPedirPersonajesEliminar(ArrayList<Personaje> personajes){
        System.out.println("Estos son los personajes a eliminar: ");
        showTodosPersonajes(personajes);
        System.out.println("¿Cual quieres?: ");
    }

    public void showErrorPjNoEncontrado() {
        System.out.println("No se ha encontrado el personaje, vuelve a insertar pj.");
    }

    public void showErrorEqNoEncontrado() {
        System.out.println("No se ha encontrado el equipo, vuelve a insertar equipo.");
    }

    public void showSaludRestantePj(Personaje pj) {
        System.out.println("A " + pj.getNombre() + " le quedan " + pj.getSalud() + " puntos de vida");
    }

    public void showMostrarOpcionesEditarEquipo() {
        System.out.println("¿Y que quieres hacer?: ");
        System.out.println("0. Salir");
        System.out.println("1. Añadir personaje");
        System.out.println("2. Eliminar personaje");
        System.out.println("3. Cambiar nombre");
        System.out.println("4. Añadir masilla");
    }

    public void showMostrarOpcionesEditarPersonajes() {
        System.out.println("¿Y que quieres hacer?: ");
        System.out.println("0. Salir");
        System.out.println("1. Cambiar Nombre");
        System.out.println("2. Cambiar salud");
        System.out.println("3. Cambiar HA");
        System.out.println("4. Cambiar HD");
        System.out.println("5. Cambiar turno");
        System.out.println("6. Cambiar daño");
    }

    public void showCorrectoSeleccionEquipo(Equipo eqSelec) {
        System.out.println("Has seleccionado: " + eqSelec.getNombre());
    }

    public void showCorrectoSeleccionPj(Personaje pjSelec) {
        System.out.println("Has seleccionado: " + pjSelec.getNombre());
    }

    public void showCorrectoEliminarPj(Personaje pjSelec) {
        System.out.println("Has eliminado: " + pjSelec.getNombre());
    }

    public void showCorrectoEliminarEq(Equipo eqSelec) {
        System.out.println("Has eliminado: " + eqSelec.getNombre());
    }

    public void showMostrarYPedirPjEliminar(ArrayList<Personaje> personajes) {
        System.out.println("Pjs disponibles: ");
        showTodosPersonajes(personajes);
        System.out.println("\nElige pj a eliminar: ");
    }
    public void showMostrarYPedirEquiposEliminar(ArrayList<Equipo> equipos) {
        System.out.println("Equipos disponibles: ");
        showTodosEquipos(equipos);
        System.out.println("\nElige equipo a eliminar: ");
    }

    public void showPedirNombre() {
        System.out.println("Nombre: ");
    }

    public void showPedirSalud() {
        System.out.println("Salud actual: ");
    }

    public void showPedirHA() {
        System.out.println("HA actual: ");
    }

    public void showPedirHD() {
        System.out.println("HD actual: ");
    }

    public void showPedirTurno() {
        System.out.println("Turno actual: ");
    }

    public void showPedirDano() {
        System.out.println("Daño actual: ");
    }

    public void showSaliendo() {
        System.out.println("Saliendo... ");
    }

    public void showGuardando() {
        System.out.println("Guardando...");
    }

    public void showPreguntarOtroTurno() {
        System.out.println("¿Otro turno?(s/n): ");
    }

    public void showMostrarOpcionesAccion() {
        System.out.println("0. Nada");
        System.out.println("1. Atacar");
        System.out.println("2. Tirada de habilidad");
    }

    public void showErrorRangoCeroDos() {
        System.out.println("Haz seleccion en el rango [0-2]");
    }

    public void showContadorTurno(int numeroDeTurnos) {
        System.out.println("Turno " + numeroDeTurnos);
        System.out.println("Tiramos turno...");
    }

    public void showPreguntarAccionPj(String nombre) {
        System.out.println("¿Que va a hacer " + nombre + "?");
    }

    public void showInaccionPj(String nombre) {
        System.out.println(nombre + " no hizo nada");
    }

    public void showPreguntarHApj(int HA) {
        System.out.println("Tu habilidad de ataque es: " + HA);
        System.out.println("¿Es correcto?(s/n): ");
    }

    public void showPreguntaCorrecto() {
        System.out.println("¿Es correcto?(s/n): ");
    }

    public void showPreguntaDanoActual(Personaje pj) {
        System.out.println("Tu daño es: " + pj.getDanoBase());
    }

    public void showPreguntaDefensaActual(Personaje enem) {
        System.out.println("Su habilidad de defensa es: " + enem.getHDbase());
    }

    public void showHACorrecto(Personaje pj) {
        System.out.println("Correcto, ahora tu HA es: " + pj.getHAbase());
    }

    public void showHDCorrecto(Personaje enem) {
        System.out.println("Correcto, ahora tu HD es: " + enem.getHDbase());
    }

    public void showDanoCorrecto(Personaje pj) {
        System.out.println("Correcto, ahora tu daño es: " + pj.getDanoBase());
    }

    public void showTiradaMasBaseHA(int tirada, Personaje pj, int bono) {
        System.out.print("Ataque: " + tirada + " (tirada) + " + pj.getHAbase() + " (base) " );
        if(bono != 0){
            System.out.print( "+ " + bono + " (contra) ");
        }
        System.out.println("= " + (pj.getHAbase() + tirada + bono));
    }

    public void showTiradaMasBaseHD(int tirada, Personaje pj) {
        System.out.println("Defensa: " + tirada + " (tirada) + " + pj.getHDbase() + " (base) = " + (pj.getHDbase() + tirada));
    }

    public void showContraataque(int contra){
        System.out.println("CONTRA!!!");
        System.out.println("El defensor puede atacar con " + (-contra) + " de bono");
        System.out.println("¿Lo hace?: ");
    }

    public void showDanoCalculado(int dano) {
        System.out.println("El daño ejercido ha sido: " + dano);
        System.out.println("¿Quieres que se aplique?: ");
    }

    public void showDanoAplicado() {
        System.out.println("Daño aplicado");
    }

    public void showPreguntaDanoEntonces() {
        System.out.println("Indica el daño aplicado entonces: ");
    }

    public void showAtacables(Equipo ambosEquipos) {
        System.out.println("Estos son los personajes atacables: ");
        showMostrarParty(ambosEquipos.getParty());
    }

    public void showPreguntaObjetivoAtaque() {
        System.out.println("¿A quien atacas?: ");
    }

    public void showPreguntaActual() {
        System.out.println("Entonces, ¿cual es ahora?: ");
    }

    public void showPedirFIL(){
        System.out.println("TA FIL actual: ");
    }

    public void showPedirCON(){
        System.out.println("TA CON actual: ");
    }

    public void showPedirPEN(){
        System.out.println("TA PEN actual: ");
    }

    public void showPedirCAL(){
        System.out.println("TA CAL actual: ");
    }

    public void showPedirELE(){
        System.out.println("TA ELE actual: ");
    }

    public void showPedirFRI(){
        System.out.println("TA FRI actual: ");
    }

    public void showPedirENE(){
        System.out.println("TA ENE actual: ");
    }

    public void clear() {
        System.out.println();
    }

}
