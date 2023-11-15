import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";
import { NavBar } from './componentes/NavBar';
import Footer from './componentes/Footer';
import { Detail } from './componentes/Detail';
import CrearClienteForm from './componentes/crearClienteForm';
import ModificarClienteForm from './componentes/modificarClienteForm';
import { useState } from 'react';


function App() {
  const [userRoles, setUserRoles] = useState(["USER", "ADMIN", "PROVEEDOR"]);

  return (
    <BrowserRouter>
      <NavBar userRoles={userRoles} />
      <Routes>
        {userRoles.includes('ADMIN') && <Route path="/admin" element={<ListaClientes />} />}
        {userRoles.includes('USER') && (
          <>
            <Route path={"/details/id/:id"} element={<Detail />} />
            <Route path={"/modificar/:id"} element={<ModificarClienteForm />} />
            <Route path="/cliente/registrar" element={<CrearClienteForm />} />
          </>
        )}
       
{/* {userRoles.includes('PROVEEDOR') && <Route path="/proveedor" element={<ProveedorComponent />} />} */}
{/* Otras rutas... */}
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;



