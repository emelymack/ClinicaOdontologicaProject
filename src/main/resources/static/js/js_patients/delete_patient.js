function deleteById(patientId){

    const url = `patients/${patientId}`
    const settings = {
        method: "DELETE"
    }

    fetch(url,settings)
    .then(response => response.text())
    .then(data => console.log(data))

    //remove deleted patient's row
    let row_id = `#tr_${patientId}`
    document.querySelector(row_id).remove();
}