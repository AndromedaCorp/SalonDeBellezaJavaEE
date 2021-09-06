<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Servicio</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Servicio</h5>
            <form action="Servicio" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                    <div class="col-md-6">
                        <label for="txtNombre" class="form-label">Nombre</label>
                        <input  id="txtNombre" type="text" name="nombre" required class="validate form-control" maxlength="50">
                    </div>
                    <div class="col-md-6">
                        <label for="txtDescripcion" class="form-label">Descripcion</label>
                        <input  id="txtDescripcion" type="text" name="descripcion" required class="validate form-control" maxlength="250">                    
                    </div>
                    <div class="col-md-6">
                        <label for="txtPrecio" class="form-label">Precio</label>
                        <input  id="txtPrecio" type="text" name="precio" required class="validate form-control" maxlength="30">
                    </div>
                    <div class="col-md-6">
                        <label for="txtDuracion" class="form-label">Duracion</label>
                        <input  id="txtDuracion" type="text" name="duracion" required class="validate form-control" maxlength="30">                        
                    </div>                                       
                <div class="mb-3">
                        <button type="sutmit" class="btn btn-primary">Guardar</button>
                        <a href="Servicio" class="btn btn-danger">Cancelar</a>                         
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
