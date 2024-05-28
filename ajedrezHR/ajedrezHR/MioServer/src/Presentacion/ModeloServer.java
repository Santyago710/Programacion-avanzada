
package Presentacion;

import Logica.Server;
import java.io.IOException;


public class ModeloServer {
    private Server s;
    
    public Server getServidor(){
        if(s == null){
            s = new Server();
        }
        return s;
    }
    
    
    public void iniciar() throws IOException{
        getServidor().activar(true);
        getServidor().leerCliente();
        getServidor().activar(false);
    
    }
    
    
    
}
