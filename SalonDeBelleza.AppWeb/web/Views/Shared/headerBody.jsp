<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.appweb.utils.*"%>

<!--Creado con booostrap-->



<!doctype html>
<!--<html lang="en">
  <head>
     Required meta tags 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

     Bootstrap CSS 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">-->

<!--    <title>Hello, world!</title>
-->  
<!--</head>
  <body>-->
    <nav class="navbar navbar-light bg-light fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="Home">Salon de Belleza</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasNavbarLabel" href="Home">Salon de Belleza</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page"  href="Home">Inicio</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="Usuario">Usuario</a>
          </li>   
           <li class="nav-item">
            <a class="nav-link"href="Rol">Rol</a>
          </li>    
           <li class="nav-item">
            <a class="nav-link"href="Servicio">Servicio</a>
          </li>    
           <li class="nav-item">
            <a class="nav-link"  href="Cliente">Cliente</a>
          </li>    
           <li class="nav-item">
            <a class="nav-link" href="Usuario?accion=cambiarpass">Cambiar password</a>
          </li>    
           <li class="nav-item">
            <a class="nav-link" href="Usuario?accion=login">Cerrar sesión</a>
          </li>    
        </ul>       
      </div>
    </div>
  </div>
</nav>

    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
<!--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>-->

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    -->
<!--  </body>
</html>-->



<!--Ejemplo de marvin-->

<!--<nav>
    <div class="nav-wrapper blue">
        <a href="Home" class="brand-logo">Salon de Belleza</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down">  
        <%--    <% if (SessionUser.isAuth(request)) {  %> --%>
            <li><a href="Home">Inicio</a></li>
            <li><a href="Usuario">Usuario</a></li>
            <li><a href="Rol">Rol</a></li>
            <li><a href="Servicio">Servicio</a></li>
            <li><a href="Cliente">Cliente</a></li>
            <li><a href="Usuario?accion=cambiarpass">Cambiar password</a></li>
            <li><a href="Usuario?accion=login">Cerrar sesión</a></li>
         <%--     <%}%> --%>
        </ul>
    </div>
</nav>-->

<!--<ul class="sidenav" id="mobile-demo">
 <%--      <% if (SessionUser.isAuth(request)) {  %> --%>
    <li><a href="Home">Inicio</a></li>
    <li><a href="Usuario">Usuario</a></li>
    <li><a href="Rol">Rol</a></li>
    <li><a href="Servicio">Servicio</a></li>
    <li><a href="Cliente">Cliente</a></li>
    <li><a href="Usuario?accion=cambiarpass">Cambiar password</a></li>
    <li><a href="Usuario?accion=login">Cerrar sesión</a></li>
   <%--     <%}%> --%>
</ul>-->