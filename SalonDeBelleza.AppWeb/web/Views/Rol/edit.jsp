<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Rol"%>
<% Rol rol = (Rol) request.getAttribute("rol");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Rol</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Rol</h5>
            <form action="Rol" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=rol.getId()%>">   
                <div class="col-md-6">
                    <label for="txtNombre" class="form-label">Nombre</label> 
                    <input  id="txtNombre" type="text" name="nombre" value="<%=rol.getNombre()%>" required class="validate form-control" maxlength="30">
                                                           
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
