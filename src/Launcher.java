import hero.Calidad;
import hero.Hero;
import base.GameEnginer;
import hero.ItemsHero;
import taberna.CreateMision;

import java.util.ArrayList;
import java.util.Scanner;

public class Launcher {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean salir = false;
        int counter = 0;

        while (!salir) {
            System.out.println();
            System.out.println("Menú del Juego PRINCIPAL:");
            System.out.println("1. Iniciar nuevo juego");
            System.out.println("2. Cargar juego");
            System.out.println("3. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Hola capullo/a de turno en este caso Bruno." +
                            " Lo más importante es escoger un nombre para tu héroe!! guay, grandioso... Di un dichoso nombre:");
                    String nombre = sc.nextLine();
                    System.out.println("Encantado de conocerte capitán Gilipollas. Te vas a creer que vas a escoger nombre en MI juego, jeje. Bueno al meollo disfruta!");
                    Hero hero = new Hero("Gilipollas", 1,100,30,0,50,50,10,10,10, 15, Calidad.STANDARD, ItemsHero.Inventario(), null, CreateMision.listaMisiones());
                    GameEnginer.Posada(hero, sc);
                    break;
                case 2:
                    System.out.println("Cargando partida");
                    break;
                case 3:
                    System.out.println("Saliendo del juego... Espero que hayas guardado");
                    salir = true;
                    break;
                default:
                    if (counter == 2) {
                        System.out.println("Eres un troll que solo molesta a sus putos padres porque le dejan y le dan de todo. Te cierro el puto juego.");
                        salir = true;
                        break;
                    }
                    if (counter>0)
                        System.out.println("Te la estas jugando amiguito... Prueba otra vez");
                    else
                        System.out.println("¿Es tan difícil escoger un número del 1 al 3? Inténtalo de nuevo.");

                    counter++;

            }
        }

        sc.close();

    }
}
