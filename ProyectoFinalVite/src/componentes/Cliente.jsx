import React from "react";
import { Link } from "react-router-dom";

export const Cliente = ({ cliente, eliminarCliente }) => {
  return (
    <div className="mozaico">
      <div className="col">
        <div className="card shadow-sm">
          <div className="card-body">
            <h4>Nombre : {cliente.nombre}</h4>
            <p className="card-text">Apellido : {cliente.apellido}</p>
            <p className="card-text">Correo : {cliente.correo}</p>
            <div className="d-flex justify-content-between align-items-center">
              <div className="btn-group">
                <button
                  type="button"
                  className="btn btn-sm btn-outline-secondary"
                >
                  <Link to={`/details/id/${cliente.id}`} className="nav-link">
                    Ver Mas
                  </Link>
                </button>
                <button
                  type="button"
                  className="btn btn-sm btn-outline-secondary"
                  style={{ color: "red" }}
                  onClick={() => eliminarCliente(cliente)}
                >
                  X Eliminar
                </button>
                <button
                  type="button"
                  className="btn btn-sm btn-outline-secondary"
                  style={{ color: "green" }}
                  // onClick={() => modificarCliente(cliente)}
                >
                  <Link to={`/modificar/${cliente.id}`} className="nav-link">
                  + Modificar
                  </Link>     
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
