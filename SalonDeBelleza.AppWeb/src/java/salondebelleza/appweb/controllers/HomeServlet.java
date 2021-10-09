package sysseguridad.appweb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * En este Servlet, vamos a recibir todas las peticiones get que se realice al
 * Servlet Home. Aprender conceptos básicos de servlets
 * http://www.jtech.ua.es/j2ee/2002-2003/modulos/servlets/apuntes/apuntes1_1.htm
 * Actualizamos la anotación WebServlet para cambiar el atributo urlPatterns
 * para acceder al Servlet Home utilizando la siguiente Url: la del sitio web
 * mas /Home
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {

    /**
     * En este método vamos a direccionar al jsp index que esta la carpeta raiz
     * de la aplicacion web
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Home
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Home que utlizaremos para enviar el jsp
     * al navegador web
     */
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // El request.getRequestDispatcher nos permite direccionar a un jsp desde un servlet. 
        // Enviaremos index.jsp que se encuentra en la carpeta raiz de la aplicacion web
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones get que se realice al Servlet Home
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Home
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Home que utlizaremos para enviar el jsp
     * al navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGetRequestIndex(request, response); // Ir al método doGetRequestIndex
    }
    // </editor-fold>

}