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
            {
                path:'login',
                name:'login',
                component:() => import('@/components/userController/login.vue')
            },
            {
                path:'register',
                name:'register',
                component:() => import('@/components/userController/register.vue')
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router