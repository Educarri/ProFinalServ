const API_URL = "http://localhost:8080"

export async function crearCliente(cliente){
    await fetch(`${API_URL}/cliente`, {
      method : "POST",
      headers : {
        "Content-type" : "application/json"
      },
      body: JSON.stringify(cliente)
    })
  }
  
  export async function eliminarCliente(id){
    await fetch(`${API_URL}/cliente/${id}`, {
      method : "DELETE",
    });
  }

  export async function modificarCliente(id, clienteModificado) {
    await fetch(`${API_URL}/cliente/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(clienteModificado),
    });
  }

  export async function getClienteById(id) {
    try {
      const response = await fetch(`${API_URL}/cliente/id/${id}`);
      if (!response.ok) {
        throw new Error("Hubo un error al obtener el cliente por ID");
      }
      const data = await response.json();
      return data;
    } catch (error) {
      console.error(error);
      throw error;
    }
  }