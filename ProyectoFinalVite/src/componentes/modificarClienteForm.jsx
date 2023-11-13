import React, { useEffect, useState } from "react";
import { getClienteById, modificarCliente } from "../servicios/clienteService";
import { useNavigate, useParams } from "react-router-dom";

export default function ModificarClienteForm() {
  const [nombre, setnombre] = useState("");
  const [apellido, setapellido] = useState("");
  const [dni, setDNI] = useState(0);
  const [correo, setcorreo] = useState("");
  const [telefono, settelefono] = useState(0);
  const [password, setpassword] = useState("");
  const [direccion, setdireccion] = useState("");
  const [error, seterror] = useState(false);
  const navigate = useNavigate();

  const { id } = useParams();

  const [cliente, setCliente] = useState({});

  useEffect(() => {
    getClienteById(id)
      .then((data) => {
        if (data) {
          setCliente(data);
          // Establecer los valores iniciales del formulario
          setnombre(data.nombre);
          setapellido(data.apellido);
          setDNI(data.dni);
          setcorreo(data.correo);
          settelefono(data.telefono);
          setpassword(data.password);
          setdireccion(data.direccion);
        } else {
          console.error("La respuesta está vacía o no es un JSON válido.");
        }
      })
      .catch((error) => {
        console.error("Error al obtener el Cliente por ID:", error);
      });
  }, [id]);

  async function modificarCli(evento) {
    evento.preventDefault();
    if (
      nombre === "" ||
      apellido === "" ||
      dni <= 0 ||
      correo === "" ||
      telefono <= 0 ||
      password === "" ||
      direccion === ""
    ) {
      seterror(true);
      return;
    }

    seterror(false);

    const clienteModificado = {
      nombre,
      apellido,
      dni,
      correo,
      telefono,
      password,
      direccion,
    };

    // Llamada a la función modificarCliente del servicio
    await modificarCliente(id, clienteModificado);
    console.log(clienteModificado);
    navigate("/");
  }

  return (
    <div className="login-box">
      <form className="form" onSubmit={(evento) => modificarCli(evento)}>
        <p className="title">Modificacion</p>

        <div className="user-box">
          <input
            type="text"
            name="nombre"
            placeholder={cliente.nombre}
            onChange={(e) => setnombre(e.target.value)}
          />
        </div>
        <div className="user-box">
          <input
            type="text"
            name="apellido"
            placeholder={cliente.apellido}
            onChange={(e) => setapellido(e.target.value)}
          />
        </div>

        <div className="user-box">
          <input
            type="number"
            name="dni"
            placeholder={cliente.dni}
            onChange={(e) => setDNI(e.target.value)}
          />
        </div>

        <div className="user-box">
          <input
            type="text"
            name="correo"
            placeholder={cliente.correo}
            onChange={(e) => setcorreo(e.target.value)}
          />
        </div>
        <div className="user-box">
          <input
            type="number"
            name="telefono"
            placeholder={cliente.telefono}
            onChange={(e) => settelefono(e.target.value)}
          />
        </div>
        <div className="user-box">
          <input
            type="password"
            name="password"
            placeholder={cliente.password}
            onChange={(e) => setpassword(e.target.value)}
          />
        </div>
        <div className="user-box">
          <input
            type="text"
            name="direccion"
            placeholder={cliente.direccion}
            onChange={(e) => setdireccion(e.target.value)}
          />
        </div>

        <button className="submit">Modificar</button>
        {error && (
          <h4 style={{ color: "red", textAlign: "center" }}>
            DATOS INCORRECTOS
          </h4>
        )}
      </form>
    </div>
  );
}
