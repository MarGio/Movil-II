/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnostico;

import java.util.Scanner;

/**
 *
 * @author mario
 */
public class Arreglo {
    int pila1[]=new int[4];
    int tope=0;
     Scanner teclado = new Scanner(System.in);
    public int push(){
 
        if(tope>=pila1.length){
            System.out.println("la pila está llena");
        }else
        {
           
            System.out.println("ingrese el dato");
            pila1[tope]=teclado.nextInt();
            tope+=1;
        }
        return tope;
 
    }
    
    public void peek(){
        if(tope>0){
            System.out.println("|"+pila1[tope-1]+"|");
        }else{
            System.out.println("No hay nada");
        }
    }
 
    public int pop(){
 
        if(tope==0){
            System.out.println("La pila esta vacia");
        }else{
            System.out.println("Se elimino un elemento de la pila");
            pila1[tope-1]=0;
            tope-=1;
        }
        return tope;
    }
 
    public void ver(){
 
        for(int tope=3;tope>=0;tope--){
            System.out.println("Datos de la pila: "+pila1[tope]);
        }
    }
}
