window.addEventListener('load', function (){
    console.log("WELCOME TO DENTAL CLINIC \"DH\" - List Dentists")

    //invoking the API
    const url = '/dentists';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        //iterate the collection
        for(dentist of data){
            const tableBody = document.querySelector('#dentistTBody');
            let dentistRow = tableBody.insertRow();
            //persist the dentist id with the id in the DB
            let tr_id = 'tr_' + dentist.id;
            dentistRow.id = tr_id

            //delete
            const deleteButton = '<button id="btn_delete_'+dentist.id+'"'+
            'type="button" onclick="deleteById('+dentist.id+')" '+
            'class="btn btn-danger btn_delete" title="Delete dentist">'+'&times;'+'</button>';

            //update
            const updateButton = '<button id="btn_id_'+dentist.id+'"'+
            'type="button" onclick="findBy('+dentist.id+')" '+
            'class="btn btn-info btn_id" title="Update dentist">'+dentist.id+'</button>';

            //modify the table with the patient's data
            dentistRow.innerHTML =
            '<td>' + updateButton + '</td>' +
            '<td class=\"td_surname\">'+ dentist.surname.charAt(0).toUpperCase() + dentist.surname.slice(1) +'</td>' +
            '<td class=\"td_name\">'+ dentist.name.charAt(0).toUpperCase() + dentist.name.slice(1) +'</td>' +
            '<td class=\"td_license\">'+ dentist.license + '</td>' +
            '<td>'+ deleteButton +'</td>';
        }

      })

});