import './App.css'
import ListaClientes from './componentes/ListaClientes';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter } from "react-router-dom";
import { NavBar } from './componentes/NavBar';
import Footer from './componentes/Footer';
import { Detail } from './componentes/Detail';

function App() {
  

  return (
    <BrowserRouter>
    <NavBar/>
        <Routes>
          <Route path="/" element={<ListaClientes />} />
          <Route path={"/details/id/:id"} element={<Detail />} />
       </Routes>  
    <Footer/>
    </BrowserRouter>
  )
}

export default App
