$(document).ready(function() {
    cargarUsuarios();
    $('#usuarios').DataTable();
});

async function cargarUsuarios() {
        const response = await fetch('api/usuarios', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        const usuarios = await response.json();



        let listaHTML = '';
        for (let usuario of usuarios) {

            let botonEliminar = '<a href="#" onclick="deleteUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm">' +
                '<i class="fas fa-trash"></i>' +
                '</a>';

            let textoTelefono = usuario.telefono == null ?  '-' : usuario.telefono;

            let usuarioHTML = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>' + usuario.email + '</td><td>' + textoTelefono + '</td><td>' + botonEliminar + '</td></tr>';
            listaHTML += usuarioHTML;
        }

        document.querySelector('#usuarios tbody').innerHTML = listaHTML;
}

async function deleteUsuario(id){

    if(!confirm('Desea eliminar este usuario?')){
        return;
    }
    const request= await fetch('api/usuarios/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    location.reload();
}