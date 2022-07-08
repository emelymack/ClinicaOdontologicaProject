window.addEventListener('load', function (){
    console.log("WELCOME TO DENTAL CLINIC \"DH\" - List Patients")

    //with the fetch method we invoke the API, method --> GET
    //it will return a JSON with the patients' collection
    const url = '/patients';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        //iterate the collection
        for(patient of data){
            const tableBody = document.querySelector('#patientTBody');
            let patientRow = tableBody.insertRow();
            //this way we persist the patient id with the id in the DB
            let tr_id = 'tr_' + patient.id;
            patientRow.id = tr_id

            //delete
            const deleteButton = '<button id="btn_delete_'+patient.id+'"'+
            'type="button" onclick="deleteById('+patient.id+')" '+
            'class="btn btn-danger btn_delete" title="Delete patient">'+'&times;'+'</button>';

            //update
            const updateButton = '<button id="btn_id_'+patient.id+'"'+
            'type="button" onclick="findBy('+patient.id+')" '+
            'class="btn btn-info btn_id" title="Update patient">'+patient.id+'</button>';

            //modify the table with the patient's data
            patientRow.innerHTML =
            '<td>' + updateButton + '</td>' +
            '<td class=\"td_surname\">'+ patient.surname.charAt(0).toUpperCase() + patient.surname.slice(1) +'</td>' +
            '<td class=\"td_name\">'+ patient.name.charAt(0).toUpperCase() + patient.name.slice(1) +'</td>' +
            '<td class=\"td_email\">'+ patient.email +'</td>'+
            '<td class=\"td_dni\">'+ patient.dni +'</td>'+
            '<td class=\"td_entryDate\">'+ patient.entryDate +'</td>'+
            '<td class=\"td_address\">'+ patient.address.street + ' '+ patient.address.number +'</td>'+
            '<td class=\"td_address_city\">'+ patient.address.city + '</td>' +
            '<td class=\"td_address_province\">'+ patient.address.province + '</td>' +
            '<td>'+deleteButton+'</td>';
        }

      })

});