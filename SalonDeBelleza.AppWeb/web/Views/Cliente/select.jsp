<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Cliente"%>
<%@page import="salondebelleza.accesoadatos.ClienteDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Cliente> clientes = ClienteDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>

<label for="idCliente" class="form-label">Cliente</label>
<select id="slCliente" class="form-select" name="idCliente">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Cliente cliente : clientes) {%>
    <option <%=(id == cliente.getId()) ? "selected" : ""%>  value="<%=cliente.getId()%>"><%= cliente.getNombre()%></option>
    <%}%>
</select>

