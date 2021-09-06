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
            <form action="Cliente" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=cliente.getId()%>">   
                
                <div class="col-md-6"> 
                    <label for="txtNombre" class="form-label">Nombre</label>
                    <input disabled  class="form-control" id="txtNombre" type="text" value="<%=cliente.getNombre()%>">
                </div>
                
                <div class="col-md-6">
                    <label for="txtApellido" class="form-label" >Apellido</label>
                    <input disabled  class="form-control" id="txtApellido" type="text" value="<%=cliente.getApellido()%>">
                </div>
                
                <div class="col-md-6">
                    <label for="txtDui" class="form-label">DUI</label>
                    <input disabled class="form-control" id="txtDui" type="text" value="<%=cliente.getDui()%>">

                </div>
                    
                <div class="col-md-6">
                    <label for="txtNumero" class="form-label" >Numero</label>
                    <input disabled class="form-control" id="txtNumero" type="text" value="<%=cliente.getNumero()%>">            
                </div>
                    
                
               <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Eliminar</button>
                    <a href="Cliente" class="btn btn-danger">Cancelar</a>
              </div>
                
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
