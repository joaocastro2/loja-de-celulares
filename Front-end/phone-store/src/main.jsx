// src/main.jsx

import React, { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom'; // Importa o roteador
import './index.css';
import AppRoutes from './AppRoutes'; // Importa o componente de definição de rotas

const rootElement = document.getElementById('root');

createRoot(rootElement).render(
  <StrictMode>
    {/* O BrowserRouter DEVE envolver todo o componente de rotas */}
    <BrowserRouter>
      <AppRoutes />
    </BrowserRouter>
  </StrictMode>
);