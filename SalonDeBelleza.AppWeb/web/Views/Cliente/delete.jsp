<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Cliente"%>
<% Cliente cliente = (Cliente) request.getAttribute("cliente");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Cliente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Cliente</h5>          
            <form action="Cliente" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=cliente.getId()%>">   
                <div class="row">
                <div class="input-field col l4 s12"> 
                    <input id="txtNombre" type="text" value="<%=cliente.getNombre()%>" disabled>
                    <label for="txtNombre">Nombre</label>
                </div>
                
                <div class="input-field col l4 s12">
                    <input id="txtApellido" type="text" value="<%=cliente.getApellido()%>" disabled>
                    <label for="txtApellido">Apellido</label>
                </div>  
                <div class="input-field col l4 s12">
                    <input id="txtDui" type="text" value="<%=cliente.getDui()%>" disabled>
                    <label for="txtDui">DUI</label>
                </div>        
                <div class="input-field col l4 s12">
                    <input id="txtNumero" type="text" value="<%=cliente.getNumero()%>" disabled> 
                    <label for="txtNumero">Numero</label>
                </div>
                </div>
               <div class="row">
               <div class="input-field col l4 s12">
                    <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                    <a href="Cliente" class="waves-effect waves-light btn purple darken-1"><i class="material-icons right">list</i>Cancelar</a>
              </div>
               </div>  
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
