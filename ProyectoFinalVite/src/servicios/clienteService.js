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