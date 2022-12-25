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
              <v-card min-height="240">
                <v-card-title>
                  管理员
                </v-card-title>
                <v-card-text>
                  <v-sheet height="360" class="overflow-auto">
                    <v-list two-line>
                      <div v-if="roleView.adminDisplayList">
                        <v-list-item
                            v-for="adminItem in roleView.adminDisplayList"
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
                      </div>
                      <div v-else>
                        <v-list-item>
                          <v-list-item-avatar>
                            <v-icon
                                class="primary"
                                dark
                            >mdi-account-off
                            </v-icon>
                          </v-list-item-avatar>

                          <v-list-item-content>
                            <v-list-item-title v-text="'暂无'"></v-list-item-title>
                            <v-list-item-subtitle v-text="'本列表暂无数据可用'"></v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                      </div>
                    </v-list>
                  </v-sheet>
                </v-card-text>
              </v-card>
            </v-col>
            <v-col cols="4">
              <v-card>
                <v-card-title>
                  老师
                </v-card-title>
                <v-card-text>
                  <v-sheet height="360" class="overflow-auto">
                    <v-list two-line>
                      <div v-if="roleView.teacherDisplayList">
                        <v-list-item
                            v-for="teacherItem in roleView.teacherDisplayList"
                            :key="teacherItem.id"
                        >
                          <v-list-item-avatar>
                            <v-icon
                                class="primary"
                                dark
                            >mdi-account-tie
                            </v-icon>
                          </v-list-item-avatar>

                          <v-list-item-content>
                            <v-list-item-title
                                v-text="`${teacherItem.name} (${teacherItem.idNumber})`"></v-list-item-title>

                            <v-list-item-subtitle
                                v-text="`${teacherItem.username}: ${teacherItem.phone}`"></v-list-item-subtitle>
                          </v-list-item-content>

                          <v-list-item-action>
                            <v-btn icon>
                              <v-icon color="warning">mdi-delete</v-icon>
                            </v-btn>
                          </v-list-item-action>
                        </v-list-item>
                      </div>
                      <div v-else>
                        <v-list-item>
                          <v-list-item-avatar>
                            <v-icon
                                class="primary"
                                dark
                            >mdi-account-off
                            </v-icon>
                          </v-list-item-avatar>

                          <v-list-item-content>
                            <v-list-item-title v-text="'暂无'"></v-list-item-title>
                            <v-list-item-subtitle v-text="'本列表暂无数据可用'"></v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                      </div>
                    </v-list>
                  </v-sheet>
                </v-card-text>
              </v-card>
            </v-col>
            <v-col cols="4">
              <v-card>
                <v-card-title>
                  学生
                </v-card-title>
                <v-card-text>
                  <v-sheet height="360" class="overflow-auto">
                    <v-list two-line>
                      <div v-if="roleView.studentDisplayList">
                        <v-list-item
                            v-for="studentItem in roleView.studentDisplayList"
                            :key="studentItem.id"
                        >
                          <v-list-item-avatar>
                            <v-icon
                                class="primary"
                                dark
                            >mdi-account-question
                            </v-icon>
                          </v-list-item-avatar>

                          <v-list-item-content>
                            <v-list-item-title
                                v-text="`${studentItem.name} (${studentItem.idNumber})`"></v-list-item-title>

                            <v-list-item-subtitle
                                v-text="`${studentItem.username}: ${studentItem.phone}`"></v-list-item-subtitle>
                          </v-list-item-content>

                          <v-list-item-action>
                            <v-btn icon>
                              <v-icon color="warning">mdi-delete</v-icon>
                            </v-btn>
                          </v-list-item-action>
                        </v-list-item>
                      </div>
                      <div v-else>
                        <v-list-item>
                          <v-list-item-avatar>
                            <v-icon
                                class="primary"
                                dark
                            >mdi-account-off
                            </v-icon>
                          </v-list-item-avatar>

                          <v-list-item-content>
                            <v-list-item-title v-text="'暂无'"></v-list-item-title>
                            <v-list-item-subtitle v-text="'本列表暂无数据可用'"></v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                      </div>
                    </v-list>
                  </v-sheet>

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
          课程管理
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
    roleView: {
      adminDisplayList: [{
        id: 1,
        name: "康萱琪",
        username: "admin"
      },
        {
          id: 2,
          name: "于一",
          username: "yu1"
        }],
      teacherDisplayList: [{
        id: 1,
        idNumber: 2007112,
        name: "柴锐",
        phone: "19945458763",
        username: "cherry"
      }],
      studentDisplayList: [{
        id: 1,
        idNumber: 2007040134,
        name: "丁燕伟",
        phone: "19945458763",
        username: "dyw"
      },
        {
          id: 2,
          idNumber: 2007040124,
          name: "郭忠昊",
          phone: "19945458763",
          username: "yuanpi"
        }],
    },
    courseView: {
      courseHeader: [
        'id', '课程名'
      ],
      courseList: [
        {
          id: 0,
          title: "Java程序设计",
          description: "jvav哈哈哈哈jvav",
          teacherName: "于一",
        }
      ]
    }
  }),
  methods: {}
}
</script>

<style scoped>

</style>
