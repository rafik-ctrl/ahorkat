package juego;

import java.util.Random;
import java.util.Scanner;

public class Ahorcado {

    private static final String[] LISTA_PALABRAS = {
        "programacion", "java", "ordenador", "teclado", "variable",
        "git", "algoritmo", "servidor", "compilador", "bucle"
    };

    private static final int LIMITE_ERRORES = 6;

    public static void main(String[] argumentos) {
        Scanner teclado = new Scanner(System.in);
        Random generador = new Random();
        boolean continuarPartida = true;

        System.out.println("=== JUEGO DEL AHORCADO ===");

        while (continuarPartida) {
            String palabraSecreta =
                LISTA_PALABRAS[generador.nextInt(LISTA_PALABRAS.length)];

            iniciarPartida(palabraSecreta, teclado);

            System.out.print("¿Quieres jugar otra vez? (s/n): ");
            String opcion = teclado.nextLine().trim().toLowerCase();
            continuarPartida = opcion.equals("s");
        }

        System.out.println("¡Hasta la próxima!");
        teclado.close();
    }

    private static void iniciarPartida(String palabraSecreta, Scanner teclado) {
        StringBuilder palabraOculta =
            new StringBuilder("_".repeat(palabraSecreta.length()));

        StringBuilder letrasIntentadas = new StringBuilder();
        int errores = 0;

        while (errores < LIMITE_ERRORES && palabraOculta.indexOf("_") != -1) {
            System.out.println("\n" + obtenerDibujo(errores));
            System.out.println(
                "Palabra: " + palabraOculta.toString().replace("", " ").trim()
            );
            System.out.println("Letras usadas: " + letrasIntentadas);
            System.out.print("Introduce una letra: ");

            String textoIntroducido = teclado.nextLine().trim().toLowerCase();

            if (textoIntroducido.length() != 1
                    || !Character.isLetter(textoIntroducido.charAt(0))) {
                System.out.println("Introduce una sola letra.");
                continue;
            }

            char letraElegida = textoIntroducido.charAt(0);

            if (letrasIntentadas.indexOf(String.valueOf(letraElegida)) != -1) {
                System.out.println("Ya has usado esa letra.");
                continue;
            }

            letrasIntentadas.append(letraElegida).append(" ");

            if (palabraSecreta.indexOf(letraElegida) != -1) {
                for (int posicion = 0; posicion < palabraSecreta.length(); posicion++) {
                    if (palabraSecreta.charAt(posicion) == letraElegida) {
                        palabraOculta.setCharAt(posicion, letraElegida);
                    }
                }

                System.out.println("¡Bien! La letra está en la palabra.");
            } else {
                errores++;
                System.out.println(
                    "La letra no está. Fallos: " + errores + "/" + LIMITE_ERRORES
                );
            }
        }

        System.out.println("\n" + obtenerDibujo(errores));

        if (palabraOculta.indexOf("_") == -1) {
            System.out.println("¡Enhorabuena! Has adivinado: " + palabraSecreta);
        } else {
            System.out.println("Has perdido. La palabra era: " + palabraSecreta);
        }
    }

    private static String obtenerDibujo(int errores) {
        String[] etapasAhorcado = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
        };

        return etapasAhorcado[errores];
    }
}