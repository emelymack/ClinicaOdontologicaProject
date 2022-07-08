function deleteById(dentistId){

    const url = `dentists/${dentistId}`;
    const settings = {
        method: "DELETE"
    }

    fetch(url,settings)
    .then(response => response.text())
    .then(data => console.log(data))

    //remove deleted dentist's row
    let row_id = `#tr_${dentistId}`
    document.querySelector(row_id).remove();
}
