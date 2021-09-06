<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Cliente</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Cliente</h5>
            
            <form action="Cliente" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="col-md-6">
                        <label for="txtNombre" class="form-label">Nombre</label>
                        <input  id="txtNombre" type="text" name="nombre" required class="validate form-control" maxlength="50"> 
                </div>   
                        
                        
                <div class="col-md-6"> 
                        <label for="txtDescripcion" class="form-label">Apellido</label>
                        <input  id="txtApellido" type="text" name="apellido" required class="validate form-control" maxlength="50">     
                </div>    
                <div class="col-md-6">
                        <label for="txtDui" class="form-label">DUI</label>
                        <input  id="txtDui" type="text" name="dui" required class="validate form-control" maxlength="50">
                </div>
                <div class="col-md-6">    
                        <label for="txtNumero" class="form-label">Numero</label>
                        <input  id="txtNumero" type="text" name="numero" required class="validate form-control" maxlength="30">
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
