<?php

$singin = new SQLite3('baseDato.db');

$nombreIn = $_POST['nombreIn'];
$usuarioIn = $_POST['usuarioIn'];
$passwordIn = $_POST['passwordIn'];
$apellidoIn = "-";
$edadIn = 0;
//ahora el comando para ingresar
$ingresar ="INSERT INTO login(Usuario,Contrasena,Nombre,Apellido,Edad) VALUES('$usuarioIn','$passwordIn','$nombreIn','$apellidoIn','$edadIn')"; // aca ingresamos cada uno
//ejecutamos la consulta:
$res =  $singin->exec($ingresar);

if ($res) {
    echo json_encode("Fila insertada con éxito");
} else {
    echo json_encode("Error al insertar fila: " . $singin->lastErrorMsg());
}
?>