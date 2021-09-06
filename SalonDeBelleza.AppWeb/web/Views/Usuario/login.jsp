<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Login</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Login</h5>
            <form action="Usuario?accion=login" method="post" class="row g-3">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">     
                
                
                    <div class="col-md-6">                                             
                        <label for="txtLogin" class="form-label">Login</label>
                        <input  id="txtLogin" class="form-control" type="text" name="login" required class="validate" maxlength="25">  
                    </div>                                       
                
                
                
                    <div class="col-md-6">                                             
                        <label for="txtPassword" class="form-label">Password</label>
                        <input  id="txtPassword" class="form-control" type="password" name="password" required class="validate" minlength="5" maxlength="32">  
                    </div>                                       
                
                
                <div class="mb-3">
                    <button type="sutmit" class="btn btn-primary">Login</button> 
                </div>
                <% if (request.getAttribute("error") != null) { %>
                <div class="row">
                    <div class="col l12 s12">
                        <span style="color:red"><%= request.getAttribute("error") %></span>                                              
                    </div>
                </div>
                <%}%>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>

