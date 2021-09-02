<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Cliente"%>
<% Cliente cliente = (Cliente) request.getAttribute("cliente");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Cliente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Cliente</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=cliente.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                    <input disabled  id="txtApellido" type="text" value="<%=cliente.getApellido()%>">
                    <label for="txtApellido">Apellido</label>
                    <input disabled  id="txtDui" type="text" value="<%=cliente.getDui()%>">
                    <label for="txtDui">DUI</label>
                    <input disabled  id="txtNumero" type="text" value="<%=cliente.getNumero()%>">
                    <label for="txtNumero">Numero</label>
                </div>                                         
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Cliente?accion=edit&id=<%=cliente.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Cliente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
