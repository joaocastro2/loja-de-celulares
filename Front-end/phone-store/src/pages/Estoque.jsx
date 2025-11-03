// src/pages/Estoque.jsx

import React from 'react';
import { FaBoxes } from 'react-icons/fa';

const Estoque = () => {
  return (
    <div className="p-6 bg-white rounded-lg shadow-xl">
      <h1 className="text-3xl font-bold text-gray-800 flex items-center mb-4">
        <FaBoxes className="mr-3 text-indigo-600" /> Lista de Estoque (Em Construção)
      </h1>
      <p className="text-gray-600">Este módulo será implementado a seguir para exibir e gerenciar todos os produtos.</p>
    </div>
  );
};

export default Estoque;