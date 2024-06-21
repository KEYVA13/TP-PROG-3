import java.util.ArrayList;

import utils.Procesador;
import utils.Tarea;

public class Backtracking {

    ArrayList<Procesador> procesadores;
    ArrayList<Tarea> tareas;
    int tiempoMaximoNoRefrigerado;
    Estado mejorSolucion;
    int solucionesEncontradas;

    public Backtracking(ArrayList<Procesador> procesadores,ArrayList<Tarea> tareas, int tiempoMaximoNoRefrigerado){
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoMaximoNoRefrigerado = tiempoMaximoNoRefrigerado;
        this.solucionesEncontradas = 0;
    }

    /*
    * Método público que llama a backtracking con un primer estado
    * al cual ya se le cargan todos los procesadores disponibles.
    * Se le pasa por parametro todas las tareas a asignar
    */

    public void back() {
        Estado primerEstado = new Estado();
        for (Procesador procesador : this.procesadores) {
            primerEstado.addProcesador(procesador);
        }
        this.back(primerEstado,tareas);
    }

    /*
    * Lo que hace esta funcion de backtracking es obtener todas las soluciones/combinaciones posibles
    * de tareas con cada procesador, cumpliendo las restricciones o condiciones solicitadas. Si todas
    * las tareas han sido asignadas, se comprueba si la asignación actual es mejor que la mejor solución
    * encontrada hasta el momento, si es así nos quedamos con esta última.
    */

    private void back(Estado estadoActual,ArrayList<Tarea> tareasRestantes) {
        if (todasLasTareasAsignadas(tareas)) {
            solucionesEncontradas++;
            if (mejorSolucion == null || esMejorSolucion(estadoActual)) {
                this.mejorSolucion = new Estado(estadoActual);
            }

        } else {

            Tarea tarea = tareasRestantes.remove(0);
            for (Procesador p : estadoActual.getProcesadores()) {
                if (puedeAsignarTarea(p, tarea)) {
                    p.addTarea(tarea);
                    back(estadoActual, tareasRestantes);
                    p.deleteTarea(tarea);
              }
            }
            tareasRestantes.add(0, tarea);

        }

    }

    private boolean puedeAsignarTarea(Procesador procesador, Tarea tarea) {
        // Restricción de tareas críticas

        if (tarea.getCritica() && procesador.getTareasCriticas() >= 2) {
            return false;
        }

        // Restricción de tiempo en procesadores no refrigerados
        if (!procesador.getRefrigerado()  && (procesador.tiempoTotal() + tarea.getTiempo() > tiempoMaximoNoRefrigerado)) {
            return false;
        }

        return true;
    }
    
    private boolean todasLasTareasAsignadas(ArrayList<Tarea> tareas){
        return tareas.isEmpty();
    }

    private boolean esMejorSolucion(Estado estadoActual) {
        return mejorSolucion == null || estadoActual.procesadorConMayorTiempo() < mejorSolucion.procesadorConMayorTiempo();
    }

    public Estado getMejorSolucion() {
        return mejorSolucion;
    }

    public int getSolucionesEncontradas(){
        return solucionesEncontradas;
    }
    
}