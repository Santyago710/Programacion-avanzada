let nav = 0;
let clicked = null;
let events = []; 
// constantes
var TIPO = ""; //me dira que tipo hagarre, si alimento, o mercado etc. 
const iniCM = document.getElementById("iniCM"); // sirve como inicio del Centro del Modal
const arrayGporMes = {}; // para los gastos por mes
const calendar = document.getElementById('calendar');
const modalGastos = document.getElementById('modalGastos');
const backDrop = document.getElementById('modalBackDrop');
const addItem = document.getElementById('addItem');
const newItems = document.getElementsByClassName('newItem');
const setItems = document.getElementsByClassName('setItems');
const centoModal = document.getElementById('centroModal');
const weekdays = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];
var idContItem = 1; // contador para los ids de los items nuevos, no lo veo necesario pero despues
var idNI;
//ahora constantes para almacenar los items, si es de transporte etc-
const startDate = document.getElementById('startDate');
const endDate = document.getElementById('endDate')
var boxTextInfoGastos = document.getElementById('boxTextInfoGastos')
const mesGasto = document.getElementById('mesGasto')
const cantGasto = document.getElementById('cantGasto')
const modelsChart = document.getElementById('modelsChart');
//cada uno es un arreglo para ahi almacenar
//Vamos a iniciar lo que son los colores por cada uno de los gastos, entonces instanciamos dos objetos json: que se relacionan miramente 1:1
const gastosPersona = ['Alimento','Entretenimiento','Transporte','Mercado','Otros','NaN'] //alimento o alimentos, ahora contamos en el mes cuanto hay de cada uno
const colorGasto = ['#E7C4AD','#EBDEF0','#CCD1D1','#ADE7BF','#E7ADE3','#333']//si vemos que hay almenos 1 entonces lo coloca, junto al color, si no, no lo coloca, y listo, eso es todo
var dataGasto = []
var alimentos, entretenimiento, mercado, transporte, otros, nada;
let cantAlimentos = 0,cantEntretenimiento = 0, cantMercado = 0, cantTransporte = 0, cantOtros = 0, cantNada = 0;

const valueEvents = []; // inicio para la tabla, almacenara estos datos
const columnas = ["Gasto", "Tipo", "Cadena","Cantidad"]

const graf = new grafico(); // creamos el grafico, luego
const tabla = new tablaDatos() // creamos la tabla


function addValueEvents() {
    valueEvents.splice(0, valueEvents.length);
    eventsPorMesElegido = events.filter((e)=>{
        var dividido = e.Fecha.split('/')
        if(dividido[1]-1 == new Date().getMonth()+nav){
            return e;
        }
    })
    eventsPorMesElegido.forEach((e) => {
      valueEvents.push([e.Gasto, e.Tipo, e.Cadena, e.Valor]);
    });
  }
// Funciones importantes: 
function openModal(date) {
    clicked=date;
    modalGastos.style.display = "block"; // lo ponemos block para hacerlo visible
    backDrop.style.display = "block";
}
function load() {
    const dt = new Date();
    if (nav !== 0) 
        dt.setMonth(new Date().getMonth() + nav);
    const month = dt.getMonth();
    const year = dt.getFullYear();
    const firstDay = new Date(year, month, 1); // esto nos da un numero
    const daysInMonth = new Date(year, month + 1, 0).getDate(); // to give me the numbers of day of the month that we want (this case this month)
    const paddingDays = firstDay.getDay(); // the numbers of day before our week begining jijijiji
    document.getElementById('monthDisplay')
        .innerText = `${dt.toLocaleDateString('es-ES', { month: 'long' })}  ${year}`;
    calendar.innerHTML = ''; // aqui eliminamos todo el html que tengamos para volverlo a cambiar, si no se elimina pailaswas

    for (let i = 1; i <= paddingDays + daysInMonth; i++) {
        const daySquare = document.createElement('div'); // we create a div
        daySquare.classList.add('day'); // we do that each one will be class = "day"
        const dayString = `${i - paddingDays}/${month + 1}/${year}`; // para convertirlo en simplemente una funcion
        if (i > paddingDays) {
            daySquare.innerText = i - paddingDays; // we put a day under the box
            const eventForDay = events.filter(event => event.Fecha === dayString); // filtra todos los que tengan la misma fecha
            const eventTypesShown = {}; // Objeto para realizar un seguimiento de los tipos mostrados
            for (let a = 0; a < eventForDay.length; a++) {
                const currentEvent = eventForDay[a];
                const eventType = currentEvent.Tipo;
                // Verificar si el tipo ya ha sido mostrado
                if (!eventTypesShown[eventType]) {
                    const eventDiv = document.createElement('div');
                    eventDiv.classList.add('event');
                    eventDiv.innerText = eventType;
                    daySquare.appendChild(eventDiv);
                    // Marcar el tipo como mostrado
                    eventTypesShown[eventType] = true;
                }
            }
            daySquare.addEventListener('click', () => openModal(dayString));
            if(i-paddingDays === new Date().getDate() && nav == 0){
                daySquare.style.background = "#BDA7CF";
            }
        }
        else {
            daySquare.classList.add('padding'); // lo añadimos a una lista oculta, jijjii
        }
        calendar.appendChild(daySquare); // al parecer esto es para añadir jijiji appendChild lo añadimos al calendar
    }
    gastoPorMes(2); //aqui se envian cada que se recatga el gasto mensual.
    escribirLosGastosPorMes(dt.toLocaleDateString('es-ES', { month: 'long' }));
    graf.renderModelsChart(gastosPersona,dataGasto,colorGasto,modelsChart);
    addValueEvents();
    tabla.crearTabla(columnas,valueEvents,document.getElementById("tabla"));
}

function escribirLosGastosPorMes(mesElegido){
    mesGasto.innerText = mesElegido.toUpperCase();
    gastoPorMes(1);
}
function closeModal() {
    modalGastos.style.display = "none";
    backDrop.style.display = "none";
    clicked = null;
    for (let a = 0; a < newItems.length; a++) {
        newItems[a].style.display = "none";
    }
    for (let a = 0; a < setItems.length; a++) {
        setItems[a].style.display = "none";
    }
    iniCM.style.display = "block";
    load();
}
//funcion de 

// Inicializar
function initItem() {
    //esto ya no sera necesario porque se añadira al centro modal
    const newItem = document.createElement('div');
    newItem.classList = 'newItem';
    do{
    idNI = 'miDiv' + idContItem; // ponemos un id a cada uno
    idContItem++;
    }while(events.filter((e)=>e.Id === idNI).length >= 1); 

    newItem.id =idNI;
    newItem.dataset.date = clicked; // le añado la fecha a cada item, de esa forma los puedo organizar
    newItem.dataset.modelo = TIPO; // entonces cada div tiene una fecha y un modelo, es decir, 05/12/2023 -> Alimentos , si?
    newItem.style = "display: flex;"
    newItem.innerHTML = `
    <div class ="ppI" id = "arribaNew${idNI}"> 
        <input type="text" id="inputTipo${idNI}" style = "margin:1%" class = "inputItem" placeholder = "Item" >
        <input type="number" id="inputValor${idNI}" style = "margin:1%" class = "inputItem" placeholder = "Valor" >
        <div class="empresa" id="empresa${idNI}"></div>
    </div>
    <div class ="ppI" id ="abajoNew${idNI}">
        <button onclick="aceptar(this)" id = "bontonAceptar${idNI}" >Aceptar </button>
        <button onclick="modificar(this)" id = "botonModificar${idNI}">Modificar</button>
        <button onclick="eliminar(this)" id = "botonEliminar${idNI}" > Eliminar</button>
    </div>
        `;
    centoModal.appendChild(newItem); // añadimos el nuevo item
    const empresaAux = document.getElementById('empresa'+idNI)
    cadena(empresaAux,TIPO,idNI)
}
function initButtons() {
    document.getElementById('nextButton').addEventListener('click', () => {
        nav++;
        load();
    });
    document.getElementById('backButton').addEventListener('click', () => {
        nav--;
        load(); // para cuando mvoemos los indicadores
    });

    document.getElementById('cancelButton').addEventListener('click', closeModal);
    //listener al add item
    addItem.addEventListener('click', ()=>{initItem()}); // solo lo inicamos, este se cambio, verificar si no srive
    
    document.getElementById('gastoCalcular').addEventListener('click', () => {
        var diasTotales = gastoSegmentado(startDate.value,endDate.value)
        total = valorGastoSegmentado(startDate.value,diasTotales,0)
        console.log(total) // vamos a meterlo en un div provicional
        const gg = document.getElementById('mostrarGasto');
        gg.innerHTML = `Gasto: ${total}`;
    })
}
// funcion boton diferencia gastos
function gastoSegmentado(fechaInicio, fechaFinal) {
    var fInicio = fechaInicio.split('-');  var fFinal = fechaFinal.split('-');  var fechaInicioDate = new Date(fInicio[0], fInicio[1] - 1, fInicio[2]); // año, mes (restar 1), día
    var fechaFinDate = new Date(fFinal[0], fFinal[1] - 1, fFinal[2]); // al ser separadas por spli estan definicas de esa forma
    var difDiasMS = fechaFinDate.getTime()-fechaInicioDate.getTime()
    var difDias = difDiasMS/ (1000*60*60*24)   //ya que dimos los dias en milisegundos
    return difDias;
}
function valorGastoSegmentado(fechaInicio , diasTotales, opcion){
    if(diasTotales<0) {return;}
    var sumaGastos = 0; // iniciamos una suma de gastos
    var varFecha = new Date(fechaInicio);
    cantAlimentos = 0 ; cantEntretenimiento = 0; cantMercado = 0; cantTransporte = 0; cantOtros = 0; // inicializamos para no caer en un error de sumarce al pasado
    varFecha.setDate(varFecha.getDate() + 1) // p de provicional
    for(var i = 0; i < diasTotales;i++ ){
        // haremos que dia se inicio en formato dia,mes,año
        const dia = varFecha.getDate();  //quiero el dia Para que coja el dia primero
        const mes = (varFecha.getMonth()+1)// el mes 
        const año = varFecha.getFullYear(); // y el año
        const newFecha = `${dia}/${mes}/${año}`;
        const filteredEvents = events.filter((e) => e.Fecha === newFecha);
        filteredEvents.forEach((e)=>{
            sumaGastos+=parseFloat(e.Valor) ; // sumamos los gastos a sumaGastos
            if(opcion ===1 ){
                switch(e.Tipo)
                {
                    case 'Alimentos':  
                        alimentos++
                        cantAlimentos += parseFloat(e.Valor);
                    break;
                    case 'Entretenimiento':  
                        entretenimiento++
                        cantEntretenimiento += parseFloat(e.Valor);
                    break;
                    case 'Mercado':  
                        mercado++
                        cantMercado += parseFloat(e.Valor);
                    break;
                    case 'Transporte':  
                        transporte++
                        cantTransporte += parseFloat(e.Valor);
                    break;
                    case 'Otros':  
                        otros++
                        cantOtros += parseFloat(e.Valor);
                    break;
                    default:
                        cantNada++;
                    break;
                    }
            }
            });
        varFecha.setDate(varFecha.getDate()+1); //sumamos el dia
    }
    if(opcion===1){
        dataGasto = [Number(cantAlimentos),Number(cantEntretenimiento),Number(cantTransporte),Number(cantMercado),Number(cantOtros),Number(cantNada)]
        var canttt = 0;
        dataGasto.forEach((valor)=>{
            if(valor === 0){
                console.log("aja")
                canttt++;
            }
            if(canttt===dataGasto.length){
                    //una vez hecho el chart reiniciamos los datos
                    dataGasto = [0,0,0,0,0,1]
                    //dataGasto.splice(0,dataGasto.length);
            }
            });

        console.log("Tonc: "+cantNada)
    }
    return sumaGastos;
}
//obtener los nombres del mes por numero
function obtenerNombreMes(numeroMes) {
    const meses = [
      "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", //el aux al inicio puede ser importante si no funciona
      "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    ];
    return meses[numeroMes];
}
function gastoPorMes(opcion){ // opcion 1 es solo para escribir el dato en el MES si no enviar los datos a la base de datos
    Object.assign(arrayGporMes, {});
    for (let mes = 0; mes < 12; mes++) {
        const fechaInicio = new Date(2023, mes, 0); // El día 1 de cada mes
        const ultimoDiaMes = new Date(2023, mes, 0).getDate();// Obtiener el último día del mes actual
        //se calculara los gastos por mes
        const gastosMes = (mes === (new Date().getMonth()+nav))?valorGastoSegmentado(fechaInicio, ultimoDiaMes,1) :valorGastoSegmentado(fechaInicio, ultimoDiaMes,0); 
        //aca metermos el condicional para escribir el gasto por mes
        if(mes === (new Date().getMonth()+nav)){
            cantGasto.innerText = gastosMes+" COP";
        }
        // Almacena los resultados en el array
        arrayGporMes[obtenerNombreMes(mes)] = gastosMes;
      }
      //aqui enviaremos los datos a la base de datos ñejeje: 
    if(opcion ===2){
       console.log(arrayGporMes)
       enviarDatos(arrayGporMes,'../../src/PHPP/gastosPorMes.php'); // lo corro
    }
}
// funciones botones para aceptar o no en el modal al expandir cada lugar
function aceptar(btn) { 
    var contenedor = btn.parentNode.parentNode;
    var inputs = contenedor.querySelectorAll('input');
    var inputTipo = "";
    var inputValor = 0;
    var cadenaContenedor = document.getElementById('cadenaElegida'+contenedor.id)
    var cadenaValor = cadenaContenedor.value; //obtenemos el valor de la cadena y toca mandar la cadena

    for(let a = 0; a <inputs.length; a++){
        condicionalValores = (inputs[a].type === "text")?inputTipo = inputs[a].value:inputValor = inputs[a].value;
    }
    inputValor = (inputValor.length === 0)? inputValor = 0 : inputValor = inputValor; // aqui cambiamos
    inputTipo = (inputTipo.length === 0)? inputTipo = "-.-" : inputTipo = inputTipo; // aqui cambiamos
    const obj = {
        Fecha: clicked, // en si guarda lo que es la fecha que clickeamos
        Id: contenedor.id,
        Tipo: TIPO, // si es alimento, transporte etc
        Gasto: inputTipo,
        Valor: inputValor,
        Cadena: cadenaValor //metemos la cadena a la que pertenece xd
    };
        events.push(obj); // añadare tambien el objeto a los eventos
        //hacemos el push obj, y aqui mandamos un fecth para que tambien lo mande a nuestra querida base de datos: 
    enviarDatos(obj,'../../src/PHPP/gastosGuardar.php');
} // PRIMER REGISTRO A LEER----------------------------------------------------------------------------------------- 1PRIMER




function modificar(btn) {

}
function eliminar(btn) {
    var contenedor = btn.parentNode.parentNode//.id;
    const elementoEliminar = document.getElementById(contenedor.id)
    elementoEliminar.remove();
}
/* La parte en la que expandimos el Modal */
function tabHidde(tab,divisor) {
    const y = document.getElementById(tab);
    TIPO = divisor.id;
    iniCM.style.display = "none";
    y.style.display = "grid";
    // necesito que, fuera de quitar el display me muestre los de la fecha: 
    var newItemElements = document.querySelectorAll('.newItem');
    var cont = 0;
    for(var a = 0; a < newItemElements.length ; a++){
     if (newItemElements[a].dataset.modelo === TIPO && newItemElements[a].dataset.date === clicked) {
        newItemElements[a].style.display = "block";
        }
        else{
        newItemElements[a].style.display = "none";
        }
    }
    
}
initButtons(); // e iniciamos los botones