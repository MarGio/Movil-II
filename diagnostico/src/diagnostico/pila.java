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
public class pila {
    
    public static void main(String[] args) {
 
        Arreglo objeto=new Arreglo();
 
        Scanner opcion = new Scanner(System.in);
        int seleccion;
 
        do
        {
 
          System.out.println("Menu de pila");//Creamos un menú sencillo para la pila
          System.out.println("1. Push");//Llamamos al método para insertar al tope
          System.out.println("2. Pop");//Llamamos al método para sacar el tope de la pila
          System.out.println("3. Mostrar completa");//Ver la pila completa
          System.out.println("4. peek");
           System.out.println("5. Salir");//Salir de nuestro pequeño menú principal
          
 
          System.out.println("Teclee la opcion");
          seleccion=opcion.nextInt();
 
          switch(seleccion){
              case 1:
                  objeto.push();
                  break;
              case 2:
                  objeto.pop();
                  break;
              case 3:
                  objeto.ver();
                  break;
                  
              case 4:
                  objeto.peek();
                  break;
              
              default:
                  System.out.println("Error\nOpcion invalida");
          }
 
        }while(seleccion!=5);
 
    }
}

