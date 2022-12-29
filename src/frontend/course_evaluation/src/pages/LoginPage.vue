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
              v-model="form.account"
              label="账号"
              :rules="[form.rules.required]"
          ></v-text-field>

          <v-text-field
              v-model="form.password"
              label="密码"
              type="password"
              :rules="[form.rules.required]"
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
import {adminPage, studentPage} from "@/router";
import requestHelper from "@/request";
import storage from "@/storage";
import rules from '@/util/validate'

export default {
  name: "LoginPage",
  data: () => ({
    form: {
      rules: rules,
      role: '学生',
      account: '',
      password: ''
    },
    options: {
      items: ['学生', '管理员']
    }
  }),
  methods: {
    handleReset: function () {
      this.form = {
        role: '学生',
        account: '',
        password: ''
      }
    },
    handleLogin: async function () {
      if (this.form.role === "学生") {
        const resp = await requestHelper.post("/student/session", this.form);

        if (resp['status'] === 200) {
          // 登录成功
          storage['role'] = "student";
          storage['info'] = resp['data'];
          await this.$router.push({name: studentPage.name})
        } else {
          alert("登录失败" + JSON.stringify(resp));
        }
      } else if (this.form.role === "管理员") {
        const resp = await requestHelper.post("/admin/session", this.form);
        if (resp['status'] === 200) {
          // 登录成功
          storage['role'] = "admin";
          storage['info'] = resp['data'];
          await this.$router.push({name: adminPage.name})
        } else {
          alert("登录失败" + JSON.stringify(resp));
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
