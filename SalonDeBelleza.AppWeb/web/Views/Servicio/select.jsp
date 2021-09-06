<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Servicio"%>
<%@page import="salondebelleza.accesoadatos.ServicioDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Servicio> servicios = ServicioDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<label for="idServicio" class="form-label">Servicio</label>
<select id="slServicio" class="form-select" name="idServicio">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Servicio servicio : servicios) {%>
    <option <%=(id == servicio.getId()) ? "selected" : ""%>  value="<%=servicio.getId()%>"><%= servicio.getNombre()%></option>
    <%}%>
</select>

