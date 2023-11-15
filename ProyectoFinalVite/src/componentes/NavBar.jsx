import { Link } from 'react-router-dom';


export const NavBar = () => {
  // const { contextTheme, setContextTheme } = useThemeContext();

  // const [checked, setChecked] = useState(false);

  // const handleSwitch = (nextChecked) => {
  //   setContextTheme((state) => (state === 'Light' ? 'Dark' : 'Light'));
  //   setChecked(nextChecked);
  // };

  // useEffect(() => {
  //   document.body.className = contextTheme;
  // }, [contextTheme]);

  return (
    <nav className="navbar navbar-dark bg-dark" aria-label="First navbar example" >
    <div className="container-fluid">
      <Link className="link" to={"/"}>Servicios</Link>
      <img src='/src/assets/logo.png' alt="logo" />
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample01" aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      {/* <div>
      <ReactSwitch 
          onChange={handleSwitch}
          checked={checked}
          onColor="#86d3ff"
          onHandleColor="#2693e6"
          handleDiameter={30}
          uncheckedIcon={false}
          checkedIcon={false}
          boxShadow="0px 1px 5px rgba(0, 0, 0, 0.6)"
          activeBoxShadow="0px 0px 1px 10px rgba(0, 0, 0, 0.2)"
          height={20}
          width={48}
          className="react-switch"
          id="material-switch"
        /> 
        <p className='mode'>{contextTheme} Mode</p>
      </div> */}
      <div className="collapse navbar-collapse" id="navbarsExample01">
        <ul className="navbar-nav me-auto mb-2">
          <li className="nav-item">
            <Link className="link" to={"/"}>Home</Link>
          </li>
          <li className="nav-item">
            <Link className="link" to={"/registrar"}>Registrar Cliente</Link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  )
}

