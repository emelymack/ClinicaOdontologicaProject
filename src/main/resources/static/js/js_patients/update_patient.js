window.addEventListener('load', ()=>{

    /*Get the update form with the data the user may have changed */
    const updateForm = document.querySelector('#update_patient_form');
    updateForm.addEventListener('submit', function(e) {
        let dentistId = document.querySelector('#patient_id').value;

        //create a JSON with the patient's data and send the patient id
        //so that it can be identified and modified, and not uploaded as a new patient
        let addressSplit = document.querySelector('#address').value.split(' ');
        const formData = {
            id: document.querySelector('#patient_id').value,
            name: document.querySelector('#name').value,
            surname: document.querySelector('#surname').value,
            email: document.querySelector('#email').value,
            dni: document.querySelector('#dni').value,
            entryDate: document.querySelector('#entryDate').value,
            address: {
                street: addressSplit[0],
                number: addressSplit[1],
                city: document.querySelector('#city').value,
                province: document.querySelector('#province').value
            }
        }

        //invoke the dentists API with fetch, method --> PUT
        const url = '/patients';
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

})

//It's the function that executes when the user clicks on a patient ID from the list
//it fills out the form with the patient's data that wants to be modified
function findBy(id){

    const url = `/patients/${id}`;
    const settings = {
        method: "GET"
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        let patient = data;
        document.querySelector('#patient_id').value = patient.id;
        document.querySelector('#surname').value = patient.surname;
        document.querySelector('#name').value = patient.name;
        document.querySelector('#email').value = patient.email;
        document.querySelector('#dni').value = patient.dni;
        document.querySelector('#entryDate').value = patient.entryDate;
        document.querySelector('#address').value = patient.address.street + ' '+ patient.address.number;
        document.querySelector('#city').value = patient.address.city;
        document.querySelector('#province').value = patient.address.province;

        //change form display so that it is now visible
        document.querySelector('#div_patient_updating').style.display="block";
    })
    .catch(err => {
        alert("Error!: "+err);
    })

}

