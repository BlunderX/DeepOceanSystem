import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {
        path: '/',
        component: () => import('@/view/index.vue'),
        name:'index'
    },
    {
        path: '/user',
        component: () => import('@/view/user.vue'),
        name:'user',
        children: [
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router