import {createRouter, createWebHistory} from 'vue-router';
import Register from './components/register.vue';
import Login from './components/login.vue';
import Home from './components/client/Home.vue';

const routes = [
    {
        path: '/', redirect : '/login'
    },
    {
        path: '/login',
        name : 'login',
        component: Login
    },
    {
        path: '/register',
        name : 'register',
        component: Register
    },
    {
        path: '/home',
        name : 'home',
        component: Home
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;