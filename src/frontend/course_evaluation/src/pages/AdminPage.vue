<template>
  <div>
    <v-container>
      <v-card>
        <v-card-title>
          用户管理
          <v-spacer></v-spacer>
          <v-text-field
              v-model="roleView.keyword"
              append-icon="mdi-magnify"
              label="搜索"
              single-line
              hide-details
          ></v-text-field>
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
                                <v-icon color="error">mdi-delete</v-icon>
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
                              <v-icon color="error">mdi-delete</v-icon>
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
                              <v-icon color="error">mdi-delete</v-icon>
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
              <v-toolbar flat>
                <v-spacer></v-spacer>
                <!-- 添加/编辑 -->
                <v-dialog
                    v-model="courseView.dialog.editDialog"
                    max-width="500px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                        color="primary"
                        dark
                        class="mb-2"
                        v-bind="attrs"
                        v-on="on"
                        @click="openAddCourse"
                    >添加课程
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="text-h5"> 课程管理 </span>
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
                            <v-select
                                v-model="courseView.editedItem.teacherId"
                                :items="roleView.teacherDisplayList"
                                item-text="name"
                                item-value="id"
                                label="任课老师"
                            >
                            </v-select>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                          color="blue darken-1"
                          text
                          @click="saveCourse"
                      >
                        保存
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-toolbar>
            </template>
            <template v-slot:item.actions="{ item }">
              <v-btn icon @click="openResultList(item)">
                <v-icon
                    small
                    color="primary"
                >mdi-folder-open
                </v-icon>
              </v-btn>
              <v-btn icon @click="openEditCourse(item)">
                <v-icon
                    small
                    color="success"
                >mdi-pencil
                </v-icon>
              </v-btn>
              <v-btn icon @click="deleteCourse(item)">
                <v-icon
                    small
                    color="error"
                >mdi-delete
                </v-icon>
              </v-btn>
            </template>
          </v-data-table>
        </v-card-text>
      </v-card>
    </v-container>
    <v-dialog
        v-model="resultView.resultDialog"
        width="640"
    >
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          {{ resultView.selectCourse.title }}
        </v-card-title>

        <v-card-text>

        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="primary"
              text
              @click="resultView.resultDialog = false"
          >关闭
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "AdminPage",
  data: () => ({
    displayHeight: 304,
    roleView: {
      keyword: "",
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
      },
        {
          id: 2,
          idNumber: 2007212,
          name: "许颜杰",
          phone: "19942228763",
          username: "xyj"
        },
        {
          id: 3,
          idNumber: 2007212,
          name: "于一",
          phone: "19942228763",
          username: "yu1"
        },
        {
          id: 4,
          idNumber: 2007212,
          name: "秦品乐",
          phone: "19942228763",
          username: "pyl"
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
          username: "yuan_pi"
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
          description: "j v a v 哈哈哈哈 j v a v",
          teacherId: 3,
          teacherName: "于一",
        },
        {
          id: 1,
          title: "数值分析",
          description: "头疼的课",
          teacherId: 4,
          teacherName: "秦品乐",
        },
        {
          id: 2,
          title: "高等数学",
          teacherId: 4,
          description: "头疼的课",
          teacherName: "abc",
        },
        {
          id: 3,
          title: "线性代数",
          teacherId: 4,
          description: "头疼的课",
          teacherName: "sss",
        },
      ],
      dialog: {
        editDialog: false,
        deleteDialog: false,
      },
      editedItem: {
        id: -1,
        title: "",
        description: "",
        teacherId: -1,
      },
    },
    resultView: {
      resultDialog: false,
      selectCourse: -1,
      resultDict: {
        1: [{
          id: 1,
          praiseId: -1,
          questionIds: [1, 2, 3],
          studentId: 1,
          courseId: 1
        }],
      }
    }
  }),
  methods: {
    openAddCourse: function () {
      this.courseView.editedItem = {
        id: -1,
        title: "",
        description: "",
        teacherId: -1,
      };
    },
    openEditCourse: function (item) {
      // copy item
      this.courseView.editedItem = structuredClone(item);

      this.courseView.dialog.editDialog = true;
    },
    openResultList: function (item) {
      this.resultView.selectCourse = item;
      this.resultView.resultDialog = true;
    },
    saveCourse: async function () {
      const index = this.courseView.courseList.findIndex(
          x => x.id === this.courseView.editedItem.id);
      if (index === -1) {
        // 新增
        const resp = await axios.post("/course");
        console.log(resp);

        this.courseView.editedItem["teacherName"]
            = this.roleView.teacherDisplayList
            .find(x => x.id === this.courseView.editedItem.teacherId)
            .name;

        // noinspection JSCheckFunctionSignatures
        this.courseView.courseList.push(
            this.courseView.editedItem);
      } else {
        // 修改
        const oldItem = this.courseView.courseList[index];
        oldItem.title = this.courseView.editedItem.title;
        oldItem.description = this.courseView.editedItem.description;
        oldItem.teacherId = this.courseView.editedItem.teacherId;
        oldItem.teacherName = this.roleView.teacherDisplayList
            .find(x => x.id === oldItem.teacherId)
            .name;
      }

      this.courseView.dialog.editDialog = false;
    },
    deleteCourse: function (item) {
      this.courseView.courseList.splice(
          this.courseView.courseList.indexOf(item), 1);
    },
  }
}
</script>
