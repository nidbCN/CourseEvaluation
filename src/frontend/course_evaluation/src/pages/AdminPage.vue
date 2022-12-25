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
              <v-card outlined>
                <v-card-title>
                  管理员
                </v-card-title>
                <v-card-text>
                  <v-sheet :height="displayHeight" class="overflow-auto">
                    <v-list two-line>
                      <div v-if="roleView.adminDisplayList">
                        <v-list-item-group>
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
                        </v-list-item-group>
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
              <v-card outlined>
                <v-card-title>
                  老师
                </v-card-title>
                <v-card-text>
                  <v-sheet :height="displayHeight" class="overflow-auto">
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
              <v-card outlined>
                <v-card-title>
                  学生
                </v-card-title>
                <v-card-text>
                  <v-sheet :height="displayHeight" class="overflow-auto">
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
          <v-spacer/>
          <v-text-field
              v-model="courseView.keyword"
              append-icon="mdi-magnify"
              label="搜索"
              single-line
              hide-details
          ></v-text-field>
        </v-card-title>
        <v-card-text>
          <v-data-table
              :height="displayHeight"
              :headers="courseView.courseHeader"
              :items="courseView.courseList"
              :items-per-page="5"
              :search="courseView.keyword"
              sort-by="id"
          >
            <template v-slot:top>
              <v-toolbar
                  flat
              >
                <v-spacer></v-spacer>
                <v-dialog
                    v-model="courseView.dialog.addDialog"
                    max-width="500px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                        color="primary"
                        dark
                        class="mb-2"
                        v-bind="attrs"
                        v-on="on"
                    >
                      添加
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="text-h5"> 添加课程 </span>
                    </v-card-title>

                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col
                              cols="12"
                              sm="6"
                              md="4"
                          >
                            <v-text-field
                                v-model="courseView.editedItem.title"
                                label="课程名"
                            ></v-text-field>
                          </v-col>
                          <v-col
                              cols="12"
                              sm="6"
                              md="4"
                          >
                            <v-text-field
                                v-model="courseView.editedItem.description"
                                label="简介"
                            ></v-text-field>
                          </v-col>
                          <v-col
                              cols="12"
                              sm="6"
                              md="4"
                          >
                            <v-text-field
                                v-model="courseView.editedItem.teacherName"
                                label="任课老师"
                            ></v-text-field>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                          color="blue darken-1"
                          text
                          @click="courseView.dialog.addDialog = false"
                      >
                        Cancel
                      </v-btn>
                      <v-btn
                          color="blue darken-1"
                          text
                          @click="addItem(courseView.editedItem)"
                      >
                        Save
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-toolbar>
            </template>
            <template v-slot:item.actions="{ item }">
              <v-icon
                  small
                  color="primary"
                  class="mr-2"
                  @click="editItem(item)"
              >
                mdi-pencil
              </v-icon>
              <v-icon
                  small
                  color="warning"
                  @click="deleteItem(item)"
              >
                mdi-delete
              </v-icon>
            </template>
          </v-data-table>
        </v-card-text>
      </v-card>
    </v-container>
  </div>

</template>

<script>
export default {
  name: "AdminPage",
  data: () => ({
    displayHeight: 280,
    roleView: {
      adminDisplayList: [{
        id: 1,
        name: "系统管理",
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
      keyword: "",
      courseHeader: [
        {
          text: "序号",
          value: "id"
        }, {
          text: "课程名",
          value: "title"
        }, {
          text: "简介",
          value: "description"
        }, {
          text: "任课老师",
          value: "teacherName"
        }, {
          text: "操作",
          value: "actions"
        }
      ],
      courseList: [
        {
          id: 0,
          title: "Java程序设计",
          description: "jvav哈哈哈哈jvav",
          teacherName: "于一",
        }
      ],
      dialog: {
        addDialog: false,
        deleteDialog: false
      },
      editedItem: {
        title: "",
        description: "",
        teacherName: "",
      }
    }
  }),
  methods: {
    addItem: function (item) {
      this.courseView.courseList.push(item);
    },
    deleteItem: function (item) {
      this.courseView.courseList.unshift(item);
    },
    editItem: function (item) {
      this.courseView.editedItem = item;
    }
  }
}
</script>

