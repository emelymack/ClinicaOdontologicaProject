window.addEventListener('load', function (){
    console.log("WELCOME TO MY APP - Register New Appointment")

    const form = document.querySelector('#add_new_appointment');

   form.addEventListener('submit', function (event) {
      event.preventDefault();

      const formData={
        patient: {
            id: document.querySelector("#patient_id").value
        },
        dentist: {
            id: document.querySelector("#dentist_id").value
        },
        date: document.querySelector('#date').value
      };

      const url="/appointments";
      const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

      fetch(url, settings)
      .then(response =>{
        if(!response.ok){
            throw new Error(response.status)
        }
        return response.text();
      })
      .then(data => {
        console.log(data);
        let successAlert = '<div class = "alert alert-success alert-dismissible>' +
                           '<button type="button" class="close" data-dismiss="alert">&times;</button>'+
                           '<strong>Appointment saved successfully!</strong></div>'

        document.querySelector('#response').style.display = "block";
        document.querySelector('#response').innerHTML = successAlert;

        form.reset();
      })
      .catch(err => {
        let errorAlert = '<div class="alert alert-danger alert-dismissible">'+
                         '<button type="button" class="close" data-dismiss="alert">&times;</button>'+
                         '<strong>Error! Some data is incorrect.<br> Try again.</strong></div>'

        document.querySelector('#response').style.display = "block";
        document.querySelector('#response').innerHTML = errorAlert;
        form.reset();
      })
   })
})
