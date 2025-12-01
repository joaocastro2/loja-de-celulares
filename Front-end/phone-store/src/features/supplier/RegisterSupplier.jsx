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

  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;

    // Limite de 9 dígitos para CNPJ
    if (name === 'supplierCpf' && value.length > 11) return;

    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsError(false);
    setLoading(true);

    // Validação do CNPJ (11 dígitos)
    if (formData.supplierCpf.length !== 11) {
      setMessage('O CNPJ deve ter exatamente 11 dígitos.');
      setIsError(true);
      setLoading(false);
      return;
    }

    try {
      const token = localStorage.getItem('token');
      const response = await axios.post(API_URL, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      const supplierId = response.data.supplierId;
      setMessage(
        `Fornecedor "${response.data.supplierName || formData.supplierName}" cadastrado com sucesso! ID: ${supplierId || 'não retornado'}`
      );
      setIsError(false);
      setFormData({ supplierName: '', supplierCpf: '', active: true });
    } catch (error) {
      console.error(error);
      setMessage('Erro ao cadastrar fornecedor. Verifique o console para detalhes.');
      setIsError(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 bg-white shadow-md rounded-lg p-6">
      {/* MENSAGEM DE FEEDBACK */}
      {message && (
        <div
          className={`p-4 mb-4 rounded-lg shadow-md ${
            isError ? 'bg-red-100 text-red-800' : 'bg-green-100 text-green-800'
          }`}
        >
          {message}
        </div>
      )}

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
            CNPJ (11 dígitos)
          </label>
          <input
            type="number"
            name="supplierCpf"
            value={formData.supplierCpf}
            onChange={handleChange}
            required
            placeholder="Ex: 12345678910"
            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-gray-400 focus:outline-none"
          />
        </div>

        <div className="flex items-center space-x-2">
          <input
            type="checkbox"
            name="active"
            checked={formData.active}
            onChange={(e) => setFormData({ ...formData, active: e.target.checked })}
          />
          <label className="text-gray-700">Ativo</label>
        </div>

        <div className="flex justify-end">
          <button
            type="submit"
            disabled={loading}
            className={`px-6 py-2 rounded-md shadow-sm text-white font-medium transition duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2 ${
              loading
                ? 'bg-gray-400 cursor-not-allowed'
                : 'bg-emerald-600 hover:bg-emerald-700 focus:ring-emerald-500'
            }`}
          >
            {loading ? 'Cadastrando...' : 'Salvar Fornecedor'}
          </button>
        </div>
      </form>
    </div>
  );
};

export default CadastrarFornecedor;
