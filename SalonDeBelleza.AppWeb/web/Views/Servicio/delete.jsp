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
            <form action="Servicio" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="id" value="<%=servicio.getId()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input disabled  id="txtNombre" type="text" value="<%=servicio.getNombre()%>">
                        <label for="txtNombre">Nombre</label
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
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Servicio" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
