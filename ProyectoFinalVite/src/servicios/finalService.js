const API_URL = "http://localhost:8080"

export async function getAllClientes() {
    try {
      const response = await fetch(`${API_URL}/admin/clientes`); //por defecto el fetch hace GET
      if (!response.ok) { // response.ok en lugar de response.status.toString().startsWith("4").
        throw new Error("Hubo un error en la búsqueda de Clientes");
      }
      const data = await response.json();
      return data;
    } catch (error) {
      console.error(error);
      throw error; // Puedes lanzar el error nuevamente para que quien llame a esta función pueda manejarlo.
    }
  }