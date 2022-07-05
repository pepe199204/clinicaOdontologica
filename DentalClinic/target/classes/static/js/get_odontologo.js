window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontologos con el método GET
      //nos devolverá un JSON con una colección de odontologos
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         //recorremos la colección de odontologos del JSON
         for(odontologo of data){
            //por cada odontologo armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontologo
            var table = document.getElementById("odontologoTable");
            var odontologoRow =table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            let deleteButton = '<button' +
                                ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                ' onclick="deleteBy('+odontologo.id+')"' +
                                ' type="button" class="btn btn-labeled btn-danger" style="background-color: #dc3545;"><span class="btn-label"><i class="fa fa-trash"></i></span></button>';

            //como ultima columna el boton eliminar
            let updateButton = '<button' +
                                ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                ' onclick="findBy('+odontologo.id+')"' +
                                ' type="button" class="btn btn-labeled btn-info" style="background-color: #17a2b8;"><span class="btn-label"><i class="fa fa-refresh"></i></span></button>';
            //
            //luego los datos del odontologo
            odontologoRow.innerHTML =
                    '<td>'+updateButton+'</td>' +
                    '<td class=\"td_id\">' + odontologo.id + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>'+
                    '<td>'+deleteButton+'</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologoList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })