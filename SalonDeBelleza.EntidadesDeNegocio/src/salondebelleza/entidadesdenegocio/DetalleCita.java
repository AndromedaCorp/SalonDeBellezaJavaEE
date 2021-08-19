
package salondebelleza.entidadesdenegocio;

//import java.util.ArrayList;

public class DetalleCita {
    
    private int id;
    private int idCita;
    private int idServicio;
    private double Precio;
    private double Duracion;
    private Servicio servicio;
    private Cita cita;
    private int top_aux;
    public byte TipoAccion_Aux;
    
    

    public DetalleCita() {
    }

    public DetalleCita(int id, int idCita, int idServicio, double Precio, double Duracion, Servicio servicio, Cita cita) {
        this.id = id;
        this.idCita = idCita;
        this.idServicio = idServicio;
        this.Precio = Precio;
        this.Duracion = Duracion;
        this.servicio = servicio;
        this.cita = cita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public double getDuracion() {
        return Duracion;
    }

    public void setDuracion(double Duracion) {
        this.Duracion = Duracion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
    
    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public byte getTipoAccion_Aux() {
        return TipoAccion_Aux;
    }

    public void setTipoAccion_Aux(byte TipoAccion_Aux) {
        this.TipoAccion_Aux = TipoAccion_Aux;
    }
    
}
