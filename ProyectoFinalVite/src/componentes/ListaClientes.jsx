import { useEffect, useState } from "react";
import { getAllClientes } from "../servicios/finalService";
import { Cliente } from './Cliente';

export default function ListaClientes (){
    const [clientes, setClientes] = useState([]);
  
    useEffect(() => {
      getAllClientes().then((data) => setClientes(data))
      .catch((error)=>console.log(error))
    }, []);
  
  
//   async function eliminarVehiculo(auto){
//    await eliminarAuto(auto.id);
//    const newArray = clientes.filter(cl => cl.id !== auto.id);
//    setAuto(newArray);
//   }
  
    const cardsList = clientes.map((c)=> <Cliente cliente={c}  key={c.id}/>) //hago automaticamente la cantidad de cartas necesarias
    
    return (
      <div className={`album ${clientes.length === 0 ? 'hidden' : ''}`} >
        <div className="album py-5 bg-body-tertiary">
          <div className="container">
            {clientes.length > 0 && (
              <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                {cardsList}
              </div>
            )}
          </div>
        </div>
      </div>
    );
  }