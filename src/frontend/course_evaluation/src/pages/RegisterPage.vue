<template>
  <v-container class="d-flex justify-center">
    <v-card width="600px">
      <v-card-title>注册新的账号</v-card-title>
      <v-card-text>
        <v-form
            ref="form"
            lazy-validation
        >
          <v-text-field
              v-model="form.username"
              label="学号"
              required
          ></v-text-field>
          <v-text-field
              v-model="form.username"
              label="姓名"
              required
          ></v-text-field>
          <v-text-field
              v-model="form.username"
              label="邮箱"
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
            @click="resetValidation"
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
export default {
  name: "RegisterPage",
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
}
</script>

<style scoped>

</style>
