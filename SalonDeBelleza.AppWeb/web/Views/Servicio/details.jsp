<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Servicio"%>
<% Servicio servicio = (Servicio) request.getAttribute("servicio");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Servicio</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Servicio</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=servicio.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                    <input disabled  id="txtDescripcion" type="text" value="<%=servicio.getDescripcion()%>">
                    <label for="txtDescripcion">Descripcion</label>
                    <input disabled  id="txtPrecio" type="text" value="<%=servicio.getPrecio()%>">
                    <label for="txtPrecio">Precio</label>
                    <input disabled  id="txtDuracion" type="text" value="<%=servicio.getDuracion()%>">
                    <label for="txtDuracion">Duracion</label>
                </div>                                         
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Servicio?accion=edit&id=<%=servicio.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Servicio" class="waves-effect waves-light btn purple darken-1"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
