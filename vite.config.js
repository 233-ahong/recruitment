import {defineConfig, loadEnv} from 'vite'
import path from 'path'
import createVitePlugins from "./vite/plugins";

// https://vitejs.dev/config/
export default defineConfig(({ mode, command }) =>{
  const env = loadEnv(mode, process.cwd())
  const { VITE_APP_ENV } = env
  return{
    plugins: createVitePlugins(env,command==='build'),
    resolve: {
      alias: {
        // 设置路径
        '~': path.resolve(__dirname, './'),
        // 设置别名
        '@': path.resolve(__dirname, './src')
      },
      extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
    },
    server: {
      port: 81,
      host: true,
      open: false,
      proxy: {
        '/api': {
          target: 'http://localhost:9100',
          changeOrigin: false,
          ws: false,
          rewrite: (path) => path.replace(/^\/api/, ''),
        }
      }
    }
  }
})

