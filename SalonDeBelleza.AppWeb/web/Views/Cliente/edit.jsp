<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Cliente"%>
<% Cliente cliente = (Cliente) request.getAttribute("cliente");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Cliente</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Cliente</h5>
            <form action="Cliente" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=cliente.getId()%>">   
                <div class="col-md-6">
                        <label for="txtNombre" class="form-label">Nombre</label>
                        <input  id="txtNombre" type="text" name="nombre" value="<%=cliente.getNombre()%>" required class="validate form-control" maxlength="50">
                        
                </div>
                <div class="col-md-6">
                        <label for="txtApellido" class="form-label">Apellido</label>
                        <input  id="txtApellido" type="text" name="apellido" value="<%=cliente.getApellido()%>" required class="validate form-control" maxlength="50">
                        
                </div>
                <div class="col-md-6">
                        <label for="txtDui" class="form-label">DUI</label>
                        <input  id="txtDui" type="text" name="dui" value="<%=cliente.getDui()%>" required class="validate form-control" maxlength="50">
                </div>
                <div class="col-md-6">
                        <label for="txtNumero" class="form-label">Numero</label>
                        <input  id="txtNumero" type="text" name="numero" value="<%=cliente.getNumero()%>" required class="validate form-control" maxlength="30">
                </div>
                         
                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Guardar</button>
                    <a href="Cliente" class="btn btn-danger">Cancelar</a>                          
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>