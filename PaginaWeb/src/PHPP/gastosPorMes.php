<?php
//iniciamos la sesion
session_start();
$bd =  new SQLite3('../baseDato.db');

$data = json_decode(file_get_contents("php://input"), true);

$personaID = $_SESSION['id'];
$ENERO = $data['Enero'];
$FEBRERO = $data['Febrero'];
$MARZO = $data['Marzo'];
$ABRIL = $data['Abril'];
$MAYO = $data['Mayo'];
$JUNIO = $data['Junio'];
$JULIO = $data['Julio'];
$AGOSTO = $data['Agosto'];
$SEPTIEMBRE = $data['Septiembre'];
$OCTUBRE = $data['Octubre'];
$NOVIEMBRE = $data['Noviembre'];
$DICIEMBRE = $data['Diciembre'];

// pasamos cada uno de los que queremos, ahora buscamos la tabla

$buscaTabla =  $bd->prepare("SELECT * FROM GastoMes WHERE PersonaID = :pID");
$buscaTabla->bindParam('pID',$personaID,SQLITE3_INTEGER); // metemos por entero
$resultado = $buscaTabla->execute();

if($row = $resultado->fetchArray()){
    //para cuando lo encuentre que lo modifique
    $modificar = $bd->prepare("UPDATE GastoMes SET 
    ENERO=:enero,
    FEBRERO=:febrero,
    MARZO=:marzo,
    ABRIL=:abril,
    MAYO=:mayo,
    JUNIO=:junio,
    JULIO=:julio,
    AGOSTO=:agosto,
    SEPTIEMBRE=:septiembre,
    OCTUBRE=:octubre,
    NOVIEMBRE=:noviembre,
    DICIEMBRE=:diciembre
    WHERE PersonaID = $personaID"  
);
    $modificar->bindParam(':enero', $ENERO, SQLITE3_NUM);
    $modificar->bindParam(':febrero', $FEBRERO, SQLITE3_NUM);
    $modificar->bindParam(':marzo', $MARZO, SQLITE3_NUM);
    $modificar->bindParam(':abril', $ABRIL, SQLITE3_NUM);
    $modificar->bindParam(':mayo', $MAYO, SQLITE3_NUM);
    $modificar->bindParam(':junio', $JUNIO, SQLITE3_NUM);
    $modificar->bindParam(':julio', $JULIO, SQLITE3_NUM);
    $modificar->bindParam(':agosto', $AGOSTO, SQLITE3_NUM);
    $modificar->bindParam(':septiembre', $SEPTIEMBRE, SQLITE3_NUM);
    $modificar->bindParam(':octubre', $OCTUBRE, SQLITE3_NUM);
    $modificar->bindParam(':noviembre', $NOVIEMBRE, SQLITE3_NUM);
    $modificar->bindParam(':diciembre', $DICIEMBRE, SQLITE3_NUM);

    //despues veo como mejorar este codigo porque con arreglos es posible, pero por ahora a lo bruto

    $res= $modificar->execute();//y ya
    if($res){
        echo json_encode("godModificacion".$personaID);
    }
    else{
        echo json_encode("chaleModificar");
    }
}
else{
    $add = $bd->prepare("INSERT INTO GastoMes
    (ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE, PersonaID)
    VALUES
    (:enero, :febrero, :marzo, :abril, :mayo, :junio, :julio, :agosto, :septiembre, :octubre, :noviembre, :diciembre, :personaId)");

    $add->bindParam(':enero', $ENERO, SQLITE3_NUM);
    $add->bindParam(':febrero', $FEBRERO, SQLITE3_NUM);
    $add->bindParam(':marzo', $MARZO, SQLITE3_NUM);
    $add->bindParam(':abril', $ABRIL, SQLITE3_NUM);
    $add->bindParam(':mayo', $MAYO, SQLITE3_NUM);
    $add->bindParam(':junio', $JUNIO, SQLITE3_NUM);
    $add->bindParam(':julio', $JULIO, SQLITE3_NUM);
    $add->bindParam(':agosto', $AGOSTO, SQLITE3_NUM);
    $add->bindParam(':septiembre', $SEPTIEMBRE, SQLITE3_NUM);
    $add->bindParam(':octubre', $OCTUBRE, SQLITE3_NUM);
    $add->bindParam(':noviembre', $NOVIEMBRE, SQLITE3_NUM);
    $add->bindParam(':diciembre', $DICIEMBRE, SQLITE3_NUM);
    $add->bindParam(':personaId',$personaID,SQLITE3_NUM);
    //despues veo como mejorar este codigo porque con arreglos es posible, pero por ahora a lo bruto

    $res= $add->execute();//y ya
    if($res){
        echo json_encode("godMETER");
    }
    else{
        echo json_encode("chaleMETER");
    }
}



?>