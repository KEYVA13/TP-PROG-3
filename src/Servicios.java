import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.CSVReader;
import utils.Procesador;
import utils.Tarea;
/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private ArrayList<Procesador> procesadores;
	private HashMap<String, Tarea> tareas;

	private ArrayList<Tarea> tareasCriticas;
	private ArrayList<Tarea> tareasNoCriticas;

	/*
     * La complejidad temporal del Constructor es O(n).
     */
	public Servicios(String pathProcesadores, String pathTareas){

		this.tareasCriticas = new ArrayList<>();
		this.tareasNoCriticas = new ArrayList<>();
		this.tareas = new HashMap<>();

		CSVReader reader = new CSVReader();
		this.procesadores = reader.readProcessors(pathProcesadores);
		ArrayList<Tarea> tareas = reader.readTasks(pathTareas);

		for ( Tarea tarea : tareas) {
			this.tareas.put(tarea.getId(), tarea);

			if(tarea.getCritica()){
				tareasCriticas.add(tarea);
			}else{
				tareasNoCriticas.add(tarea);
			}
		}
	}
	
	/*
     * Complejidad temporal o(1).
     */
	public Tarea servicio1(String ID) {	
		// Servicio 1: Dado un identificador de tarea obtener toda la información de la tarea asociada
	
		return tareas.get(ID);
	}
    
    /*
     * Complejidad temporal o(1).
	 * La desventaja de esto es que no devolvemos una copia por lo tanto nos pueden modificar las tareas desde el main, 
	 * si realizamos la copia la complejidad aumenta a O(n) por eso decidimos quedarnos con esta opcion
     */
	public List<Tarea> servicio2(boolean esCritica) {
		// Servicio 2: Permitir que el usuario decida si quiere ver todas las tareas críticas o no críticas y generar 
		// el listado apropiado resultante.´{}

		if(esCritica){
			return this.tareasCriticas;
		}else{
			return this.tareasNoCriticas;
		}
	}

    /*
     * Complejidad temporal o(n).
	 * Lo podriamos realizar en un array donde lo ordenamos y 
	 * hacemos las busquedas mas eficientes pero el peor caso seguiria siendo o(N),
	 * como deberiamos implementar varias cosas por el momento decidimos omitirlo.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		//Servicio 3: Obtener todas las tareas entre 2 niveles de prioridad indicados
		
		ArrayList<Tarea> tareasQueCumplen = new ArrayList<>();

		for (Tarea tarea : tareas.values()) {
			if(tarea.getPrioridad() > prioridadInferior && tarea.getPrioridad() < prioridadSuperior){
				tareasQueCumplen.add(tarea);
			}
		}

		return tareasQueCumplen;
	}

	public ArrayList<Procesador> getProcesadores() {
        return procesadores;
    }

    public HashMap<String, Tarea> getTareas() {
        return tareas;
    }

}
