<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Usuario"%>
<% Usuario usuario = (Usuario) request.getAttribute("usuario");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Usuario</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Usuario</h5>
            <form action="Usuario" method="post" onsubmit="return  validarFormulario()" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=usuario.getId()%>">  
                
                    <div class="col-md-6">
                        <label for="txtNombre" class="form-label">Nombre</label>
                        <input  id="txtNombre" class="validate form-control" type="text" name="nombre" value="<%=usuario.getNombre()%>" required class="validate" maxlength="30">  
                    </div>                       
                    <div class="col-md-6">
                        <label for="txtApellido" class="form-label">Apellido</label>
                        <input  id="txtApellido" class="validate form-control" type="text" name="apellido" value="<%=usuario.getApellido()%>" required class="validate" maxlength="30">
                    </div> 
                    <div class="col-md-6">
                        <label for="txtLogin" class="form-label">Login</label>
                        <input  id="txtLogin" class="validate form-control" type="text" name="login" value="<%=usuario.getLogin()%>" required  class="validate" maxlength="25">
                    </div>                     
                    <div class="col-md-6">   
                        <label for="slEstatus" class="form-label" >Estatus</label>
                        <select id="slEstatus" name="estatus" class="form-select">
                            <option value="0" <%=(usuario.getEstado()== 10) ? "selected" : ""%>>SELECCIONAR</option>
                            <option value="<%=Usuario.EstadoUsuario.ACTIVO%>"  <%=(usuario.getEstado() == Usuario.EstadoUsuario.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                            <option value="<%=Usuario.EstadoUsuario.INACTIVO%>"  <%=(usuario.getEstado() == Usuario.EstadoUsuario.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                        </select>          
                        <span id="slEstatus_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="col-md-6">   
                        <jsp:include page="/Views/Rol/select.jsp">                           
                            <jsp:param name="id" value="<%=usuario.getIdrol()%>" />  
                        </jsp:include>  
                        <span id="slRol_error" style="color:red" class="helper-text"></span>
                    </div>
                

                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Guardar</button>
                    <a href="Usuario" class="btn btn-danger">Cancelar</a>                          
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var slEstatus = document.getElementById("slEstatus");
                var slEstatus_error = document.getElementById("slEstatus_error");
                var slRol = document.getElementById("slRol");
                var slRol_error = document.getElementById("slRol_error");
                if (slEstatus.value == 0) {
                    slEstatus_error.innerHTML = "El estatus es obligatorio";
                    result = false;
                } else {
                    slEstatus_error.innerHTML = "";
                }
                if (slRol.value == 0) {
                    slRol_error.innerHTML = "El Rol es obligatorio";
                    result = false;
                } else {
                    slRol_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
