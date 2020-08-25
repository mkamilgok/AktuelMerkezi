import React, {useEffect,useState} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    fetch("api/a101").then(products => products.json()).then(products => setProducts(products));
  })
  return (
    <div className="App">

      <ul>
        {products.map(product =>
            <a href={product.link}>
          <li>
              <h3>{product.name}</h3>
              <h4>{product.price}</h4>
              <img src={product.imgLink} alt="foto" width="50px"/>
          </li>
            </a>
        )}
      </ul>
    </div>
  );
}

export default App;
