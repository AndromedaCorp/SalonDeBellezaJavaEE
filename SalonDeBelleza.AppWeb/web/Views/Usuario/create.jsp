<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Usuario"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Usuario</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Usuario</h5>
            <form action="Usuario" method="post"  class="row g-3" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="col-md-6">
                        <label for="txtNombre" class="form-label">Nombre</label>
                        <input  id="txtNombre" class="validate form-control" type="text" name="nombre" required class="validate" maxlength="30">
                    </div>                       
                    <div class="col-md-6">
                        <label for="txtApellido" class="form-label">Apellido</label>
                        <input  id="txtApellido" class="validate form-control" type="text" name="apellido" required class="validate" maxlength="30">
                    </div> 
                    <div class="col-md-6">
                        <label for="txtDui" class="form-label">DUI</label>
                        <input  id="txtDUI" class="validate form-control" type="text" name="dui" required class="validate" maxlength="30">
                    </div> 
                    <div class="col-md-6">
                        <label for="txtNumero" class="form-label">Numero</label>
                        <input  id="txtNumero" class="validate form-control" type="text" name="numero" required class="validate" maxlength="30">
                    </div> 
                    <div class="col-md-6">
                        <label for="txtLogin" class="form-label">Login</label>
                        <input  id="txtLogin" class="validate form-control" type="text" name="login" required class="validate" maxlength="25">
                    </div> 
                    <div class="col-md-6">
                        <label for="txtPassword" class="form-label">Password</label>
                        <input  id="txtPassword" class="validate form-control"  type="password" name="password" required class="validate" minlength="5" maxlength="32">
                    </div> 
                    <div class="col-md-6">
                        <label for="txtConfirmPassword_aux" class="form-label">Confirmar password</label>
                        <input  id="txtConfirmarPassword_aux" class="validate form-control" type="password" name="confirmarPassword_aux" required class="validate" minlength="5" maxlength="32">
                        <span id="txtConfirmarPassword_aux_error" style="color:red" class="helper-text"></span>
                    </div> 
                    <div class="col-md-6">  
                        <label for="slEstatus" class="form-label">Estatus</label>
                        <select id="slEstatus" name="estatus" class="form-select">
                            <option value="0">SELECCIONAR</option>
                            <option value="<%=Usuario.EstadoUsuario.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Usuario.EstadoUsuario.INACTIVO%>">INACTIVO</option>
                        </select>       
                        <span id="slEstatus_error" style="color:red" class="helper-text"></span>
                    </div>
                   <div class="col-md-6">   
                        <jsp:include page="/Views/Rol/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>  
                        <span id="slRol_error" style="color:red" class="helper-text"></span>
                    </div>
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
                var txtPassword = document.getElementById("txtPassword");
                var txtConfirm_password = document.getElementById("txtConfirmPassword_aux");
                var txtConfirm_password_error = document.getElementById("txtConfirmPassword_aux_error");
                var slEstatus = document.getElementById("slEstatus");
                var slEstatus_error = document.getElementById("slEstatus_error");
                var slRol = document.getElementById("slRol");
                var slRol_error = document.getElementById("slRol_error");
                if (txtPassword.value != txtConfirm_password.value) {
                    txtConfirm_password_error.innerHTML = "El password y confirmar password debe ser iguales";
                    result = false;
                } else {
                    txtConfirm_password_error.innerHTML = "";
                }
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
