import { useState } from 'react';
import axios from 'axios';
import { FaUser } from 'react-icons/fa'; // Ícone de cliente

const API_URL = 'http://localhost:8080/customers';

const CadastrarClientes = () => {
  const [formData, setFormData] = useState({
    nome: '',
    ssn: '',
    email: '',
    telefone: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'ssn' && value.length > 9) return;
    if (name === 'telefone' && value.length > 10) return;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.ssn.length !== 9) {
      alert('O SSN deve ter exatamente 9 dígitos.');
      return;
    }
    if (formData.telefone.length < 8 || formData.telefone.length > 10) {
      alert('O telefone deve ter entre 8 e 10 dígitos.');
      return;
    }

    try {
      const payload = {
        customerName: formData.nome,
        customerSsn: formData.ssn,
        customerEmail: formData.email,
        customerPhone: formData.telefone,
      };

      const token = localStorage.getItem('token');
      const response = await axios.post(API_URL, payload, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      alert(`Cliente cadastrado com sucesso! ID: ${response.data.customerId}`);
      setFormData({ nome: '', ssn: '', email: '', telefone: '' });
    } catch (error) {
      console.error(error);
      alert('Erro ao cadastrar cliente.');
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 bg-white shadow-md rounded-lg p-6">
      <h2 className="text-xl font-bold mb-4 text-gray-700 flex items-center">
        <FaUser className="mr-2 text-gray-500" /> Cadastrar Novo Cliente
      </h2>
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
          type="text"
          name="telefone"
          placeholder="Telefone (até 10 dígitos)"
          value={formData.telefone}
          onChange={handleChange}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
          required
        />

        <button
          type="submit"
          className="w-full flex justify-center items-center py-2 px-4 rounded-lg text-lg font-medium text-white shadow-md transition duration-200 bg-emerald-600 hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
        >
          Salvar
        </button>
      </form>
    </div>
  );
};

export default CadastrarClientes;
