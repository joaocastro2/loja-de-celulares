// src/pages/Cadastros.jsx

import React, { useState } from 'react';
import axios from 'axios';
import { FaSave, FaBox, FaDollarSign, FaSortNumericUp } from 'react-icons/fa';

const API_URL = 'http://localhost:8080/stock'; // O endpoint do seu StockController

// Estado inicial dos campos do produto
// src/pages/Cadastros.jsx
const INITIAL_PRODUCT_STATE = {
    name: '',
    description: '',
    quantity: '', // Altere para string vazia para evitar NaN ao limpar o campo
    unit_price: '', // Altere para string vazia para evitar NaN ao limpar o campo
    supplier_id: '' 
};

const Cadastros = () => {
    const [product, setProduct] = useState(INITIAL_PRODUCT_STATE);
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState(''); // Mensagem de sucesso ou erro
    const [isError, setIsError] = useState(false);

    // Função genérica para atualizar o estado de qualquer campo
    const handleChange = (e) => {
        const { name, value, type } = e.target;
        setProduct(prev => ({
            ...prev,
            // Converte para número se o campo for numérico
            [name]: type === 'number' ? parseFloat(value) : value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage(''); // Limpa mensagens anteriores
        setLoading(true);

        // 1. Obter o Token
        const token = localStorage.getItem('token');
        if (!token) {
            setMessage('Erro: Usuário não autenticado. Faça login novamente.');
            setIsError(true);
            setLoading(false);
            return;
        }

        try {
            // 2. Requisição POST
            const response = await axios.post(API_URL, product, {
                headers: {
                    // Adiciona o token JWT no cabeçalho Authorization
                    'Authorization': `Bearer ${token}` 
                }
            });

            // 3. Sucesso (Status 200/201)
            setMessage(`Produto "${response.data.name}" cadastrado com sucesso! ID: ${response.data.id}`);
            setIsError(false);
            setProduct(INITIAL_PRODUCT_STATE); // Limpa o formulário
            
        } catch (err) {
            // 4. Trata Erros (400, 401, etc.)
            const errorMessage = err.response?.data?.message || err.response?.data || 
                               'Ocorreu um erro no servidor ou a requisição falhou.';
            
            setMessage(`Erro no Cadastro: ${errorMessage}`);
            setIsError(true);
            console.error("Erro no cadastro:", err);
            
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="p-6">
            <h1 className="text-3xl font-bold mb-6 text-gray-800 flex items-center">
                <FaBox className="mr-3 text-indigo-600" /> Cadastrar Novo Produto
            </h1>

            {/* MENSAGEM DE FEEDBACK */}
            {message && (
                <div className={`p-4 mb-4 rounded-lg shadow-md ${isError ? 'bg-red-100 text-red-800' : 'bg-green-100 text-green-800'}`}>
                    {message}
                </div>
            )}

            {/* FORMULÁRIO */}
            <form onSubmit={handleSubmit} className="bg-white p-8 rounded-xl shadow-lg space-y-6">
                
                {/* LINHA 1: NOME E DESCRIÇÃO */}
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label htmlFor="name" className="block text-sm font-medium text-gray-700">Nome do Produto</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            value={product.name}
                            onChange={handleChange}
                            required
                            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm p-3 focus:ring-indigo-500 focus:border-indigo-500"
                        />
                    </div>
                    <div>
                        <label htmlFor="description" className="block text-sm font-medium text-gray-700">Descrição</label>
                        <input
                            type="text"
                            id="description"
                            name="description"
                            value={product.description}
                            onChange={handleChange}
                            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm p-3 focus:ring-indigo-500 focus:border-indigo-500"
                        />
                    </div>
                </div>

                {/* LINHA 2: QUANTIDADE, PREÇO E SUPPLIER ID */}
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div>
                        <label htmlFor="quantity" className="block text-sm font-medium text-gray-700">
                            <FaSortNumericUp className="inline mr-1" /> Quantidade Inicial
                        </label>
                        <input
                            type="number"
                            id="quantity"
                            name="quantity"
                            value={product.quantity}
                            onChange={handleChange}
                            required
                            min="0"
                            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm p-3 focus:ring-indigo-500 focus:border-indigo-500"
                        />
                    </div>

                    <div>
                        <label htmlFor="unit_price" className="block text-sm font-medium text-gray-700">
                            <FaDollarSign className="inline mr-1" /> Preço Unitário
                        </label>
                        <input
                            type="number"
                            id="unit_price"
                            name="unit_price"
                            value={product.unit_price}
                            onChange={handleChange}
                            required
                            min="0"
                            step="0.01"
                            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm p-3 focus:ring-indigo-500 focus:border-indigo-500"
                        />
                    </div>

                    <div>
                        <label htmlFor="supplier_id" className="block text-sm font-medium text-gray-700">ID do Fornecedor</label>
                        <input
                            type="text"
                            id="supplier_id"
                            name="supplier_id"
                            value={product.supplier_id}
                            onChange={handleChange}
                            required
                            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm p-3 focus:ring-indigo-500 focus:border-indigo-500"
                            placeholder="Ex: 1, 2, 3..."
                        />
                    </div>
                </div>

                {/* BOTÃO SUBMIT */}
                <button
                    type="submit"
                    disabled={loading}
                    className={`w-full flex justify-center items-center py-3 px-4 border border-transparent rounded-lg text-lg font-medium text-white shadow-md transition duration-200 ${
                        loading ? 'bg-indigo-400 cursor-not-allowed' : 'bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500'
                    }`}
                >
                    <FaSave className="mr-3" />
                    {loading ? 'Cadastrando...' : 'Salvar Produto'}
                </button>
            </form>
        </div>
    );
};

export default Cadastros;