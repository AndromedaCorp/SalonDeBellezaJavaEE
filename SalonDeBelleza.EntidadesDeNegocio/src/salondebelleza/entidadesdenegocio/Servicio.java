package salondebelleza.entidadesdenegocio;

public class Servicio {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private double duracion;
    private int top_aux;

    public Servicio() {
    }

    public Servicio(int id, String nombre, String descripcion, double precio,double duracion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precioServicio) {
        this.precio = precioServicio;
    }
    
    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    
}
