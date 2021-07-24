/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author suset
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import utils.CodigoObjeto;
import utils.Cuadruplo;
import utils.Simbolo;
import views.codigoObjetoFrame;
import utils.TipoToken;
import utils.Token;
      
public class codigoObjeto {
    
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
    ArrayList<CodigoObjeto> CodObj;

    public codigoObjeto(ArrayList<Simbolo> listaSimbolos) {
        this.listaSimbolos = listaSimbolos;
        obtenerExpresiones();
        generarCodigoObjeto();
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
    
    private void generarCodigoObjeto() {
        for (Expresion ex : listaExpresiones) {
            cabeceraImpresion(ex);
            CodObj = new ArrayList<>();
            ArrayList<String> elementos = new ArrayList<>(Arrays.asList(ex.expresion.split("\\s+")));
            for (int i = 0; i < elementos.size(); i++) {
                if(elementos.get(i).equals("*")){
                    elementos.set(i, "MUL");
                }else if(elementos.get(i).equals("/")){
                    elementos.set(i,"DIV");
                }else if(elementos.get(i).equals("+")){
                    elementos.set(i,"ADD");
                }else if(elementos.get(i).equals("-")){
                    elementos.set(i,"SUB");
                }
                
            }
             
             ArrayList<String> MOV = new ArrayList<>();
            MOV.add("MOV");
            ArrayList<String> ET = new ArrayList<>();
            ET.add("AX");
            ET.add("BX");
            ET.add("CX");
            ET.add("DX");

  
             int con=0;
             for(int i = 0; i < elementos.size(); i++){
                 
                if(elementos.get(i).equals("MUL")||elementos.get(i).equals("DIV")){
                 if(CodObj.isEmpty()){
                CodObj.add(new CodigoObjeto(
                        MOV.get(0),
                        ET.get(con),
                        elementos.get(i-1)
                ));
                elementos.set(i-1,ET.get(con));
                
             }
                 
             }
                  
             }
             
             for(int i = 0; i < elementos.size(); i++){
                 
             if(elementos.get(i).equals("MUL")||elementos.get(i).equals("DIV")){
 
            elementos.set(i-1,ET.get(con)); 
            generarCodInt(elementos, i);
            
           
        }
             }
             for(int i = 0; i < elementos.size(); i++){
                 if(elementos.get(i).equals("ADD")||elementos.get(i).equals("SUB")){
               
            generarCodIntSR(elementos, i);
                 
           
        }
         
        }

           //System.out.print(elementos);
           cuerpoImpresion(ex);  
           finalImpresion(ex);
        }
        
    }
    
    
    
    
        private void generarCodInt(ArrayList<String> elementos, int i) {

        CodObj.add(new CodigoObjeto(
                elementos.get(i),       
                elementos.get(i + 1),   
                ""
        ));
  
        }
        
        private void generarCodIntSR(ArrayList<String> elementos, int i) {

        CodObj.add(new CodigoObjeto(
                elementos.get(i),
                elementos.get(i + 1), 
                elementos.get(i - 1)   
                
        ));
   
        }
    
    private void cabeceraImpresion(Expresion ex){
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%15s %15s %15s\n", "", ".MODEL", "Small");
        System.out.printf("%15s %15s %15s\n", "", ".STACK", "");
        System.out.printf("%15s %15s %15s\n", "", ".DATA",  "");
        System.out.println("--------------------------------------------------------------------------");
    }
     private void finalImpresion(Expresion ex){
        System.out.printf("%15s %15s %15s\n", "MOV", "AH,", "4CH");
        System.out.printf("%15s %15s %15s\n", "INT", "21H", "");
        System.out.printf("%15s %15s %15s\n", "MAIN", "ENDP", "");
        System.out.printf("%15s %15s %15s\n", "END", "MAIN",  "");
        System.out.println("--------------------------------------------------------------------------");
    }


    private void cuerpoImpresion(Expresion ex) {
        for (CodigoObjeto CodObjeto : CodObj) {
            System.out.format("%15s %15s %15s\n",CodObjeto.getEtiqueta() , CodObjeto.getRegistro1(), CodObjeto.getRegistro2());
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    
}
