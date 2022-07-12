window.addEventListener('load', function (){
    console.log("WELCOME TO MY APP - Register New Patient")

    const form = document.querySelector('#add_new_patient');

   form.addEventListener('submit', function (event) {
      event.preventDefault();
      const formData={
        surname: document.querySelector("#surname").value,
        name: document.querySelector("#name").value,
        email: document.querySelector("#email").value,
        dni: document.querySelector("#dni").value,
        entryDate: document.querySelector("#entryDate").value,
        address: {
            "street": document.querySelector("#address_street").value,
            "number": document.querySelector("#address_number").value,
            "city": document.querySelector("#address_city").value,
            "province": document.querySelector("#address_province").value
        }
      };

      const url="/patients";
      const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

      fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let successAlert = '<div class = "alert alert-success alert-dismissible>' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>'+
                '<strong>Patient saved successfully!</strong></div>'

            document.querySelector('#response').style.display = "block";
            document.querySelector('#response').innerHTML = successAlert;

            form.reset();
        })
        .catch(err => {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">'
            +'<button type="button" class="close" data-dismiss="alert">&times;</button>'
            +'<strong>Error! Some data is incorrect.<br> Try again.</strong></div>'

            document.querySelector('#response').style.display = "block";
            document.querySelector('#response').innerHTML = errorAlert;

            form.reset();
        });
   });
});
