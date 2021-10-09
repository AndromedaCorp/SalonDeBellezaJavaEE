<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.appweb.utils.*"%>



    <nav class="nav-wrapper purple darken-4">
        <a href="Home" class="brand-logo"><font face="Comic Sans MS,arial"><b>Salon de Belleza</b></a>
    <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    <ul class="right hide-on-med-and-down">
   <% if (SessionUser.isAuth(request)) {  %>
            <li><a href="Home"><i class="material-icons left">home</i>Inicio</a></li>
            <li><a href="Usuario"><i class="material-icons left">persona</i>Usuario</a></li>
            <li><a href="Rol"><i class="material-icons left">people</i>Rol</a></li>
            <li><a href="Servicio"><i class="material-icons left">event</i>Servicio</a></li>
            <li><a href="Cliente"><i class="material-icons left">people_outline</i>Cliente</a></li>
            
            <li><a href="Usuario?accion=cambiarpass"><i class="material-icons left">vpn_key</i>Cambiar password</a></li>
            <li><a href="Usuario?accion=login"><i class="material-icons left">power_settings_new</i>Cerrar sesión</a></li>
            <%}%>
          </ul>
    </div>
</nav>
          
          
    <ul class="sidenav" id="mobile-demo">
     <% if (SessionUser.isAuth(request)) {  %>
    <li><a href="Home"><i class="material-icons left">home</i>Inicio</a></li>
    <li><a href="Usuario"><i class="material-icons left">persona</i>Usuario</a></li>
    <li><a href="Rol"><i class="material-icons left">people</i>Rol</a></li>
    <li><a href="Servicio"><i class="material-icons left">event</i>Servicio</a></li>
    <li><a href="Cliente"><i class="material-icons left">people_outline</i>Cliente</a></li>
    <li><a href="Usuario?accion=cambiarpass"><i class="material-icons left">vpn_key</i>Cambiar password</a></li>
    <li><a href="Usuario?accion=login"><i class="material-icons left">power_settings_new</i>Cerrar sesión</a></li>
     <%}%>
</ul>
            
  