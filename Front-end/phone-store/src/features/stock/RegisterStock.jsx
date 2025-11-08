import { useState } from 'react';
import axios from 'axios';
import { FaSave, FaBox, FaDollarSign, FaSortNumericUp } from 'react-icons/fa';

const API_URL = 'http://localhost:8080/stock';

const INITIAL_PRODUCT_STATE = {
  product_name: '',
  description: '',
  amount: '',
  unit_price: '',
  supplier_id: '',
};

const Cadastros = () => {
  const [product, setProduct] = useState(INITIAL_PRODUCT_STATE);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setLoading(true);
    setIsError(false);

    const token = localStorage.getItem('token');
    if (!token) {
      setMessage('Erro: Token de autenticação não encontrado.');
      setIsError(true);
      setLoading(false);
      return;
    }

    const amountInt = parseInt(product.amount, 10);
    const priceFloat = parseFloat(product.unit_price);

    if (isNaN(amountInt) || amountInt < 0) {
      setMessage('Erro de validação: Quantidade deve ser um número inteiro não negativo.');
      setIsError(true);
      setLoading(false);
      return;
    }

    if (isNaN(priceFloat) || priceFloat <= 0) {
      setMessage('Erro de validação: Preço Unitário deve ser um valor positivo.');
      setIsError(true);
      setLoading(false);
      return;
    }

    const payload = {
      product_name: product.product_name,
      description: product.description,
      amount: amountInt,
      price_in_cents: Math.round(priceFloat * 100),
      supplier_id: product.supplier_id,
    };

    try {
      const response = await axios.post(API_URL, payload, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      setMessage(
        `Produto "${response.data.product_name || payload.product_name}" cadastrado com sucesso! ID: ${response.data.id}`
      );
      setIsError(false);
      setProduct(INITIAL_PRODUCT_STATE);
    } catch (err) {
      const errorMsg =
        err.response?.data?.message ||
        'Erro ao cadastrar produto. Verifique o console para detalhes.';
      console.error('API Error:', err.response || err);
      setMessage(`Falha no cadastro: ${errorMsg}`);
      setIsError(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-xl mx-auto mt-10">
      {/* MENSAGEM DE FEEDBACK */}
      {message && (
        <div
          className={`p-4 mb-4 rounded-lg shadow-md ${
            isError ? 'bg-red-100 text-red-800' : 'bg-green-100 text-green-800'
          }`}
        >
          {message}
        </div>
      )}

      {/* CARD DO FORMULÁRIO */}
      <div className="bg-white p-6 rounded-lg shadow-md">
        {/* TÍTULO DENTRO DA BOX */}
        <h1 className="text-2xl font-bold mb-6 text-gray-700 flex items-center justify-center sm:justify-start">
          <FaBox className="mr-3 w-6 h-6 text-gray-500" /> Cadastrar Novo Produto
        </h1>

        <form onSubmit={handleSubmit} className="space-y-6">
          {/* LINHA 1: NOME E DESCRIÇÃO */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label htmlFor="name" className="block text-sm font-medium text-gray-700">
                Nome do Produto
              </label>
              <input
                type="text"
                id="name"
                name="product_name"
                value={product.product_name}
                onChange={handleChange}
                required
                className="mt-1 block w-full border border-gray-300 rounded shadow-sm px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
              />
            </div>
            <div>
              <label htmlFor="description" className="block text-sm font-medium text-gray-700">
                Descrição
              </label>
              <input
                type="text"
                id="description"
                name="description"
                value={product.description}
                onChange={handleChange}
                className="mt-1 block w-full border border-gray-300 rounded shadow-sm px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
              />
            </div>
          </div>

          {/* LINHA 2: QUANTIDADE, PREÇO E SUPPLIER ID */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            <div>
              <label htmlFor="amount" className="block text-sm font-medium text-gray-700">
                <FaSortNumericUp className="inline mr-1 w-4 h-4 text-gray-500" /> Quantidade Inicial
              </label>
              <input
                type="number"
                id="amount"
                name="amount"
                value={product.amount}
                onChange={handleChange}
                required
                min="0"
                className="mt-1 block w-full border border-gray-300 rounded shadow-sm px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
              />
            </div>

            <div>
              <label htmlFor="unit_price" className="block text-sm font-medium text-gray-700">
                <FaDollarSign className="inline mr-1 w-4 h-4 text-gray-500" /> Preço Unitário
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
                className="mt-1 block w-full border border-gray-300 rounded shadow-sm px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
              />
            </div>

            <div>
              <label htmlFor="supplier_id" className="block text-sm font-medium text-gray-700">
                ID do Fornecedor
              </label>
              <input
                type="text"
                id="supplier_id"
                name="supplier_id"
                value={product.supplier_id}
                onChange={handleChange}
                required
                placeholder="Ex: 1, 2, 3..."
                className="mt-1 block w-full border border-gray-300 rounded shadow-sm px-3 py-2 focus:outline-none focus:ring-2 focus:ring-gray-400"
              />
            </div>
          </div>

          {/* BOTÃO SUBMIT */}
          <button
            type="submit"
            disabled={loading}
            className={`w-full flex justify-center items-center py-3 px-4 border border-transparent rounded-lg text-lg font-medium text-white shadow-md transition duration-200 ${
              loading
                ? 'bg-gray-400 cursor-not-allowed'
                :  'bg-emerald-600 hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500'
            }`}
          >
              <FaSave className="mr-3 w-5 h-5 text-white" />
              {loading ? 'Cadastrando...' : 'Salvar Produto'}
          </button>

        </form>
      </div>
    </div>
  );
};

export default Cadastros;
