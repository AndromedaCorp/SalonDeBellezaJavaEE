<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Rol</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Rol</h5>
            <form action="Rol" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="col-md-6">
                    <label for="txtNombre" class="form-label">Nombre</label>
                    <input  id="txtNombre" type="text" name="nombre" required class="validate form-control" maxlength="30">                                                       
                </div>
                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Guardar</button>
                    <a href="Rol" class="btn btn-danger">Cancelar</a> 
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>