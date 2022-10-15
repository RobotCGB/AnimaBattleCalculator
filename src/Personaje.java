public class Personaje {
    private String nombre;
    private int HAbase;
    private int HDbase;
    private int turnoBase;

    public Personaje(String nombre, int HAbase, int HDbase, int turnoBase){
        this.nombre = nombre;
        this.HAbase = HAbase;
        this.HDbase = HDbase;
        this.turnoBase = turnoBase;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }



}
