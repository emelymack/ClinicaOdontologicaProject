function findBy(dentistId){

    const url = `dentists/${dentistId}`;
    const settings = {
        method: "PUT"
    }

    fetch(url,settings)
    .then(response => response.text())
    .then(data => console.log(data))

    //remove deleted dentist's row
    let row_id = `#tr_${dentistId}`
    document.querySelector(row_id).remove();
}

window.addEventListener('load', ()=>{

    /*Buscamos y obtenemos el form donde están los datos que el usuario
    pudo haber modificado de la película*/




})