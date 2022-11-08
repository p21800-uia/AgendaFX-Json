package uia.com.agenda.agendafxjson;

public class Evento extends Contacto{
    
    private String nombre="";
    private String fechaRecordatorio="";

    public Evento(String nombre, String fechaRecordatorio) {
        this.nombre=nombre;
        this.fechaRecordatorio=fechaRecordatorio;
    }

    public Evento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(String fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }
}
