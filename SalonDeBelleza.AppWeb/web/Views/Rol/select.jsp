<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Rol"%>
<%@page import="salondebelleza.accesoadatos.RolDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Rol> roles = RolDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>

<label for="idRol" class="form-label">Rol</label>
<select id="slRol" class="form-select" name="idRol">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Rol rol : roles) {%>
    <option <%=(id == rol.getId()) ? "selected" : ""%>  value="<%=rol.getId()%>"><%= rol.getNombre()%></option>
    <%}%>
</select>
