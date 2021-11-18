package Modelo;

public class ComodinTimer extends Thread{

    final int MINUTOS = 1;
    int time = 60*MINUTOS;

    @Override
    public void run() {
        while (true){
            try {
                sleep(time*1000);
                Partida.getInstance().comodinTimer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
