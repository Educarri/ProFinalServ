import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";
import { NavBar } from './componentes/NavBar';
import Footer from './componentes/Footer';
import { Detail } from './componentes/Detail';
import CrearClienteForm from './componentes/CrearClienteForm';

function App() {
  

  return (
    <BrowserRouter>
    <NavBar/>
        <Routes>
          <Route path="/" element={<ListaClientes />} />
          <Route path={"/details/id/:id"} element={<Detail />} />
          <Route path="/registrar" element={<CrearClienteForm />} />
       </Routes>  
    <Footer/>
    </BrowserRouter>
  )
}

export default App
