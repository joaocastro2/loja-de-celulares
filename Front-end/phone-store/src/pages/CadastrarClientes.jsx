import { useState } from 'react';
import axios from 'axios';

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
    // Limita tamanho do SSN e telefone
    if (name === 'ssn' && value.length > 9) return;
    if (name === 'telefone' && value.length > 10) return;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validações simples
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
    <div className="max-w-md mx-auto mt-10 bg-white shadow-lg rounded-lg p-6">
      <h2 className="text-xl font-bold mb-4 text-indigo-700">Cadastrar Cliente</h2>
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
          type="text"
          name="telefone"
          placeholder="Telefone (até 10 dígitos)"
          value={formData.telefone}
          onChange={handleChange}
          className="w-full border rounded px-3 py-2"
          required
        />
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

export default CadastrarClientes;