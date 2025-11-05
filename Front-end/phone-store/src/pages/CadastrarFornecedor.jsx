import React, { useState } from 'react';
import axios from 'axios';

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
    <div className="max-w-2xl mx-auto mt-10 bg-white shadow-lg rounded-lg p-8">
      <h2 className="text-3xl font-bold text-indigo-700 mb-6 text-center">
        Cadastro de Fornecedor
      </h2>
      <form onSubmit={handleSubmit} className="space-y-6">
        <div>
          <label className="block text-sm font-semibold text-gray-700 mb-1">
            Nome do Fornecedor
          </label>
          <input
            type="text"
            name="supplierName"
            value={formData.supplierName}
            onChange={handleChange}
            required
            placeholder="Ex: Tech Distribuidora"
            className="w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          />
        </div>
        <div>
          <label className="block text-sm font-semibold text-gray-700 mb-1">
            EIN (CNPJ)
          </label>
          <input
            type="number"
            name="supplierEIN"
            value={formData.supplierEIN}
            onChange={handleChange}
            required
            placeholder="Ex: 12345678000199"
            className="w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          />
        </div>
        <div className="flex justify-end">
          <button
            type="submit"
            className="bg-indigo-600 hover:bg-indigo-700 text-white font-semibold px-6 py-2 rounded-md transition duration-200"
          >
            Salvar Fornecedor
          </button>
        </div>
      </form>
    </div>
  );
};

export default CadastrarFornecedor;
