package salondebelleza.entidadesdenegocio;

import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;

//propiedades de la clase
public class Cita {
    private int id;
    private int idUsuario;
    private int idCliente;
    private LocalDate fechaRegistrada;
    private LocalDate fechaCita;
    private LocalDate fechaCitaRealizada;
    private double total;
    private byte estado;
    private int top_aux;
    private Cliente clientes;
    private Usuario usuarios;

    //constructores de la clase
    public Cita() {
    }

    public Cita(int id, int idUsuario, int idCliente, LocalDate fechaRegistrada, LocalDate fechaCita,LocalDate fechaCitaRealizada, double total, byte estado, Cliente clientes, Usuario usuarios) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.fechaRegistrada = fechaRegistrada;
        this.fechaCita = fechaCita;
        this.fechaCitaRealizada = fechaCitaRealizada;
        this.total = total;
        this.estado = estado;
        this.clientes = clientes;
        this.usuarios = usuarios;
    }

 

//getter and setters
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

    public LocalDate getFechaCitaRealizada() {
        return fechaCitaRealizada;
    }

    public void setFechaCitaRealizada(LocalDate fechaCitaRealizada) {
        this.fechaCitaRealizada = fechaCitaRealizada;
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

      public Cliente getCliente() {
        return clientes;
    }

    public void setCliente(Cliente clientes ) {
        this.clientes = clientes;
    }

    public Usuario getUsuario() {
        return usuarios;
    }

    public void setUsuario(Usuario usuarios) {
        this.usuarios = usuarios;
    }

      public class EstadoCita {

        public static final byte ACTIVO = 1;
        public static final byte INACTIVO = 2;
    }

}