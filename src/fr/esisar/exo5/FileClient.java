package fr.esisar.exo5;

import java.io.*;
import java.net.Socket;

public class FileClient {
    public static void main(String[] args) throws Exception
    {
        FileClient client = new FileClient();
        client.startClient();
    }

    private void startClient() throws IOException
    {
        // Connexion au serveur
        String serverAddress = "localhost"; // ou l'adresse IP du serveur
        int port = 8080;
        Socket socket = new Socket(serverAddress, port);
        System.out.println("Connecté au serveur");

        // Fichier à recevoir du serveur
        String filePath = "/home/ange/filetest2.txt";
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        // Flux d'entrée pour lire les données du serveur
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        // Réception du fichier par blocs de 4 Ko
        byte[] buffer = new byte[4096];
        int bytesRead;
        long start = System.currentTimeMillis();
        while ((bytesRead = bis.read(buffer)) != -1) 
        {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush(); // S'assurer que tout est bien écrit dans le fichier

        // Fermer les ressources
        bos.close();
        bis.close();
        socket.close();

        long stop = System.currentTimeMillis();
        System.out.println("Fichier reçu, temps écoulé : " + (stop - start) + " ms");
    }
}
