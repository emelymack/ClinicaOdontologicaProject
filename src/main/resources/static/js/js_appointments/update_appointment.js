window.addEventListener('load', ()=>{

    /*Get the update form with the data the user may have changed */
    const updateForm = document.querySelector('#update_appointment_form');
    updateForm.addEventListener('submit', function(e) {
        let dentistId = document.querySelector('#appointment_id').value;

        //create a JSON with the appointment's data and send the appointment id
        //so that it can be identified and modified, and not uploaded as a new appointment
        const formData={
            id: document.querySelector('#appointment_id').value,
            patient: {
                id: document.querySelector("#patient_id").value
            },
            dentist: {
                id: document.querySelector("#dentist_id").value
            },
            date: document.querySelector('#date').value
        };

        //invoke the dentists API with fetch, method --> PUT
        const url = '/appointments';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
        .then(response => response.json())
        .then(data => console.log(data))
    })

    const btnCancel = document.querySelector('#btn-update-cancel');
    btnCancel.addEventListener('click', () => {
        document.querySelector('#div_appointment_updating').style.display="none";
    })

})

//It's the function that executes when the user clicks on an appointment ID from the list
//it fills out the form with the appointments's data that wants to be modified
function findBy(id){

    const url = `/appointments/${id}`;
    const settings = {
        method: "GET"
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        let dentist = data;
        document.querySelector('#appointment_id').value = appointment.id;
        document.querySelector('#patient_id').value = appointment.patient.id;
        document.querySelector('#dentist_id').value = appointment.dentist.id;
        document.querySelector('#date').value = appointment.date;

        //change form display so that it is now visible
        document.querySelector('#div_appointment_updating').style.display="block";
    })
    .catch(err => {
        alert("Error!: "+err);
    })

}

