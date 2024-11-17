import axios from "axios";

// Variables de entorno 
const backendServer = import.meta.env.VITE_BACKEND_SERVER;
const backendPort = import.meta.env.VITE_BACKEND_PORT;

// Crear una instancia personalizada axios
const httpClient = axios.create({
  baseURL: `http://${backendServer}:${backendPort}`,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

export default httpClient;