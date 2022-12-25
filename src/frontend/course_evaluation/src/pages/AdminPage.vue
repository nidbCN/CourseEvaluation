<template>
  <div>
    <v-container>
      <v-card>
        <v-card-title>
          用户管理
        </v-card-title>
        <v-card-text>
          <v-row>
            <v-col cols="4">
              <v-card>
                <v-card-title>
                  管理员
                </v-card-title>
                <v-card-text>
                  <v-list two-line>
                    <v-list-item
                        v-for="adminItem in adminList"
                        :key="adminItem.id"
                    >
                      <v-list-item-avatar>
                        <v-icon
                            class="primary"
                            dark
                        >mdi-account-cog
                        </v-icon>
                      </v-list-item-avatar>

                      <v-list-item-content>
                        <v-list-item-title v-text="adminItem.name"></v-list-item-title>

                        <v-list-item-subtitle v-text="adminItem.username"></v-list-item-subtitle>
                      </v-list-item-content>

                      <v-list-item-action>
                        <v-btn icon>
                          <v-icon color="warning">mdi-delete</v-icon>
                        </v-btn>
                      </v-list-item-action>
                    </v-list-item>
                  </v-list>
                </v-card-text>
              </v-card>
            </v-col>
            <v-col cols="4">
              <v-card>
                <v-card-title>
                  老师
                </v-card-title>
                <v-card-text>
                  <v-list two-line>
                    <v-list-item
                        v-for="teacherItem in teacherList"
                        :key="teacherItem.id"
                    >
                      <v-list-item-avatar>
                        <v-icon
                            class="primary"
                            dark
                        >mdi-account-cog
                        </v-icon>
                      </v-list-item-avatar>

                      <v-list-item-content>
                        <v-list-item-title v-text="`${teacherItem.name} (${teacherItem.sex}, ${teacherItem.age})`"></v-list-item-title>

                        <v-list-item-subtitle v-text="`${teacherItem.username}: ${teacherItem.idNumber}`"></v-list-item-subtitle>
                      </v-list-item-content>

                      <v-list-item-action>
                        <v-btn icon>
                          <v-icon color="warning">mdi-delete</v-icon>
                        </v-btn>
                      </v-list-item-action>
                    </v-list-item>
                  </v-list>
                </v-card-text>
              </v-card>
            </v-col>
            <v-col cols="4">
              <v-card>
                <v-card-title>
                  学生
                </v-card-title>
                <v-card-text>

                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
    </v-container>
    <v-container>
      <v-card>
        <v-card-title>
          用户管理
        </v-card-title>
        <v-card-text>

        </v-card-text>
      </v-card>
    </v-container>
  </div>

</template>

<script>
export default {
  name: "AdminPage",
  data: () => ({
    adminList: [{
      id: 1,
      name: "康萱琪",
      username: "admin"
    },
      {
        id: 2,
        name: "于一",
        username: "yu1"
      }],
    teacherList: [{
      id: 1,
      idNumber: 2007112,
      name: "柴",
      phone: "19945458763",
      sex: "男",
      age: 30,
      username: "cherry"
    }]
  })
}
</script>

<style scoped>

</style>
