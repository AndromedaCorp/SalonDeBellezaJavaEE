<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Servicio"%>
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
            <form action="Servicio" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                    <input  id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30">
                    <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">
                    <input  id="txtDescripcion" type="text" name="descripcion" required class="validate" maxlength="30">
                    <label for="txtDescripsion">Descripcion</label>
                    </div>
                    <div class="input-field col l4 s12">
                    <input  id="txtPrecio" type="text" name="precio" required class="validate" maxlength="30">
                    <label for="txtPrecio">Precio</label>
                    </div>
                    <div class="input-field col l4 s12">
                    <input  id="txtDuracion" type="text" name="duracion" required class="validate" maxlength="30">
                    <label for="txtDuracion">Duracion</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                    <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                    <a href="Servicio" class="waves-effect waves-light btn purple darken-1"><i class="material-icons right">list</i>Cancelar</a> 
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
