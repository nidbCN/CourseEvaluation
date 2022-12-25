<template>
  <v-app>
    <div class="overflow-hidden">
      <nav-bar title="学生评教系统"/>
      <v-main>
        <div style="margin-top: 160px">
          <transition>
            <router-view></router-view>
          </transition>
        </div>
      </v-main>
    </div>
    <v-footer
        color="primary"
        padless
    >
      <v-row
          justify="center"
          no-gutters
      >
        <v-col
            class="primary py-4 text-center white--text"
            cols="12"
        >
          <p>2017 - {{ new Date().getFullYear() }} <strong>Gaein nidb | Gaein.cn</strong></p>
          <v-btn target='_blank' color='white' text href='https://beian.miit.gov.cn/'>
            <v-icon color='green'>mdi-shield-star</v-icon>
            冀ICP备17015375-1号
          </v-btn>
        </v-col>
      </v-row>
    </v-footer>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar.vue";

export default {
  name: 'App',

  components: {
    NavBar,
  },

  data: () => ({}),
};
</script>
