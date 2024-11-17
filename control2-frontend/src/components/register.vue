<script setup>
import { ref } from 'vue'
  const name = ref('')
  const surname = ref('')
  const nombre = ref('')
  const password = ref('')
  const password2 = ref('')
  const register = async () => {
    try {
      console.log('register')
      if(name.value === '' || surname.value === '' || password.value === '' || password2.value === ''){
        alert('Todos los campos son obligatorios')
        return
      }
      if(password2.value !== password.value){
        alert('Las contraseñas no coinciden')
        return
      }
      if(password.value.length < 4){
        alert('La contraseña debe tener al menos 4 caracteres')
        return
      }
      const data = {
        nombre: `${name.value} ${surname.value}`,
        clave: password.value
      }
      console.log(data)
      /*
      const response = await fetch('http://localhost:3000/api/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      })
      const res = await response.json()
      console.log(res)
      */
    } catch (error) {
      console.log(error)
    }
  }
</script>

<template>
  <div class="fondo">
    <div class="conteiner">
      <h2 class="title">Registro</h2>
      <form @submit.prevent="register">
        <div class="fila">
          <div class="form-group">
            <label class="label" for="name">Nombre</label>
            <input type="text" v-model="name" class="form-control" id="name" placeholder="Ej: Juan" @input="name = name.replace(/\d/g, '')">
          </div>
          <div class="form-group">
            <label class="label" for="email">Apellido</label>
            <input type="text" v-model="surname" class="form-control" id="surname" placeholder="Ej: Torres" @input="name = name.replace(/\d/g, '')">
          </div>
        </div>
        <div class="fila">
          <div class="form-group">
            <label class="label" for="password">Contraseña</label>
            <input type="password" v-model="password" class="form-control" id="password" placeholder="Password">
          </div>
          <div class="form-group">
            <label class="label" for="password">Repita La Contraseña</label>
            <input type="password" v-model="password2" class="form-control" id="password2" placeholder="Password">
          </div>
        </div class="div-btn-register">
            <button type="submit" class="btn-btn-primary">Registrarse</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
.fondo{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(120deg, #6666ff 0%, #be73cb 100%);
  display: flex;
  align-items: center;
  justify-content: center;
}
.conteiner{
  margin: 0 auto;
  width: 600px;
  padding: 40px;
  background: white;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 1rem;
}
.title{
  text-align: center;
  color: #9216a8;
  margin-bottom: 20px;
}
.form-group{
  position: relative;
  margin-bottom: 25px;
}
.form-control{
  width: 100%;
  height: 35px;
  padding: 5px;
  font-size: 15px;
  background: white;
  color: black;
}
.label{
  display: block;
  font-size: 15px;
  color: #9216a8;
  font-weight: bold;
  margin-bottom: 5px;
}
.fila{
  display: flex;
  gap: 30px;
  margin-left: 10px;
  margin-right: 10px;
}
.btn-btn-primary{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 80%;
  height: 2rem;
  margin: 1rem auto; /* Center horizontally */
  padding: 1rem;
  font-size: 1rem;
  background-color: #9216a8;
  color: white;
  border-radius: 0.5rem;
  border: solid 1px #9216a8;
}
</style>
