package fr.esisar.exo5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServeur{
    public static void main(String[] args) throws Exception
    {
        FileServeur server = new FileServeur();
        server.startServer();
    }

    private void startServer() throws IOException{
        // Définir le port du serveur
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Serveur démarré sur le port " + port);

        // Attente de connexion client
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connecté");

        // Fichier à envoyer au client
        String filePath = "/home/ange/filetest.txt";
        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Flux de sortie pour envoyer des données au client
        OutputStream os = clientSocket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);

        // Envoi du fichier par blocs de 4 Ko
        byte[] buffer = new byte[4096];
        int bytesRead;
        long start = System.currentTimeMillis();
        while ((bytesRead = bis.read(buffer)) != -1) 
        {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush(); // S'assurer que tous les octets sont envoyés

        // Fermer les ressources
        bis.close();
        bos.close();
        clientSocket.close();
        serverSocket.close();

        long stop = System.currentTimeMillis();
        System.out.println("Fichier envoyé, temps écoulé : " + (stop - start) + " ms");
    }
}
