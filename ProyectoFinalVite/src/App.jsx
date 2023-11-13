import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";
import { NavBar } from './componentes/NavBar';
import Footer from './componentes/Footer';
import { Detail } from './componentes/Detail';
<<<<<<< HEAD
import CrearClienteForm from './componentes/CrearClienteForm';
=======
import CrearClienteForm from './componentes/crearClienteForm';
import ModificarClienteForm from './componentes/modificarClienteForm';
>>>>>>> developer

function App() {
  

  return (
    <BrowserRouter>
    <NavBar/>
        <Routes>
          <Route path="/" element={<ListaClientes />} />
          <Route path={"/details/id/:id"} element={<Detail />} />
<<<<<<< HEAD
=======
          <Route path={"/modificar/:id"} element={<ModificarClienteForm />} />
>>>>>>> developer
          <Route path="/registrar" element={<CrearClienteForm />} />
       </Routes>  
    <Footer/>
    </BrowserRouter>
  )
}

export default App
