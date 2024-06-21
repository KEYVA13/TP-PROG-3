package utils;

import java.util.ArrayList;

public class Procesador {
    
    private String id;
    private String codigo;
    private Boolean refrigerado;
    private Integer anio;
    private ArrayList<Tarea> tareas;
    int tareasCriticas;

    public Procesador(String id, String codigo, Boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.tareas = new ArrayList<>();
        this.tareasCriticas = 0;
    }

    public void addTarea(Tarea t){
        if(t.getCritica()){
            tareasCriticas ++;
        }
        this.tareas.add(t);

    }

    public void deleteTarea(Tarea t){
        if(t.getCritica()){
            tareasCriticas --;
        }
        this.tareas.remove(t);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(Boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public int getTareasCriticas(){
        return this.tareasCriticas;
    }

    public int tiempoTotal() {
        int total = 0;
        for (Tarea t : this.tareas) {
            total += t.getTiempo();
        }
        return total;
    }

}