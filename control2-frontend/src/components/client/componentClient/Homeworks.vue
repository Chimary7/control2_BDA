<template>
    <div class="container-homeworks">
        <div class="container-task">
            <p>barra de busqueda y etiquetas</p>
        </div>
        <div class="container-listhomework">
            <div class="homework">
                <p>titulo</p>
                <p>Asignatura</p>
                <p>fecha de entrega</p>
                <input type="checkbox" class="checkbox">
            </div>
        </div>
    </div>
</template>

<style scoped>
.container-homeworks{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    width: 100%;
    color: black;
}

.container-task{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 10%;
    border-bottom: 1px solid #e0e0e0;
    margin: 1rem;
}

.container-listhomework{
    display: flex;
    justify-content: center;
    width: 100%;
    height: 90%;
    margin: 1rem;
}

.container-listhomework .homework{
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

.homework .checkbox{
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