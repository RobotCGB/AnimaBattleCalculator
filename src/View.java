import java.util.ArrayList;
import java.util.Scanner;

public class View {

    Equipo equipo;
    Personaje pj;
    ArrayList<Equipo> equipos;
    ArrayList<Personaje> pjs;

    public View(){

    }

    public void showError(){
        System.out.println("Error, algo ha salido mal");
    }

    public void showIntroduccion(){
        System.out.println("Bienvenido a Anima Battle Calculator (ABC): ");
    }

    public void showTodosEquipos(){

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

    public void showTodosPersonajes(){
        System.out.println(pjs.toString());
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

    public void showMostrarYPedirEquipos(){
        System.out.println("Equipos a elegir: ");
        showTodosEquipos();
        System.out.println("Elige un equipo: ");
    }

    public void showMostrarYPedirPersonajes(){
        System.out.println("Personajes elegibles: ");
        showTodosPersonajes();
        System.out.println("Elige personaje que añadir: ");
    }

    public void showMostrarYPedirMasillas(){
        System.out.println("Personajes elegibles: ");
        showTodosPersonajes();
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

    public void showPjSelecCorrecto(Personaje pjSelec){
        System.out.println("Personaje: " + pjSelec + " seleccionado");
    }

    public void showMostrarYPedirEquiposEditar(){
        System.out.println("Estos son los equipos a editar: ");
        showTodosEquipos();
        System.out.println("¿Cual quieres?: ");
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

    public void clear(){
        System.out.println();
    }

}
