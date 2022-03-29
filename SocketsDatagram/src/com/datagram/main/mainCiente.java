/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datagram.main;

import com.datagram.cliente.Cliente;

/**
 *
 * @author chris
 */
public class mainCiente {
    
    public static void main(String[] args) {
        
        Cliente client = new Cliente("localhost", 5656);
        
        client.inicio();
    }
}
