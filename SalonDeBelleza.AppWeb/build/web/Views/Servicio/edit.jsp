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
            <form action="Servicio" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=servicio.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=servicio.getNombre()%>" required class="validate" maxlength="50">
                        <label for="txtNombre">Nombre</label>
                        <input  id="txtDescripcion" type="text" name="descripcion" value="<%=servicio.getDescripcion()%>" required class="validate" maxlength="250">
                        <label for="txtDescripcion">Nombre</label>
                        <input  id="txtPrecio" type="text" name="precio" value="<%=servicio.getPrecio()%>" required class="validate" maxlength="13">
                        <label for="txtPrecio">Nombre</label>
                        <input  id="txtDuracion" type="text" name="duracion" value="<%=servicio.getDuracion()%>" required class="validate" maxlength="50">
                        <label for="txtDuracion">Nombre</label>
                    </div>                                       
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Servicio" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
