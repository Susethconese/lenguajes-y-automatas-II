/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Kenia Cordero
 */


public class Cuadruplo {

    private String operador;
    private String operador1;
    private String operador2;
    private String resultado;
   

  
    public Cuadruplo(String operador, String operador1, String operador2, String resultado) {
        this.operador = operador;
        this.operador1 = operador1;
        this.operador2 = operador2;
        this.resultado = resultado;
    }

    public String getOperador() {
        return operador;
    }

    public String getOperador1() {
        return operador1;
    }

    @Override
    public String toString() {
        return  resultado ;
    }

    public String getOperador2() {
        return operador2;
    }

    public String getResultado() {
        return resultado;
    }
     
}
