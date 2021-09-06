<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Usuario"%>
<% Usuario usuario = (Usuario) request.getAttribute("usuario");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Usuario</h5>
            <form action="Usuario" method="post" class="row g-3">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=usuario.getId()%>">  
                
                    <div class="col-md-6"">
                        <label for="txtNombre" class="form-label">Nombre</label>
                        <input  id="txtNombre" class="form-control" type="text" value="<%=usuario.getNombre()%>" disabled>
                    </div>                       
                    <div class="col-md-6"">
                        <label for="txtApellido" class="form-label">Apellido</label>
                        <input  id="txtApellido" class="form-control" type="text" value="<%=usuario.getApellido()%>" disabled>   
                    </div> 
                    <div class="col-md-6"">
                        <label for="txtLogin" class="form-label">Login</label>
                        <input  id="txtLogin" class="form-control" type="text" value="<%=usuario.getLogin()%>" disabled>
                    </div>                     
                    <div class="col-md-6"">   
                        <label for="slEstatus" class="form-label">Estatus</label>
                        <select id="slEstatus" name="estatus" class="form-select" disabled>
                            <option value="0" <%=(usuario.getEstado()== 10) ? "selected" : ""%>>SELECCIONAR</option>
                            <option value="<%=Usuario.EstadoUsuario.ACTIVO%>"  <%=(usuario.getEstado() == Usuario.EstadoUsuario.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                            <option value="<%=Usuario.EstadoUsuario.INACTIVO%>"  <%=(usuario.getEstado() == Usuario.EstadoUsuario.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                        </select>                                  
                    </div>
                    <div class="col-md-6"">
                        <label for="txtRol" class="form-label">Rol</label>
                        <input id="txtRol" class="form-control" type="text" value="<%=usuario.getRol().getNombre()%>" disabled>
                    </div> 
                
                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Eliminar</button>
                    <a href="Usuario" class="btn btn-danger">Cancelar</a>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />         
    </body>
</html>
