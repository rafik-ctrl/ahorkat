package juego;

import java.util.Random;
import java.util.Scanner;


/**
 * Juego del ahorcado por consola.
 * El jugador tiene que adivinar una palabra letra a letra antes de finalizar todos los intentos
 * 
 * @author rafik
 * @version 1.0
 * 
 */
/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
public class Ahorcado {
	
	/**
	 * Las palabras que estan dentro del array y que tiene que adivinar el jugador*/

    private static final String[] PALABRAS = {
        "programacion", "java", "ordenador", "teclado", "variable",
        "git", "algoritmo", "servidor", "compilador", "bucle"
    };

    /**
     * numero maximo de fallos permitdos para el jugador 
     */
    
    private static final int MAX_FALLOS = 6;

    /**
     * Constructor por defecto 
     */
    
    public Ahorcado() {
    }

    /**
     *metodo principaldel juego
     *
     *<p>
     *muestra el titulo , juega secesivas rondas con palabras aleatorias.
     *al final de cada ronda pregunta al jugador si quiere seguir jugando.
     *el juego termina cuando el jugador responde algo distinto a s.
     *</p>
     *
     *@param args argumentos de linea
     **/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean seguirJugando = true;

        System.out.println("=== JUEGO DEL AHORCADO ===");

        while (seguirJugando) {
            String palabra = PALABRAS[random.nextInt(PALABRAS.length)];
            jugarRonda(palabra, sc);

            System.out.print("¿Quieres jugar otra vez? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            seguirJugando = respuesta.equals("s");
        }

        System.out.println("¡Hasta la próxima!");
        sc.close();
    }
    
    /**
     * juega una ronda completa con la palabra indicada.
     * 
     * <p>
     * en cada turno se muestra el dibujo , con las letras acertadas de la palabra secreta y las letras ya usadas pide una letra al jugador
     * la ronda termina cuando el jugador adivina la palabra o alcanzamos el numero total de fallos {@value #MAX_FALLOS}
     * <p>
     * 
     * @param palabra la palabra secreta que hay que adivinar 
     * @param sc variable desde donde se leen las letras que mete el juador.
     **/

   
    private static void jugarRonda(String palabra, Scanner sc) {
        StringBuilder oculta = new StringBuilder("_".repeat(palabra.length()));
        StringBuilder letrasUsadas = new StringBuilder();
        int fallos = 0;

        while (fallos < MAX_FALLOS && oculta.indexOf("_") != -1) {
            System.out.println("\n" + dibujo(fallos));
            System.out.println("Palabra: " + oculta.toString().replace("", " ").trim());
            System.out.println("Letras usadas: " + letrasUsadas);
            System.out.print("Introduce una letra: ");

            String entrada = sc.nextLine().trim().toLowerCase();

            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("Introduce una sola letra.");
                continue;
            }

            char letra = entrada.charAt(0);

            if (letrasUsadas.indexOf(String.valueOf(letra)) != -1) {
                System.out.println("Ya has usado esa letra.");
                continue;
            }

            letrasUsadas.append(letra).append(" ");

            if (palabra.indexOf(letra) != -1) {
                for (int i = 0; i < palabra.length(); i++) {
                    if (palabra.charAt(i) == letra) {
                        oculta.setCharAt(i, letra);
                    }
                }
                System.out.println("¡Bien! La letra está en la palabra.");
            } else {
                fallos++;
                System.out.println("La letra no está. Fallos: " + fallos + "/" + MAX_FALLOS);
            }
        }

        System.out.println("\n" + dibujo(fallos));
        if (oculta.indexOf("_") == -1) {
            System.out.println("¡Enhorabuena! Has adivinado: " + palabra);
        } else {
            System.out.println("Has perdido. La palabra era: " + palabra);
        }
    }

  /**
   * Devuelve el dibujo del ahorcado correspondiendo con el numero de fallos
   * 
   * @param fallos numero de letras erroneas que ha metido el jugador entre 0 y {@value #MAX_FALLOS}
   * @return el dibuujo del ahorcado
   * 
   **/
    private static String dibujo(int fallos) {
        String[] dibujos = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
        };
        return dibujos[fallos];
    }
}