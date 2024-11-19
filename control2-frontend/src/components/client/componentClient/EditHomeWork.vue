<script setup>
  import {onMounted, ref} from 'vue'
  import {useRouter} from 'vue-router';
  import {updateTask} from "../../../Services/TaskService.js";
  import {deleteTask} from "../../../Services/TaskService.js";
  import {getTaskById} from "../../../Services/TaskService.js";

  const router = useRouter();
  const id_tarea = router.currentRoute.value.params.id;
  const id_usuario = ref('')
  const anio = ref('')
  const mes = ref('')
  const dia = ref('')
  const nombre = ref('')
  const descripcion = ref('')
  const idTarea = ref('')


  onMounted(async () => {
    // Se obtiene la tarea a editar
    const tarea = await getTaskById(id_tarea);
    const fecha = tarea.fecha_vencimiento.split('T')[0].split('-')
    anio.value = fecha[0]
    mes.value = fecha[1]
    dia.value = fecha[2]
    nombre.value = tarea.nombre
    descripcion.value = tarea.descripcion
    id_usuario.value = tarea.id_usuario
  })

  const eliminar = () => {
    console.log('eliminar')
    const response = deleteTask(idTarea.value);
    if (response.status === 201) {
      console.log('Tarea eliminada correctamente')
      router.push({ name: 'homework' })
    } else {
      alert('Error al eliminar la tarea')
    }
    router.push({ name: 'homework' })
  }

  const EditarTarea = async () => {
      // Se valida que los campos no estén vacíos
      if(nombre.value === '' || descripcion.value === '' || anio.value === '' || mes.value === '' || dia.value === ''){
        alert('Todos los campos son obligatorios')
        return
      }

      // Se valida que la fecha sea válida
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

      // Se valida que el mes y el día sean válidos
      const data = {
        id_tarea: id_tarea,
        id_usuario: id_usuario.value,
        nombre: nombre.value,
        descripcion: descripcion.value,
        fecha_vencimiento: `${anio.value}-${mes.value}-${dia.value}T00:00:00.00`,
        estado: false,
      }
      console.log(data)

      // Se hace la petición PUT al servidor
      const response = await updateTask(data)
      if (response.status === 201) {
        console.log('Tarea editada correctamente')
        router.push({ name: 'homework' })
      } else {
        alert('Error al editar la tarea')
      }
  }
</script>

<template>
  <div class="fondo">
    <div class="Contenedor">
      <h1 class="title">Editar Tarea</h1>
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
        <button @click="eliminar" style="margin-top: 10px; background: #9216a8; width: 130px;">Eliminar</button>
        <button @click="EditarTarea" style="margin-top: 10px; background: #9216a8; margin-left: 5px;">Editar Tarea</button>
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