import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';
import Cadastros from './pages/Cadastros';
import Estoque from './pages/Estoque';
import PrivateRoute from './components/PrivateRoute';

// Novos componentes de fornecedores
import CadastrarFornecedor from './pages/CadastrarFornecedor';

const AppRoutes = () => {
  return (
    <Routes>
      {/* Rota pública: Login */}
      <Route path="/" element={<Login />} />

      {/* Rotas protegidas */}
      <Route path="/app" element={<PrivateRoute />}>
        <Route path="home" element={<Home />} />
        <Route path="cadastros" element={<Cadastros />} />
        <Route path="estoque" element={<Estoque />} />

        {/* Rotas de Fornecedores */}
        <Route path="fornecedores" element={<CadastrarFornecedor />} />

        {/* Fallback interno da área protegida */}
        <Route path="*" element={<Navigate to="home" replace />} />
      </Route>

      {/* Fallback global */}
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
};

export default AppRoutes;
