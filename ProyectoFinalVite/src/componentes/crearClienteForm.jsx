import React, { useState } from "react";
import { crearCliente } from '../servicios/clienteService';
import { useNavigate } from "react-router-dom";

export default function CrearClienteForm() {
    const [nombre, setnombre] = useState("");
    const [apellido, setapellido] = useState("");
    const [dni, setDNI] = useState(0);
    const [correo, setcorreo] = useState("");
    const [telefono, settelefono] = useState(0);
    const [descripcion, setdescripcion] = useState("");
    const [password, setpassword] = useState("");
    const [direccion, setdireccion] = useState("");
    const [error, seterror] = useState(false);
    const navigate = useNavigate();


    async function crearUsuario(evento) {
        evento.preventDefault();
        if (
            nombre === "" ||
            apellido === "" ||
            dni <= 0 ||
            correo === "" ||
            telefono <= 0 ||
            descripcion === "" ||
            password === "" ||
            direccion === ""

        ) {
            seterror(true);
            return;
        }

        seterror(false);
        const newCliente = {
            nombre,
            apellido,
            dni,
            correo,
            telefono,
            descripcion,
            password,
            direccion,

        };
        await crearCliente(newCliente);
        navigate("/");
    }

    return (
        <div className="login-box">
            <form className="form" onSubmit={crearUsuario}>
                <p className="title">Registrarse</p>
                <p className="message">Al registrarse tendra acceso a los datos de contacto de los proveedores</p>

                <div className="user-box">
                    <label>Nombre  </label>
                    <input
                        type="text"
                        name="nombre"
                        onChange={(e) => setnombre(e.target.value)}
                    />

                </div>
                <div className="user-box">
                    <label>Apellido  </label>
                    <input
                        type="text"
                        name="apellido"
                        onChange={(e) => setapellido(e.target.value)}
                    />

                </div>
                <div className="user-box">
                    <label>DNI  </label>
                    <input
                        type="number"
                        name="dni"
                        onChange={(e) => setDNI(e.target.value)}
                    />

                </div>

                <div className="user-box">
                    <label>Correo electronico  </label>
                    <input
                        type="text"
                        name="correo"
                        onChange={(e) => setcorreo(e.target.value)}
                    />

                </div>
                <div className="user-box">
                    <label>Telefono  </label>
                    <input
                        type="number"
                        name="telefono"
                        onChange={(e) => settelefono(e.target.value)}
                    />

                </div>
                <div className="user-box">
                    <label>Breve Descripcion  </label>
                    <input
                        type="text"
                        name="descripcion"
                        onChange={(e) => setdescripcion(e.target.value)}
                    />

                </div>
                <div className="user-box">
                    <label>Password  </label>
                    <input
                        type="password"
                        name="password"
                        onChange={(e) => setpassword(e.target.value)}
                    />
                </div>
                <div className="user-box">
                    <label>Direccion  </label>
                    <input
                        type="text"
                        name="direccion"
                        onChange={(e) => setdireccion(e.target.value)}
                    />

                </div>


                <button className="submit">Registrarse</button>
                {error && (
                    <h4 style={{ color: "red", textAlign: "center" }}>
                        DATOS INCORRECTOS
                    </h4>
                )}
            </form>
        </div>
    );
}