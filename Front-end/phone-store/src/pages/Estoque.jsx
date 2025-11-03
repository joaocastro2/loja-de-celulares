import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaBoxes, FaSpinner, FaSyncAlt } from 'react-icons/fa';

// URL base do seu StockController
const API_URL = 'http://localhost:8080/stock'; 

// Função utilitária para formatar centavos para BRL (R$)
const formatPrice = (priceInCents) => {
    // Converte centavos para reais e formata para BRL
    const priceInReals = priceInCents / 100;
    return new Intl.NumberFormat('pt-BR', { 
        style: 'currency', 
        currency: 'BRL' 
    }).format(priceInReals);
};

const Estoque = () => {
    const [stockList, setStockList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Função para buscar os dados do estoque
    const fetchStock = async () => {
        setLoading(true);
        setError(null);
        // O token de autenticação, se você estiver usando em outros endpoints, 
        // deve ser incluído aqui. Assumindo o mesmo padrão do seu Cadastros.jsx.
        const token = localStorage.getItem('token'); 
        
        try {
            const response = await axios.get(API_URL, {
                headers: {
                    // Inclua o header de autorização apenas se o endpoint /stock exigir.
                    // Se o endpoint for público, você pode remover esta linha.
                    ...(token && { 'Authorization': `Bearer ${token}` })
                }
            });
            setStockList(response.data);
            
        } catch (err) {
            console.error("Erro ao buscar estoque:", err);
            setError('Falha ao carregar a lista de estoque. Verifique o servidor e o backend.');
            
        } finally {
            setLoading(false);
        }
    };

    // Executa a busca ao montar o componente
    useEffect(() => {
        fetchStock();
    }, []);

    // --- RENDERIZAÇÃO DE ESTADOS ---

    if (loading) {
        return (
            <div className="p-6 text-center bg-white rounded-lg shadow-xl">
                <FaSpinner className="animate-spin text-indigo-500 text-4xl mx-auto mb-4" />
                <p className="text-gray-600">Carregando dados do estoque...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="p-6 bg-red-100 border border-red-400 text-red-700 rounded-lg shadow-xl">
                <p className="font-bold mb-2">Erro ao carregar:</p>
                <p>{error}</p>
                <button 
                    onClick={fetchStock}
                    className="mt-4 flex items-center px-4 py-2 bg-red-500 text-white font-medium rounded-md hover:bg-red-600 transition duration-150"
                >
                    <FaSyncAlt className="mr-2" /> Tentar Novamente
                </button>
            </div>
        );
    }

    // --- RENDERIZAÇÃO DA TABELA ---

    return (
        <div className="p-6">
            <h1 className="text-3xl font-bold text-gray-800 flex items-center mb-6">
                <FaBoxes className="mr-3 text-indigo-600" /> Estoque Atual ({stockList.length} itens)
            </h1>

            {stockList.length === 0 ? (
                <div className="p-6 text-center bg-yellow-100 border border-yellow-400 text-yellow-700 rounded-lg shadow-md">
                    <p className="font-bold">Estoque Vazio</p>
                    <p>Nenhum produto ativo encontrado no momento.</p>
                </div>
            ) : (
                <div className="overflow-x-auto bg-white rounded-xl shadow-lg">
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
                        <tbody className="bg-white divide-y divide-gray-200">
                            {stockList.map((item) => (
                                <tr key={item.productId} className="hover:bg-indigo-50 transition duration-100">
                                    
                                    {/* 1. ID: Usando productId (UUID) */}
                                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 truncate max-w-xs">{item.productId}</td>
                                    
                                    {/* 2. PRODUTO: Usando productName */}
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{item.productName}</td>
                                    
                                    {/* 3. DESCRIÇÃO: Este campo não existe no StockModel, então exibirá sempre '-' */}
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{item.description || '-'}</td>
                                    
                                    {/* 4. QUANTIDADE: Usando amount */}
                                    <td className="px-6 py-4 whitespace-nowrap text-sm font-bold text-right text-indigo-600">{item.amount}</td>
                                    
                                    {/* 5. PREÇO: Mantido como estava */}
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-right text-green-600 font-semibold">
                                        {formatPrice(item.price_in_cents)}
                                    </td>
                                    
                                    {/* 6. FORNECEDOR ID: Usando supplierId?.id */}
                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 truncate max-w-xs">{item.supplierId?.id || 'N/A'}</td> 
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

// Componente auxiliar para cabeçalhos da tabela
const TableHead = ({ text, className = '' }) => (
    <th 
        scope="col" 
        className={`px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider ${className}`}
    >
        {text}
    </th>
);

export default Estoque;