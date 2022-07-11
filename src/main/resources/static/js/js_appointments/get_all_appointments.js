window.addEventListener('load', function (){
    console.log("WELCOME TO DENTAL CLINIC \"DH\" - List Appointments")

    //with the fetch method we invoke the API, method --> GET
    //it will return a JSON with the appointments' collection
    const url = '/appointments';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        //iterate the collection
        for(appointment of data){
            const tableBody = document.querySelector('#appointmentTBody');
            let appointmentRow = tableBody.insertRow();
            //this way we persist the appointment id with the id in the DB
            let tr_id = 'tr_' + appointment.id;
            appointmentRow.id = tr_id

            //delete
            const deleteButton = '<button id="btn_delete_'+appointment.id+'"'+
            'type="button" onclick="deleteById('+appointment.id+')" '+
            'class="btn btn-danger btn_delete" title="Delete patient">'+'&times;'+'</button>';

            //update
            const updateButton = '<button id="btn_id_'+appointment.id+'"'+
            'type="button" onclick="findBy('+appointment.id+')" '+
            'class="btn btn-info btn_id" title="Update patient">'+appointment.id+'</button>';

            //modify the table with the patient's data
            appointmentRow.innerHTML =
            '<td>' + updateButton + '</td>' +
            '<td class=\"td_patient\">'+ appointment.patient.surname + ', '+  appointment.patient.name +'</td>' +
            '<td class=\"td_dentist\">'+ appointment.dentist.surname + ', '+  appointment.dentist.name +'</td>' +
            '<td class=\"td_date\">'+ appointment.date +'</td>' +
            '<td>'+deleteButton+'</td>';
        }

      })

});