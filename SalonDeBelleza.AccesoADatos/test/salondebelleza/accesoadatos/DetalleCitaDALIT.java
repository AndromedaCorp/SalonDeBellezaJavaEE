
package salondebelleza.accesoadatos;


import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import salondebelleza.entidadesdenegocio.DetalleCita;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import salondebelleza.entidadesdenegocio.Cita;
import salondebelleza.entidadesdenegocio.Servicio;
import java.time.LocalDate;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DetalleCitaDALIT {
    
    private static DetalleCita detallecitaActual;
    
    public DetalleCitaDALIT() {
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

     *  Testear el metodo de Crear de la clase UsuarioDAL
     */
    @Test
    public void test1Crear() throws Exception {
        System.out.println("crear");
        DetalleCita detallecita = new DetalleCita();
         detallecita.setIdCita(1);
         detallecita.setIdServicio(1);
        detallecita.setPrecio(0);
        detallecita.setDuracion(0); 
        Cita citaB = new Cita();
        citaB.setTop_aux(1);
        detallecita.setIdCita(CitaDAL.buscar(citaB).get(0).getId());
        Servicio servicioB = new Servicio();
        servicioB.setTop_aux(1);
        detallecita.setIdServicio(ServicioDAL.buscar(servicioB).get(0).getId());
        int expResult = 0;
        int result = DetalleCitaDAL.crear(detallecita);
        assertNotEquals(expResult, result);
    }
    
    
    
    
    public int testIndividualQuerySelect(DetalleCita pDetalleCita) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("", null, 0);
        DetalleCitaDAL.querySelect(pDetalleCita, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }
    
    
    /**
     *  Testear el metodo de QuerySelect de la clase UsuarioDAL
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        int index = 0;
        DetalleCita detallecita = new DetalleCita();
        detallecita.setId(1);
        index++;
          assertTrue(testIndividualQuerySelect(detallecita) == index);
        detallecita.setIdCita(1);
        index++;
        assertTrue(testIndividualQuerySelect(detallecita) == index);
        detallecita.setIdServicio(1);
        index++;
        assertTrue(testIndividualQuerySelect(detallecita) == index);
        detallecita.setPrecio(1);
        index++;
          assertTrue(testIndividualQuerySelect(detallecita) == index);
        detallecita.setDuracion(1);
        index++;
    }
    
        /**
     *  Testear el metodo de Buscar de la clase UsuarioDAL
     */
    @Test
    public void test3Buscar() throws Exception {
        System.out.println("buscar");
        
        DetalleCita detallecita = new DetalleCita();
        //detallecita.setIdCita(0);
        //detallecita.setIdServicio(0);
        detallecita.setPrecio(0);
        detallecita.setDuracion(0);
        detallecita.setTop_aux(10);
        ArrayList<DetalleCita> result = DetalleCitaDAL.buscar(detallecita);
        assertTrue(result.size()> 0);
        detallecitaActual = result.get(0);

    }
    
    
    /**
     *  Testear el metodo de ObtenerPorId de la clase UsuarioDAL
     */
    @Test
    public void test4ObtenerPorId() throws Exception {
        System.out.println("obtenerPorId");
        DetalleCita result = DetalleCitaDAL.obtenerPorId(detallecitaActual);
        assertEquals(detallecitaActual.getId(), result.getId());
    }
    
    
    
    /**
     *  Testear el metodo de Modificar de la clase UsuarioDAL
     */
    @Test
    public void test5Modificar() throws Exception {
        System.out.println("modificar");
        DetalleCita detallecita = new DetalleCita();
        detallecita.setId(detallecitaActual.getId());
        detallecita.setPrecio(1);
        detallecita.setDuracion(1);
        Cita citaB = new Cita();
        citaB.setTop_aux(2);
        detallecita.setIdCita(CitaDAL.buscar(citaB).get(1).getId());
        
        Servicio servicioB = new Servicio();
       // Servicio.setTop_aux(2);
        detallecita.setIdServicio(ServicioDAL.buscar(servicioB).get(1).getId());
        
        int expResult = 0;
        int result = DetalleCitaDAL.modificar(detallecita);
        assertNotEquals(expResult, result);
        DetalleCita detallecitaUpdate = DetalleCitaDAL.obtenerPorId(detallecitaActual);
        assertTrue(detallecitaUpdate.getPrecio() == (detallecita.getPrecio()));
        assertTrue(detallecitaUpdate.getDuracion() == (detallecita.getDuracion()));

    }
    
    /**
     *  Testear el metodo de ObtenerTodos de la clase UsuarioDAL
     */
    @Test
    public void test6ObtenerTodos() throws Exception {
        System.out.println("obtenerTodos");
        ArrayList<DetalleCita> result = DetalleCitaDAL.obtenerTodos();
        assertTrue(result.size() > 0);
    }
    
    /**
     * Test of eliminar method, of class ServicioDAL.
     */
    @Test
    public void test7Eliminar() throws Exception {
        System.out.println("eliminar");
        int expResult = 0;
        int result = DetalleCitaDAL.eliminar(detallecitaActual);
        assertNotEquals(expResult, result);
        DetalleCita detallecitaDelete = DetalleCitaDAL.obtenerPorId(detallecitaActual);
        assertTrue(detallecitaDelete.getId()==0);
    }
    
}
