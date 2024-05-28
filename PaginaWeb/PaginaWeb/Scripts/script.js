
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    var pImgMenuElementos = document.getElementsByClassName("pImgMenu");
    var div_imgPanelElementos = document.getElementsByClassName("div_imgPanel");

    if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
        document.getElementById("Inicio").style.padding = "0% 1%";
        document.getElementById("Inicio").style.height = "3rem";

        for(var p of pImgMenuElementos){
            p.style.visibility = "hidden";
        }

        for(var p of div_imgPanelElementos){
            p.style.height = "100%";
        }


    } else {
        document.getElementById("Inicio").style.padding = "1.5% 1%";
        document.getElementById("Inicio").style.height = "5rem";

        for(var p of pImgMenuElementos){
            p.style.visibility = "visible";
        }
        for(var p of div_imgPanelElementos){
            p.style.height = "60%";
        }
    }
}

