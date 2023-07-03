var btnFiltro = document.getElementById('aplicar-filtros-btn');
var btnsEditar = document.getElementsByClassName('editar-btn');
var btnsEliminar = document.getElementsByClassName('elimniar-btn');

btnFiltro.addEventListener('click', function () {
    var comboTipoProducto = document.getElementById('recipient-tipo-productoF');
    var comboProveedor = document.getElementById('recipient-proveedorF');
    var proveedorS = comboProveedor.value;
    var tipoOrderS = comboTipoProducto.value;

    var tableProductos = document.getElementById('table-productos');
    var filasProductos = Array.from(tableProductos.getElementsByTagName('tbody')[0].getElementsByTagName('tr'));
    filasProductos.forEach(function (fila) {
        if (fila.getAttribute('data-bs-proveedorid') == proveedorS || fila.getAttribute('data-bs-tipoid') == tipoOrderS) {
            fila.style.display = '';
        } else {
            fila.style.display = 'none';
        }
    })
    var filtroModal = document.getElementById('filtromodal');
    var modal = bootstrap.Modal.getInstance(filtroModal);
    modal.hide();
})

Array.from(btnsEliminar).forEach(function (btnEliminar) {
    btnEliminar.addEventListener('click', function () {
        var id = btnEliminar.getAttribute('data-bs-id');
        var nombre = btnEliminar.getAttribute('data-bs-nombre');

        document.getElementById('recipient_eliminar_id').value = id;
        document.getElementById('recipient_eliminar_name').value = nombre;
    })
})

Array.from(btnsEditar).forEach(function (btnEditar) {
    btnEditar.addEventListener('click', function () {
        var id = btnEditar.getAttribute('data-bs-id');
        var nombre = btnEditar.getAttribute('data-bs-nombre');
        var valor = btnEditar.getAttribute('data-bs-valor');
        var fechaVencimiento = btnEditar.getAttribute('data-bs-vencimiento');
        var valorCompra = btnEditar.getAttribute('data-bs-compra');
        var valorIva = btnEditar.getAttribute('data-bs-iva');
        var proveedor = btnEditar.getAttribute('data-bs-proveedor');
        var stock = btnEditar.getAttribute('data-bs-stock');
        var tipoProducto = btnEditar.getAttribute('data-bs-tipo');

        document.getElementById('recipient-id').value = id;
        document.getElementById('recipient-name').value = nombre;
        document.getElementById('recipient-valor').value = valor;
        document.getElementById('recipient-fechaVencimiento').value = fechaVencimiento;
        document.getElementById('recipient-valorCompra').value = valorCompra;
        document.getElementById('recipient-valorIva').value = valorIva;
        document.getElementById('recipient-stock').value = stock;
        document.getElementById('recipient-tipo-producto').value = tipoProducto;
        document.getElementById('recipient-proveedor').value = proveedor;
    })
})




$('#myModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Botón que activó el modal

    // Rellenar los datos en el modal
    $('#modal-producto-id').text('ID: ' + button.data('bs-id'));
    $('#modal-producto-nombre').text('Nombre: ' + button.data('bs-nombre'));
    $('#modal-producto-valor').text('Valor: ' + button.data('bs-valor'));
    $('#modal-producto-stock').text('Stock: ' + button.data('bs-stock'));
    $('#modal-producto-fechaIngreso').text('Fecha De Ingreso: ' + button.data('bs-ingreso'));
    $('#modal-producto-fechaVencimiento').text('Fecha De Vencimiento: ' + button.data('bs-vencimiento'));
    $('#modal-producto-valorIva').text('Valor Iva: ' + button.data('bs-iva'));
    $('#modal-producto-valorCompra').text('Valor Compra: ' + button.data('bs-compra'));
    $('#modal-producto-tipoDeProducto').text('Tipo De Producto: ' + button.data('bs-tipo'));
    $('#modal-producto-proveedor').text('Proveedor: ' + button.data('bs-proveedor'));
});

function llenarCombos() {
    var proveedorSelect = document.getElementById('recipient-proveedor');
    var proveedorSelectf = document.getElementById('recipient-proveedorF');
    var tipoOrdenSelect = document.getElementById('recipient-tipo-producto');
    var tipoOrdenSelectF = document.getElementById('recipient-tipo-productoF');

    proveedores.forEach(function (proveedor) {
        var option = document.createElement('option');
        option.value = proveedor.id;
        option.text = proveedor.nombre;
        proveedorSelectf.appendChild(option)

        var option2 = document.createElement('option');
        option2.value = proveedor.id;
        option2.text = proveedor.nombre;
        proveedorSelect.appendChild(option2);
    });

    tiposOrden.forEach(function (tipoOrden) {
        var option = document.createElement('option');
        option.value = tipoOrden.id;
        option.text = tipoOrden.nombre;
        tipoOrdenSelectF.appendChild(option);

        var option2 = document.createElement('option');
        option2.value = tipoOrden.id;
        option2.text = tipoOrden.nombre;
        tipoOrdenSelect.appendChild(option2);
    });
}
llenarCombos()
/* Llenar los combos select con los datos obtenidos */
// initialize();
