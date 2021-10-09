<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Servicio"%>
<% Servicio servicio = (Servicio) request.getAttribute("servicio");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Servicio</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Rol</h5>
            <form action="Servicio" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=servicio.getId()%>">   
                <div class="col-md-6">
                    <label for="txtNombre" class="form-label">Nombre</label>    
                    <input  id="txtNombre" type="text" name="nombre" value="<%=servicio.getNombre()%>" required class="validate form-control" maxlength="50">
                        
                </div>
                <div class="col-md-6">
                    <label for="txtDescripcion" class="form-label">Descripcion</label>    
                    <input  id="txtDescripcion" type="text" name="descripcion" value="<%=servicio.getDescripcion()%>" required class="validate form-control" maxlength="250">
                        
                </div>
                <div class="col-md-6">
                    <label for="txtPrecio" class="form-label">Precio</label>    
                    <input  id="txtPrecio" type="text" name="precio" value="<%=servicio.getPrecio()%>" required class="validate form-control" maxlength="13">
                        
                </div>
                <div class="col-md-6">
                    <label for="txtDuracion" class="form-label">Duracion</label>    
                    <input  id="txtDuracion" type="text" name="duracion" value="<%=servicio.getDuracion()%>" required class="validate form-control" maxlength="50">
                        
                </div>
                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Guardar</button>
                    <a href="Servicio" class="btn purple darken-1">Cancelar</a>                          
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
