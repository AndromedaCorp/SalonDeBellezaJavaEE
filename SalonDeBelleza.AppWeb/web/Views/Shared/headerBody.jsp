<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="salondebelleza.appweb.utils.*"%>



    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="Home">Salon de Belleza</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
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
          <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Acciones
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a class="dropdown-item" href="Usuario?accion=cambiarpass">Cambiar password</a></li>
            <li><a class="dropdown-item" href="Usuario?accion=login">Cerrar sesión</a></li>
          </ul>
        </li>  
      </ul>
    </div>
  </div>
</nav>