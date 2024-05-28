package Launcher;
import Presentacion.Modelo;
import java.io.IOException;

public class LauncherAjedrez {
    private Modelo m;
    
  public LauncherAjedrez() throws IOException {
     
     m = new Modelo();
     m.Iniciar();
  }
    
    
    public static void main(String[] args) throws IOException {

        new LauncherAjedrez();
         
    }
    
}
