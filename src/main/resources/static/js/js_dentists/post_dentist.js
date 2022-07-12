window.addEventListener('load', function (){
    console.log("WELCOME TO MY APP - Register New Dentist")

    const form = document.querySelector('#add_new_dentist');

   form.addEventListener('submit', function (event) {
      event.preventDefault();
      const formData={
        surname: document.querySelector("#surname").value,
        name: document.querySelector("#name").value,
        license: document.querySelector("#license").value,
      };

      const url="/dentists";
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
                '<strong>Dentist saved successfully!</strong></div>'

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
