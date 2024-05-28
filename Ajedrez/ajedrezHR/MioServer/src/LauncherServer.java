
import Presentacion.ModeloServer;
import java.io.IOException;

public class LauncherServer {
    
    private ModeloServer m;
    
    
    public LauncherServer() throws IOException{
        if(m == null){
            m = new ModeloServer();
        }
        m.iniciar();
    }
    
    public static void main(String[] args) throws IOException {
        new LauncherServer();
        
    }
    
    
}
