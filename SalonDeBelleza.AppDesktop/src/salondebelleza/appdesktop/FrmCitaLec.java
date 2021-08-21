/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salondebelleza.appdesktop;

// Importaciones para el funcionamiento de la pantalla de mantenimiento de Usuario
import salondebelleza.appdesktop.utils.*; // importar todas las clases de utilidades de la aplicaciones escritorio
import salondebelleza.accesoadatos.*; // importar todas la clases de la capa de acceso a datos
import salondebelleza.entidadesdenegocio.*; // importar todas la clases de la capa de entidades de negocio
import java.util.ArrayList; // importar el ArrayList para recibir la lista de Usuarios de la base de datos
import javax.swing.JOptionPane; // importa la clase JOptionPane para mostrar alertas o advertencias a los usuarios
import javax.swing.table.DefaultTableModel; // importar la clase DefaultTableModel para llenar los datos de la tabla
import java.time.LocalDate;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FrmCitaLec extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Codigo para las clases,propiedades y metodos locales del formulario FrmUsuarioLec">
    private javax.swing.JFrame frmPadre; // Propiedad para almacenar la pantalla de Inicio del sistema

    // Crear la clase anidada ColumnaTabla para saber la posicion de las columnas en la tabla de datos
    public class ColumnaTabla {

        static final int ID = 0;
        static final int IDUSUARIO = 1;
        static final int IDCLIENTE = 2;
        static final int ESTADO = 3;
        static final int FECHAREGISTRADA = 4;
        static final int FECHACITA = 5;
        static final int FECHACITAREALIZADA = 6;
        static final int TOTAL = 7;
        static final int USUARIO = 8;
        static final int CLIENTE = 9;
        static final int ESTATUSSTR = 10;
    }

    // Metodo para ocultar columnas de nuestra tabla de datos
    private void ocultarColumnasDeLaTabla(int pColumna) {
        this.tbCitas.getColumnModel().getColumn(pColumna).setMaxWidth(0); // le dejamos en el ancho maximo de la tabla en cero en la columna
        this.tbCitas.getColumnModel().getColumn(pColumna).setMinWidth(0);// le dejamos en el ancho minimo de la tabla en cero  en la columna
        // le dejamos en el ancho maximo de la tabla en cero en el header
        this.tbCitas.getTableHeader().getColumnModel().getColumn(pColumna).setMaxWidth(0);
        // le dejamos en el ancho minimo de la tabla en cero  en el header
        this.tbCitas.getTableHeader().getColumnModel().getColumn(pColumna).setMinWidth(0);
    }

    // Metodo para iniciar los datos de la tabla a partir de una lista de Usuarios
    public void iniciarDatosDeLaTabla(ArrayList<Cita> pCitas) {
        // Iniciamos el DefaultTableModel utilizaremos para crear las columnas y los datos en nuestra tabla    
        DefaultTableModel model = new DefaultTableModel() {
            // aplicamos override al metodo isCellEditable para deshabilitar la edicion en la filas de la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // retornamos false para deshabilitar todas las fila y no puedan ser editables en la tabla de datos
            }
        };
        model.addColumn("Id"); // agregar la columna Id a la tabla de datos
        model.addColumn("IdUsuario"); // agregar la columna IdRol a la tabla de datos
        model.addColumn("IdCliente");
        model.addColumn("EstatusEnt");
         model.addColumn("FechaRegistrada");
        model.addColumn("FechaCita"); // agregar la columna EstatusEnt a la tabla de datos
        model.addColumn("FechaCitaRealizada"); // agregar la columna Login a la tabla de datos
        model.addColumn("Total"); // agregar la columna Nombre a la tabla de datos
        model.addColumn("Estado"); // agregar la columna Apellido a la tabla de datos

        model.addColumn("Usuario"); // agregar la columna Rol a la tabla de datos
        model.addColumn("Cliente"); // agregar la columna Estatus a la tabla de datos
        this.tbCitas.setModel(model);
        Object row[] = null;
        for (int i = 0; i < pCitas.size(); i++) {
            Cita cita = pCitas.get(i);
            model.addRow(row);
            model.setValueAt(cita.getId(), i, ColumnaTabla.ID); // agregar el valor de la columna Id en la fila
            model.setValueAt(cita.getEstado(), i, ColumnaTabla.ESTADO);
            model.setValueAt(cita.getIdUsuario(), i, ColumnaTabla.IDUSUARIO);
             model.setValueAt(cita.getIdCliente(), i, ColumnaTabla.IDCLIENTE);
            model.setValueAt(cita.getFechaRegistrada().toString(), i, ColumnaTabla.FECHAREGISTRADA);
            model.setValueAt(cita.getFechaCita(), i, ColumnaTabla.FECHACITA);
            model.setValueAt(cita.getFechaCitaRealizada(), i, ColumnaTabla.FECHACITAREALIZADA);
             model.setValueAt(cita.getTotal(), i, ColumnaTabla.TOTAL);
            model.setValueAt(cita.getUsuario().getNombre(), i, ColumnaTabla.USUARIO);
            model.setValueAt(cita.getCliente().getNombre(), i, ColumnaTabla.CLIENTE);
            if (cita.getEstado()== Cita.EstadoCita.ACTIVO) {
                model.setValueAt("ACTIVO", i, ColumnaTabla.ESTATUSSTR);
            } else if (cita.getEstado()== Usuario.EstadoUsuario.INACTIVO) {
                model.setValueAt("INACTIVO", i, ColumnaTabla.ESTATUSSTR);
            }
            
        }
        ocultarColumnasDeLaTabla(ColumnaTabla.ID); // Ocultar la columna de Id en la tabla 
        ocultarColumnasDeLaTabla(ColumnaTabla.ESTADO);
        ocultarColumnasDeLaTabla(ColumnaTabla.IDUSUARIO);
        ocultarColumnasDeLaTabla(ColumnaTabla.IDCLIENTE);
    }

    // Metodo para llenar la clase de Rol con los datos que tiene la fila seleccionada de la tabla
    private boolean llenarEntidadConLaFilaSeleccionadaDeLaTabla(Cita pCita) {
        int filaSelect; // variable para almacenar el indice de la fila seleccionada
        boolean isSelectRow = false; // variable para saber si esta seleccionada una fila o no
        filaSelect = this.tbCitas.getSelectedRow(); // obtener el indice de la fila seleccionada
        if (filaSelect != -1) { // verificar que se ha seleccionado una fila el cual la variable filaSelect debe ser diferente a -1
            isSelectRow = true; // colocar en true la variable isSelectRow porque si esta seleccionada una fila
            pCita.setId((int) this.tbCitas.getValueAt(filaSelect, ColumnaTabla.ID)); // agregar el valor de la columna Id a la propiedad Id
            pCita.setIdUsuario((int) this.tbCitas.getValueAt(filaSelect, ColumnaTabla.IDUSUARIO));
            pCita.setIdCliente((int) this.tbCitas.getValueAt(filaSelect, ColumnaTabla.IDCLIENTE));
            pCita.setFechaCita((Date) this.tbCitas.getValueAt(filaSelect, ColumnaTabla.FECHACITA));
            pCita.setFechaCitaRealizada((Date) this.tbCitas.getValueAt(filaSelect, ColumnaTabla.FECHACITAREALIZADA));
            pCita.setEstado((byte) this.tbCitas.getValueAt(filaSelect, ColumnaTabla.ESTADO));
            pCita.setUsuario(new Usuario());
            pCita.getUsuario().setNombre((String) this.tbCitas.getValueAt(filaSelect, ColumnaTabla.USUARIO));
        }
        return isSelectRow; // devolver el valor de isSelectRow 
    }

    // El metodo abrirFormularioDeEscritura lo utilizaremos para abrir el formulario de FrmRolEsc
    private void abrirFormularioDeEscritura(int pOpcionForm) {
        Cita cita = new Cita(); // Crear una instancia de la clase de Rol
        // Verificar si pOpcionForm es Crear abrimos el formulario de FrmUsuarioEsc
        // en el caso que la pOpcionForm sea diferente a Crear, se va a verificar el metodo de  llenarEntidadConLaFilaSeleccionadaDeLaTabla
        // para llenar la instancia de Usuario y verificar que este seleccionada una fila en la tabla de datos
        if (pOpcionForm == FormEscOpcion.CREAR || this.llenarEntidadConLaFilaSeleccionadaDeLaTabla(cita)) {
            // Abrir el formulario FrmUsuarioEsc utilizando el contructor lleno con los parametros de Usuario,OpcionForm y enviando el
            // formulario actual de FrmUsuarioEsc
//            FrmCitaEsc frmCitaEsc = new FrmCitaEsc(cita, pOpcionForm, this);
//            frmCitaEsc.setVisible(true); // Mostrar el formulario FrmUsuarioEsc
            this.setVisible(false); // ocultar el formulario FrmUsuarioLec
        } else {
            // en el caso que pOpcionForm sea diferente a Crear y el metodo llenarEntidadConLaFilaSeleccionadaDeLaTabla devuelva false
            // se le notificara al usuario del sistema que debe de seleccionar una fila de la tabla 
            JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila.");
        }

    }

    // metodo que se utilizara para buscar los datos en la base de datos al dar click en el boton de buscar
    private void buscar() {
        try {
            Cita citaSearch = new Cita();
            citaSearch.setTop_aux(TopRegistro.obtenerValorSeleccionado(cboTop));
            citaSearch.setFechaCita(this.campoFechaCita.getDate());
            citaSearch.setFechaCitaRealizada(this.campoFechaCitaRealizada.getDate());
            citaSearch.setTotal(Integer.parseInt(this.txtTotal.getText()));
            ItemsCombo itemsCbEstado = (ItemsCombo) cboEstado.getSelectedItem();
            citaSearch.setEstado((byte) itemsCbEstado.getId());
            ItemsCombo itemsCbUsuarios = (ItemsCombo) cboUsuarios.getSelectedItem();
            citaSearch.setIdUsuario(itemsCbUsuarios.getId());
            ItemsCombo itemsCbClientes = (ItemsCombo) cboClientes.getSelectedItem();
            citaSearch.setIdCliente(itemsCbClientes.getId());
            ArrayList<Cita> citasUsuario = CitaDAL.buscarIncluirUsuario(citaSearch); // buscar el usuario  en la base de datos
            ArrayList<Cita> citasCliente = CitaDAL.buscarIncluirCliente(citaSearch); // buscar el usuario  en la base de datos
            ArrayList<Cita> citasUsuariosCliente = CitaDAL.buscarIncluirUsuarioCliente(citaSearch); // buscar el usuario  en la base de datos
            iniciarDatosDeLaTabla(citasUsuariosCliente); // iniciar la tabla con los datos obtenidos en el metodo de buscar de la DAL de Usuario
        } catch (Exception ex) {
            // mostrar un error al usuario de pantalla en el caso que suceda al momento de obtener los datos
            JOptionPane.showMessageDialog(this, "Sucedio el siguiente error: " + ex.getMessage());
        }
    }

    // limpiar los controles que tienen la informacion a enviar a buscar los Usuarios en la base de datos
    private void limpiarControles() {
//           this.txtNumeroTelefono.setText(""); // limpiar la caja de texto 
//           this.txtDUI.setText(""); // limpiar la caja de texto 
//        this.txtNombre.setText(""); // limpiar la caja de texto 
//        TopRegistro.limpiarTopRegistro(cbTop); // iniciar el combo box de cbTop al valor 10
//        this.txtApellido.setText(""); // limpiar la caja de texto 
//        this.txtLogin.setText("");  // limpiar la caja de texto  
//        this.cbRol.setSelectedItem(new ItemsCombo(0, null));  // iniciar  el combo box Rol al valor 0  "SELECCIONAR"
//        this.cbEstado.setSelectedItem(new ItemsCombo(0, null)); // iniciar  el combo box Estatus al valor 0  "SELECCIONAR"
    }

    // cerrar el formulario de FrmUsuarioLec
    private void cerrarFormulario(boolean pIsEvtClosing) {
        if (frmPadre != null) {
            frmPadre.setEnabled(true); // habilitar el formulario de Inicio
        }
        if (pIsEvtClosing == false) {
            this.setVisible(false); // cerrar el formulario de FrmUsuarioLec
            this.dispose(); // cerrar el formulario de FrmUsuarioLec
        }
    }

    // Metodo para agregar los valor al combo box de Estatus
    public void iniciarComboEstatus(javax.swing.JComboBox<ItemsCombo> pJComboBox) {
        pJComboBox.addItem(new ItemsCombo(0, "SELECCIONAR"));
        pJComboBox.addItem(new ItemsCombo(Cita.EstadoCita.ACTIVO, "ACTIVO"));
        pJComboBox.addItem(new ItemsCombo(Cita.EstadoCita.INACTIVO, "INACTIVO"));
    }
    // Metodo para agregar los valor al combo box de Rol obteniendo los datos desde la base de datos

    public void iniciarComboUsuario(javax.swing.JComboBox<ItemsCombo> pJComboBox, javax.swing.JFrame pFrame) {
        pJComboBox.addItem(new ItemsCombo(0, "SELECCIONAR"));
        try {
            ArrayList<Usuario> usuarios = UsuarioDAL.obtenerTodos();
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuario = usuarios.get(i);
                pJComboBox.addItem(new ItemsCombo(usuario.getId(), usuario.getNombre()));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pFrame, "Sucedio el siguiente error: " + ex.getMessage());
        }
    }
    
    public void iniciarComboCliente(javax.swing.JComboBox<ItemsCombo> pJComboBox, javax.swing.JFrame pFrame) {
        pJComboBox.addItem(new ItemsCombo(0, "SELECCIONAR"));
        try {
            ArrayList<Cliente> clientes = ClienteDAL.obtenerTodos();
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                pJComboBox.addItem(new ItemsCombo(cliente.getId(), cliente.getNombre()));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(pFrame, "Sucedio el siguiente error: " + ex.getMessage());
        }
    }

    private void iniciarDatos(javax.swing.JFrame pFrmPadre) {
        frmPadre = pFrmPadre;
        pFrmPadre.setEnabled(true); // deshabilitar el formulario FrmInicio
        TopRegistro.llenarDatos(cboTop); // iniciar los datos del combo box cbTop con los valores a enviar en el top registro 
        iniciarComboEstatus(this.cboEstado); // iniciar del combo box estatus
        iniciarComboUsuario(this.cboUsuarios, this.frmPadre);  // iniciar del combo box Rol
        iniciarComboCliente(this.cboClientes, this.frmPadre);  // iniciar del combo box Rol
    }

    // contructor de la clae FrmRolLec que pide como parametro un JFrame
    // en nuestro caso pedira como parametro el formulario FrmInicio
    public FrmCitaLec(javax.swing.JFrame pFrmPadre) {
        initComponents();
        iniciarDatos(pFrmPadre); // llamar el metodo IniciarDatos para cargar los combo box y llenar propiedades iniciales del formulario
    }
// </editor-fold>
    
    /**
     * Creates new form FrmCitaLec
     */
    public FrmCitaLec() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboUsuarios = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cboClientes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboTop = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCitas = new javax.swing.JTable();
        campoFechaCita = new com.toedter.calendar.JDateChooser();
        campoFechaCitaRealizada = new com.toedter.calendar.JDateChooser();
        cboEstado = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtTotal.setText("0");

        jLabel1.setText("IdUsuario");

        jLabel2.setText("IdCliene");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel4.setText("FechaCita");

        jLabel5.setText("FechaCitaRealizada");

        jLabel6.setText("Total");

        jLabel7.setText("Estado");

        jLabel8.setText("Top");

        tbCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbCitas);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(28, 28, 28)
                                .addComponent(cboTop, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6))
                                    .addGap(117, 117, 117)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(58, 58, 58)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campoFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(31, 31, 31)
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campoFechaCitaRealizada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(campoFechaCitaRealizada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnVer)
                    .addComponent(btnCerrar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.buscar(); // llamar el metodo de buscar
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.limpiarControles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.abrirFormularioDeEscritura(FormEscOpcion.CREAR);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.abrirFormularioDeEscritura(FormEscOpcion.MODIFICAR);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.abrirFormularioDeEscritura(FormEscOpcion.ELIMINAR);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        this.abrirFormularioDeEscritura(FormEscOpcion.VER);
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.cerrarFormulario(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.cerrarFormulario(true);
    }//GEN-LAST:event_formWindowClosing
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVer;
    private com.toedter.calendar.JDateChooser campoFechaCita;
    private com.toedter.calendar.JDateChooser campoFechaCitaRealizada;
    private javax.swing.JComboBox<ItemsCombo> cboClientes;
    private javax.swing.JComboBox<ItemsCombo> cboEstado;
    private javax.swing.JComboBox<ItemsCombo> cboTop;
    private javax.swing.JComboBox<ItemsCombo> cboUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCitas;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
