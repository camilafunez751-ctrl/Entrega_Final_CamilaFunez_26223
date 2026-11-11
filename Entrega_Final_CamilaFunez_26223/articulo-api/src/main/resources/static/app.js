
document.addEventListener("DOMContentLoaded", function () {
    const btnNuevo = document.getElementById("btnNuevoArticulo");
    const modal = document.getElementById("modalArticulo");
    const btnCancelar = document.getElementById("btnCancelar");
    const formArticulo = document.getElementById("formArticulo");
    const buscador = document.getElementById("buscarArticulo");

    btnNuevo.addEventListener("click", function () {
        formArticulo.reset();
        document.getElementById("idArticulo").value = "";
        document.getElementById("tituloModal").textContent = "Nuevo artículo";
        document.getElementById("btnGuardarArticulo").textContent = "Guardar artículo";
        modal.style.display = "flex";
    });

    btnCancelar.addEventListener("click", function () {
        modal.style.display = "none";
    });

    formArticulo.addEventListener("submit", async function (event) {
        event.preventDefault();

        const id = document.getElementById("idArticulo").value;
        const articulo = {
            nombre: document.getElementById("nombre").value.trim(),
            precio: parseFloat(document.getElementById("precio").value),
            categoria: {
                id: parseInt(document.getElementById("categoria").value)
            },
            fechaVencimiento: document.getElementById("fechaVencimiento").value || null
        };

        try {
            const respuesta = await fetch(id ? `/articulos/${id}` : "/articulos", {
                method: id ? "PUT" : "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(articulo)
            });

            if (!respuesta.ok) {
                throw new Error("No se pudo guardar el artículo");
            }

            alert(id ? "Artículo actualizado correctamente" : "Artículo guardado correctamente");
            formArticulo.reset();
            modal.style.display = "none";
            document.getElementById("idArticulo").value = "";
            cargarArticulos(buscador.value);
        } catch (error) {
            console.error(error);
            alert("Error al guardar el artículo");
        }
    });

    buscador.addEventListener("input", function () {
        cargarArticulos(this.value);
    });

    cargarCategorias();
    cargarArticulos();
});

async function cargarArticulos(busqueda = "") {
    const tabla = document.getElementById("tablaArticulos");

    try {
        const url = busqueda.trim()
            ? `/articulos/buscar?nombre=${encodeURIComponent(busqueda)}`
            : "/articulos";

        const respuesta = await fetch(url);

        if (!respuesta.ok) {
            throw new Error("Error al obtener artículos");
        }

        const articulos = await respuesta.json();
        tabla.innerHTML = "";

        document.getElementById("totalArticulos").textContent = articulos.length;

        articulos.forEach(function (articulo) {
            const fila = document.createElement("tr");

            const datos = [
                articulo.id,
                articulo.nombre,
                "$ " + articulo.precio,
                articulo.categoria ? articulo.categoria.nombre : "Sin categoría",
                articulo.fechaVencimiento || "Sin vencimiento"
            ];

            datos.forEach(function (dato) {
                const celda = document.createElement("td");
                celda.textContent = dato;
                fila.appendChild(celda);
            });

            const acciones = document.createElement("td");

            const btnEditar = document.createElement("button");
            btnEditar.textContent = "Editar";
            btnEditar.className = "btn-editar";
            btnEditar.addEventListener("click", function () {
                editarArticulo(articulo);
            });

            const btnEliminar = document.createElement("button");
            btnEliminar.textContent = "Eliminar";
            btnEliminar.className = "btn-eliminar";
            btnEliminar.addEventListener("click", function () {
                eliminarArticulo(articulo.id);
            });

            acciones.appendChild(btnEditar);
            acciones.appendChild(btnEliminar);
            fila.appendChild(acciones);
            tabla.appendChild(fila);
        });
    } catch (error) {
        console.error("Error al cargar artículos:", error);
    }
}

function editarArticulo(articulo) {
    document.getElementById("idArticulo").value = articulo.id;
    document.getElementById("nombre").value = articulo.nombre;
    document.getElementById("precio").value = articulo.precio;
    document.getElementById("categoria").value = articulo.categoria
        ? articulo.categoria.id
        : "";
    document.getElementById("fechaVencimiento").value = articulo.fechaVencimiento || "";

    document.getElementById("tituloModal").textContent = "Editar artículo";
    document.getElementById("btnGuardarArticulo").textContent = "Guardar cambios";
    document.getElementById("modalArticulo").style.display = "flex";
}

async function eliminarArticulo(id) {
    const confirmar = confirm("¿Estás segura de que querés eliminar este artículo?");

    if (!confirmar) return;

    try {
        const respuesta = await fetch(`/articulos/${id}`, {
            method: "DELETE"
        });

        if (!respuesta.ok) {
            throw new Error("No se pudo eliminar el artículo");
        }

        alert("Artículo eliminado correctamente");
        cargarArticulos(document.getElementById("buscarArticulo").value);
    } catch (error) {
        console.error(error);
        alert("Error al eliminar el artículo");
    }
}

async function cargarCategorias() {
    const select = document.getElementById("categoria");

    try {
        const respuesta = await fetch("/categorias");

        if (!respuesta.ok) {
            throw new Error("Error al obtener categorías");
        }

        const categorias = await respuesta.json();
        select.innerHTML = '<option value="">Seleccionar categoría</option>';

        categorias.forEach(function (categoria) {
            const opcion = document.createElement("option");
            opcion.value = categoria.id;
            opcion.textContent = categoria.nombre;
            select.appendChild(opcion);
        });

        document.getElementById("totalCategorias").textContent = categorias.length;
    } catch (error) {
        console.error("Error al cargar categorías:", error);
    }
}
