public enum Colores {
    ROJO("ROJO", "\u001B[31m"), AZUL("AZUL", "\u001B[36m"), VACIO("VACIO", "\u001B[0m");

    private String pinta;
    private String pigmento;

    private Colores(String pinta, String pigmento){
        this.pinta = pinta;
        this.pigmento = pigmento;
    }

    public String getPinta() {
        return pinta;
    }

    public String getPigmento() {
        return pigmento;
    }
}
