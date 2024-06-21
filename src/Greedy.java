import java.util.ArrayList;

import utils.Procesador;
import utils.Tarea;

public class Greedy {

    ArrayList<Tarea> tareas;
    ArrayList<Procesador> procesadores;
    int tiempoMaximoNoRefrigerado;
    int cantidadCandidatosConsiderados;

    public Greedy(ArrayList<Procesador> procesadores, ArrayList<Tarea> tareas, int tiempoMaximoNoRefrigerado) {
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoMaximoNoRefrigerado = tiempoMaximoNoRefrigerado;
        this.cantidadCandidatosConsiderados = 0;
    }

    /*
    * Método público que llama a greedy con un primer estado
    * al cual ya se le cargan todos los procesadores disponibles.
    * Se le pasa por parametro todas las tareas a asignar
    */
    public Estado greedy() {
        Estado estadoInicial = new Estado();
        for (Procesador procesador : this.procesadores) {
            estadoInicial.addProcesador(new Procesador(procesador.getId(), procesador.getCodigo(), procesador.getRefrigerado(), procesador.getAnio()));
        }
        return this.greedy(estadoInicial, new ArrayList<>(tareas));
    }

    /*
    * Lo que hace esta función de Greedy es asignar cada tarea al procesador que actualmente tenga el menor tiempo total
    * de ejecución, cumpliendo con las restricciones o condiciones solicitadas. En cada paso, el algoritmo toma una decisión
    * óptima, tratando de encontrar una solución rápida y eficiente. Aunque esta estrategia no garantiza encontrar
    * la MEJOR solución, proporciona una asignación rápida, basada en decisiones tomadas por el algoritmo que pensamos.
    */

    private Estado greedy(Estado estadoActual, ArrayList<Tarea> tareasRestantes) {
        while (!tareasRestantes.isEmpty()) {
            Tarea tarea = tareasRestantes.remove(0); // Selecciona la siguiente tarea a asignar
            Procesador mejorProcesador = null;
            for (Procesador p : estadoActual.getProcesadores()) {
                cantidadCandidatosConsiderados++;
                if (puedeAsignarTarea(p, tarea)) {
                    if (mejorProcesador == null || p.tiempoTotal() < mejorProcesador.tiempoTotal()) {
                        mejorProcesador = p;
                    }
                }
            }
            if (mejorProcesador != null) {
                mejorProcesador.addTarea(tarea);
            }
        }

        if (existeSolucion(estadoActual)) {
            return estadoActual;
        }
        return null;
    }

    private boolean existeSolucion(Estado estado) {
        // Verifica si todas las tareas han sido asignadas
        for (Procesador p : estado.getProcesadores()) {
            if (!p.getTareas().isEmpty()) {
                return true;
            }
        }
        return false;
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

    public int getcantidadCandidatosConsiderados(){
        return this.cantidadCandidatosConsiderados;
    }
}