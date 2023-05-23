import java.util.Scanner;

import connectionpool.ConnectionPool;
import jugadores.Jugador;
import jugadores.JugadorService;

import java.sql.SQLException;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

public class App {

    private static Scanner sc;
    private static JugadorService service;
    private static ConnectionPool pool;

    public static void inicializa(){
        sc = new Scanner(System.in);
        pool = new ConnectionPool("jdbc:mysql://localhost:3306/jugadores","daniel","12345678");
        service = new JugadorService(pool.getConnection());
    }

    public static void finaliza(){
        if(sc!=null)
            sc.close();
        pool.closeAll();
    }

    public static void mostrarMenu(){
        System.out.println("1. Listar jugadores");
        System.out.println("2. Importar jugadores");
        System.out.println("3. Exportar jugadores");
        System.out.println("4. Salir");
    }

    public static int leerOpcion(){
        return Integer.parseInt(sc.nextLine());
    }

    public static void listarJugadores(){
        try {
            ArrayList<Jugador> jugadores = service.requestAll();
            for(Jugador j: jugadores)
                System.out.println(j);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void importarJugadores(){
        BufferedReader br = null;
        try {
            File file = new File("jugadores.txt");
            br = new BufferedReader(new FileReader(file));   
            String line = "";
            while((line = br.readLine())!=null){
                service.createOrUpdate(new Jugador(line));
            } 
            br.close();

        } catch (Exception e) {
            System.out.println("Ocurrió un error mientras se importaban los datos");
        }
    }

    public static void exportarJugadores(){
        File file = new File("jugadores.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            ArrayList<Jugador> jugadores = service.requestAll();
            for(Jugador j: jugadores)
                bw.write(j.serialize());
            bw.close();
        } catch (Exception e) {
            System.out.println("Ocurrión un error mientras se exportaban los datos");
        }
        
    }

    public static void main(String[] args) throws Exception {
        inicializa();
        int opcion = -1;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    listarJugadores();
                    break;
                case 2:
                    importarJugadores();
                    break;
                case 3:
                    exportarJugadores();
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion!=4);
        finaliza();
    }
}
