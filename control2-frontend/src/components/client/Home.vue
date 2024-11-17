<script setup>
import Navbar from './componentClient/Navbar.vue';
</script>

<template>

<div class="fondo-home">
    <Navbar />
    <div class="content">
        <router-view />
    </div>
</div>

</template>

<style scoped>
.fondo-home {
    display: flex;
    height: 100%;
    width: 100%;
    background-color: #d2e9f8;
}
.content {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgba(255, 255, 255, 0.75);
    border: 1px solid #e0e0e0;
    border-radius: 1rem;
    margin: 1rem;
    width: calc(100% - 15rem);
}
</style>