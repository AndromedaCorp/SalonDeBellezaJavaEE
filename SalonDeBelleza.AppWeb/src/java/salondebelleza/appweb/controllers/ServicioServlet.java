package salondebelleza.appweb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;     // Importar la clase ArrayList
import salondebelleza.accesoadatos.ServicioDAL;  // Importar la clase RolDAL de la capa de acceso a datos
import salondebelleza.entidadesdenegocio.Servicio; // Importar la clase Rol de la capa de entidades de negocio
import salondebelleza.appweb.utils.*; // Importar las clases SessionUser, Utilidad del paquete de utils

/**
 * En este Servlet, vamos a recibir todas las peticiones get y post que se
 * realice al Servlet Rol. Aprender conceptos básicos de servlets
 * http://www.jtech.ua.es/j2ee/2002-2003/modulos/servlets/apuntes/apuntes1_1.htm
 * Actualizamos la anotación WebServlet para cambiar el atributo urlPatterns
 * para acceder al Servlet Rol utilizando la siguiente Url: la del sitio web mas
 * /Rol
 */
@WebServlet(name = "ServicioServlet", urlPatterns = {"/Servicio"})
public class ServicioServlet extends HttpServlet {

    //<editor-fold defaultstate="collapsed" desc="Metodor  para procesar las solicitudes get o post del servlet">
    /**
     * En este método vamos a obtener la información enviada, en una peticion
     * get o post, obteniendo los datos de los parámetros enviados de un
     * formulario o la url del navegador, enviar esa información a una instancia
     * de la entidad Rol
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get o post enviada al servlet Rol
     * @return Servicio devolver la instancia de la entidad Servicio con los
     * valores obtenidos del request
     */
    private Servicio obtenerServicio(HttpServletRequest request) {
        // Obtener el parámetro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        Servicio servicio = new Servicio();
        if (accion.equals("create") == false) { // Si la accion no es create.
            // Obtener el parámetro id del request  y asignar ese valor a la propiedad Id de Servicio.
            servicio.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        // Obtener el parámetro nombre del request   y asignar ese valor a la propiedad Nombre de Servicio.
        servicio.setNombre(Utilidad.getParameter(request, "nombre", ""));
        if (accion.equals("index")) {  // Si accion es index.
            // Obtener el parámetro top_aux del request  y asignar ese valor a la propiedad Top_aux de Servicio.
            servicio.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            // Utilizando un operador ternario, colocar en el Top_aux, si  es igual a cero enviar en el Top_aux, el valor maximo de un entero 
            // en java, para obtener todos los registro, en el caso contrario obtener la cantidad de registros
            // que se obtiene en el parámetro top_aux del request.
            servicio.setTop_aux(servicio.getTop_aux() == 0 ? Integer.MAX_VALUE : servicio.getTop_aux());
        }
        // Devolver la instancia de la entidad Servicio con los valores obtenidos del request.
        return servicio;
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Servicio, y el parámetro accion sea igual index. Este método se encargara
     * de enviar los datos de los roles al jsp de index de Rol.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Servicio servicio = new Servicio(); // Crear una instancia  de la entidad de Servicio.
            servicio.setTop_aux(10); // Agregar el Top_aux con el valor de 10 a la propiedad Top_aux de Servicio.
            ArrayList<Servicio> servicios = ServicioDAL.buscar(servicio); // Ir a la capa de acceso a datos y buscar los registros de Rol.
            // El request.setAttribute se utiliza para enviar datos desde un servlet a un jsp.
            request.setAttribute("servicios", servicios); // Enviar los roles al jsp utilizando el request.setAttribute con el nombre del atributo servicios.
            // Enviar el Top_aux de Servicio al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", servicio.getTop_aux());
            // El request.getRequestDispatcher nos permite direccionar a un jsp desde un servlet.              
            request.getRequestDispatcher("Views/Servicio/index.jsp").forward(request, response); // Direccionar al jsp index de Servicio.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post, al servlet
     * Servicio , y el parámetro accion sea igual index. Este método se
     * encargara de enviar los datos de los servicios al jsp de index de
     * Servicio
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Servicio
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Servicio servicio = obtenerServicio(request); // Llenar la instancia de Servicio con los parámetros enviados en el request 
            ArrayList<Servicio> servicios = ServicioDAL.buscar(servicio); // Buscar los servicios que cumple con los datos enviados en el request
            request.setAttribute("servicios", servicios); // Enviar los servicios al jsp utilizando el request.setAttribute con el nombre del atributo roles
            // Enviar el Top_aux de Servicio al jsp utilizando el request.setAttribute con el nombre del atributo top_aux
            request.setAttribute("top_aux", servicio.getTop_aux());
            request.getRequestDispatcher("Views/Servicio/index.jsp").forward(request, response); // Direccionar al jsp index de Servicio
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception 
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Servicio, y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Servicio
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // direccionar al jsp create de Servicio
        request.getRequestDispatcher("Views/Servicio/create.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Rol , y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Rol
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Servicio servicio = obtenerServicio(request); // Llenar la instancia de Servicio con los parámetros enviados en el request.
            // Enviar los datos de Servicio a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            int result = ServicioDAL.crear(servicio);
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
     * En este método obtiene por Id un Servicio desde la capa de acceso a datos
     * el Id se captura del request que se envio al servlet de Servicio
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get o post enviada al servlet Servicio
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Servicio servicio = obtenerServicio(request); // Llenar la instancia de Servicio con los parámetros enviados en el request.
            // Obtener desde la capa de acceso a datos el servicio por Id.
            Servicio servicio_result = ServicioDAL.obtenerPorId(servicio);
            if (servicio_result.getId() > 0) { // Si el Id es mayor a cero.
                // Enviar el atributo servicio con el valor de los datos del servicio de nuestra base de datos a un jsp
                request.setAttribute("servicio", servicio_result);
            } else {
                // Enviar al jsp de error el siguiente mensaje. El Id: ? no existe en la tabla de Servicio
                Utilidad.enviarError("El Id:" + servicio.getId() + " no existe en la tabla de Servicio", request, response);
            }
        } catch (Exception ex) {
            // enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Servicio , y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Servicio
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el servicio al jsp de edit que se obtiene por Id
        requestObtenerPorId(request, response);
        // Direccionar al jsp edit de Rol
        request.getRequestDispatcher("Views/Servicio/edit.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Servicio, y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Servicio
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Servicio servicio = obtenerServicio(request); // Llenar la instancia de Servicio con los parámetros enviados en el request.
            // Enviar los datos de Servicio a la capa de accesoa a datos para modificar el registro.
            int result = ServicioDAL.modificar(servicio);
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
     * Servicio , y el parámetro accion sea igual details.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Servicio
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el servicio al jsp de details que se obtiene por Id.
        requestObtenerPorId(request, response);
        // Direccionar al jsp details de Servicio.
        request.getRequestDispatcher("Views/Rol/details.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Servicio , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Rol
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el servicio al jsp de delete que se obtiene por Id.
        requestObtenerPorId(request, response);
        // Direccionar al jsp delete de Servicio.
        request.getRequestDispatcher("Views/Servicio/delete.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Servicio , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Servicio
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Servicio servicio = obtenerServicio(request); // Llenar la instancia de Servicio con los parámetros enviados en el request.
            // Enviar los datos de Servicio a la capa de accesoa a datos para que elimine el registro.
            int result = ServicioDAL.eliminar(servicio);
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
     * todas las peticiones get que se realice al Servlet Rol
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Rol. Todo el codigo que este dentro  expresion Lambda, se ejecutara si el usuario tiene permitido
        // acceder a este Servlet      
        final HttpServletRequest requestLocal=request;
        final HttpServletResponse  responseLocal=response;
        
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
    


    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones post que se realice al Servlet Rol
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Servicio. Todo el codigo que este dentro  expresion Lambda,  se ejecutara si el usuario tiene permitido
        // acceder a este Servlet 
        final HttpServletRequest requestLocal=request;
        final HttpServletResponse  responseLocal=response;
        
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
