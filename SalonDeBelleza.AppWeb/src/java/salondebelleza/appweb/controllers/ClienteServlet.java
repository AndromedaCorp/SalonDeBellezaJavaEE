package salondebelleza.appweb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;//importar la clase ArrayList.
import salondebelleza.entidadesdenegocio.Cliente;//Importar la clase Cliente de la capa entidades de negocio.
import salondebelleza.appweb.utils.*;//Importar las clases SessionUser, Utilidad del paquete de Utils.

/* Librerias para utilizar la Web API */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import salondebelleza.entidadesdenegocio.Rol;

/* ************************************* */
/**
 * En este servlet vamos a recibir todas las peticiones GET y Pos que se realice
 * al servlet Cliente. Aperender conceptos basicos de servlets
 * http://jtech.ua.es/j2ee/2002-2003/modulos/servlets/apuntes/apuntes1_1.html
 * Actualizamos la anotacion WebServlets para cambiar el atributo urlPatterns*
 * para acceder al servlets cliente utilizando la siguiente url la del sitio web
 * mas /Cliente
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/Cliente"})
public class ClienteServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Metodo para procesar las solicitudes GET y POST del servlet">
    /**
     * En este metodo bamos a obtener la informacion enviada en una peticion get
     * o post, obteniendo los datos de los parametros enviados de un formulario
     * o la url de el navegador, enviar esa informacion a una estancia de la
     * entidad Cliente
     *
     * @param request en este p arametro vamos a recibir el request de la
     * peticion get o post enviada al servlet Cliente
     * @return Cliente devolver la entancia de la entidad Cliente con los
     * valores obtenidos del request
     */
    private Cliente obtenerCliente(HttpServletRequest request) {
        // Obtener el parámetro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        Cliente cliente = new Cliente();
        if (accion.equals("create") == false) { // Si la accion no es create.
            // Obtener el parámetro id del request  y asignar ese valor a la propiedad Id de Cliente.
            cliente.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        // Obtener el parámetro nombre del request   y asignar ese valor a la propiedad Nombre de Cliente.
        cliente.setNombre(Utilidad.getParameter(request, "nombre", ""));
        
        cliente.setApellido(Utilidad.getParameter(request, "apellido", ""));
        
        cliente.setDui(Utilidad.getParameter(request, "dui", ""));
        
        cliente.setNumero(Integer.parseInt(Utilidad.getParameter(request, "numero", "0")));
        
        if (accion.equals("index")) {  // Si accion es index.
            // Obtener el parámetro top_aux del request  y asignar ese valor a la propiedad Top_aux de Cliente.
            cliente.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            // Utilizando un operador ternario, colocar en el Top_aux, si  es igual a cero enviar en el Top_aux, el valor maximo de un entero 
            // en java, para obtener todos los registro, en el caso contrario obtener la cantidad de registros
            // que se obtiene en el parámetro top_aux del request.
            cliente.setTop_aux(cliente.getTop_aux() == 0 ? Integer.MAX_VALUE : cliente.getTop_aux());
        }
        // Devolver la instancia de la entidad Cliente con los valores obtenidos del request.
        return cliente;
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Cliente, y el parámetro accion sea igual index. Este método se encargara
     * de enviar los datos de los clientes al jsp de index de Cliente.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Cliente
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Cliente que utlizaremos para enviar el
     * jsp
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cliente cliente = new Cliente(); // Crear una instancia  de la entidad de Cliente.
            cliente.setTop_aux(10); // Agregar el Top_aux con el valor de 10 a la propiedad Top_aux de cliente.

            // Codigo agregar para consumir la Web API
            HttpURLConnection con = Utilidad.obtenerConnecionWebAPI("Cliente/Buscar", "POST", request);
            con.setDoOutput(true);
            Gson gson = new Gson();
            Utilidad.asignarJSONWebAPI(con, gson.toJson(cliente));
            con.connect();
            int status = con.getResponseCode();
            ArrayList<Cliente> clientes = new ArrayList();
            if (status == HttpURLConnection.HTTP_OK) {
                String json = Utilidad.obtenerJSONWebAPI(con);
                con.disconnect();
                Type tipo = new TypeToken<ArrayList<Cliente>>() {
                }.getType();
                clientes = gson.fromJson(json, tipo);
            }
            //********************************************

            //ArrayList<Cliente> clientes = ClienteDAL.buscar(cliente); // Ir a la capa de acceso a datos y buscar los registros de Cliente.
            // El request.setAttribute se utiliza para enviar datos desde un servlet a un jsp.
            request.setAttribute("clientes", clientes); // Enviar los clientes al jsp utilizando el request.setAttribute con el nombre del atributo Clientes.
            // Enviar el Top_aux de Cliente al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", cliente.getTop_aux());
            // El request.getRequestDispatcher nos permite direccionar a un jsp desde un servlet.              
            request.getRequestDispatcher("Views/Cliente/index.jsp").forward(request, response); // Direccionar al jsp index de Cliente.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post, al servlet
     * Cliente , y el parámetro accion sea igual index. Este método se encargara
     * de enviar los datos de los Clientes al jsp de index de Cliente
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cliente cliente = obtenerCliente(request); // Llenar la instancia de cliente con los parámetros enviados en el request 

            // Codigo agregar para consumir la Web API   
            HttpURLConnection con = Utilidad.obtenerConnecionWebAPI("Cliente/Buscar", "POST", request);
            con.setDoOutput(true);
            Gson gson = new Gson();
            Utilidad.asignarJSONWebAPI(con, gson.toJson(cliente));
            con.connect();
            int status = con.getResponseCode();
            ArrayList<Cliente> clientes = new ArrayList();
            if (status == HttpURLConnection.HTTP_OK) {
                String json = Utilidad.obtenerJSONWebAPI(con);
                con.disconnect();
                Type tipo = new TypeToken<ArrayList<Cliente>>() {
                }.getType();
                clientes = gson.fromJson(json, tipo);
            }
            //*******************************************

            //ArrayList<Cliente> clientes = ClienteDAL.buscar(cliente); // Buscar los clientes que cumple con los datos enviados en el request
            request.setAttribute("clientes", clientes); // Enviar los clientes al jsp utilizando el request.setAttribute con el nombre del atributo Clientes
            // Enviar el Top_aux de Cliente al jsp utilizando el request.setAttribute con el nombre del atributo top_aux
            request.setAttribute("top_aux", cliente.getTop_aux());
            request.getRequestDispatcher("Views/Cliente/index.jsp").forward(request, response); // Direccionar al jsp index de Cliente
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception 
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Cliente, y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // direccionar al jsp create de Rol
        request.getRequestDispatcher("Views/Cliente/create.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Cliente , y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cliente cliente = obtenerCliente(request); // Llenar la instancia de Cliente con los parámetros enviados en el request.

            // Codigo agregar para consumir la Web API
            int result = 0;
            HttpURLConnection con = Utilidad.obtenerConnecionWebAPI("Cliente", "POST",request);
            con.setDoOutput(true);
            Gson gson = new Gson();
            Utilidad.asignarJSONWebAPI(con, gson.toJson(cliente));
            con.connect();
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                result = 1;
            }
            //*******************************************

            // Enviar los datos de Cliente a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            //int result = ClienteDAL.crear(cliente);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron ingresados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // ir al metodo doGetRequestIndex para que nos direcciones al jsp index
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro registrar un nuevo registro
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }

    }

    /**
     * En este método obtiene por Id un Cliente desde la capa de acceso a datos
     * el Id se captura del request que se envio al servlet de Cliente
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get o post enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cliente cliente = obtenerCliente(request); // Llenar la instancia de Cliente con los parámetros enviados en el request.

            // Codigo agregar para consumir la Web API
            HttpURLConnection con = Utilidad.obtenerConnecionWebAPI("Cliente/" + cliente.getId(), "GET",request);
            con.connect();
            int status = con.getResponseCode();
            Gson gson = new Gson();
            Cliente cliente_result = new Cliente();
            if (status == HttpURLConnection.HTTP_OK) {
                String json = Utilidad.obtenerJSONWebAPI(con);
                con.disconnect();
                cliente_result = gson.fromJson(json, Cliente.class);
            }
            //******************************************

            // Obtener desde la capa de acceso a datos el cliente por Id.
            //Cliente cliente_result = ClienteDAL.obtenerPorId(cliente);
            if (cliente_result.getId() > 0) { // Si el Id es mayor a cero.
                // Enviar el atributo rol con el valor de los datos del rol de nuestra base de datos a un jsp
                request.setAttribute("cliente", cliente_result);
            } else {
                // Enviar al jsp de error el siguiente mensaje. El Id: ? no existe en la tabla de Cliente
                Utilidad.enviarError("El Id:" + cliente.getId() + " no existe en la tabla de cliente", request, response);
            }
        } catch (Exception ex) {
            // enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Cliente , y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          // Enviar el rol al jsp de edit que se obtiene por Id
        requestObtenerPorId(request, response);
        // Direccionar al jsp edit de Rol
        request.getRequestDispatcher("Views/Cliente/edit.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Rol , y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Rol
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
            Cliente cliente = obtenerCliente(request); // Llenar la instancia de Rol con los parámetros enviados en el request.
            

            // Codigo agregar para consumir la Web API
            int result = 0;
            HttpURLConnection con = Utilidad.obtenerConnecionWebAPI("Cliente/" + cliente.getId(), "PUT",request);
            con.setDoOutput(true);
            Gson gson = new Gson();
            Utilidad.asignarJSONWebAPI(con, gson.toJson(cliente));
            con.connect();
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                result = 1;
            }
            //********************************************
            
            
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron modificado correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro actualizar el registro.
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Cliente , y el parámetro accion sea igual details.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el cliente al jsp de details que se obtiene por Id.
        requestObtenerPorId(request, response);
        // Direccionar al jsp details de Cliente.
        request.getRequestDispatcher("Views/Cliente/details.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Cliente , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el cliente al jsp de delete que se obtiene por Id.
        requestObtenerPorId(request, response);
        // Direccionar al jsp delete de Cliente.
        request.getRequestDispatcher("Views/Cliente/delete.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Cliente , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Cliente
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cliente cliente = obtenerCliente(request); // Llenar la instancia de Cliente con los parámetros enviados en el request.

            // Codigo agregar para consumir la Web API
            int result = 0;
            HttpURLConnection con = Utilidad.obtenerConnecionWebAPI("Cliente/" + cliente.getId(), "DELETE",request);
            con.connect();
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                result = 1;
            }
            //*******************************************

            // Enviar los datos de Cliente a la capa de accesoa a datos para que elimine el registro.
            //int result = ClienteDAL.eliminar(cliente);
            if (result != 0) {// Si el result es diferente a cero significa que los datos fueron eliminados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al método doGetRequestIndex para que nos direccione al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro eliminar el registro.
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception.
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones get que se realice al Servlet Cliente
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Cliente
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Cliente que utlizaremos para enviar el
     * jsp al navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    //Inicio de solucion de Metodo Lambda
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Cliente. Todo el codigo que este dentro  expresion Lambda, se ejecutara si el usuario tiene permitido
        // acceder a este Servlet 
        final HttpServletRequest requestLocal = request;
        final HttpServletResponse responseLocal = response;

        SessionUser.authorize(requestLocal, response, new IAuthorize() {
            @Override
            public void authorize() throws ServletException, IOException {
                // Obtener el parámetro accion del request.
                String accion = Utilidad.getParameter(requestLocal, "accion", "index");
                // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
                switch (accion) {
                    case "index":
                        // Enviar el atributo accion al jsp de index.
                        requestLocal.setAttribute("accion", accion);
                        doGetRequestIndex(requestLocal, responseLocal); // Ir al metodo doGetRequestIndex.
                        break;
                    case "create":
                        // Enviar el atributo accion al jsp de create.
                        requestLocal.setAttribute("accion", accion);
                        doGetRequestCreate(requestLocal, responseLocal); // Ir al metodo doPostRequestCreate.
                        break;
                    case "edit":
                        // Enviar el atributo accion al jsp de edit.
                        requestLocal.setAttribute("accion", accion);
                        doGetRequestEdit(requestLocal, responseLocal); // Ir al metodo doPostRequestEdit.
                        break;
                    case "delete":
                        // Enviar el atributo accion al jsp de delete.
                        requestLocal.setAttribute("accion", accion);
                        doGetRequestDelete(requestLocal, responseLocal); // Ir al metodo doPostRequestDelete.
                        break;
                    default:
                        // Enviar el atributo accion al jsp de index.
                        requestLocal.setAttribute("accion", accion);
                        doGetRequestIndex(requestLocal, responseLocal); // Ir al metodo doGetRequestIndex.
                }
            }
        });
    }

    //Fin de solucion de Metodo Lambda
    /*SessionUser.authorize(request, response, () -> { // Expresion Lambda  
            // Obtener el parámetro accion del request
            String accion = Utilidad.getParameter(request, "accion", "index");
            // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            switch (accion) {
                case "index":
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al método doGetRequestIndex.
                    break;
                case "create":
                    // Enviar el atributo accion al jsp de create.
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response); // Ir al metodo doGetRequestCreate.
                    break;
                case "edit":
                    // Enviar el atributo accion al jsp de edit.
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);// Ir al metodo doGetRequestEdit.
                    break;
                case "delete":
                    // Enviar el atributo accion al jsp de delete.
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response); // Ir al metodo doGetRequestDelete.
                    break;
                case "details":
                    // Enviar el atributo accion al jsp de details.
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response); // Ir al metodo doGetRequestDetails.
                    break;
                default:
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
        });*/
    // }
    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones post que se realice al Servlet Cliente
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Cliente
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Cliente que utlizaremos para enviar el
     * jsp al navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Servicio. Todo el codigo que este dentro  expresion Lambda,  se ejecutara si el usuario tiene permitido
        // acceder a este Servlet 
        final HttpServletRequest requestLocal = request;
        final HttpServletResponse responseLocal = response;

        SessionUser.authorize(requestLocal, response, new IAuthorize() {
            @Override
            public void authorize() throws ServletException, IOException {
                // Obtener el parámetro accion del request.
                // Obtener el parámetro accion del request.
                String accion = Utilidad.getParameter(requestLocal, "accion", "index");
                // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
                switch (accion) {
                    case "index":
                        // Enviar el atributo accion al jsp de index.
                        requestLocal.setAttribute("accion", accion);
                        doPostRequestIndex(requestLocal, responseLocal); // Ir al metodo doGetRequestIndex.
                        break;
                    case "create":
                        // Enviar el atributo accion al jsp de create.
                        requestLocal.setAttribute("accion", accion);
                        doPostRequestCreate(requestLocal, responseLocal); // Ir al metodo doPostRequestCreate.
                        break;
                    case "edit":
                        // Enviar el atributo accion al jsp de edit.
                        requestLocal.setAttribute("accion", accion);
                        doPostRequestEdit(requestLocal, responseLocal); // Ir al metodo doPostRequestEdit.
                        break;
                    case "delete":
                        // Enviar el atributo accion al jsp de delete.
                        requestLocal.setAttribute("accion", accion);
                        doPostRequestDelete(requestLocal, responseLocal); // Ir al metodo doPostRequestDelete.
                        break;
                    default:
                        // Enviar el atributo accion al jsp de index.
                        requestLocal.setAttribute("accion", accion);
                        doGetRequestIndex(requestLocal, responseLocal); // Ir al metodo doGetRequestIndex.
                }
            }
        });
    }

    // </editor-fold>
}
