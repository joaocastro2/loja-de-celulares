import React, { useState } from 'react';
import axios from 'axios';
import { FaUserTie } from 'react-icons/fa'; // Ícone para vendedor

const API_URL = 'http://localhost:8080/sellers';

const CadastrarVendedores = () => {
  const [formData, setFormData] = useState({
    nome: '',
    ssn: '',
    email: '',
    comissao: '',
    ativo: true,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'ssn' && value.length > 9) return;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.ssn.length !== 9) {
      alert('O SSN deve ter exatamente 9 dígitos.');
      return;
    }

    try {
      const payload = {
        sellerName: formData.nome,
        sellerSsn: formData.ssn,
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

      alert(`Vendedor cadastrado com sucesso! ID: ${response.data.sellerId}`);
      setFormData({ nome: '', ssn: '', email: '', comissao: '', ativo: true });
    } catch (error) {
      console.error(error);
      alert('Erro ao cadastrar vendedor.');
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 bg-white shadow-md rounded-lg p-6">
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
          name="ssn"
          placeholder="SSN (9 dígitos)"
          value={formData.ssn}
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
          className="w-full bg-emerald-600 hover:bg-emerald-700 text-white font-medium py-2 rounded shadow-sm transition duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
        >
          Salvar Vendedor
        </button>
      </form>
    </div>
  );
};

export default CadastrarVendedores;
