<template>
    <div class="container-homeworks">
        <div class="container-task">
            <div class="search-bar-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" fill="#646cff" class="search-icon">
                    <path d="M0 0h24v24H0V0z" fill="none" />
                    <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" />
                </svg>
                <input 
                    v-model="searchQuery" 
                    type="text" 
                    class="search-bar" 
                    placeholder="Buscar tarea por nombre..." 
                />
            </div>
        </div>


        <div class="container-listhomework">
            <div 
                v-for="(task, index) in filteredTasks" 
                :key="task.id" 
                class="homework"
                @Click="goViewTask(task.id)"
            >
                <p>{{ task.nombre }}</p>
                <p>{{ task.descripcion }}</p>
                <p>Vencimiento: {{ formatFecha(task.fecha_vencimiento) }}</p>
                <input 
                    type="checkbox" 
                    class="checkbox" 
                    :checked="task.estado" 
                    @change="toggleEstado(index)" 
                />
            </div>

            <p v-if="filteredTasks.length === 0">No se encontraron tareas.</p>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import {getTasksByIdUser} from '../../../Services/TaskService'

const store = useStore();
const user = computed(() => store.getters.getUser);
const router = useRouter();
const tasks = ref([]);

const searchQuery = ref("");

const formatFecha = (fecha) => {
  return fecha ? fecha.split('T')[0] : '';
};

const filteredTasks = computed(() => {
    return tasks.value.filter(task =>
        task.nombre.toLowerCase().includes(searchQuery.value.toLowerCase())
    );
});


const toggleEstado = (index) => {
    tasks.value[index].estado = !tasks.value[index].estado;
};

const getTasks = async () =>{
    console.log('User:', user.value.id_user);
    const response = await getTasksByIdUser(user.value.id_user);
    if(response.status === 200){
        tasks.value = response.data;
    } else {
        alert("Error al caergar las tareas");
    }
}

const goViewTask = (id) => {
    console.log('Tarea: ' + id);
    router.push({ name: 'ViewTask', params: { id:  id} });
};

onMounted(() => {
    getTasks();
});

</script>

<style scoped>
.container-homeworks {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    width: 100%;
    color: black;
}

.container-task {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 10%;
    border-bottom: 1px solid #e0e0e0;
    margin: 1rem;
}

.search-bar-wrapper {
    position: relative;
    display: flex;
    align-items: center;
    width: 50%;
}

.search-icon {
    position: absolute;
    left: 10px;
    pointer-events: none;
}

.search-bar {
    width: 100%;
    background-color: white;
    padding: 0.5rem 0.5rem 0.5rem 2.5rem; /* Espacio para el icono */
    border: 1px solid #ccc;
    color: #646cff;
    border-radius: 0.5rem;
    outline: none;
    font-size: 1rem;
    transition: all 0.3s ease;
}

.search-bar:focus {
    border-color: #646cff;
    color: #646cff;
    box-shadow: 0 0 5px rgba(100, 108, 255, 0.5);
}


.search-bar:focus {
    border-color: #646cff;
    box-shadow: 0 0 5px rgba(100, 108, 255, 0.5);
}

.container-listhomework {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    width: 100%;
    height: 90%;
    margin: 1rem;
}

.container-listhomework .homework {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 3rem;
    background-color: #646cffc7;
    border: 1px solid #e0e0e0;
    color: white;
    font-weight: bold;
    border-radius: 1rem;
    margin: 2rem;
    padding: 1rem;
}

.homework .checkbox {
    width: 1.7rem;
    height: 1.7rem;
    border: 1px solid #646cff;
    border-radius: 0.5rem;
    appearance: none;
    outline: none;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    background-color: white;
}

.homework .checkbox:checked {
    background-color: #646cff;
    border-color: white;
    color: white;
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%23fff' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3E%3Cpolyline points='20 6 9 17 4 12'/%3E%3C/svg%3E");
    background-repeat: no-repeat;
    background-position: center;
    background-size: 1rem;
}

.homework .checkbox:hover {
    border-color: #646cff;
}
</style>
