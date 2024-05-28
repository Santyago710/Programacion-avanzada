const container = document.getElementsByClassName('contenedorPrincipal');
const registerBtn = document.getElementById('registrarse');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container[0].classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container[0].classList.remove("active");
});


/* Conexion */
// Login : 
const login = document.getElementById("log-in");
login.addEventListener('submit', (e)=>{
    e.preventDefault();
    var dataLogin = new FormData(login); // creamos un formData para enviar los datos
    fetch('../src/login.php', {
        method: 'POST',
        body: dataLogin
        })
        .then(res => res.json())
        .then(data=>{
            if(data.url != '1'){
               // console.log(data)
                window.location.replace(data.url);
            }
            else{
                alert(data.txt);
            }

        })
        
            .catch(error => {
            // Manejar errores de la solicitud
            console.error('Error en la solicitud,chale'+ error);
        });
        
})

// Sing-in

const singin =  document.getElementById('sing-in');
singin.addEventListener('submit', (e)=>{
    e.preventDefault();
    var dataSingin =  new FormData(singin);
    fetch('../src/singin.php', {
        method: 'POST',
        body: dataSingin
        })
        .then(res=>res.json())
        .then(data=>{
            console.log(data);
        })
        window.alert("Registro Valido")
})

