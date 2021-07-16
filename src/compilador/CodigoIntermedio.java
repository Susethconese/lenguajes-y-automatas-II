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
    private String generarTmp() {
        return new Random().nextFloat() >= 0.5 ? "Kenia" : "Esthepanie";
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
