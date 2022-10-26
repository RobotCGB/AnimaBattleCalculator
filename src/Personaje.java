public class Personaje {
    private String nombre;
    private int salud;
    private int HAbase;
    private int HDbase;
    private int turnoBase;
    private int danoBase;
    Colores color;

    public Personaje(String nombre, int salud, int HAbase, int HDbase, int turnoBase, int danoBase){
        this.nombre = nombre;
        this.salud = salud;
        this.HAbase = HAbase;
        this.HDbase = HDbase;
        this.turnoBase = turnoBase;
        this.danoBase = danoBase;
        this.color = Colores.VACIO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTurnoBase() {
        return turnoBase;
    }

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
    }

    public int getHAbase() {
        return HAbase;
    }

    public int getHDbase() {
        return HDbase;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public int getSalud() {
        return salud;
    }

    public void setHAbase(int HAbase) {
        this.HAbase = HAbase;
    }

    public void setDanoBase(int danoBase) {
        this.danoBase = danoBase;
    }

    @Override
    public String toString(){
        return nombre;
    }

    public Personaje clona(){
        Personaje ret = new Personaje(this.nombre, this.salud, this.HAbase, this.HDbase, this.turnoBase, this.danoBase);
        return ret;
    }



}
