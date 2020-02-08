/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.util;

import me.carlosjai.ad04.obxectos.Franquicia;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class BackupUtils {

    public static void realizarBackup(Franquicia f) {
        try {
            File arquivo = new File("data.backup");
            FileOutputStream fileOut = new FileOutputStream(arquivo);
            ObjectOutputStream fluxoDatos = new ObjectOutputStream(fileOut);
            fluxoDatos.writeObject(f);
            fluxoDatos.close();
        } catch (IOException e) {
            System.out.println("Non se poido escribir no arquivo");
        }
    }

    public static Franquicia obterBackup() {
        Franquicia f = null;
        FileInputStream fileIn = null;
        try {
            File arquivo = new File("data.backup");
            fileIn = new FileInputStream(arquivo);
            ObjectInputStream fluxoDatos = new ObjectInputStream(fileIn);
            f = (Franquicia) fluxoDatos.readObject();
            fluxoDatos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Non existe o ficheiro data.backup");
        } catch (IOException ex) {
            System.out.println("Error de entrada ou saída do ficheiro");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao serializar o ficheiro");
        } finally {
            try {
                fileIn.close();
            } catch (IOException ex) {

            }
        }
        return f;
    }

}
