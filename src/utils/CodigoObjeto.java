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
public class CodigoObjeto {
     private String etiqueta;
    private String registro1;
    private String registro2;
   

  
    public CodigoObjeto(String etiqueta, String registro1, String registro2) {
        this.etiqueta = etiqueta;
        this.registro1 = registro1;
        this.registro2 = registro2;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getRegistro1() {
        return registro1;
    }

    public String getRegistro2() {
        return registro2;
    }

}
