import React, { useState } from 'react';
import { Link, Outlet, useNavigate, useLocation } from 'react-router-dom';
// Importações de ícones atualizadas para incluir FaUserTie e FaUsers
import { FaHome, FaBox, FaPlusSquare, FaSignOutAlt, FaBars, FaTimes, FaUserTie, FaUsers } from 'react-icons/fa';

// Itens de navegação do menu lateral
const navItems = [
    { name: 'Início', path: '/app/home', icon: FaHome },
    { name: 'Listar Estoque', path: '/app/estoque', icon: FaBox },         // Rota correta
    { name: 'Cadastrar Produto', path: '/app/cadastros', icon: FaPlusSquare }, // Rota correta
    // NOVOS ITENS DE FORNECEDORES ADICIONADOS AQUI
    { name: 'Cadastrar Fornecedor', path: '/app/fornecedores', icon: FaUserTie }, 
];

const Dashboard = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [sidebarOpen, setSidebarOpen] = useState(false);

    // Função para fazer logout
    const handleLogout = () => {
        localStorage.removeItem('token');
        // Opcional: localStorage.removeItem('user_name');
        navigate('/', { replace: true });
    };

    const userName = localStorage.getItem('user_name') || 'Usuário';

    return (
        <div className="flex h-screen bg-gray-50">
            {/* Overlay da Sidebar (Mobile) */}
            <div 
                className={`fixed inset-0 bg-gray-900 bg-opacity-50 z-20 md:hidden transition-opacity duration-200 ${
                    sidebarOpen ? 'opacity-100 block' : 'opacity-0 hidden'
                }`}
                onClick={() => setSidebarOpen(false)}
            ></div>

            {/* Sidebar Desktop/Mobile */}
            <div className={`flex flex-col w-64 bg-indigo-700 shadow-xl transition-all duration-300 z-30
                ${sidebarOpen ? 'translate-x-0 ease-out' : '-translate-x-full md:translate-x-0 md:static ease-in'}`}>
                
                {/* Cabeçalho da Sidebar */}
                <div className="flex items-center justify-between h-16 px-4 bg-indigo-800">
                    <span className="text-xl font-semibold text-white">
                        Phone Store
                    </span>
                    <button className="text-white md:hidden" onClick={() => setSidebarOpen(false)}>
                        <FaTimes className="w-6 h-6" />
                    </button>
                </div>

                {/* Itens de Navegação */}
                <nav className="flex-1 px-2 py-4 space-y-2 overflow-y-auto">
                    {navItems.map((item) => (
                        <Link
                            key={item.name}
                            to={item.path}
                            className={`flex items-center px-4 py-2 rounded-lg transition duration-200
                                ${location.pathname === item.path || (item.path !== '/app/home' && location.pathname.startsWith(item.path + '/'))
                                    ? 'bg-indigo-900 text-white font-semibold shadow-md' 
                                    : 'text-indigo-200 hover:bg-indigo-600 hover:text-white'}`}
                        >
                            <item.icon className="w-5 h-5 mr-3" />
                            {item.name}
                        </Link>
                    ))}
                </nav>

                {/* Rodapé da Sidebar (Logout) */}
                <div className="p-4 border-t border-indigo-600">
                    <button
                        onClick={handleLogout}
                        className="w-full flex items-center justify-center px-4 py-2 border border-transparent rounded-lg text-sm font-medium text-red-100 bg-indigo-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 transition duration-200"
                    >
                        <FaSignOutAlt className="mr-2" /> Sair
                    </button>
                </div>
            </div>

            {/* Conteúdo Principal */}
            <div className="flex flex-col flex-1 overflow-hidden">
                {/* Navbar Topo */}
                <header className="flex items-center justify-between h-16 bg-white border-b shadow-sm px-4">
                    <button className="text-gray-500 md:hidden" onClick={() => setSidebarOpen(true)}>
                        <FaBars className="w-6 h-6" />
                    </button>
                    <h2 className="text-xl font-semibold text-gray-700 hidden md:block">
                        Dashboard
                    </h2>
                    <div className="text-sm font-medium text-gray-600">
                        Bem-vindo(a), <span className="text-indigo-600">{userName}</span>
                    </div>
                </header>

                {/* Área de Conteúdo (Outlet) */}
                <main className="flex-1 overflow-x-hidden overflow-y-auto bg-gray-100 p-6">
                    <Outlet />
                </main>
            </div>
        </div>
    );
};

export default Dashboard;