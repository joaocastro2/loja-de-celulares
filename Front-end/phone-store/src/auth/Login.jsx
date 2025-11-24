// src/pages/Login.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FaUser, FaLock, FaChartPie } from 'react-icons/fa';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/auth/login';
const TOKEN_STORAGE_KEY = 'token';

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (loading) return;

    setLoading(true);
    setError('');

    try {
      const response = await axios.post(API_BASE_URL, { email, password });

      const { token, userName } = response.data; // backend retorna userName + token
      localStorage.setItem(TOKEN_STORAGE_KEY, token);
      localStorage.setItem('userName', userName); // salva nome do usuário

      navigate('/app/home', { replace: true });
    } catch (err) {
      if (err.response) setError(err.response.data || 'E-mail ou senha incorretos.');
      else if (err.request) setError('Erro de conexão com o servidor.');
      else setError('Erro desconhecido.');
      setPassword('');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      <div className="w-full max-w-4xl bg-gray-50 rounded-xl shadow-lg flex overflow-hidden">
        
        {/* Lado esquerdo */}
        <div className="hidden md:flex w-1/2 bg-gray-200 items-center justify-center p-8">
          <div className="text-gray-700 text-center">
            <FaChartPie className="text-7xl mx-auto mb-4" />
            <h2 className="text-2xl font-bold">Sistema de Gestão</h2>
            <p className="text-gray-600 mt-2">Controle completo de fornecedores, vendedores e vendas</p>
          </div>
        </div>

        {/* Lado direito - formulário */}
        <div className="w-full md:w-1/2 p-8">
          <h2 className="text-2xl font-bold text-gray-800 mb-6 text-center">Bem-vindo de volta</h2>
          <p className="text-center text-gray-500 mb-6">Faça login para acessar o sistema</p>

          {error && (
            <div className="bg-red-100 border border-red-300 text-red-700 px-4 py-2 rounded mb-4 text-sm">
              {error}
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="relative">
              <span className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                <FaUser />
              </span>
              <input
                type="email"
                placeholder="E-mail"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-300 bg-gray-100 focus:bg-white focus:outline-none focus:ring-2 focus:ring-gray-400 transition"
              />
            </div>

            <div className="relative">
              <span className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                <FaLock />
              </span>
              <input
                type="password"
                placeholder="Senha"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-300 bg-gray-100 focus:bg-white focus:outline-none focus:ring-2 focus:ring-gray-400 transition"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className={`w-full py-2 rounded-lg font-semibold text-white transition duration-200 ${
                loading ? 'bg-gray-400 cursor-not-allowed' : 'bg-gray-700 hover:bg-gray-600'
              }`}
            >
              {loading ? 'Entrando...' : 'Entrar'}
            </button>
          </form>

          <div className="text-center text-sm mt-4 text-gray-500">
            <a href="#" className="hover:text-gray-700">Esqueceu sua senha?</a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
