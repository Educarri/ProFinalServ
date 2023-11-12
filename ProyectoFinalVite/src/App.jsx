import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";
import CrearClienteForm from './componentes/CrearClienteForm';

function App() {
  

  return (
    <BrowserRouter>
        <Routes>
          <Route path="/" element={<ListaClientes />} />
          <Route path="/registrar" element={<CrearClienteForm />} />
       </Routes>  
    </BrowserRouter>
  )
}

export default App
