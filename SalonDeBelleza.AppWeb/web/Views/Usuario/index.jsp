<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.entidadesdenegocio.Usuario"%>
<%@page import="salondebelleza.entidadesdenegocio.Rol"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (usuarios == null) {
        usuarios = new ArrayList();
    } else if (usuarios.size() > numReg) {
        double divNumPage = (double) usuarios.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Usuario</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar Usuario</h5>
            <form action="Usuario" method="post" class="row g-3">
                <div>
                    <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">
                </div>
                 
                
                    <div class="col-md-6">
                        <label for="txtNombre" class="form-label">Nombre</label>
                        <input  id="txtNombre" class="form-control" type="text" name="nombre">
                        
                    </div>  
                    <div class="col-md-6">
                        <label for="txtApellido" class="form-label">Apellido</label>
                        <input  id="txtApellido" class="form-control" type="text" name="apellido">
                        
                    </div> 
                    <div class="col-md-6">
                        <label for="txtNumero" class="form-label">Numero</label>
                        <input  id="txtNumero" class="form-control" type="text" name="numero">
                        
                    </div>
                    <div class="col-md-6">
                        <label for="txtDui" class="form-label">DUI</label>
                        <input  id="txtDUI" class="form-control" type="text" name="dui">
                        
                    </div>  
                    <div class="col-md-6">
                        <label for="txtLogin" class="form-label">Login</label>
                        <input  id="txtLogin" class="form-control" type="text" name="login">
                        
                    </div>                    
                    <div class="col-md-6">   
                        <label for="slEstatus" class="form-label">Estatus</label>
                        <select id="slEstatus" name="estatus" class="form-select">
                            <option value="0">SELECCIONAR</option>
                            <option value="<%=Usuario.EstadoUsuario.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Usuario.EstadoUsuario.INACTIVO%>">INACTIVO</option>
                        </select>       
                        
                    </div>
                    <div class="col-md-6">  
                        <jsp:include page="/Views/Rol/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>                        
                    </div>
                    <div class="col-md-6">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                
                <div class="mb-3">
                    <button type="sutmit" class="btn btn-outline-primary">Buscar</button>
                    <a href="Usuario?accion=create" class="btn btn-outline-success">Crear</a> 
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs table table-striped">
                            <thead>
                                <tr>                                     
                                    <th>Nombre</th>  
                                    <th>Apellido</th>
                                    <th>Numero</th>  
                                    <th>Dui</th>  
                                    <th>Login</th>  
                                    <th>Estatus</th>  
                                    <th>Rol</th>   
                                    <th>Fecha registro</th>   
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Usuario usuario : usuarios) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                        String estatus = "";
                                        switch (usuario.getEstado()) {
                                            case Usuario.EstadoUsuario.ACTIVO:
                                                estatus = "ACTIVO";
                                                break;
                                            case Usuario.EstadoUsuario.INACTIVO:
                                                estatus = "INACTIVO";
                                                break;
                                            default:
                                                estatus = "";
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">                                    
                                    <td><%=usuario.getNombre()%></td>  
                                    <td><%=usuario.getApellido()%></td>
                                    <td><%=usuario.getNumero()%></td>
                                    <td><%=usuario.getDui()%></td>
                                    <td><%=usuario.getLogin()%></td>  
                                    <td><%=estatus%></td>
                                    <td><%=usuario.getRol().getNombre()%></td> 
                                    <td><%=usuario.getFechaRegistro()%></td> 
                                    <td>
                                        <div style="display:flex">
                                            <a href="Usuario?accion=edit&id=<%=usuario.getId()%>" title="Modificar" class="btn btn-success">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                                </svg>
                                            </a>
                                            <a href="Usuario?accion=details&id=<%=usuario.getId()%>" title="Ver" class="btn btn-info">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                                                    <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                                    <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                                                </svg>
                                            </a>
                                            <a href="Usuario?accion=delete&id=<%=usuario.getId()%>" title="Eliminar" class="btn btn-danger">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-archive" viewBox="0 0 16 16">
                                                    <path d="M0 2a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 12.5V5a1 1 0 0 1-1-1V2zm2 3v7.5A1.5 1.5 0 0 0 3.5 14h9a1.5 1.5 0 0 0 1.5-1.5V5H2zm13-3H1v2h14V2zM5 7.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
                                                </svg>
                                            </a>     
                                        </div>
                                    </td>                                
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>             
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>