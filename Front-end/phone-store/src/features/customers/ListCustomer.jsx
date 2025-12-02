import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { FaUser, FaSyncAlt, FaSpinner } from 'react-icons/fa';

const API_URL = 'http://localhost:8080/customers';
const TOKEN_STORAGE_KEY = 'token';

// Cabeçalhos da tabela
const TableHead = ({ text, className = '' }) => (
  <th
    scope="col"
    className={`px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider ${className}`}
  >
    {text}
  </th>
);

const ListCustomer = () => {
  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Buscar clientes
  const fetchCustomers = async () => {
    setLoading(true);
    setError(null);
    const token = localStorage.getItem(TOKEN_STORAGE_KEY);

    try {
      const response = await axios.get(API_URL, {
        headers: {
          ...(token && { Authorization: `Bearer ${token}` }),
        },
      });
      console.log('JSON Recebido do Servidor:', response.data);
      setCustomers(response.data);
    } catch (err) {
      console.error('Erro ao carregar clientes:', err);
      if (err.response) {
        if (err.response.status === 401 || err.response.status === 403) {
          setError('Acesso negado pelo servidor. Verifique se o login foi feito e se o token é válido.');
        } else {
          setError(`Erro do servidor (${err.response.status}): Falha na requisição.`);
        }
      } else if (err.request) {
        setError('Erro de conexão: O servidor pode estar offline ou inacessível.');
      } else {
        setError('Ocorreu um erro desconhecido.');
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCustomers();
  }, []);

  // Estado: carregando
  if (loading) {
    return (
      <div className="w-full h-screen flex flex-col justify-center items-center bg-gray-50">
        <FaSpinner className="animate-spin text-gray-500 text-5xl mb-4" />
        <p className="text-gray-600 text-lg">Carregando clientes...</p>
      </div>
    );
  }

  // Estado: erro
  if (error) {
    return (
      <div className="w-full h-screen flex flex-col justify-center items-center bg-gray-50 text-gray-700">
        <div className="bg-white p-8 rounded-lg shadow-lg text-center max-w-md border border-gray-200">
          <p className="font-bold text-lg mb-2 text-red-600">Erro de Carregamento</p>
          <p>{error}</p>
          <button
            onClick={fetchCustomers}
            className="mt-4 flex items-center justify-center px-4 py-2 bg-gray-500 hover:bg-gray-600 text-white font-medium rounded-md transition duration-150"
          >
            <FaSyncAlt className="mr-2" /> Tentar Novamente
          </button>
        </div>
      </div>
    );
  }

  // Estado: sucesso
  return (
    <div className="min-h-screen bg-gray-50 p-8">
      {/* Box principal */}
      <div className="bg-white rounded-lg shadow-md p-6">
        {/* Título dentro da box */}
        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-6">
          <h1 className="text-2xl font-bold text-gray-700 flex items-center mb-4 sm:mb-0">
            <FaUser className="mr-3 w-6 h-6 text-gray-500" />
            Lista de Clientes ({customers.length})
          </h1>

          {/* Botão de atualização */}
          <button
            onClick={fetchCustomers}
            className="flex items-center justify-center px-4 py-2 bg-gray-500 hover:bg-gray-600 text-white font-medium rounded-md shadow-sm transition duration-200"
          >
            <FaSyncAlt className="mr-2" /> Atualizar Lista
          </button>
        </div>

        {/* Conteúdo da tabela */}
        {customers.length === 0 ? (
          <div className="p-6 text-center bg-gray-100 border border-gray-300 text-gray-600 rounded-lg shadow-sm">
            <p className="font-bold">Nenhum Cliente Encontrado</p>
            <p>A lista de clientes está vazia. Certifique-se de que o Back-end está rodando e autenticado.</p>
          </div>
        ) : (
          <div className="overflow-x-auto rounded-lg border border-gray-200 shadow-sm">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <TableHead text="ID" />
                  <TableHead text="Nome Completo" />
                  <TableHead text="Email" />
                  <TableHead text="Documento (CPF)" />
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-100">
                {customers.map((customer) => (
                  <tr key={customer.customerId} className="hover:bg-gray-50 transition duration-100">
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 truncate max-w-xs">
                      {customer.customerId}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {customer.customerName}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                      {customer.customerEmail}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                      {customer.customerCpf}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default ListCustomer;
