import React, { useState } from 'react'; // Asegúrate de importar React
import './App.css';
import ShoppeCRUDApp from "./components/shoppe-crud-app.tsx";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <ShoppeCRUDApp />
    </>
  );
}

export default App;