<script setup>
  import { ref, computed } from 'vue'
  import {useRouter} from 'vue-router';
  import {createTask} from "../../../Services/TaskService.js";
  import {useStore} from "vuex";

  const router = useRouter();
  const store = useStore();
  const user = computed(() => store.getters.getUser);
  const anio = ref('')
  const mes = ref('')
  const dia = ref('')
  const nombre = ref('')
  const descripcion = ref('')

  const crearTarea = async () => {
      console.log('crearTarea')
      if(nombre.value === '' || descripcion.value === '' || anio.value === '' || mes.value === '' || dia.value === ''){
        alert('Todos los campos son obligatorios')
        return
      }
      console.log(anio.value<20)
      console.log(anio.value.length)
      if(anio.value.length !== 4 && anio.value<1900 && anio.value>2024){
        alert('El año debe estar dentro de rango')
        return
      }
      if(mes.value.length !== 2 && mes.value>0 && mes.value>13){
        alert('El mes debe estar dentro de rango')
        return
      }
      if(dia.value.length !== 2 && dia.value<0 && dia.value>31){
        alert('El dia debe estar dentro de rango')
        return
      }
      if(mes.value < 1 || mes.value > 12){
        alert('El mes debe estar dentro de rango')
        return
      }
      if(dia.value < 1 || dia.value > 31){
        alert('El dia debe estar entre 1 y 31')
        return
      }
      const data = {
        id_usuario: user.value.id_user,
        nombre: nombre.value,
        descripcion: descripcion.value,
        fecha_vencimiento: `${anio.value}-${mes.value}-${dia.value}T00:00:00.00`,
        estado: false,
      }
      console.log(data)

      // Se hace la petición POST al servidor
      const response = await createTask(data)
      if (response.status === 201) {
        console.log('Tarea creada correctamente')
        router.push({ name: 'homework' })
      } else {
        alert('Error al crear la tarea')
      }
  }

</script>

<template>
  <div class="fondo">
    <div class="Contenedor">
      <h1 class="title">Crear Tarea</h1>
      <div class="div">
        <label class="label" for="nombre">Nombre</label>
        <input class="campo" type="text" v-model="nombre" id="nombre" placeholder="Estudiar Calculo III" style="">
      </div>
      <h3 style="color: #9216a8; margin-bottom: 0px;">Fecha de entrega</h3>
      <div class="fecha">
        <div class="div">
          <label class="label" for="anio">Año</label>
          <input class="campo" type="text" v-model="anio" id="anio" placeholder="YYYY" min="1900" max="2100" @input="anio = anio.replace(/[^\d]/g, '')">
        </div>
        <div class="div">
          <label class="label" for="mes">Mes</label>
          <input class="campo" type="text" v-model="mes" id="mes" placeholder="MM" @input="mes = mes.replace(/[^\d]/g, '')">
        </div>
        <div class="div">
          <label class="label" for="dia">Día</label>
          <input class="campo" type="text" v-model="dia" id="dia" placeholder="DD" min="1" max="31" @input="dia = dia.replace(/[^\d]/g, '')">
        </div>
      </div>
      <div class="div">
        <label class="label" for="descripcion">Descripcion</label>
        <textarea class="text" v-model="descripcion" id="descripcion" placeholder="Estudiar Derivadas parciales en conjunto con direccionales"/>
      </div>
      <div>
        <button @click="crearTarea" style="margin-top: 10px; background: #9216a8; margin-left: 5px;">Crear Tarea</button>
      </div>

    </div>
  </div>
</template>

<style scoped>
.fondo {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  background: linear-gradient(120deg, #6666ff 0%, #be73cb 100%);
}

.Contenedor{
  margin: 0 auto;
  width: 600px;
  padding: 50px;
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

  font-size: 2.5rem;
}

.campo{
  background-color: white;
  color: black;
  font-size: 1rem;
  padding: 0.1rem;
  width: 80%;
  height: 1rem;
}

.text{
  background-color: white;
  color: black;
  height: 150px;
  width: 400px;
  font-size: 1rem;
}

.label {
  display: block; /* Asegura que el label ocupe toda la línea y esté encima del campo */
  margin-bottom: 0.5em; /* Espacio entre el label y el campo */
  font-weight: bold; /* Para que el label sea más visible */
  color: #9216a8;
}

.campo[type="number"]::-webkit-outer-spin-button,
.campo[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.campo[type="number"] {
  -moz-appearance: textfield; /* Para Firefox */
  appearance: none; /* Para otros navegadores */
}


.fecha {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
}

.fecha .div {
  flex: 1;
  margin-right: 10px;
}

.fecha .div:last-child {
  margin-right: 0;
}

</style>