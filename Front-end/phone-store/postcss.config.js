// postcss.config.js
import tailwindcss from '@tailwindcss/postcss'; // Importa o plugin V4+
import autoprefixer from 'autoprefixer';

export default {
  plugins: [
    tailwindcss,
    autoprefixer,
  ],
};