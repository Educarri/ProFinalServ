import React, { useState } from "react";
import { crearCliente } from '../servicios/clienteService';
import { useNavigate } from "react-router-dom";

export default function CrearClienteForm() {
    const [nombre, setnombre] = useState("");
    const [apellido, setapellido] = useState("");
    const [dni, setDNI] = useState(0);
    const [correo, setcorreo] = useState("");
    const [telefono, settelefono] = useState(0);
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
                    
                    <input
                        type="text"
                        name="nombre"
                        placeholder="Nombre"
                        onChange={(e) => setnombre(e.target.value)}
                    />

                </div>
                <div className="user-box">
                    
                    <input
                        type="text"
                        name="apellido"
                        placeholder="Apellido"
                        onChange={(e) => setapellido(e.target.value)}
                    />

                </div>
                <div className="user-box">
                    
                    <input
                        type="number"
                        name="dni"
<<<<<<< HEAD:ProyectoFinalVite/src/componentes/CrearClienteForm.jsx
=======
                        placeholder="DNI"
>>>>>>> back:ProyectoFinalVite/src/componentes/crearClienteForm.jsx
                        onChange={(e) => setDNI(e.target.value)}
                    />

                </div>

                <div className="user-box">
                    
                    <input
                        type="text"
                        name="correo"
                        placeholder="Correo electronico"
                        onChange={(e) => setcorreo(e.target.value)}
                    />

                </div>
                <div className="user-box">
                  
                    <input
                        type="number"
                        name="telefono"
                        placeholder="Telefono "
                        onChange={(e) => settelefono(e.target.value)}
                    />

                </div>
                <div className="user-box">
<<<<<<< HEAD:ProyectoFinalVite/src/componentes/CrearClienteForm.jsx
                    <label>Password  </label>
=======
                   
>>>>>>> back:ProyectoFinalVite/src/componentes/crearClienteForm.jsx
                    <input
                        type="password"
                        name="password"
                        placeholder="Password "
                        onChange={(e) => setpassword(e.target.value)}
                    />
                </div>
                <div className="user-box">
                   
                    <input
                        type="text"
                        name="direccion"
                        placeholder="Direccion"
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