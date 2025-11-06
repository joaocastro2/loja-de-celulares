import React, { useState } from 'react';
import axios from 'axios';
import { FaTruck } from 'react-icons/fa'; // Ícone de fornecedor

const API_URL = 'http://localhost:8080/suppliers';

const CadastrarFornecedor = () => {
  const [formData, setFormData] = useState({
    supplierName: '',
    supplierEIN: '',
    active: true,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem('token');
      const response = await axios.post(API_URL, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const supplierId = response.data.supplierId;
      alert(`Fornecedor cadastrado com sucesso!\nID: ${supplierId}`);

      setFormData({ supplierName: '', supplierEIN: '', active: true });
    } catch (error) {
      console.error(error);
      alert('Erro ao cadastrar fornecedor.');
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 bg-white shadow-md rounded-lg p-6">
      {/* TÍTULO COM ÍCONE */}
      <h2 className="text-xl font-bold mb-4 text-gray-700 flex items-center">
        <FaTruck className="mr-2 text-gray-500" /> Cadastrar Novo Fornecedor
      </h2>

      {/* FORMULÁRIO */}
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Nome do Fornecedor
          </label>
          <input
            type="text"
            name="supplierName"
            value={formData.supplierName}
            onChange={handleChange}
            required
            placeholder="Ex: Tech Distribuidora"
            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-gray-400 focus:outline-none"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            EIN (CNPJ)
          </label>
          <input
            type="number"
            name="supplierEIN"
            value={formData.supplierEIN}
            onChange={handleChange}
            required
            placeholder="Ex: 12345678000199"
            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-gray-400 focus:outline-none"
          />
        </div>

        <div className="flex justify-end">
          <button
            type="submit"
            className="bg-emerald-600 hover:bg-emerald-700 text-white font-medium px-6 py-2 rounded-md shadow-sm transition duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
          >
            Salvar Fornecedor
          </button>
        </div>
      </form>
    </div>
  );
};

export default CadastrarFornecedor;
