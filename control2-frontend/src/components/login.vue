<template>
    <div class="fondo-login">
        <div class="contenedor-login">
                <div class="div-login-form">
                    <form @submit.prevent="login">
                        <h1>Inicia sesión</h1>
                        <div class="div-inputs-login">
                            <label>Name</label>
                            <input type="text" v-model = data.name placeholder="Ej:Nombre Apellido" required>
                            <label>Password</label>
                            <input type="password" v-model = data.password placeholder="Contraseña" required>
                            <p>¿No estas registrado? <router-link to="/register">Registrate</router-link></p>
                        </div>
                        <div class="div-button-login">
                            <button type="submit">Iniciar sesión</button>
                        </div>
                    </form>
                </div>
        </div>  
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { Login } from '../Services/UserService';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const store = useStore();
const router = useRouter();
const data = ref({ name: '', password: '' });

const login = async () => {

    console.log('Data:', data.value);
    const response = await Login(data.value);
    console.log('Response:', response);
    if (response.status === 200) {
        alert('Sesión iniciada correctamente');
        store.commit('setUser', response.data);
        store.commit('login')
        router.push({ name: 'homework' });

    } else {
        alert('Error al iniciar sesión');
    }
};
</script>

<style scoped>
.fondo-login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    background: linear-gradient(120deg, #6666ff 0%, #be73cb 100%);
}

.contenedor-login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 75%;
    width: 35%;
    background-color:white;
    border-radius: 1rem;
    border: solid 1px white;
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}

.div-login-form {
    height: 100%;
    width: 100%;
    right: 0;
    border-radius: 1rem;
    margin: 1rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-family: "Open Sans", sans-serif;
}

.div-login-form h1 {
    font-size: 2.5rem;
    font-weight: bold;
    margin-bottom: 1rem;
    height: 20%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #9216a8;
}

.div-login-form form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
}


.div-button-login {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 20%;
}

.div-button-login button {
    width: 85%;
    height: 3rem;
    padding: 1rem;
    background-color: #9216a8;
    color: white;
    border-radius: 0.5rem;
    border: solid 2px #9216a8;
    cursor: pointer;
    font-size: 1rem;
    font-weight: bold;
    justify-content: center;
    align-items: center;
    display: flex;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.div-button-login button:hover {
    background-color: white;
    color: #9216a8;
    border: solid 2px #9216a8;
}

.div-inputs-login {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: #9216a8;
    width: 100%;
    height: 50%;
}

.div-inputs-login input{
    width: 80%;
    height: 2rem;
    margin: 1rem;
    padding: 1rem;
    font-size: 1rem;
    background-color: white;
    color: rgb(45, 44, 44);
    border-radius: 0.5rem;
    border: solid 1px #9216a8;
}

.div-inputs-login input:focus{
    outline: none;
    border: solid 1px #9216a8;
    box-shadow: 0 0 2.5px #9216a8;
}

.div-inputs-login label{
    font-size: 1rem;
    font-weight: bold;
    margin: 0.5rem;
}

</style>