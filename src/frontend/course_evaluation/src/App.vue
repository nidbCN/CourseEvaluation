<template>
  <v-app>
    <div class="overflow-hidden">
      <nav-bar title="学生评教系统"/>
      <v-main>
        <div style="margin-top: 160px">
          以下是 router-view 显示:
          <router-view></router-view>
        </div>
      </v-main>
    </div>
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
