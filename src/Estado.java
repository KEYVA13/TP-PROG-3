import java.util.ArrayList;

import utils.Procesador;

public class Estado {

    ArrayList<Procesador> procesadores;

    public Estado(){
        this.procesadores = new ArrayList<>();
    }

    /* esto es para realizar una copia */
    public Estado(Estado otro) {
        this.procesadores = new ArrayList<>();
        for (Procesador p : otro.getProcesadores()) {
            Procesador copia = new Procesador(p.getId(), p.getCodigo(), p.getRefrigerado(), p.getAnio());
            copia.getTareas().addAll(p.getTareas());
            this.procesadores.add(copia);
        }
    }


    public void addProcesador(Procesador p){
        this.procesadores.add(p);
    }

    public ArrayList<Procesador> getProcesadores() {
        return procesadores;
    }

    public int procesadorConMayorTiempo(){
        int tiempoMayor = 0;
        for (Procesador p : this.procesadores) {
            if(p.tiempoTotal() > tiempoMayor )
                tiempoMayor = p.tiempoTotal();
        }
        return tiempoMayor;
    }

}