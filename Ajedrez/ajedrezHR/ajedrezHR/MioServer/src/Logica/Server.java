package Logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    //Cliente
    private ServerSocket servidor; // es el servidor
    private final ArrayList<HiloCliente> ListaClientes; // lo creamos de tipo hilo cliente para que almacene hilos
    private Socket c;
    private DataInputStream datosEntrada;
    private DataOutputStream datosSalida;
    private final int puerto;
    //activo
    private boolean activo;
    
    public Server(){ // este es el constructor
        activo = false;
        puerto = 5000;
        ListaClientes = new ArrayList();
        }
    
    public void activar(boolean b){
        activo = b;
    }
    
    public void leerCliente() throws IOException{
        servidor = new ServerSocket(puerto); // establecemos el puerto
        if(activo){    
            System.out.println("Esperando conexiones..."); // esperamos conexines
        while(activo){
            c = servidor.accept(); // es un bloqueante, esperamos a que se aaceptado el cliente , ademas de crear al cliente
            datosEntrada = new DataInputStream(c.getInputStream());
            datosSalida = new DataOutputStream(c.getOutputStream()); 
            HiloCliente cliente = new HiloCliente(c,this,datosEntrada,datosSalida); // añadimos al cliente en un hilo
            ListaClientes.add(cliente); // y lo independizamos
            new Thread(cliente).start(); // Ponemos activo al cliente en un hilos
            
            if(ListaClientes.size()==2) break;
            }
        
        System.out.println("Jugadores Listos . . . ");
        
        //y ahora enviamos una señal para que cambie de pantalla: 
        for(HiloCliente hC : ListaClientes){
                   hC.enviarMensaje("TIME"); // hacemos que inicie en 0
         }
           

        }
     }
    
    public void broadcast(String mensaje, HiloCliente clienteExcluido){
        for(HiloCliente hC : ListaClientes){
               try {
                   if(!hC.equals(clienteExcluido)){
                   hC.enviarMensaje(mensaje);
                   }
               } catch (IOException ex) {
                   //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
               }
           
        }
    }
    
    
}
