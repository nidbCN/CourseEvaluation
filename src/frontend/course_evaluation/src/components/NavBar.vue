<template>
  <div>
    <v-app-bar
        prominent
        absolute
        color="deep-purple"
        dark
        :src="require('@/assets/banner1.webp')"
    >
      <template v-slot:img="{ props }">
        <v-img
            v-bind="props"
            gradient="to right, rgba(0,0,0,0.5), rgba(0,0,0,1)"
        ></v-img>
      </template>
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <h3>{{ title }}</h3>
      <v-spacer/>
      <v-btn
          href="https://github.com/nidbCN"
          target="_blank"
          text
      >
        <span class="mr-2">GitHub</span>
        <v-icon>mdi-github</v-icon>
      </v-btn>
    </v-app-bar>

    <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
    >
      <v-card tile elevation="0" height="100%">
        <v-img
            class="white--text align-end"
            :src="require('@/assets/picture.webp')">
          <v-card-title style="opacity: 0.4" class="black" v-text="title"></v-card-title>
        </v-img>
        <v-card-text height="100%">
          <v-list
              nav
          >
            <v-list-item-group
                active-class="deep-purple--text text--accent-4"
            >
              <v-list-item
                  @click="$router.push({name: storage['role'] === 'admin' ? adminPage.name : studentPage.name}) ">
                <v-list-item-title>首页</v-list-item-title>
              </v-list-item>
              <v-list-item @click="$router.push({name: loginPage.name})">
                <v-list-item-title>退出</v-list-item-title>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card-text>
      </v-card>
    </v-navigation-drawer>
  </div>
</template>

<script>
import {adminPage, loginPage, studentPage} from "@/router";
import storage from "@/storage";

export default {
  name: "NavBar",
  computed: {
    studentPage() {
      return studentPage
    },
    adminPage() {
      return adminPage
    },
    storage() {
      return storage
    },
    loginPage() {
      return loginPage
    }
  },
  props: {
    title: String
  },
  data: () => ({
    drawer: false
  }),
}
</script>

<style scoped>

</style>
