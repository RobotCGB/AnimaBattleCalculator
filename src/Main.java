import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Elige modo: ");
        System.out.println("1. Modo file");
        System.out.println("2. Modo limpio");

        Cli cli;
        Serializar ser = new Serializar();

        switch (sc.nextInt()){
            case 1 -> cli = new Cli(ser.deSerializarPj(), ser.deSerializarEq());
            default -> cli = new Cli();
        }

        cli.run();



    }



}