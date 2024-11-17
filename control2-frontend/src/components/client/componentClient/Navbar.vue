<template>
    <div class="container-navbar">
        <div class="container-content">
            <div class="user-profile">
                <svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24" height="24px" viewBox="0 0 24 24" width="24px" fill="#2a60c9"><g><rect fill="none" height="24" width="24"/></g><g><g><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zM7.35 18.5C8.66 17.56 10.26 17 12 17s3.34.56 4.65 1.5c-1.31.94-2.91 1.5-4.65 1.5s-3.34-.56-4.65-1.5zm10.79-1.38C16.45 15.8 14.32 15 12 15s-4.45.8-6.14 2.12C4.7 15.73 4 13.95 4 12c0-4.42 3.58-8 8-8s8 3.58 8 8c0 1.95-.7 3.73-1.86 5.12z"/><path d="M12 6c-1.93 0-3.5 1.57-3.5 3.5S10.07 13 12 13s3.5-1.57 3.5-3.5S13.93 6 12 6zm0 5c-.83 0-1.5-.67-1.5-1.5S11.17 8 12 8s1.5.67 1.5 1.5S12.83 11 12 11z"/></g></g></svg>
                <p>username</p>
            </div>
            <nav>
                <router-link to="/home">
                    <div class="btn-nav">
                        lista de tareas
                    </div>
                </router-link>
                <router-link to="/home">
                    <div class="btn-nav">
                        crear tarea
                    </div>
                </router-link>
            </nav>
            <button class="container-logout">
                Cerrar sesión
            </button>
        </div>
    </div>
</template>

<style scoped>
.container-navbar {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    color: #333;
    height: 96%;
    width: 15rem;
    background-color: white;
    border: 1px solid #e0e0e0;
    border-radius: 1rem;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
    margin: 1rem; /* Espacio alrededor del navbar */
}

.container-navbar .container-content{
    padding: 1rem;
    width: calc(100% - 2rem);
    height: 100%;
}

.container-content .user-profile{
    display: flex;
    align-items: center;
    justify-content: center;
    color: #2a60c9;
    font-weight: bold;
    width: 100%;
    height: 3rem;
    border-bottom: 1px solid #e0e0e0;
    margin-bottom: 1rem;
}

.user-profile svg {
    margin-right: 1rem;
}

.container-content nav {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: 100%;
    height: calc(100% - 8rem);
}

nav .btn-nav {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 3rem;
    background-color: white;
    border-radius: 0.5rem;
    border: 1px solid #646cff;
    cursor: pointer;
    transition: background-color 0.3s ease, color 0.3s ease;
}

nav .btn-nav:hover {
    background-color: #646cff;
    color: white;
}

.container-logout {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 3rem;
    width: 100%;
    background-color: #646cff;
    border-radius: 0.5rem;
    margin-top: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.container-logout:hover {
    background-color: white;
    color: #646cff;
}

</style>