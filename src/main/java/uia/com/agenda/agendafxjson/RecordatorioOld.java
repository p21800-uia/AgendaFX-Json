package uia.com.agenda.agendafxjson;


public class RecordatorioOld {

    private String tipo;
    private String name;
    private String fechaRecordatorio;
    private String fecha;


    public RecordatorioOld(String tipo, String name, String fechaRecordatorio, String fecha) {
        this.tipo = tipo;
        this.name = name;
        this.fechaRecordatorio = fechaRecordatorio;
        this.fecha = fecha;
    }

    public RecordatorioOld() {
    }


    public String getname() {
        return name;
    }

    public String getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }


    public void setTipo(String text) {
        this.tipo = text;

    }

    public void setname(String text) {
        this.name = text;

    }

    public void setFecha(String text) {
        this.fechaRecordatorio = text;
    }

    public void setFechaRecordatorio(String text) {
        this.fecha = text;

    }
}
