/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salondebelleza.accesoadatos;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import salondebelleza.entidadesdenegocio.Cita;
import salondebelleza.entidadesdenegocio.Cliente;
import salondebelleza.entidadesdenegocio.Rol;
import salondebelleza.entidadesdenegocio.Usuario;

;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CitaDALIT {
    private static Cita citaActual;
    public CitaDALIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     /**
     * Test of crear method, of class ServicioDAL.
     */
    @Test
    public void test1Crear() throws Exception {   
          int randomNum = (int) (Math.random() * 1000);      
        System.out.println("crear");
        Cita cita = new Cita();
        cita.setIdUsuario(randomNum);
        cita.setIdCliente(1);
//        cita.setFechaRegistrada(LocalDate.now());
//        cita.setFechaCita(LocalDate.now());
        cita.setTotal(1);
        cita.setEstado(Usuario.EstadoUsuario.INACTIVO);   
        cita.setTop_aux(1);
//        Rol rolB = new Rol();
//        rolB.setTop_aux(1);
//        cita.setIdrol(RolDAL.buscar(rolB).get(0).getId());
//        int expResult = 0;
//        int result = UsuarioDAL.crear(usuario);
//        assertNotEquals(expResult, result);
    }
    
    public int testIndividualQuerySelect(Cita pCita) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("",null, 0);
        CitaDAL.querySelect(pCita, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }
    
    /**
     * Test of querySelect method, of class ServicioDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        Cita pCita = new Cita();
        pCita.setId(1);
        assertTrue(testIndividualQuerySelect(pCita) == 1);
        pCita.setIdUsuario(1);
        assertTrue(testIndividualQuerySelect(pCita) == 2);
        pCita.setTotal(1);
        assertTrue(testIndividualQuerySelect(pCita) == 3);
         pCita.setEstado((byte) 1);
        assertTrue(testIndividualQuerySelect(pCita) == 4);
        ;
    }
    
    /**
     * Test of buscar method, of class ServicioDAL.
     */
    @Test
    public void test3Buscar() throws Exception {
        System.out.println("buscar");
        
        Cita cita = new Cita();
//         usuario.setIdrol(1);
//        cita.setTotal(1);
        cita.setEstado(Cita.EstadoUsuario.ACTIVO);
        cita.setTop_aux(1);
        ArrayList<Cita> result = CitaDAL.buscar(cita);
//        ArrayList<Cita> result = new ArrayList(CitaDAL.crear(cita));
//        System.out.println(result.get(0));
        assertTrue(result.size()> 0);
        citaActual = result.get(0);
    }
    
    /**
     * Test of obtenerPorId method, of class ServicioDAL.
     */
    @Test
    public void test4ObtenerPorId() throws Exception {
        System.out.println("obtenerPorId");
        Cita result = CitaDAL.obtenerPorId(citaActual);
        assertEquals(citaActual.getId(), result.getId());
    }

    /**
     * Test of modificar method, of class ServicioDAL.
     */ 
    @Test
    public void test5Modificar() throws Exception {
        System.out.println("modificar");
        Cita cita = new Cita();
        cita.setId(citaActual.getId());
        cita.setTotal(2);           
        cita.setEstado(Cita.EstadoUsuario.INACTIVO);
//        Cliente clienteB = new Cliente();
//        clienteB.setTop_aux(2);
//        cita.setIdCliente(ClienteDAL.buscar(clienteB).get(1).getId());
//        Usuario usuarioB = new Usuario();
//        usuarioB.setTop_aux(2);
//        cita.setIdUsuario(UsuarioDAL.buscar(usuarioB).get(1).getId());
        int expResult = 0;
        int result = CitaDAL.modificar(cita);
        assertNotEquals(expResult, result);
//        Cita citaUpdate = CitaDAL.obtenerPorId(citaActual);
//        assertTrue(citaUpdate.getLogin().equals(cita.getLogin()));

     
    }
    
    /**
     * Test of obtenerTodos method, of class ServicioDAL.
     */
    @Test
    public void test6ObtenerTodos() throws Exception {
        System.out.println("obtenerTodos");
        ArrayList<Cita> result = CitaDAL.obtenerTodos();
        assertTrue(result.size()>0);
    }

    /**
     * Test of eliminar method, of class ServicioDAL.
     */
    @Test
    public void test7Eliminar() throws Exception {
        System.out.println("eliminar");
        int expResult = 0;
        int result = CitaDAL.eliminar(citaActual);
        assertNotEquals(expResult, result);
        Cita citaDelete = CitaDAL.obtenerPorId(citaActual);
        assertTrue(citaDelete.getId()==0);
    }
    ////////////////////////////////////////////////////////////////////
    //Commit de prueba
}
