import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";
<<<<<<< HEAD
import CrearClienteForm from './componentes/CrearClienteForm';
=======
import { NavBar } from './componentes/NavBar';
import Footer from './componentes/Footer';
import { Detail } from './componentes/Detail';
import CrearClienteForm from './componentes/crearClienteForm';
>>>>>>> 52ec5ac9bad4e635b8fd45ba53a658e62ace514d

function App() {
  

  return (
    <BrowserRouter>
    <NavBar/>
        <Routes>
          <Route path="/" element={<ListaClientes />} />
<<<<<<< HEAD
=======
          <Route path={"/details/id/:id"} element={<Detail />} />
>>>>>>> 52ec5ac9bad4e635b8fd45ba53a658e62ace514d
          <Route path="/registrar" element={<CrearClienteForm />} />
       </Routes>  
    <Footer/>
    </BrowserRouter>
  )
}

export default App
