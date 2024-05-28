<?php
//esta sera para guardar los datos en la base de datos,

//iniciamos la session
session_start();
$bd =  new SQLite3('../baseDato.db'); 

// lo que recibe 
$data = json_decode(file_get_contents('php://input'), true);

//pbtendremos cada dato
$persona = $_SESSION['id'];
$fecha = $data['Fecha'];
$id = $data['Id'];
$tipo = $data['Tipo'];
$gasto = $data['Gasto'];
$valor = $data['Valor'];
$cadena =  $data['Cadena'];
// ya una vez obtenidos ahora si procederemos a ponerlos en el insert, pero primero buscaremos si hay
$busqueda = $bd->prepare("SELECT PersonaID,Fecha,Gasto,Tipo,Valor,Cadena FROM gastos WHERE Fecha = :fechaB AND Tipo = :tipoB AND Valor =:valorB AND Gasto=:gastoB AND PersonaID=:personaIDB AND Cadena=:cad");
$busqueda ->bindParam('personaIDB',$persona,SQLITE3_TEXT);
$busqueda ->bindParam('fechaB',$fecha,SQLITE3_TEXT);
$busqueda ->bindParam('gastoB',$gasto,SQLITE3_TEXT);
$busqueda ->bindParam('tipoB',$tipo,SQLITE3_TEXT);
$busqueda ->bindParam('valorB',$valor,SQLITE3_NUM);
$busqueda ->bindParam('cad',$cadena,SQLITE3_TEXT);

$resultado = $busqueda->execute(); //ejecutamos

if($row = $resultado->fetchArray()){
    echo json_encode("si");
}
else{
    $insertar =  $bd->prepare("INSERT INTO gastos(PersonaID,Fecha,Gasto,Id,Tipo,Valor,Cadena) VALUES (:personaID,:fechaN,:gasto,:id,:tipo,:valor,:cadena)"); //fechaN es de fechaNueva para no confudir con arriba
    $insertar ->bindParam('personaID',$persona,SQLITE3_TEXT);
    $insertar ->bindParam('fechaN',$fecha,SQLITE3_TEXT);
    $insertar ->bindParam('gasto',$gasto,SQLITE3_TEXT);
    $insertar ->bindParam('id',$id,SQLITE3_TEXT);
    $insertar ->bindParam('tipo',$tipo,SQLITE3_TEXT);
    $insertar ->bindParam('valor',$valor,SQLITE3_NUM);
    $insertar ->bindParam('cadena',$cadena,SQLITE3_TEXT); 

    $res = $insertar->execute(); // ejecutamos despues de meter todos los parametros

    if ($res) {
        echo json_encode("Registro insertado con éxito. ".$gasto);
    } else {
        echo json_encode("Error al insertar registro:".$fecha);
    }
}
?>