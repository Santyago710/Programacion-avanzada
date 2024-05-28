package Logica.Reloj;


public class Reloj extends Thread{
    
    private int INICIO = 0;
    private int FINAL = 20; // el final va a ser de 60 segundos
    private int TIEMPO = 0; // añadimos el tiempo a usar
    
    
    public int getINICIO() {
        return INICIO;
    }

    public int getFINAL() {
        return FINAL;
    }
    
    public int getTiempo(){
        return TIEMPO;
    }
    public void setTiempo(int tiempo) {
        TIEMPO = tiempo; 
    }    
    @Override
    public void run(){
        try {
        while(true){ 
            // Ya que el reloj va fucnionar para siempre    
            
            sleep(1000); //dormimos un segundo
            TIEMPO++; // añadimos uno
        }   
        }
             catch (InterruptedException ex) {
            }
            
        }

  
       
 
}
