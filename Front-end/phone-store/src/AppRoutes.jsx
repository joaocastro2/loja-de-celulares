// src/AppRoutes.jsx
import { Routes, Route } from 'react-router-dom';
import PrivateRoute from './components/PrivateRoute';
import Dashboard from './layouts/Dashboard';
import Login from './pages/Login';
import Home from './pages/Home';
import Estoque from './pages/Estoque';
import Cadastros from './pages/Cadastros'; // usado para cadastrar produto
import CadastrarFornecedor from './pages/CadastrarFornecedor';
import CadastrarClientes from './pages/CadastrarClientes';
import CadastrarVendedores from './pages/CadastrarVendedores';

function AppRoutes() {
  return (
    <Routes>
      {/* Rota pública */}
      <Route path="/" element={<Login />} />

      {/* Rotas privadas */}
      <Route element={<PrivateRoute />}>
        <Route path="/app" element={<Dashboard />}>
          {/* Página inicial */}
          <Route index element={<Home />} />
          <Route path="home" element={<Home />} />

          {/* Grupo Estoque */}
          <Route path="estoque" element={<Estoque />} />
          <Route path="estoque/cadastrar" element={<Cadastros />} />

          {/* Clientes */}
          <Route path="clientes" element={<CadastrarClientes />} />

          {/* Fornecedores */}
          <Route path="fornecedores" element={<CadastrarFornecedor />} />

          {/* Vendedores */}
          <Route path="vendedores" element={<CadastrarVendedores />} />
        </Route>
      </Route>
    </Routes>
  );
}

export default AppRoutes;