let nav = 0;
let clicked = null;
let events = [] ; //localStorage.getItem('events')? JSON.parse(localStorage.getItem('evetns')) : [];  // Primero mira si hay algo de tipo events en el almacenamiento local, si es asi lo coge, si no no
    // el localStorage sera necesario solamente para hacer pruiebas para usar el almacenamiento local

const calendar = document.getElementById('calendar');
const newEventModal = document.getElementById('newEventModal');
const backDrop = document.getElementById('modalBackDrop'); 
const newItems = document.getElementsByClassName('newItem');
const setItems =  document.getElementsByClassName('setItems');
var idContItem = 1; // contador para los ids de los items nuevos, no lo veo necesario pero despues
//ahora constantes para almacenar los items, si es de transporte etc-
const alimento = []
const entretenimiento = []
const transporte = []
const mercado = []
const otros = []
//cada uno es un arreglo para ahi almacenar

function openModal(date){
    clicked = date;
   // console.log("abierto en: "+clicked);
    const eventForDay =  events.find(e => e.Fecha === clicked );

    if(eventForDay){
        for(let a = 0; a < newItems.length ; a++){
           // console.log("es valido"+a)
            newItems[a].style.display = "flex";
        }
        newEventModal.style.display = "block";

    }
    else{
        newEventModal.style.display = "block";
    }

    backDrop.style.display = "block";

}

function load(){
    const dt = new Date();

    if(nav !== 0){
        dt.setMonth(new Date().getMonth() + nav);
    }
    const month = dt.getMonth();
    const year = dt.getFullYear();
    
    const firstDay = new Date(year,month,1); // esto nos da un numero
    const daysInMonth = new Date(year,month+1,0).getDate(); // to give me the numbers of day of the month that we want (this case this month)
    const paddingDays =  firstDay.getDay(); // the numbers of day before our week begining jijijiji

    document.getElementById('monthDisplay').innerText =  `${dt.toLocaleDateString('es-ES', { month: 'long' })}  ${year}`; 

    calendar.innerHTML = ''; // aqui eliminamos todo el html que tengamos para volverlo a cambiar, si no se elimina pailaswas

    for(var i = 1; i <= paddingDays + daysInMonth ; i++){
        const daySquare = document.createElement('div'); // we create a div
        daySquare.classList.add('day'); // we do that each one will be class = "day"
        const dayString = `${i - paddingDays}/${month + 1}/${year}`; // para convertirlo en simplemente una funcion

        if(i > paddingDays){
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
        }
        else{
            daySquare.classList.add('padding'); // lo añadimos a una lista oculta, jijjii
        }

        calendar.appendChild(daySquare); // al parecer esto es para añadir jijiji appendChild lo añadimos al calendar
    }
}
 
function closeModal(){
    newEventModal.style.display = "none";
    backDrop.style.display = "none";
    clicked = null;
    for(let a = 0; a < newItems.length ; a++){
        newItems[a].style.display = "none";
    }
    for(let a = 0; a < setItems.length ; a++){
        setItems[a].style.display = "none";
    }
    
    load();
}

/*function saveEvent(){
    closeModal();
}*/

function addItemValue(value, obj){ // el value representa en este caso a el id y el obj el que vamos a meter a cada obj
    switch(value) {
        case 'alimentosItems':
            alimento.push(obj);
            break;
        case 'entretenimientoItems':
            entretenimiento.push(obj);
            break;
        case 'transporteItems':
            transporte.push(obj);
            break;
        case 'mercadoItems':
            mercado.push(obj);
            break;
        case 'otrosItems' : 
            otros.push(obj);
            break;
    }
}
function selectorNombreEventoDia(value){ //abstrae un valor y retorna un simple nombre
    switch(value) {
        case 'alimentosItems':
            return 'Alimentos';
        case 'entretenimientoItems':
            return 'Entretenimiento';
        case 'transporteItems':
            return 'Transporte';
        case 'mercadoItems':
            return 'Mercado';
        case 'otrosItems' : 
            return 'Otros';
    }
}

function initItem(event, selector) {
    var padreDelBoton = event.target.closest(`[${selector}]`); // Encuentra el elemento con el selector proporcionado
    var idDelPadre = padreDelBoton.getAttribute(selector); // Obtiene el valor del selector del elemento
    console.log(idDelPadre);
    const padre = document.getElementById(idDelPadre); // usamos el ide del padre para añadir luego ahi mismo un div    

    const newItem = document.createElement('div');
    newItem.classList = 'newItem';
    newItem.id = 'miDiv'+idContItem; // ponemos un id a cada uno
    idContItem++;
    newItem.innerHTML = `
    
    <input type="text" id="inputTipo" style = "margin:1%" class = "inputItem" placeholder = "Item" >

    <input type="text" id="inputValor" style = "margin:1%" class = "inputItem" placeholder = "Valor" >

    <button onclick="aceptar(this)" id = "bontonAceptar" >Aceptar </button>

    <button onclick="modificar(this)" id = "botonModificar">Modificar</button>
    `;
    
    padre.appendChild(newItem); // añadimos el nuevo item
    // y actualizamos el grid
}

function initButtons(){

    document.getElementById('nextButton').addEventListener('click',() => { 
        nav++;
        load();
    });
    document.getElementById('backButton').addEventListener('click',() => { 
        nav--;
        load();
    });

    document.getElementById('cancelButton').addEventListener('click', closeModal);
    //document.getElementById('saveButton').addEventListener('click',saveEvent);

    var addItems = document.getElementsByClassName('addItem');
    for (var i = 0; i < addItems.length; i++) {
        addItems[i].addEventListener('click',(event) => {
            initItem(event, 'id');
        });
    }


    
}

initButtons();
load();


/* La parte en la que expandimos el Modal */

function tabHidde(tab) {
    const y = document.getElementById(tab);

    if (!y.style.display || y.style.display === "none") {
        y.style.display = "grid";
    } else {
        y.style.display = "none";
    }
}
// funciones botones para aceptar o no en el modal al expandir cada lugar

function aceptar(btn) {
    var contenedor = btn.parentNode;
    var inputs = contenedor.querySelectorAll('input');
    const inputTipo = document.getElementById('inputTipo');
    const botonAceptar =  document.getElementById('bontonAceptar'); // el boton aceptar de las funciones
    const inputValor = document.getElementById('inputValor');
    const envoltorio = document.getElementById(contenedor.parentNode.id).id // en que esta aceptnado, a un 
    
    //console.log(envoltorio);

    inputs.forEach(input => {
        var div = document.createElement('div');
        div.innerText = input.value;
        div.className = 'divReemplazo'; // Clase para estilo opcional
        contenedor.replaceChild(div, input);
        if(input.id === 'inputTipo'){
            div.id = 'divTipo';
        }
        else{
            div.id = 'divValor';
        }

    });
    //seleccionamos
    const Tipo = selectorNombreEventoDia(envoltorio);
    // ahora al aceptar tambien debemos de añadirlo a cada uno de los arreglos:  primero el objeto
    const obj = {
        Fecha: clicked, // en si guarda lo que es la fecha que clickeamos
        Id : contenedor.id,
        Tipo : Tipo, 
        Gasto: inputTipo.value, 
        Valor: inputValor.value,
    };
    botonAceptar.style.visibility = "hidden";
    //aqui miramos si existe ya o no: para el tema de modificacion: 
    const validarIndice = events.findIndex(e => e.Id === contenedor.id)
    
    console.log(validarIndice);

    if(validarIndice !== -1){
        events[validarIndice].Gasto = inputTipo.value;
        events[validarIndice].Valor = inputValor.value;
    }
    else{ // si no esta creamos uno nuevo
            
    addItemValue(envoltorio,obj); // y a cad auno de los arreglos dados
    events.push(obj); // añadare tambien el objeto a los eventos
    }

}


function modificar(btn) {
    var contenedor = btn.parentNode;
    var divs = contenedor.querySelectorAll('.divReemplazo');
    const botonAceptar =  document.getElementById('bontonAceptar'); // el boton aceptar de las funciones


    divs.forEach(div => {
        var input = document.createElement('input');
        input.type = 'text';
        input.className = 'inputItem';
        if(div.id === 'divTipo'){
            input.id = 'inputTipo';
        }
        else{
            input.id = 'inputValor';
        }
        input.value = div.innerText;
        input.placeholder = "Escriba"
        contenedor.replaceChild(input, div);
    });

    botonAceptar.style.visibility = "visible";
    

}