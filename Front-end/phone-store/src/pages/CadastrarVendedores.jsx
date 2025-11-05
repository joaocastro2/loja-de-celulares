import React, { useState } from 'react';
import axios from 'axios';

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
    // Limita SSN a 9 dígitos
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
    <div className="max-w-md mx-auto mt-10 bg-white shadow-lg rounded-lg p-6">
      <h2 className="text-xl font-bold mb-4 text-indigo-700">Cadastrar Vendedor</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="nome"
          placeholder="Nome"
          value={formData.nome}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
          required
        />
        <input
          type="text"
          name="ssn"
          placeholder="SSN (9 dígitos)"
          value={formData.ssn}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
          required
        />
        <input
          type="number"
          name="comissao"
          placeholder="Taxa de Comissão (ex: 0.04)"
          value={formData.comissao}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
        />
        <div className="flex items-center space-x-2">
          <input
            type="checkbox"
            name="ativo"
            checked={formData.ativo}
            onChange={(e) => setFormData({ ...formData, ativo: e.target.checked })}
          />
          <label>Ativo</label>
        </div>
        <button
          type="submit"
          className="w-full bg-indigo-600 text-white py-2 rounded hover:bg-indigo-700"
        >
          Salvar
        </button>
      </form>
    </div>
  );
};

export default CadastrarVendedores;