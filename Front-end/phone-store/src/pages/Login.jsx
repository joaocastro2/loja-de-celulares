// src/pages/Login.jsx

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // IMPORTAÇÃO CORRETA ADICIONADA!
import { FaUser, FaLock, FaSignInAlt } from 'react-icons/fa';
import axios from 'axios';

// Usaremos o "token" como chave no localStorage, o que é mais consistente com o PrivateRoute
const TOKEN_STORAGE_KEY = 'token'; 
const API_BASE_URL = 'http://localhost:8080/auth/login';

function Login() {
  const navigate = useNavigate(); // Agora 'navigate' está definido!

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    // Prevenção de múltiplos cliques
    if (loading) return; 

    setLoading(true);

    // Substituímos o alert nativo por uma função que simula um modal,
    // pois o alert não é permitido no ambiente de execução.
    const showMessage = (title, message) => {
        // Implementação simulada de modal/message box
        console.log(`[Mensagem] ${title}: ${message}`);
        alert(message); // Mantendo o alert por agora, mas lembre-se de substituí-lo
    };

    try {
      // 1. Requisição POST
      const response = await axios.post(API_BASE_URL, {
        email: email,
        password: password,
      });

      // 2. Se for sucesso (status 200 OK)
      const { userName, token } = response.data;
      
      // **Ação Crítica:** Salvar o token
      localStorage.setItem(TOKEN_STORAGE_KEY, token);
      
      // showMessage('Sucesso', `Login bem-sucedido! Bem-vindo(a), ${userName}.`);
      console.log(`Login bem-sucedido! Bem-vindo(a), ${userName}. O token foi salvo.`);
      
      // 3. Redirecionar para a área protegida
      navigate('/app/home', { replace: true }); 

    } catch (err) {
      // 3. Se houver erro
      if (err.response) {
        // Tratar erros 4xx/5xx do servidor
        setError(err.response.data || 'E-mail ou senha incorretos. Tente novamente.');
      } else if (err.request) {
        // Erro de rede (servidor não está rodando ou CORS)
        setError('Erro ao conectar com o servidor. Verifique se a API está online (localhost:8080).');
      } else {
        setError('Ocorreu um erro desconhecido.');
      }
      console.error("Erro na requisição:", err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      
      {/* CARD DE LOGIN */}
      <div className="w-full max-w-md bg-white rounded-xl shadow-2xl p-8 space-y-6">
        
        {/* CABEÇALHO */}
        <div className="text-center">
          <h1 className="text-3xl font-bold text-gray-800">Sistema de Gestão</h1>
          <p className="text-gray-500 mt-2">Acesse sua conta</p>
        </div>

        {/* MENSAGEM DE ERRO (Se houver) */}
        {error && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-lg relative transition-all duration-300" role="alert">
            <span className="block sm:inline">{error}</span>
          </div>
        )}

        {/* FORMULÁRIO */}
        <form onSubmit={handleSubmit} className="space-y-4">
          
          {/* INPUT E-MAIL */}
          <div>
            <label className="text-sm font-medium text-gray-700 block mb-1" htmlFor="email">E-mail</label>
            <div className="relative">
              <span className="absolute inset-y-0 left-0 flex items-center pl-3 text-gray-400">
                <FaUser className="h-5 w-5" />
              </span>
              <input
                id="email"
                type="email"
                placeholder="seu.email@exemplo.com"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
              />
            </div>
          </div>
          
          {/* INPUT SENHA */}
          <div>
            <label className="text-sm font-medium text-gray-700 block mb-1" htmlFor="password">Senha</label>
            <div className="relative">
              <span className="absolute inset-y-0 left-0 flex items-center pl-3 text-gray-400">
                <FaLock className="h-5 w-5" />
              </span>
              <input
                id="password"
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150"
              />
            </div>
          </div>

          {/* BOTÃO */}
          <button
            type="submit"
            disabled={loading}
            className={`w-full flex justify-center items-center py-2 px-4 border border-transparent rounded-lg text-sm font-medium text-white shadow-sm transition duration-200 ${
              loading ? 'bg-blue-400 cursor-not-allowed' : 'bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'
            }`}
          >
            {loading ? (
              <svg className="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
            ) : (
              <>
                <FaSignInAlt className="mr-2" /> Entrar
              </>
            )}
          </button>
        </form>
        
        {/* RODAPÉ */}
        <div className="text-center text-sm">
          <a href="#" className="font-medium text-blue-600 hover:text-blue-500">
            Esqueceu sua senha?
          </a>
        </div>
      </div>
    </div>
  );
}

export default Login;