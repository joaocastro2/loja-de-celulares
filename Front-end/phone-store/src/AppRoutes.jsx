// src/AppRoutes.jsx

import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';
import Cadastros from './pages/Cadastros'; // Importado
import Estoque from './pages/Estoque';     // Importado
import PrivateRoute from './components/PrivateRoute'; 

const AppRoutes = () => {
  return (
    <Routes>
      {/* 1. Rota Pública: Tela de Login */}
      <Route path="/" element={<Login />} />
      
      {/* 2. Rotas Protegidas: Usam o PrivateRoute como Layout/Guarda */}
      <Route path="/app" element={<PrivateRoute />}>
        <Route path="home" element={<Home />} />
        
        {/* Rota para Cadastrar Produtos */}
        <Route path="cadastros" element={<Cadastros />} /> 
        
        {/* Rota para a Listagem de Estoque */}
        <Route path="estoque" element={<Estoque />} /> 

        {/* Catch-all dentro da área protegida */}
        <Route path="*" element={<Navigate to="home" replace />} /> 
      </Route>

      {/* 3. Rota de fallback para qualquer coisa não encontrada */}
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
};

export default AppRoutes;