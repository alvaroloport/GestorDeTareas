function selector(){
    var x = document.getElementById("selector").value;
    if(x=="tarea")
    {
        document.getElementById("tarea").style.display = "block";
        document.getElementById("usuario").style.display = "none";
        document.getElementById("categoria").style.display = "none";
    }
    else if (x=="usuario")
    {
        document.getElementById("tarea").style.display = "none";
        document.getElementById("usuario").style.display = "block";
        document.getElementById("categoria").style.display = "none";
    }
    else{
        document.getElementById("tarea").style.display = "none";
        document.getElementById("usuario").style.display = "none";
        document.getElementById("categoria").style.display = "block";
    }
}