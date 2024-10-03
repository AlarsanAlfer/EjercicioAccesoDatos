package org.example;

import java.io.*;

public class FiltroPeliculas {

    /**
     * El siguiente metodo se encarga de leer el archivo peliculas.csv linea a linea,
     * comprobar si el año de la pelicula perteneciente a esa linea es mayor o menos al introducido como parametro
     * en el metodo, si es mayor, lo almacena en un stringbuilder, el cual usaremos para generar un nuevo
     * documento.
     **/


    public void filtrarPorAnho(int a){
        BufferedReader br = null;
        String linea;
        StringBuilder actualizado = new StringBuilder("");
        try {
            br = new BufferedReader(new FileReader("salida/peliculas.csv"));

            while((linea = br.readLine()) != null){
                String[] datos = linea.split(",");
                int anio = Integer.valueOf(datos[2]);
                if(anio>= a){
                    actualizado.append(linea).append("\n");  // Añadimos un salto de linea para seguir el formato original
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Esto nos permite comprobar si la carpeta salida existe, y si no es así, la crea.
        File carpetaSalida = new File("salida");
        if (!carpetaSalida.exists()) {
            carpetaSalida.mkdir();
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("salida/PeliculasPosterioresA"+a+".csv"));
            bw.write(actualizado.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }


}
