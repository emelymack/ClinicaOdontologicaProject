function deleteById(appointmentId){
    var confirm = window.confirm("Want to delete this appointment?")
    if(confirm){
        const url = `appointments/${appointmentId}`;
        const settings = {
            method: "DELETE"
        }

        fetch(url,settings)
        .then(response => response.text())
        .then(data => console.log(data))

        //remove deleted dentist's row
        let row_id = `#tr_${appointmentId}`
        document.querySelector(row_id).remove();
    }
}
