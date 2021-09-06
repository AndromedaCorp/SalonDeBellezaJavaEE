<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.appweb.utils.*"%>
<% if (SessionUser.isAuth(request) == false) {
         response.sendRedirect("Usuario?accion=login");
    }
%>--%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Home</title>
  </head>
  <body>
      <jsp:include page="/Views/Shared/headerBody.jsp" />  
      
        <main class="container"> 
            <div class="row">
                <div class="col l12 s12">
                    <h1>Bienvenidos</h1> 
                    <span>Sea bienvenido al MyBeaty Salon</span> 
                </div>
            </div>            
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
  </body>
</html>