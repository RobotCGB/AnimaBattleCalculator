public class Personaje {
    private String nombre;
    private int HAbase;
    private int HDbase;
    private int turnoBase;
    private int danoBase;

    public Personaje(String nombre, int HAbase, int HDbase, int turnoBase, int danoBase){
        this.nombre = nombre;
        this.HAbase = HAbase;
        this.HDbase = HDbase;
        this.turnoBase = turnoBase;
        this.danoBase = danoBase;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }



}
