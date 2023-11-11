import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";

function App() {
  

  return (
    <BrowserRouter>
        <Routes>
          <Route path="/" element={<ListaClientes />} />
       </Routes>  
    </BrowserRouter>
  )
}

export default App
