
package salondebelleza.appweb.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import salondebelleza.entidadesdenegocio.Usuario;

/**
 * En esta clase, vamos a programar los métodos necesarios para controlar la
 * session de un usuario, almacenando en variables de session los datos del
 * usuario que se autorice en nuestro sistema.
 */
public class SessionUser {

    /**
     * En este método vamos autorizar un usuario, guardando su información en
     * las variables de session de auth, user, rol.
     *
     * @param request en este parámetro vamos a recibir el request del servlet
     * de usuario para autenticarlo
     * @param pUsuario en este parámetro vamos a recibir la información del
     * usuario que inicio session
     */

    public static void autenticarUser(HttpServletRequest request, Usuario pUsuario) {
        HttpSession session = (HttpSession) request.getSession(); // obtener las variables de session del request
        session.setMaxInactiveInterval(3600); // la variables de session se eliminara una hora despues de inactividad de un usuario
        // session.setAttribute es para crear una variable de session y asignar un valor
        session.setAttribute("auth", true); // asignar el valor de true a la variable de session auth 
        session.setAttribute("user", pUsuario.getLogin()); // asignar el login del usuario a la variable de session user 
        session.setAttribute("rol", pUsuario.getRol().getNombre()); // asignar el rol del usuario a la variable de session rol 
    }

    /**
     * En este método vamos obtener el login del usuario que inicio session
     *
     * @param request en este parámetro vamos a recibir el request de un servlet
     * o jsp
     * @return boolean true si el usuario ha iniciado session, false si no ha
     * iniciado session
     */
    public static boolean isAuth(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        // utilizar el operador ternario para verificar si la variable de session auth es diferente a null, 
        // si es diferente a null se convierte el valor de la variable de session auth en boolean, si es null se le asigna
        // a la variable auth el valor false. 
        // session.getAttribute es para obtener el valor de una variable de session, si no existe devuelve null por defecto.
        // Aprender sobre el operador ternario en java http://lineadecodigo.com/java/el-operador-ternario-en-java/
        boolean auth = session.getAttribute("auth") != null ? (boolean) session.getAttribute("auth") : false;
        return auth;
    }

    /**
     * En este método vamos obtener el login del usuario que inicio session.
     *
     * @param request en este parámetro vamos a recibir el request de un servlet
     * o jsp
     * @return String el login del usuario
     */
    public static String getUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        String user = "";
        if (SessionUser.isAuth(request)) { // verificar si el usuario ha iniciado session. 
            // obtener el valor de la variable de session user, en el caso que la variable de session sea diferente a null, utilizando 
            // operador ternario
            user = session.getAttribute("user") != null ? (String) session.getAttribute("user") : "";
        }
        return user;
    }

    /**
     * En este método vamos obtener el rol del usuario que inicio session
     *
     * @param request en este parámetro vamos a recibir el request de un servlet
     * o jsp
     * @return String el rol del usuario
     */
    public static String getRol(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        String user = "";
        if (SessionUser.isAuth(request)) { // verificar si el usuario ha iniciado session 
            // obtener el valor de la variable de session rol, en el caso que la variable de session sea diferente a null, utilizando 
            // operador ternario
            user = session.getAttribute("rol") != null ? (String) session.getAttribute("rol") : "";
        }
        return user;
    }

    /**
     * Este método lo vamos a utilizar, para validar si un usuario esta
     * autorizado, a realizar x accion en un servlet o jsp.
     *
     * @param request en este parámetro vamos a recibir el request de un servlet
     * o jsp
     * @param response en este parámetro vamos a recibir el response de un
     * servlet, para direccionar al servlet de usuario accion login, en el caso
     * que no tenga permisos.
     * @param pIAuthorize en este parámetro se enviará una expresión Lambda, que
     * cumpla con la firma del metodo, que tiene declarado en la interface de
     * IAuthorize.
     * @throws javax.servlet.ServletException devolver una exception de un
     * servlet
     * @throws java.io.IOException devolver una exception al leer o escribir un
     * archivo
     */
    public static void authorize(HttpServletRequest request, HttpServletResponse response, IAuthorize pIAuthorize) throws ServletException, IOException {
        if (SessionUser.isAuth(request)) { // verificar si el usuario ha iniciado session 
            pIAuthorize.authorize(); // ejecutar la expresión Lambda, si el usuario ha sido autorizado en el sistema
        } else {
            response.sendRedirect("Usuario?accion=login"); // direccionar al servlet de Usuario accion login, en el caso que no tenga permiso el usuario
        }
    }

    /**
     * Este método lo vamos utilizar para cerrar la session de un usuario
     * autorizado en el sistema.
     *
     * @param request en este parámetro vamos a recibir el request de un servlet
     * o jsp
     */
    public static void cerrarSession(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        session.invalidate(); // eliminanos cualquier variable de session, que tenga creada un usuario que inicio session en el sistema.
    }
}
