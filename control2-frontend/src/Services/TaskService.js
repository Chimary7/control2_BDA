import httpClient from "../http-common";

export const createTask = async (task) => {
    try{
        const response = await httpClient.post("/tarea", task);
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

export const updateTask = async (task) => {
    try{
        const response = await httpClient.put("/tarea", task);
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

export const getTasksByIdUser = async (id) => {
    try{
        const response = await httpClient.get(`/tarea/${id}`);
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

export const deleteTask = async (id) => {
    try{
        const response = await httpClient.delete(`/tarea/${id}`);
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