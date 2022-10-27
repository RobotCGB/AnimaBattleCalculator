import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Main {





    public static void main(String[] args){
        ArrayList<Personaje> personajes = new ArrayList<>();

        ArrayList<Equipo> equipos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        Personaje Alexei = new Personaje("Alexei", 200, 160, 160, 80, 60);
        Personaje Manya = new Personaje("Manya", 180, 120, 120, 120, 90);
        Personaje Yassir = new Personaje("Yassir", 140, 120, 140, 100, 80);
        Personaje Ziri = new Personaje("Ziri", 100,  140, 180, 80, 100);

        personajes.add(Alexei);
        personajes.add(Manya);
        personajes.add(Yassir);
        personajes.add(Ziri);

        ArrayList<Personaje> AlxMan = new ArrayList<>();
        AlxMan.add(Alexei);
        AlxMan.add(Manya);
        ArrayList<Personaje> YasZi = new ArrayList<>();
        YasZi.add(Yassir);
        YasZi.add(Ziri);
        Equipo eq1 = new Equipo("eq1", AlxMan);
        Equipo eq2 = new Equipo("eq2", YasZi);
        equipos.add(eq1);
        equipos.add(eq2);

        System.out.println("Elige modo: ");
        System.out.println("1. Modo demo");
        System.out.println("2. Modo limpio");

        Cli cli;

        switch (sc.nextInt()){
            case 1 -> cli = new Cli(personajes, equipos);
            default -> cli = new Cli();
        }

        cli.run();



    }



}