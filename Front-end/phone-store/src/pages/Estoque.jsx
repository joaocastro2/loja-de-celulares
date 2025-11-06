import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaBoxes, FaSpinner, FaSyncAlt } from 'react-icons/fa';

// URL base do seu StockController
const API_URL = 'http://localhost:8080/stock';

// Função utilitária para formatar centavos em reais (R$)
const formatPrice = (priceInCents) => {
  const priceInReals = priceInCents / 100;
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(priceInReals);
};

const Estoque = () => {
  const [stockList, setStockList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Buscar dados do estoque
  const fetchStock = async () => {
    setLoading(true);
    setError(null);
    const token = localStorage.getItem('token');

    try {
      const response = await axios.get(API_URL, {
        headers: {
          ...(token && { Authorization: `Bearer ${token}` }),
        },
      });
      setStockList(response.data);
    } catch (err) {
      console.error('Erro ao buscar estoque:', err);
      setError('Falha ao carregar a lista de estoque. Verifique o servidor e o backend.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStock();
  }, []);

  // Estado: carregando
  if (loading) {
    return (
      <div className="w-full h-screen flex flex-col justify-center items-center bg-gray-50">
        <FaSpinner className="animate-spin text-gray-500 text-5xl mb-4" />
        <p className="text-gray-600 text-lg">Carregando dados do estoque...</p>
      </div>
    );
  }

  // Estado: erro
  if (error) {
    return (
      <div className="w-full h-screen flex flex-col justify-center items-center bg-gray-50 text-gray-700">
        <div className="bg-white p-8 rounded-lg shadow-lg text-center max-w-md border border-gray-200">
          <p className="font-bold text-lg mb-2 text-red-600">Erro ao carregar estoque</p>
          <p>{error}</p>
          <button
            onClick={fetchStock}
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
            <FaBoxes className="mr-3 w-6 h-6 text-gray-500" />
            Estoque Atual ({stockList.length} itens)
          </h1>

          {/* Botão de atualização */}
          <button
            onClick={fetchStock}
            className="flex items-center justify-center px-4 py-2 bg-gray-500 hover:bg-gray-600 text-white font-medium rounded-md shadow-sm transition duration-200"
          >
            <FaSyncAlt className="mr-2" /> Atualizar Estoque
          </button>
        </div>

        {/* Conteúdo da tabela */}
        {stockList.length === 0 ? (
          <div className="p-6 text-center bg-gray-100 border border-gray-300 text-gray-600 rounded-lg shadow-sm">
            <p className="font-bold">Estoque Vazio</p>
            <p>Nenhum produto ativo encontrado no momento.</p>
          </div>
        ) : (
          <div className="overflow-x-auto rounded-lg border border-gray-200 shadow-sm">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <TableHead text="ID" />
                  <TableHead text="Produto" />
                  <TableHead text="Descrição" />
                  <TableHead text="Quantidade" className="text-right" />
                  <TableHead text="Preço Unitário" className="text-right" />
                  <TableHead text="Fornecedor ID" />
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-100">
                {stockList.map((item) => (
                  <tr
                    key={item.productId}
                    className="hover:bg-gray-50 transition duration-100"
                  >
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 truncate max-w-xs">
                      {item.productId}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {item.productName}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {item.description || '-'}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-bold text-right text-gray-700">
                      {item.amount}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-right text-gray-700 font-semibold">
                      {formatPrice(item.price_in_cents)}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 truncate max-w-xs">
                      {item.supplierId?.id || 'N/A'}
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

// Cabeçalhos da tabela
const TableHead = ({ text, className = '' }) => (
  <th
    scope="col"
    className={`px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider ${className}`}
  >
    {text}
  </th>
);

export default Estoque;
