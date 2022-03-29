
package com.datagram.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Cliente {
  
    private DatagramSocket socketUDP;  
    
    private final int puerto;
    
    private InetAddress inetAddress;
    
    private final String host;
    
    private byte[] mensajeTobyte;
    
    private DatagramPacket peticionEnviar;
    
     private DatagramPacket peticionRecivir;
    
    

    public Cliente(String host, int puerto) {
        
        System.out.println("--[  CLIENTE  ]--");
        this.host = host;
        this.puerto = puerto;
       
    }
    
    
    public void inicio(){
        
        try {
            
            //instaciation de un socket
            socketUDP = new DatagramSocket();
            
            //instancia adress
            inetAddress = InetAddress.getByName(host);
            
            //abre una boita de dialog y me recupera datos entrada por el cliente.
           
            String mensaje = JOptionPane.showInputDialog(null, "Escribe un mensage :"); 
            
            //convierte mensaje en un array de byte.
            mensajeTobyte = mensaje.getBytes();
            
            //cargo todos los datos que el cliente ha entrado con sus tamaño, relacionado con adress y el puerto del servidor 
            peticionEnviar = new DatagramPacket(mensajeTobyte, mensajeTobyte.length , inetAddress, puerto);
            
            
            
            //pedir  peticionEnviar para enviar un mensaje al servidor
            socketUDP.send(peticionEnviar);
            System.out.println("Mensaje bien enviado  : " + new String(peticionEnviar.getData()));
            
            //abre el paquete para pedir lo que ha mandado el servidor
            peticionRecivir= new DatagramPacket(mensajeTobyte, mensajeTobyte.length);
            
             //le digo a mi cliente que espera 5s que el servidor le responde y si no le responde el tiempo agotado que se desconecta y me aviso que no ha recivido nada.
            socketUDP.setSoTimeout(5000);
            
            //recive el mensaje del servidor
            socketUDP.receive(peticionRecivir);
            
            String str = new String(peticionRecivir.getData());
                    
            
            JOptionPane.showMessageDialog(null, "Mensaje recivido del servidor : " + str);   
            
               
                
              int option =   JOptionPane.showConfirmDialog(null, " ¿ Quieres escribir otro mensaje ?", "Connectado...", 0);
              
              if(option == 0){
                  
                  mensaje = "";
                  socketUDP.close();
                  inicio();
              }else{ 
                  
                  System.out.println("Cliente desconnectado...");
              }
            
            
            
            
        } catch (SocketException ex) {
            
                    JOptionPane.showMessageDialog(null, 
        "Se ha producido un error a la hora de comunicarse con el servidor porfa ententa más tarde", 
        "Error comunicacón",
        JOptionPane.WARNING_MESSAGE); 
             
            
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, 
             "no se encuentra el hosting ("+ host +") . nos desconnectamos", 
             "Alerta", 
             JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
      
             
             JOptionPane.showMessageDialog(null, 
             "El servidor no responde. nos desconnectamos", 
             "Alerta", 
             JOptionPane.ERROR_MESSAGE);
        }catch(NullPointerException ex){
            
            System.err.println("Error de communicación. nos desconectamos");
        }
        
    }
    
    
    
}
