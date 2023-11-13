import React, { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
//import autoService from "../servicios/autoService"; // Asegúrate de que esta importación sea correcta
import { getClienteById } from "../servicios/finalService";

export const Detail = () => {
  const [cliente, setCliente] = useState({});
  const { id } = useParams();
 

  useEffect(() => {
    getClienteById(id)
      .then((data) => {
        if (data) {
          setCliente(data);
        } else {
          console.error("La respuesta está vacía o no es un JSON válido.");
        }
      })
      .catch((error) => {
        console.error("Error al obtener el Cliente por ID:", error);
      });
  }, [id]);
  
  
  return (
    <div className="row m-3">
      <div className="col-md-12">
        <div className="card flex-md-row mb-4 box-shadow h-md-250">
          
          <div className="card-body d-flex flex-column align-items-start">
            <strong className="d-inline-block mb-2 text-success">
              {`Nombre : ${cliente.nombre}`}
            </strong>
            
            <p className="card-text mb-auto mensaje">
              Nuestro cliente {cliente.nombre} {cliente.apellido}, de correo {cliente.correo} con el DNI {cliente.dni } , telefono {cliente.telefono } vive en la direccion {cliente.direccion}.
            </p>
            <p>{new Date().toLocaleDateString()}</p>
            <Link to={"/"} className="mt-3 btn btn-primary btn-lg">Volver</Link>
          </div>
        </div>
      </div>
    </div>
  );
};
