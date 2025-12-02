import React, { useState } from 'react';
import axios from 'axios';
import { FaUserTie } from 'react-icons/fa'; // Ícone para vendedor

const API_URL = 'http://localhost:8080/sellers';

const CadastrarVendedores = () => {
  const [formData, setFormData] = useState({
    nome: '',
    cpf: '',
    email: '',
    comissao: '',
    ativo: true,
  });

  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'cpf' && value.length > 11) return;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsError(false);
    setLoading(true);

    if (formData.cpf.length !== 11) {
      setMessage('O CPF deve ter exatamente 11 dígitos.');
      setIsError(true);
      setLoading(false);
      return;
    }

    try {
      const payload = {
        sellerName: formData.nome,
        sellerCpf: formData.cpf,
        sellerEmail: formData.email,
        sellerComRate: formData.comissao ? parseFloat(formData.comissao) : 0.04,
        active: formData.ativo,
      };

      const token = localStorage.getItem('token');
      const response = await axios.post(API_URL, payload, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      setMessage(
        `Vendedor "${response.data.sellerName || payload.sellerName}" cadastrado com sucesso! ID: ${response.data.sellerId || 'não retornado'}`
      );
      setIsError(false);
      setFormData({ nome: '', cpf: '', email: '', comissao: '', ativo: true });
    } catch (error) {
      console.error(error);
      setMessage('Erro ao cadastrar vendedor. Verifique o console para detalhes.');
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

      {/* Título com ícone */}
      <h2 className="text-xl font-bold mb-4 text-gray-700 flex items-center">
        <FaUserTie className="mr-2 text-gray-500" /> Cadastrar Vendedor
      </h2>

      {/* Formulário */}
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="nome"
          placeholder="Nome"
          value={formData.nome}
          onChange={handleChange}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
          required
        />
        <input
          type="text"
          name="cpf"
          placeholder="CPF (11 dígitos)"
          value={formData.cpf}
          onChange={handleChange}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
          required
        />
        <input
          type="number"
          name="comissao"
          placeholder="Taxa de Comissão (ex: 0.04)"
          value={formData.comissao}
          onChange={handleChange}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
        />
        <div className="flex items-center space-x-2">
          <input
            type="checkbox"
            name="ativo"
            checked={formData.ativo}
            onChange={(e) => setFormData({ ...formData, ativo: e.target.checked })}
          />
          <label className="text-gray-700">Ativo</label>
        </div>
        <button
          type="submit"
          disabled={loading}
          className={`w-full py-2 rounded shadow-sm text-white font-medium transition duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2 ${
            loading
              ? 'bg-gray-400 cursor-not-allowed'
              : 'bg-emerald-600 hover:bg-emerald-700 focus:ring-emerald-500'
          }`}
        >
          {loading ? 'Cadastrando...' : 'Salvar Vendedor'}
        </button>
      </form>
    </div>
  );
};

export default CadastrarVendedores;
