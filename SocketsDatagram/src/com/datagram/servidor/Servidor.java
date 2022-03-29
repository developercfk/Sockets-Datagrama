/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datagram.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Servidor {

    private DatagramSocket socketUDP ;
     
    private byte[] mensajeTobyte;
      
    private DatagramPacket peticionRecibir;
    
    private DatagramPacket peticionEnviar;
     
    private InetAddress inetAddress;
     
    private int puertoCliente;
     
    private String mensajeRecibido;
    
     private String mensajeEnviado;
    
    
    public Servidor() {
        
         System.out.println("--[  SERVIDOR  ]--");
        
        
    }
    
    public void inicioServidor(){
        
        
        try {
            
             socketUDP = new DatagramSocket(5656);
             
             mensajeTobyte = new byte[100];
             
             JOptionPane.showMessageDialog(null, "servidor connectado ");  
            
               while (true) { 
                   
               
            
               //abre el paquete para hacer su peticionRecibir
                peticionRecibir = new DatagramPacket(mensajeTobyte, mensajeTobyte.length);
                
                
                
                //pide a recibir un mensajeRecibido
                socketUDP.receive(peticionRecibir);
           
                System.out.println("--cliente connectado--");
                
                //convertir mensajeRecibido recibido en String
                mensajeRecibido = new String(peticionRecibir.getData());
                
                
                
                System.out.println("Mensaje recivido del cliente : " + new String(mensajeRecibido));
                
                //recuperar address del cliente
                inetAddress = peticionRecibir.getAddress();
                
                //recuperar puerto del cliente
                puertoCliente = peticionRecibir.getPort();
                
                mensajeEnviado = "Hola soy el servidor su mensaje se ha bien recibido.";
                
                mensajeTobyte = mensajeEnviado.getBytes();
            
                //abrir paquete para hacer su peticionRecibir
                peticionEnviar = new DatagramPacket(mensajeTobyte, mensajeTobyte.length,inetAddress,puertoCliente);
            
                Thread.sleep(2000);
            
                //pedir que envia un mensajeRecibido al cliente
                socketUDP.send(peticionEnviar);
                
                
            
               }
               
               
        } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, 
        "Se ha producido un error a la hora de comunicase con el cliente", 
        "Error comunicación",
        JOptionPane.WARNING_MESSAGE); 
             inicioServidor();
            
        } catch (InterruptedException ex) {
            System.err.println("El servidor se ha callido");
            ex.printStackTrace();
        }
        
     
    }
    
    
    
    
}
