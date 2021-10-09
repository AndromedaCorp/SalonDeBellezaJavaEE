<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Rol"%>
<%@page import="salondebelleza.appweb.utils.*"%>
<%@page import="java.util.ArrayList"%>
<%
    // Codigo agregar para consumir la Web API
    HttpURLConnection con = Utilidad.obtenerConnecionWebAPI("Rol", "GET",request);
    con.connect();
    int status = con.getResponseCode();
    Gson gson = new Gson();
    ArrayList<Rol> roles = new ArrayList();
    if (status == HttpURLConnection.HTTP_OK) {
        String json = Utilidad.obtenerJSONWebAPI(con);
        con.disconnect();
        Type tipo = new TypeToken<ArrayList<Rol>>() {
        }.getType();
        roles = gson.fromJson(json, tipo);
    }
    //******************************************
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slRol" name="idRol">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Rol rol : roles) {%>
    <option <%=(id == rol.getId()) ? "selected" : ""%>  value="<%=rol.getId()%>"><%= rol.getNombre()%></option>
    <%}%>
</select>
<label for="idRol">Rol</label>
