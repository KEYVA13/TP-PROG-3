import java.util.ArrayList;
import java.util.List;

import utils.Procesador;
import utils.Tarea;

public class Main {
	public static void main(String args[]) {
		// Crear instancias de Servicios y Backtracking
		Servicios servicios = new Servicios("src/datasets/Procesadores.csv", "src/datasets/Tareas.csv");
		
		// Probar Servicio 1: Obtener información de una tarea por ID
		System.out.println("Servicio 1: Información de la tarea con ID 'T1'");
		Tarea tarea = servicios.servicio1("T1");
		System.out.println("ID: " + tarea.getId() + ", Nombre: " + tarea.getNombre() + ", Tiempo: " + tarea.getTiempo() + 
						   ", Crítica: " + tarea.getCritica() + ", Prioridad: " + tarea.getPrioridad());
	
		// Probar Servicio 2: Listar todas las tareas críticas
		System.out.println("\nServicio 2: Listado de tareas críticas");
		List<Tarea> tareasCriticas = servicios.servicio2(true);
		for (Tarea t : tareasCriticas) {
			System.out.println("ID: " + t.getId() + ", Nombre: " + t.getNombre());
		}
	
		// Probar Servicio 3: Obtener tareas entre dos niveles de prioridad
		System.out.println("\nServicio 3: Listado de tareas con prioridad entre 3 y 7");
		List<Tarea> tareasPrioridad = servicios.servicio3(3, 7);
		for (Tarea t : tareasPrioridad) {
			System.out.println("ID: " + t.getId() + ", Nombre: " + t.getNombre() + ", Prioridad: " + t.getPrioridad());
		}
	
		// Ejecutar algoritmo de backtracking para la asignación de tareas
		System.out.println("\nEjecutando algoritmo de Backtracking...");
		Backtracking backtracking = new Backtracking(servicios.getProcesadores(), new ArrayList<>(servicios.getTareas().values()), 260);
		backtracking.back();
		
		Estado mejorSolucion = backtracking.getMejorSolucion();
		if (mejorSolucion != null) {
			System.out.println("Mejor Solución Encontrada:");
			System.out.println();
			int mayorTiempo = 0;
			for (Procesador p : mejorSolucion.getProcesadores()) {
				if(p.tiempoTotal() > mayorTiempo){
					mayorTiempo = p.tiempoTotal();
				}
				System.out.println("Procesador " + p.getId() + " (" + (p.getRefrigerado() ? "Refrigerado" : "No Refrigerado") + ")");
				for (Tarea t : p.getTareas()) {
					System.out.println("  Tarea " + t.getId() + ":"  + " (" + t.getTiempo() + " unidades de tiempo)");
				}
				System.out.println("Tiempo total: " + p.tiempoTotal());
				System.out.println();
			}
			System.out.println("---------------------------------------------------------");
			System.out.println("Tiempo Máximo de ejecución: " + mayorTiempo);
			System.out.println();
			System.out.println("Costo de la solución (cantidad de estados generados): " + backtracking.getSolucionesEncontradas());
		} else {
			System.out.println("No se encontró ninguna solución.");
		}

		// Ejecutar algoritmo Greedy para la asignación de tareas
        System.out.println("\nEjecutando algoritmo de Greedy...");
        Greedy greedy = new Greedy(servicios.getProcesadores(), new ArrayList<>(servicios.getTareas().values()), 260);
        Estado solucionGreedy = greedy.greedy();

        if (solucionGreedy != null) {
            System.out.println("Solución Greedy Encontrada:");
            System.out.println();
            int mayorTiempoGreedy = 0;
            for (Procesador p : solucionGreedy.getProcesadores()) {
                if(p.tiempoTotal() > mayorTiempoGreedy){
                    mayorTiempoGreedy = p.tiempoTotal();
                }
                System.out.println("Procesador " + p.getId() + " (" + (p.getRefrigerado() ? "Refrigerado" : "No Refrigerado") + ")");
                for (Tarea t : p.getTareas()) {
                    System.out.println("  Tarea " + t.getId() + ":"  + " (" + t.getTiempo() + " unidades de tiempo)");
                }
                System.out.println("Tiempo total: " + p.tiempoTotal());
                System.out.println();
            }
            System.out.println("---------------------------------------------------------");
            System.out.println("Tiempo Máximo de ejecución: " + mayorTiempoGreedy);
			System.out.println("costo de la solución (cantidad de candidatos considerados): " + greedy.getcantidadCandidatosConsiderados());
        } else {
            System.out.println("No se encontró ninguna solución.");
        }
	}
}