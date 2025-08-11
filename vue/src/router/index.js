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
    },{
        path: '/account',
        meta:{title:'账户选项——主页'},
        redirect: '/account/setting',
        //基本组件控制
        components: {
            default:() => import('../view/account/index.vue'),
            header:() => import('../components/public/header.vue'),
            aside:() => import('../components/account/aside.vue'),
        },
        children: [{
            path:'setting',
            name:'setting',
            meta:{title:"账户选项——用户档案设置"},
            component:() => import('@/view/account/setting.vue'),
        },{
            path:'admin',
            name:'admin',
            meta:{title:"管理员——工作台"},
            component:() => import('@/view/account/admin/index.vue'),
            children:[{
                path:'permissions',
                name:'permissions',
                meta:{title:"管理员——用户组"},
                component:() => import('@/view/account/admin/permissions.vue')
            }]
        }]
    }
]
// account

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