import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from "@/components/HelloWorld.vue";
import LoginPage from "@/pages/LoginPage.vue";
import RegisterPage from "@/pages/RegisterPage.vue";
import AdminPage from "@/pages/AdminPage.vue";
import StudentPage from "@/pages/StudentPage.vue";

Vue.use(VueRouter)

const homePage = {
    path: '/',
    name: 'homePage',
    component: HelloWorld
}
const loginPage = {
    path: '/login',
    name: 'loginPage',
    component: LoginPage
}
const registerPage = {
    path: '/register',
    name: 'registerPage',
    component: RegisterPage
}
const adminPage = {
    path: '/admin',
    name: 'adminPage',
    component: AdminPage
}
const studentPage = {
    path: '/student',
    name: 'studentPage',
    component: StudentPage
}

const routes = [
    homePage,
    loginPage,
    registerPage,
    adminPage,
    studentPage
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
export {homePage, loginPage, registerPage, adminPage, studentPage}
