<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Cliente</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Cliente</h5>
            
            <form action="Cliente" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" required class="validate" maxlength="50"> 
                        <label for="txtNombre">Nombre</label>
                </div>   
                        
                        
                <div class="input-field col l4 s12"> 
                        <input  id="txtApellido" type="text" name="apellido" required class="validate" maxlength="50">
                        <label for="txtDescripcion">Apellido</label>
                </div>    
                <div class="input-field col l4 s12">
                        <input  id="txtDui" type="text" name="dui" required class="validate" maxlength="50">
                        <label for="txtDui">DUI</label>
                </div>
                <div class="input-field col l4 s12">    
                        <input  id="txtNumero" type="text" name="numero" required class="validate" maxlength="30">
                         <label for="txtNumero">Numero</label>
                </div>
                </div>
                    <div class="row">
                <div class="input-field col l4 s12">
                    <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                    <a href="Cliente" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a> 
                </div>
                </div>
            </form>     
                
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
