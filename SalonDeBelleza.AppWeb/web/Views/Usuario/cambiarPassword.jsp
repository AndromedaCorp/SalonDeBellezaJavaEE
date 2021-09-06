<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Usuario"%>
<% Usuario usuario = (Usuario) request.getAttribute("usuario");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Cambiar password</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Cambiar password</h5>
            <form action="Usuario" method="post"  class="row g-3" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=usuario.getId()%>">  
                
                                   
                    <div class="col-md-6">
                        <label for="txtLogin" class="form-label">Login</label>
                        <input  id="txtLogin" class="validate form-control" type="text" name="login" value="<%=usuario.getLogin()%>" readonly>
                    </div>  
                        
                    <div class="col-md-6">
                        <label for="txtPasswordActual" class="form-label">Password actual</label>
                        <input  id="txtPasswordActual" class="validate form-control" type="password" name="passwordActual" required class="validate" minlength="5" maxlength="32">
                    </div> 
               
                    <div class="col-md-6">
                        <label for="txtPassword" class="form-label">Password</label>
                        <input  id="txtPassword" class="validate form-control" type="password" name="password" required class="validate" minlength="5" maxlength="32">  
                    </div> 
                    
                    <div class="col-md-6">
                        <label for="txtConfirmPassword_aux" class="form-label">Confirmar password</label>
                        <input  id="txtConfirmPassword_aux" class="validate form-control" type="password" name="confirmPassword_aux" required class="validate" minlength="5" maxlength="32">
                        <span id="txtConfirmPassword_aux_error" style="color:red" class="helper-text"></span>
                    </div> 
                

                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Guardar</button> 
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
                if (txtPassword.value != txtConfirm_password.value) {
                    txtConfirm_password_error.innerHTML = "El password y confirmar password debe ser iguales";
                    result = false;
                } else {
                    txtConfirm_password_error.innerHTML = "";
                }
                return result;
            }
        </script>
    </body>
</html>

