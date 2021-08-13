package salondebelleza.entidadesdenegocio;
        
import java.time.LocalDate;
import java.util.ArrayList;

public class Cita {
    private int id;
    private int idUsuario;
    private int idCliente;
    private LocalDate fechaRegistrada;
    private LocalDate fechaCita;
    private double total;
    private byte estado;
     private int top_aux;
        private ArrayList<Cliente> clientes;
    private ArrayList<Usuario> usuarios;

    public Cita() {
    }

    public Cita(int id, int idUsuario, int idCliente, LocalDate fechaRegistrada, LocalDate fechaCita, double total, byte estado, int top_aux, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.fechaRegistrada = fechaRegistrada;
        this.fechaCita = fechaCita;
        this.total = total;
        this.estado = estado;
        this.top_aux = top_aux;
        this.clientes = clientes;
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaRegistrada() {
        return fechaRegistrada;
    }

    public void setFechaRegistrada(LocalDate fechaRegistrada) {
        this.fechaRegistrada = fechaRegistrada;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

   
       public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
      public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes ) {
        this.clientes = clientes;
    }
    
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}