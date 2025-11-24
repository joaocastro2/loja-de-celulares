import { Routes, Route } from 'react-router-dom';
import PrivateRoute from './hooks/layouts/PrivateRoute'; // OK
import Dashboard from './hooks/layouts/Dashboard'; // OK 


// --- CORREÇÕES DE IMPORTAÇÃO ---

// Login foi movido para features/Auth
import Login from './auth/Login'; 

// Home foi movido para features/Dashboard
import Home from './features/dashboard/HomePage'; 

// O resto das features já estava com o caminho certo, mas o 'Login' e o 'Home' estavam quebrados.
import ListStock from './features/stock/ListStock';
import RegisterStock from './features/stock/RegisterStock';
import RegisterSupplier from './features/supplier/RegisterSupplier';
import ListSupplier from './features/supplier/ListSupplier';
import RegisterCustomers from './features/customers/RegisterCustomers';
import ListCustomer from './features/customers/ListCustomer';
import RegisterSeller from './features/seller/RegisterSeller';
import ListSeller from './features/seller/ListSeller'


// --- NOVAS IMPORTAÇÕES NECESSÁRIAS ---
// O código abaixo usa 'Estoque', 'Cadastros' e 'CadastrarClientes' sem importá-los.
// Precisamos importar os componentes corretos para essas rotas.
// Assumindo:
// Estoque = ListStock
// Cadastros (Cadastro de Estoque) = RegisterStock
// CadastrarClientes = RegisterCustomers

// Agora usamos os nomes dos componentes importados acima
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
          {/* Estava: <Route path="estoque" element={<Estoque />} /> */}
          <Route path="estoque" element={<ListStock />} /> 
          
          {/* Estava: <Route path="estoque/cadastrar" element={<Cadastros />} /> */}
          <Route path="estoque/cadastrar" element={<RegisterStock />} /> 

          {/* Clientes */}
          {/* Estava: <Route path="clientes" element={<CadastrarClientes />} /> */}
          <Route path="clientes" element={<RegisterCustomers />} />
          <Route path="clientes/listar" element={<ListCustomer />} />

          {/* Fornecedores */}
          {/* O componente RegisterSupplier JÁ FOI IMPORTADO */}
          <Route path="fornecedores" element={<ListSupplier />} />
          <Route path="fornecedores/cadastrar" element={<RegisterSupplier />} />

          {/* Vendedores */}
          {/* O componente RegisterSeller JÁ FOI IMPORTADO */}
          <Route path="vendedores" element={<RegisterSeller />} />
          <Route path="vendedores/listar" element={<ListSeller />} />
        </Route>
      </Route>
    </Routes>
  );
}

export default AppRoutes;