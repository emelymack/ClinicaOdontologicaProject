window.addEventListener('load', ()=>{

    /*Get the update form with the data the user may have changed */
    const updateForm = document.querySelector('#update_dentist_form');
    updateForm.addEventListener('submit', function(e) {
        let dentistId = document.querySelector('#dentist_id').value;

        //create a JSON with the dentist's data and send the dentist id
        //so that it can be identified and modified, and not uploaded as a new dentist
        const formData = {
            id: document.querySelector('#dentist_id').value,
            name: document.querySelector('#name').value,
            surname: document.querySelector('#surname').value,
            license: document.querySelector('#license').value
        }

        //invoke the dentists API with fetch, method --> PUT
        const url = '/dentists';
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

//It's the function that executes when the user clicks on a dentist ID from the list
//it fills out the form with the dentist's data that wants to be modified
function findBy(id){

    const url = `/dentists/${id}`;
    const settings = {
        method: "GET"
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        let dentist = data;
        document.querySelector('#dentist_id').value = dentist.id;
        document.querySelector('#name').value = dentist.name;
        document.querySelector('#surname').value = dentist.surname;
        document.querySelector('#license').value = dentist.license;

        //change form display so that it is now visible
        document.querySelector('#div_dentist_updating').style.display="block";
    })
    .catch(err => {
        alert("Error!: "+err);
    })

}

