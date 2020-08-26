import React, {useState, useEffect} from 'react';
import './App.css';
import DropdownButton from 'react-bootstrap/DropdownButton'
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";
import Card from "react-bootstrap/Card";
import { Dropdown, Menu } from 'semantic-ui-react'

function App() {
  const [products, setProducts] = useState([]);
  let ButtonGrop;
  useEffect(() => {
    fetch("/api/a101").then(products => products.json()).then(products => setProducts(products));
  })

  const removeItem = (id) => {
      fetch("http://localhost:8080/api/a101/" + id,{method: 'DELETE',})
          .then(response => response.text())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));
  };


  return (
      <div className="App">
          <h1 className="main-header">Aktüel Merkezi</h1>
          <div className="navBar">
              <DropdownButton
                  key="BİM"
                  id={`dropdown-variants-BİM`}
                  variant={"BİM".toLowerCase()}
                  title="BİM"
              >
                  <Dropdown.Item eventKey="1">Action</Dropdown.Item>
                  <Dropdown.Divider />
                  <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
                  <Dropdown.Divider />
                  <Dropdown.Item eventKey="3" active>Active Item</Dropdown.Item>
              </DropdownButton>
              <DropdownButton
                  key="A101"
                  id={`dropdown-variants-A101`}
                  variant={"A101".toLowerCase()}
                  title="A101"
              >
                  <Dropdown.Item eventKey="1"><a href="http://localhost:8080/api/a101/onSale">Action</a></Dropdown.Item>
                  <Dropdown.Divider />
                  <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
              </DropdownButton>
              <DropdownButton
                  key="ŞOK"
                  id={`dropdown-variants-ŞOK`}
                  variant={"ŞOK".toLowerCase()}
                  title="ŞOK"
              >
                  <Dropdown.Item eventKey="1">Action</Dropdown.Item>
                  <Dropdown.Divider />
                  <Dropdown.Item eventKey="2">Another action</Dropdown.Item>
              </DropdownButton>
          </div>


          <ul>
              <li><a className="active" href="#home">Home</a></li>
              <li><a href="#news">News</a></li>
              <li><a href="#contact">Contact</a></li>
              <li><a href="#about">About</a></li>
          </ul>

        <h2>{products[0] && products[0].dateExplanation}</h2>
        <ul>
          {products.map(product =>
                <li>
                    <a href={product.link} key={product.id}>
                    <img src={product.imgLink} alt="foto" width="70px"/>
                  <h3>{product.name}</h3>
                    </a>
                    <div className="info">
                        <h4>{product.price}</h4>
                        <button >Edit</button>
                        <button onClick={() => removeItem(product.id)}>Delete</button>
                    </div>

                </li>
          )}
        </ul>
      </div>
  );
}

export default App;
