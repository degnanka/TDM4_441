package fr.esisar.exo3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy 
{
    public static void main(String[] args) throws Exception
    {
        FileCopy fileCopy = new FileCopy();
        fileCopy.execute("/home/ange/file1", "/home/ange/file2");
    }

    private void execute(String cheminsrc, String chemindest) throws IOException
    {
        System.out.println("Début de la copie du fichier");
        long start = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(cheminsrc);
        FileOutputStream fos = new FileOutputStream(chemindest);

        byte[] buf = new byte[1024];
        int len;
        
        while ((len = fis.read(buf)) != -1) 
        {
            fos.write(buf, 0, len);
        }

        fis.close();
        fos.close();

        long stop = System.currentTimeMillis();

        System.out.println("Fin de la copie du fichier");
        System.out.println("Temps de copie = " + (stop - start) + " ms");
    }
}
