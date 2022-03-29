/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datagram.main;

import com.datagram.servidor.Servidor;

/**
 *
 * @author chris
 */
public class main {
    
    
    public static void main(String[] args) {
        
        Servidor server = new Servidor();
        
        server.inicioServidor();
    }
}
