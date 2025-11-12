import React, { useEffect, useState } from 'react';
import axios from 'axios';

const SuppliersList = () => {
  const [suppliers, setSuppliers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Função para buscar fornecedores
  const fetchSuppliers = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await axios.get('http://localhost:8080/suppliers');
      setSuppliers(response.data);
    } catch (err) {
      setError('Erro ao carregar fornecedores.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchSuppliers();
  }, []);

  if (loading) {
    return <p>Carregando fornecedores...</p>;
  }

  if (error) {
    return <p style={{ color: 'red' }}>{error}</p>;
  }

  if (suppliers.length === 0) {
    return <p>Nenhum fornecedor encontrado.</p>;
  }

  return (
    <div>
      <h2>Lista de Fornecedores</h2>
      <table style={{ borderCollapse: 'collapse', width: '100%' }}>
        <thead>
          <tr>
            <th style={thStyle}>ID</th>
            <th style={thStyle}>Nome</th>
            <th style={thStyle}>CNPJ</th>
            <th style={thStyle}>Telefone</th>
            <th style={thStyle}>E-mail</th>
          </tr>
        </thead>
        <tbody>
          {suppliers.map((supplier) => (
            <tr key={supplier.id} style={trStyle}>
              <td style={tdStyle}>{supplier.id}</td>
              <td style={tdStyle}>{supplier.name}</td>
              <td style={tdStyle}>{supplier.cnpj}</td>
              <td style={tdStyle}>{supplier.phone}</td>
              <td style={tdStyle}>{supplier.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

// Estilos inline simples
const thStyle = {
  border: '1px solid #ddd',
  padding: '8px',
  backgroundColor: '#f2f2f2',
  textAlign: 'left',
};

const tdStyle = {
  border: '1px solid #ddd',
  padding: '8px',
};

const trStyle = {
  borderBottom: '1px solid #ddd',
};

export default SuppliersList;
