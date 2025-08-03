import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {
        path: '/',
        meta:{title:'欢迎访问DeepOS'},
        components:{
            default:() => import('../view/index.vue'),
            header:() => import('../components/public/header.vue'),
            footer:() => import('../components/public/footer.vue')
        }
    },
    {
        path: '/user',
        component: () => import('@/view/user.vue'),
        name:'user',
        redirect: '/user/login',
        children: [
            {
                path:'login',
                name:'login',
                component:() => import('@/components/userController/login.vue'),
                meta:{title:'登录—DeepOS'}
            },
            {
                path:'register',
                name:'register',
                component:() => import('@/components/userController/register.vue'),
                meta:{title:'注册—DeepOS'}
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})
router.beforeEach(async (to) => {
    if (to.meta.title){
        document.title = to.meta.title;
    }
})

export default router