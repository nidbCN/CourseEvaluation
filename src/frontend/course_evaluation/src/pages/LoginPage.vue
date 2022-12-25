<template>
  <v-container class="d-flex justify-center">
    <v-card width="600px">
      <v-card-title>登录你的账号</v-card-title>
      <v-card-text>
        <v-form
            ref="form"
            lazy-validation
        >

          <v-select
              v-model="form.role"
              :items="options.items"
              :rules="[v => !!v || '角色为必选']"
              label="角色"
              required
          ></v-select>

          <v-text-field
              v-model="form.username"
              label="账号"
              required
          ></v-text-field>

          <v-text-field
              v-model="form.password"
              label="密码"
              type="password"
              required
          ></v-text-field>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn
            color="warning"
            @click="handleReset"
        >
          重置内容
        </v-btn>

        <v-btn
            color="primary"
            @click="handleLogin"
        >
          登录账号
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import {homePage} from "@/router";
import axios from "axios";

export default {
  name: "LoginPage",
  data: () => ({
    form: {
      role: '学生',
      username: '',
      password: ''
    },
    options: {
      items: ['学生', '老师', '管理员']
    }
  }),
  methods: {
    handleReset: function () {
      this.form = {
        role: '学生',
        username: '',
        password: ''
      }
    },
    handleLogin: async function () {
      const resp = await axios.post("/login", this.form);
      if (resp.status === 200) {
        await this.$router.push({name: homePage.name})
      }
    }
  }
}
</script>

<style scoped>

</style>
