package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        List<String> links = Arrays.asList("");
        Long timeStart = System.nanoTime();
        links.stream().parallel().forEach(Main::getContentUrls);
        Long timeEnd = System.nanoTime();
        System.out.println("Diferencia: " + (timeEnd - timeStart));
    }

    /**
     * Descargar webs
     *
     * @param link
     */
    public static void getContentUrls(String link)
    {
        try
        {
            URL url = new URL(link);
            //Hacer la conexion
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Traer la cabecera de la pagina web
            String encoding = conn.getContentEncoding();
            //Descargar el contenido de la pagina
            InputStream in = conn.getErrorStream();
            System.out.println(new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining()));
        } catch (IOException e)
        {
            System.out.println(e + "");
        }
    }
}