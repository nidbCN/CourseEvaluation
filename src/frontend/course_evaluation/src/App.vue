<template>
  <v-app>
    <div class="overflow-hidden">
      <v-app-bar
          prominent
          absolute
          color="deep-purple"
          dark
          :src="require('./assets/banner1.webp')"
      >
        <template v-slot:img="{ props }">
          <v-img
              v-bind="props"
              gradient="to right, rgba(0,0,0,0.5), rgba(0,0,0,1)"
          ></v-img>
        </template>
        <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

        <h3>学生评教系统</h3>
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
        <v-list
            nav
            dense
        >
          <v-list-item-group
              v-model="group"
              active-class="deep-purple--text text--accent-4"
          >
            <v-list-item v-for="(menuItem,i) in menu.menu" :key="i">
              <v-list-item-icon>
                <v-icon>{{ menuItem.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>{{ menuItem.title }}</v-list-item-title>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-navigation-drawer>
      <v-main>
        <div style="margin-top: 160px">

        </div>
        <HelloWorld/>
      </v-main>
    </div>
  </v-app>
</template>

<script>
import Menu from './util/menu'
import HelloWorld from './components/HelloWorld';

export default {
  name: 'App',

  components: {
    HelloWorld,
  },

  data: () => ({
    menu: Menu,
    drawer: false
  }),
};
</script>
