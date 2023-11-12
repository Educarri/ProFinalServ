import React from 'react'
import { Link } from 'react-router-dom';

export const Cliente = ({cliente}) => {
    return (
        <div className="mozaico">
        <div className="col">
          <div className="card shadow-sm">
            <div className="card-body">
              <h4>
                {cliente.nombre}
              </h4>
              <p className="card-text">
                {"aaa"}
              </p>
              <div className="d-flex justify-content-between align-items-center">
                <div className="btn-group">
                  <button
                    type="button"
                    className="btn btn-sm btn-outline-secondary"
                  >
                    <Link to={`/details/id/asdas`} className="nav-link">
                      Ver Mas
                    </Link>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        </div>
      );
}
