import React, { useState } from 'react';
import axios from 'axios';
import './shoppe-crud-app.css';

interface Item {
  id: number;
  name: string;
  price?: number;
}

export default function ShoppeCRUDApp() {
  const [selectedNavItem, setSelectedNavItem] = useState('ProductSale');
  const [selectedOperation, setSelectedOperation] = useState('');
  const [formData, setFormData] = useState<Item>({ id: 0, name: '', price: 0 });
  const [findId, setFindId] = useState('');
  const [foundItem, setFoundItem] = useState<Item | null>(null);
  const [items, setItems] = useState<Item[]>([]);

  const navItems = ['ProductSale', 'Sale', 'Product', 'Customer', 'Category'];
  const crudOperations = {
    'ProductSale': ['Create', 'Find', 'List', 'Update', 'Delete'],
    'Sale': ['Create', 'Find', 'List', 'Update', 'Delete'],
    'Product': ['Create', 'Find', 'List', 'Update', 'Delete'],
    'Customer': ['Create', 'Find', 'List', 'Update', 'Delete'],
    'Category': ['Create', 'Find', 'List', 'Update', 'Delete']
  };

  const handleNavItemClick = (item: string) => {
    setSelectedNavItem(item);
    setSelectedOperation('');
    setFoundItem(null);
    setItems([]);
    setFormData({ id: 0, name: '', price: 0 });
    setFindId('');
  };

  const handleOperationClick = (operation: string) => {
    setSelectedOperation(operation);
    setFoundItem(null);
    setItems([]);
    setFormData({ id: 0, name: '', price: 0 });
    setFindId('');
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (selectedOperation === 'Create') {
      await axios.post(`/api/${selectedNavItem.toLowerCase()}s`, formData);
      alert(`${selectedNavItem} created successfully`);
    } else if (selectedOperation === 'Update' && foundItem) {
      await axios.put(`/api/${selectedNavItem.toLowerCase()}s/${foundItem.id}`, formData);
      alert(`${selectedNavItem} updated successfully`);
      setFoundItem({ ...foundItem, ...formData });
    }
    setFormData({ id: 0, name: '', price: 0 });
  };

  const handleFind = async () => {
    const response = await axios.get<Item>(`/api/${selectedNavItem.toLowerCase()}s/${findId}`);
    setFoundItem(response.data);
  };

  const handleList = async () => {
    const response = await axios.get<Item[]>(`/api/${selectedNavItem.toLowerCase()}s`);
    setItems(response.data);
  };

  const handleDelete = async () => {
    if (foundItem) {
      await axios.delete(`/api/${selectedNavItem.toLowerCase()}s/${foundItem.id}`);
      alert(`${selectedNavItem} deleted successfully`);
      setFoundItem(null);
    }
  };

  return (
    <div className="min-h-screen">
      <nav>
        <div className="max-w-7xl">
          <div className="flex justify-between h-16">
            <div className="flex items-center">
              <span className="text-2xl font-bold text-gray-800">Shoppe</span>
            </div>
            <div className="flex items-center">
              {navItems.map((item) => (
                <button
                  key={item}
                  className={selectedNavItem === item ? 'active' : ''}
                  onClick={() => handleNavItemClick(item)}
                >
                  {item}
                </button>
              ))}
            </div>
          </div>
        </div>
      </nav>

      <main className="max-w-7xl">
        <div className="operation-buttons">
          {crudOperations[selectedNavItem].map((operation) => (
            <button
              key={operation}
              onClick={() => handleOperationClick(operation)}
              className={selectedOperation === operation ? 'active' : ''}
            >
              {operation}
            </button>
          ))}
        </div>

        {selectedOperation === 'Create' && (
          <div className="card">
            <div className="card-header">
              <h2 className="card-title">Create {selectedNavItem}</h2>
            </div>
            <div className="card-content">
              <form onSubmit={handleSubmit}>
                <input
                  name="name"
                  placeholder="Name"
                  value={formData.name}
                  onChange={handleInputChange}
                />
                {selectedNavItem === 'Product' && (
                  <input
                    name="price"
                    type="number"
                    placeholder="Price"
                    value={formData.price}
                    onChange={handleInputChange}
                  />
                )}
                <button type="submit">Create</button>
              </form>
            </div>
          </div>
        )}

        {(selectedOperation === 'Find' || selectedOperation === 'Update' || selectedOperation === 'Delete') && (
          <div className="card">
            <div className="card-header">
              <h2 className="card-title">{selectedOperation} {selectedNavItem}</h2>
            </div>
            <div className="card-content">
              <div className="flex">
                <input
                  placeholder="Enter ID"
                  value={findId}
                  onChange={(e) => setFindId(e.target.value)}
                />
                <button onClick={handleFind} className="primary">Find</button>
              </div>
              {foundItem && (
                <div className="card">
                  <div className="card-content">
                    {selectedOperation === 'Update' ? (
                      <form onSubmit={handleSubmit}>
                        <input
                          name="name"
                          placeholder="Name"
                          value={formData.name || foundItem.name}
                          onChange={handleInputChange}
                        />
                        {selectedNavItem === 'Product' && (
                          <input
                            name="price"
                            type="number"
                            placeholder="Price"
                            value={formData.price || foundItem.price}
                            onChange={handleInputChange}
                          />
                        )}
                        <button type="submit">Update</button>
                      </form>
                    ) : (
                      <div>
                        <p><strong>Name:</strong> {foundItem.name}</p>
                        {selectedNavItem === 'Product' && (
                          <p><strong>Price:</strong> ${foundItem.price}</p>
                        )}
                        {selectedOperation === 'Delete' && (
                          <button onClick={handleDelete} className="primary">Delete</button>
                        )}
                      </div>
                    )}
                  </div>
                </div>
              )}
            </div>
          </div>
        )}

        {selectedOperation === 'List' && (
          <div className="card">
            <div className="card-header">
              <h2 className="card-title">List {selectedNavItem}s</h2>
            </div>
            <div className="card-content">
              <button onClick={handleList} className="primary">Fetch All</button>
              {items.length > 0 && (
                <table>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      {selectedNavItem === 'Product' && <th>Price</th>}
                    </tr>
                  </thead>
                  <tbody>
                    {items.map((item) => (
                      <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                        {selectedNavItem === 'Product' && <td>${item.price}</td>}
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </div>
          </div>
        )}
      </main>
    </div>
  );
}