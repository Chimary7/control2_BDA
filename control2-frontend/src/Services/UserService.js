import httpClient from "../http-common";

export const createClient = async (user) => {
    try{
        const response = await httpClient.post("/usuario", user);
        return {data: response.data, status: response.status};

    } catch (error) {
        if(error.response){
            console.log("error en respuesta del servidor: ", error.response.data);
            return {data: error.response.data, status: error.response.status};
        } else if(error.request){
            console.log("no se recibe respuesta del servidor: ", error.request);
        } else {
            console.log("Error al enviar la petición: ", error.message);
        }
    }
}