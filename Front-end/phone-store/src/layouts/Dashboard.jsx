import React, { useState } from 'react';
import { Link, Outlet, useNavigate, useLocation } from 'react-router-dom';
import {
  FaHome,
  FaBox,
  FaSignOutAlt,
  FaBars,
  FaTimes,
  FaUserTie,
  FaUsers,
  FaChevronDown
} from 'react-icons/fa';

const navGroups = [
  {
    name: 'Início',
    icon: FaHome,
    path: '/app/home',
  },
  {
    name: 'Estoque',
    icon: FaBox,
    subItems: [
      { name: 'Listar Estoque', path: '/app/estoque' },
      { name: 'Cadastrar Produto', path: '/app/estoque/cadastrar' },
    ],
  },
  {
    name: 'Clientes',
    icon: FaUsers,
    subItems: [
      { name: 'Cadastrar Cliente', path: '/app/clientes' },
    ],
  },
  {
    name: 'Fornecedores',
    icon: FaUserTie,
    subItems: [
      { name: 'Cadastrar Fornecedor', path: '/app/fornecedores' },
    ],
  },
  {
    name: 'Vendedores',
    icon: FaUserTie,
    subItems: [
      { name: 'Cadastrar Vendedor', path: '/app/vendedores' },
    ],
  },
];

const Dashboard = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [openGroup, setOpenGroup] = useState(null);

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/', { replace: true });
  };

  const userName = localStorage.getItem('user_name') || 'Usuário';

  const toggleGroup = (groupName) => {
    setOpenGroup(openGroup === groupName ? null : groupName);
  };

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Overlay Mobile */}
      <div
        className={`fixed inset-0 bg-gray-900 bg-opacity-50 z-20 md:hidden transition-opacity duration-200 ${
          sidebarOpen ? 'opacity-100 block' : 'opacity-0 hidden'
        }`}
        onClick={() => setSidebarOpen(false)}
      ></div>

      {/* Sidebar */}
      <div
        className={`flex flex-col w-64 bg-white border-r border-gray-200 shadow-sm transition-all duration-300 z-30
        ${sidebarOpen ? 'translate-x-0 ease-out' : '-translate-x-full md:translate-x-0 md:static ease-in'}`}
      >
        {/* Cabeçalho */}
        <div className="flex items-center justify-between h-16 px-4 bg-gray-50 border-b border-gray-200">
          <span className="text-lg font-semibold text-gray-700">Phone Store</span>
          <button className="text-gray-500 md:hidden" onClick={() => setSidebarOpen(false)}>
            <FaTimes className="w-6 h-6" />
          </button>
        </div>

        {/* Menu */}
        <nav className="flex-1 px-2 py-4 space-y-2 overflow-y-auto">
          {navGroups.map((group) => (
            <div key={group.name}>
              <button
                onClick={() => group.subItems ? toggleGroup(group.name) : navigate(group.path)}
                className={`flex items-center justify-between w-full px-4 py-2 rounded-lg transition duration-200 ${
                  location.pathname === group.path
                    ? 'bg-gray-200 text-gray-900 font-semibold'
                    : 'text-gray-700 hover:bg-gray-100 hover:text-gray-900'
                }`}
              >
                <div className="flex items-center">
                  <group.icon className="w-5 h-5 mr-3" />
                  {group.name}
                </div>
                {group.subItems && (
                  <FaChevronDown
                    className={`transition-transform ${openGroup === group.name ? 'rotate-180' : ''}`}
                  />
                )}
              </button>

              {group.subItems && openGroup === group.name && (
                <div className="ml-8 mt-2 space-y-1">
                  {group.subItems.map((item) => (
                    <Link
                      key={item.name}
                      to={item.path}
                      className={`block px-4 py-2 rounded-lg text-sm transition duration-200 ${
                        location.pathname === item.path
                          ? 'bg-gray-200 text-gray-900 font-semibold'
                          : 'text-gray-600 hover:bg-gray-100 hover:text-gray-900'
                      }`}
                    >
                      {item.name}
                    </Link>
                  ))}
                </div>
              )}
            </div>
          ))}
        </nav>

        {/* Logout */}
        <div className="p-4 border-t border-gray-200">
          <button
            onClick={handleLogout}
            className="w-full flex items-center justify-center px-4 py-2 rounded-lg text-sm font-medium text-gray-700 bg-gray-100 hover:bg-red-100 hover:text-red-600 transition duration-200"
          >
            <FaSignOutAlt className="mr-2" /> Sair
          </button>
        </div>
      </div>

      {/* Conteúdo */}
      <div className="flex flex-col flex-1 overflow-hidden">
        <header className="flex items-center justify-between h-16 bg-white border-b border-gray-200 shadow-sm px-4">
          <button className="text-gray-500 md:hidden" onClick={() => setSidebarOpen(true)}>
            <FaBars className="w-6 h-6" />
          </button>
          <h2 className="text-xl font-semibold text-gray-700 hidden md:block">Dashboard</h2>
          <div className="text-sm font-medium text-gray-600">
            Bem-vindo(a), <span className="text-gray-900">{userName}</span>
          </div>
        </header>

        <main className="flex-1 overflow-x-hidden overflow-y-auto bg-gray-50 p-6">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default Dashboard;
/* Commit correction */