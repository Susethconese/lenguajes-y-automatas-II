/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Kenia Cordero
 */
import utils.Cuadruplo;
import utils.Simbolo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CodigoIntermedio {

    private class Expresion {
        public String id;
        public String expresion;

        private Expresion(String id, String expresion) {
            this.id = id;
            this.expresion = expresion;
        }
    }

    private class tmpValor {
        public String nombre;
        public double valor;

        private tmpValor(String nombre, double valor) {
            this.nombre = nombre;
            this.valor = valor;
        }
    }

    ArrayList<Simbolo> listaSimbolos;
    ArrayList<Expresion> listaExpresiones;
    ArrayList<Cuadruplo> listaCuadruplos;

    public CodigoIntermedio(ArrayList<Simbolo> listaSimbolos) {
        this.listaSimbolos = listaSimbolos;
        obtenerExpresiones();
        generarCuadruplos();
    }

    

    private void obtenerExpresiones() {
        listaExpresiones = new ArrayList<>();
        for (Simbolo simbolo : listaSimbolos) {
            String expresion = simbolo.getValor();
            // Cuádruplos: Expresión de 4 elementos
            if (expresion.split("\\s+").length >= 3) {
                listaExpresiones.add(new Expresion(simbolo.getIdentificador(), expresion));
            }
        }
    }
    
    private void generarCuadruplos() {
        for (Expresion ex : listaExpresiones) {
            cabeceraImpresion(ex);
            listaCuadruplos = new ArrayList<>();
            ArrayList<String> elementos = new ArrayList<>(Arrays.asList(ex.expresion.split("\\s+")));
            int contadorTmp = 1;
            for (int i = 0; i < elementos.size(); i++) {
                if (elementos.get(i).equals("/") || elementos.get(i).equals("*")) {
                    generarCuadruplo(elementos, i, contadorTmp);
                    contadorTmp++;
                    i = -1;
                } else if (!elementos.contains("/") && !elementos.contains("*")  && (elementos.get(i).equals("+") || elementos.get(i).equals("-"))) {
                    generarCuadruplo(elementos, i, contadorTmp);
                    contadorTmp++;
                    i = -1;
                    
                } else if (elementos.size() == 1) {
                    listaCuadruplos.add(new Cuadruplo(
                            ex.id + " :=",  // Operador
                            elementos.get(i),       // Operador1
                            "",            // Operador2
                            ""              // Resultado
                    ));
                }
               
            }
            listaCuadruplos.add(new Cuadruplo(
                            "=" ,  // Operador
                            listaCuadruplos.get(listaCuadruplos.size()-1).toString(),       // Operador1
                            "",            // Operador2
                            ex.id             // Resultado
                    ));
            cuerpoImpresion(ex);
        }
    }
    
    private String generarTmp() {
        return new Random().nextFloat() >= 0.5 ? "Kenia" : "Esthepanie";
    }

    private void generarCuadruplo(ArrayList<String> elementos, int i, int contador) {
        String res = generarTmp() + contador;
        listaCuadruplos.add(new Cuadruplo(
                elementos.get(i),       
                elementos.get(i - 1),   
                elementos.get(i + 1),   
                res// Resultado
        ));
        elementos.set(i, res);
        elementos.remove(i + 1);
        elementos.remove(i - 1);

    }
    
    private void cabeceraImpresion(Expresion ex){
        System.out.println("---------- Expresión -----------------------------------------------------");
        System.out.println(ex.id + " = " + ex.expresion);
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%15s %15s %15s %15s\n", "Operador", "Operando1", "Operando2", "Resultado");
        System.out.println("--------------------------------------------------------------------------");
    }

    private void cuerpoImpresion(Expresion ex) {
        for (Cuadruplo cuadruplo : listaCuadruplos) {
            System.out.format("%15s %15s %15s %15s \n",cuadruplo.getOperador() , cuadruplo.getOperador1(), cuadruplo.getOperador2(), cuadruplo.getResultado());
        }
        System.out.println("--------------------------------------------------------------------------");
    }

}
