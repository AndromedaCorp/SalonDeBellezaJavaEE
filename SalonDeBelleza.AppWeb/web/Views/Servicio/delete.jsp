<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Servicio"%>
<% Servicio servicio = (Servicio) request.getAttribute("servicio");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Servicio</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Servicio</h5>          
            <form action="Servicio" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=servicio.getId()%>"> 
                <div class="col-md-6"">
                    <label for="txtNombre" class="form-label">Nombre</label>
                    <input disabled class="form-control" id="txtNombre" type="text" value="<%=servicio.getNombre()%>">
                </div>
                <div class="col-md-6"">
                    <label for="txtDescripcion" class="form-label">Descripcion</label>    
                    <input disabled class="form-control" id="txtDescripcion" type="text" value="<%=servicio.getDescripcion()%>">                        
                </div>
                <div class="col-md-6"">
                    <label for="txtPrecio" class="form-label">Precio</label>    
                    <input disabled class="form-control" id="txtPrecio" type="text" value="<%=servicio.getPrecio()%>">                       
                </div>
                <div class="col-md-6"">
                    <label for="txtDuracion" class="form-label">Duracion</label>     
                    <input disabled class="form-control" id="txtDuracion" type="text" value="<%=servicio.getDuracion()%>">                       
                </div>
                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Eliminar</button>                        
                    <a href="Servicio" class="btn purple darken-1">Cancelar</a>                          
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
