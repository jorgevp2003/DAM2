package es.ciudadescolar;

import es.ciudadescolar.utils.MessageManager;

public class Programa {
    public static void main(String[] args) throws Exception {
        MessageManager MM = new MessageManager();
        System.out.println(MM.getTitulo()); 
        System.getProperty("user.name");
    }
   
}
