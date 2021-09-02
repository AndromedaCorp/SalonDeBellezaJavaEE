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
            <form action="Cliente" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=cliente.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=cliente.getNombre()%>" required class="validate" maxlength="50">
                        <label for="txtNombre">Nombre</label>
                        <input  id="txtApellido" type="text" name="apellido" value="<%=cliente.getApellido()%>" required class="validate" maxlength="50">
                        <label for="txtApellido">Apellido</label>
                        <input  id="txtDui" type="text" name="dui" value="<%=cliente.getDui()%>" required class="validate" maxlength="50">
                        <label for="txtDui">DUI</label>
                        <input  id="txtNumero" type="text" name="numero" value="<%=cliente.getNumero()%>" required class="validate" maxlength="30">
                        <label for="txtNumero">Numero</label>
                    </div>                                       
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Cliente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>