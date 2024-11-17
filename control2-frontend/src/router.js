import {createRouter, createWebHistory} from 'vue-router';
import Register from './components/register.vue';
import Login from './components/login.vue';
import Home from './components/client/Home.vue';
import Homeworks from './components/client/componentClient/Homeworks.vue';
import CreateHomeWork from "./components/client/componentClient/CreateHomeWork.vue";

const routes = [
    {
        path: '/', redirect : '/CreateHomework'
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
        component: Home,
        children: [
            {
                path: 'homework',
                name : 'homework',
                component: Homeworks
            }
        ]
    },
    {
        path: '/CreateHomework',
        name : 'CreateHomework',
        component: CreateHomeWork,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;