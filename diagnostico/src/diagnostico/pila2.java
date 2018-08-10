/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagnostico;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author LUIS BOYSO
 */
public class pila2 {
    
    public static void main(String[] args) {
    
    
    Stack pila =  new Stack();
    
    pila.push(50);
    pila.push(24);
    pila.push(23);
    pila.push(67);
    
        System.out.println("El ultimo elemento de la pilas es:"+pila.peek());
   while(pila.empty()==false){
       System.out.println(pila.pop());
   }
    }
    
            


}
