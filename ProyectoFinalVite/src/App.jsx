import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";

import { NavBar } from './componentes/NavBar';
import Footer from './componentes/Footer';
import { Detail } from './componentes/Detail';
import CrearClienteForm from './componentes/crearClienteForm';

import ModificarClienteForm from './componentes/modificarClienteForm';


function App() {
  

  return (
    <BrowserRouter>
    <NavBar/>
        <Routes>
          <Route path="/" element={<ListaClientes />} />
          <Route path={"/details/id/:id"} element={<Detail />} />

          <Route path={"/modificar/:id"} element={<ModificarClienteForm />} />
>>>>>>> 50c6074a262ae22688433bd8ecd9ee02b4b0be1c
          <Route path="/registrar" element={<CrearClienteForm />} />
       </Routes>  
    <Footer/>
    </BrowserRouter>
  )
}

export default App
