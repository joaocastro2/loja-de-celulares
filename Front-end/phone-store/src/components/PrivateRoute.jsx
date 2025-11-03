// src/components/PrivateRoute.jsx
import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import Dashboard from '../layouts/Dashboard'; // Importa o layout

const PrivateRoute = () => {
  // Simplesmente verifica se o token existe no localStorage.
  // Você pode melhorar essa lógica depois (checando validade, etc.).
  const isAuthenticated = localStorage.getItem('token'); 

  return isAuthenticated ? (
    // Se logado, renderiza o Layout do Dashboard e o conteúdo da rota aninhada
    <Dashboard>
        <Outlet /> 
    </Dashboard>
  ) : (
    // Se não logado, redireciona para a tela de login
    <Navigate to="/" replace />
  );
};

export default PrivateRoute;