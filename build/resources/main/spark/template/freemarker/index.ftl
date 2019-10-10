<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de estudiantes</title>
    <link href="css/stylesheet.css" type="text/css" rel="stylesheet">
</head>
<body>

<header id="header">
    <nav class="links" style="--items: 5;">
        <a href="/">Inicio</a>
        <a href="crear.html">Crear</a>
        <a href="#">Editar</a>
        <a href="#">Eliminar</a>
        <span class="line"></span>
    </nav>
</header>

<div class="container">
    <table>
        <thead>
        <tr>
            <th>Matricula</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Telefono</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>20151256</td>
            <td>Cristian</td>
            <td>Bueno</td>
            <td>809-265-2324</td>
        </tr>
        <#list listado as estudiante>
            <tr>
                <td>${estudiante.matricula}</td>
                <td>${estudiante.nombre}</td>
                <td>${estudiante.apellido}</td>
                <td>${estudiante.telefono}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>