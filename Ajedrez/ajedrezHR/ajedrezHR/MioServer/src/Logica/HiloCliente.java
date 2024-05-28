package Logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HiloCliente implements Runnable{
    
    private final Server server;
    private final Socket cliente;
    private final DataInputStream datosEntrada;
    private final DataOutputStream datosSalida;
    
    private boolean conectado;
    
    
    public HiloCliente(Socket s, Server server,DataInputStream de,DataOutputStream ds) throws IOException{
        conectado = false;
        this.server = server;
        cliente = s;
        datosEntrada = de;
        datosSalida = ds;
                
        conectado = true;
    }
    
    public Socket getSocket(){
        return cliente;
    }
    
  @Override  
    public void run(){
        try {
         while(conectado){
            
                String mensaje = datosEntrada.readUTF();
                System.out.println("Mensaje recibido"+mensaje);
                server.broadcast(mensaje,this);
            } 
        }catch (IOException ex) {
                Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
    
    public void enviarMensaje(String mensaje) throws IOException{
        datosSalida.writeUTF(mensaje);
    }
    

   }
