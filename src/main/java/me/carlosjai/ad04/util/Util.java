package me.carlosjai.ad04.util;

import java.util.Scanner;

/**
 * @author carlos
 */
public class Util {

    /**
     * Devolve true se unha cadea está baleira
     *
     * @param cadea a analizar
     * @return
     */
    public static boolean estaBaleira(String cadea) {
        return cadea == null || cadea.isEmpty();
    }

    /**
     * Limpa a pantalla engadindo 1000 liñas en blanco
     */
    public static void limparPantalla() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("");
        }
    }

    /**
     * Para a execución ata que o usuario pulse unha tecla
     */
    public static void pausa() {
        System.out.println("Preme calquera tecla para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    /**
     * Proporciona un comportamento de confirmar (s/n)
     *
     * @param info información adicional a amosar antes da pregunta de
     * confirmación
     * @return
     */
    public static boolean confirmar(String info) {
        String cadea = "";
        System.out.println(info);
        do {
            cadea = pedirCadea("Introduza 's' para confirmar ou 'n' para cancelar: ");
            if (!cadea.equalsIgnoreCase("s") && !cadea.equalsIgnoreCase("n")) {
                System.out.println("Error: introduzca s ou n");
            }
        } while (!cadea.equalsIgnoreCase("s") && !cadea.equalsIgnoreCase("n"));
        return cadea.equalsIgnoreCase("s");
    }

    /**
     * Pide un enteiro por teclado ata que sexa correcto
     *
     * @param mensaxe a amosar no prompt
     * @return
     */
    public static int pedirEnteiro(String mensaxe) {
        Scanner sc = new Scanner(System.in);
        boolean correcto = false;
        int valor = -1;
        while (!correcto) {
            String resposta = pedirCadea(mensaxe);
            try {
                valor = Integer.parseInt(resposta);
                correcto = true;
            } catch (NumberFormatException ex) {
                System.out.println("Error: introduza un valor numérico");
            }
        }
        return valor;
    }

    /**
     * Pide un double por teclado ata que sexa correcto
     *
     * @param mensaxe a amosar no prompt
     * @return
     */
    public static double pedirDouble(String mensaxe) {
        Scanner sc = new Scanner(System.in);
        boolean correcto = false;
        double valor = -1;
        while (!correcto) {
            String resposta = pedirCadea(mensaxe);
            try {
                valor = Double.parseDouble(resposta);
                correcto = true;
            } catch (NumberFormatException ex) {
                System.out.println("Error: introduza un valor numérico");
            }
        }
        return valor;
    }

    /**
     * Pide unha cadea por teclado ata que sexa correcta
     *
     * @param mensaxe a amosar no prompt
     * @return
     */
    public static String pedirCadea(String mensaxe) {
        Scanner sc = new Scanner(System.in);;
        String cadea = "";
        while (Util.estaBaleira(cadea)) {
            System.out.print(mensaxe);
            cadea = sc.nextLine();
            if (Util.estaBaleira(cadea)) {
                System.out.println("ERROR: introduza un texto válido");
            }

        }
        return cadea;
    }
}
