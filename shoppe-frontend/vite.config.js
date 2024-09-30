import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'  // Importa 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),  // Asegúrate de que 'alias' esté dentro de 'resolve'
    },
  },
  server: {
    proxy: {
      "/rest": {
        target: "http://localhost:8080/api",
        changeOrigin: true,
        secure: false,
      },
    },
  },
})