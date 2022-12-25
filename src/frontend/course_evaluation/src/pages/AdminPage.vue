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
                      <div v-if="roleView.adminDisplayList && roleView.adminDisplayList.length !== 0">
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
                  <v-spacer></v-spacer>
                  <v-btn @click="openAddRole('老师')" icon>
                    <v-icon>mdi-plus</v-icon>
                  </v-btn>
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
                                v-text="teacherItem.phone"></v-list-item-subtitle>
                          </v-list-item-content>

                          <v-list-item-action>
                            <v-btn @click="removeTeacher(teacherItem.id)" icon>
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
                  <v-spacer></v-spacer>
                  <v-btn @click="openAddRole('学生')" icon>
                    <v-icon>mdi-plus</v-icon>
                  </v-btn>
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
                                v-text="studentItem.phone"></v-list-item-subtitle>
                          </v-list-item-content>

                          <v-list-item-action>
                            <v-btn @click="removeStudent(studentItem.id)" icon>
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

      <v-dialog v-model="roleView.dialog.addDialog" max-width="500px">
        <v-card>
          <v-card-title>新建角色</v-card-title>
          <v-card-text>
            <v-form>
              <v-row>
                <v-col cols="6">
                  <v-text-field
                      label="学工号"
                      v-model="roleView.newItem.idNumber">
                  </v-text-field>
                </v-col>
                <v-col cols="6">
                  <v-text-field
                      label="姓名"
                      v-model="roleView.newItem.name">
                  </v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-text-field
                      label="手机"
                      v-model="roleView.newItem.phone">
                  </v-text-field>
                </v-col>
                <v-col v-if="roleView.newItem.role === '学生'" cols="6">
                  <v-text-field
                      type="password"
                      label="密码"
                      v-model="roleView.newItem.password">
                  </v-text-field>
                </v-col>
              </v-row>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" @click="addRole">保存</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

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
              :items="courseView.courseDisplayList"
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
                      <v-container>
                        <v-row>
                          <v-col cols="3">
                            学生列表
                          </v-col>
                          <v-col offset="2" cols="4">
                            <v-select v-model="courseView.editedStudentSelect"
                                      item-value="id"
                                      item-text="name"
                                      :items="roleView.studentDisplayList">

                            </v-select>
                          </v-col>
                          <v-col cols="2">
                            <v-btn icon
                                   @click="addStudentToCourse">
                              <v-icon>mdi-plus</v-icon>
                            </v-btn>
                          </v-col>
                        </v-row>

                        <v-row>
                          <v-data-table
                              :items="courseView.editedStudentList"
                              :headers="courseView.editedStudentHeader"
                          >
                          </v-data-table>
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
          <evaluation-table :course-id="resultView.selectCourse.id"/>
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
import EvaluationTable from "@/components/EvaluationTable.vue";
import requestHelper from "@/request";

export default {
  name: "AdminPage",
  components: {EvaluationTable},
  data: () => ({
    displayHeight: 304,
    roleView: {
      keyword: "",
      adminDisplayList: [
        // {
        //   id: 1,
        //   name: "系统管理",
        //   account: "admin"
        // },
        // {
        //   id: 2,
        //   name: "于一",
        //   account: "yu1"
        // }
      ],
      teacherDisplayList: [
        // {
        //   id: 1,
        //   idNumber: 2007112,
        //   name: "柴锐",
        //   phone: "19945458763",
        //   account: "cherry"
        // },
        // {
        //   id: 2,
        //   idNumber: 2007212,
        //   name: "许颜杰",
        //   phone: "19942228763",
        //   account: "xyj"
        // },
        // {
        //   id: 3,
        //   idNumber: 2007212,
        //   name: "于一",
        //   phone: "19942228763",
        //   account: "yu1"
        // },
        // {
        //   id: 4,
        //   idNumber: 2007212,
        //   name: "秦品乐",
        //   phone: "19942228763",
        //   account: "pyl"
        // }
      ],
      studentDisplayList: [
        // {
        //   id: 1,
        //   idNumber: 2007040134,
        //   name: "丁燕伟",
        //   phone: "19945458763",
        //   account: "dyw"
        // },
        // {
        //   id: 2,
        //   idNumber: 2007040124,
        //   name: "郭忠昊",
        //   phone: "19945458763",
        //   account: "yuan_pi"
        // }
      ],
      newItem: {
        idNumber: "",
        name: "",
        phone: ""
      },
      dialog: {
        addDialog: false
      }
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
      courseDisplayList: [
        // {
        //   id: 0,
        //   title: "Java程序设计",
        //   description: "j v a v 哈哈哈哈 j v a v",
        //   teacherId: 3,
        //   teacherName: "于一",
        // },
        // {
        //   id: 1,
        //   title: "数值分析",
        //   description: "头疼的课",
        //   teacherId: 4,
        //   teacherName: "秦品乐",
        // },
        // {
        //   id: 2,
        //   title: "高等数学",
        //   teacherId: 4,
        //   description: "头疼的课",
        //   teacherName: "abc",
        // },
        // {
        //   id: 3,
        //   title: "线性代数",
        //   teacherId: 4,
        //   description: "头疼的课",
        //   teacherName: "sss",
        // },
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
        studentIds: [],
      },
      editedStudentSelect: -1,
      editedStudentList: [],
      editedStudentHeader: [
        {text: '序号', value: 'id'},
        {text: '姓名', value: 'name'}
      ]
    },
    resultView: {
      resultHeader: [],
      resultDialog: false,
      selectCourse: -1,
    }
  }),
  methods: {
    fetchAdmin: async function () {
      const resp = await requestHelper.get("/admin");
      this.roleView.adminDisplayList = resp['data'];
    },
    fetchTeacher: async function () {
      const resp = await requestHelper.get("/teacher");
      this.roleView.teacherDisplayList = resp['data'];

      return resp['data'];
    },
    fetchStudent: async function () {
      const resp = await requestHelper.get("/student");
      this.roleView.studentDisplayList = resp['data'];
    },
    fetchCourse: async function () {
      for (const teacher of this.roleView.teacherDisplayList) {
        const resp = await requestHelper.get(`/course/teacher/`, {
          params: {
            teacherId: teacher.id
          }
        });

        const courseItem = resp['data'];
        courseItem.forEach(e => e['teacherName'] = teacher.name);

        console.log(courseItem);

        this.courseView.courseDisplayList = this.courseView.courseDisplayList.concat(courseItem);
      }
    },
    openAddCourse: function () {
      this.courseView.editedItem = {
        id: -1,
        title: "",
        description: "",
        teacherId: -1,
        studentIds: []
      };

      this.courseView.editedStudentList = [];
      this.courseView.editedStudentSelect = -1;
    },
    openEditCourse: function (item) {
      // copy item
      this.courseView.editedItem = structuredClone(item);

      this.courseView.editedStudentList = this.courseView.editedItem.studentIds.map(id => {
        for (const studentItem of this.roleView.studentDisplayList) {
          if (studentItem.id === id) {
            return {
              id: id,
              name: studentItem.name
            }
          }
        }
      });

      this.courseView.dialog.editDialog = true;
    },
    openResultList: function (item) {
      this.resultView.selectCourse = item;
      this.resultView.resultDialog = true;
    },
    saveCourse: async function () {
      const index = this.courseView.courseDisplayList.findIndex(
          x => x.id === this.courseView.editedItem.id);
      if (index === -1) {
        // 新增
        const resp = await requestHelper.post("/course", {
          description: this.courseView.editedItem.description,
          title: this.courseView.editedItem.title,
          teacherId: this.courseView.editedItem.teacherId
        });

        if (resp['status'] >= 300) {
          alert("新增失败" + JSON.stringify(resp));
        }

        const courseItem = resp['data'];
        courseItem["teacherName"]
            = this.roleView.teacherDisplayList
            .find(x => x.id === courseItem.teacherId)
            .name;

        this.courseView.courseDisplayList.push(
            courseItem);
      } else {
        // 修改
        const oldItem = this.courseView.courseDisplayList[index];
        oldItem.title = this.courseView.editedItem.title;
        oldItem.description = this.courseView.editedItem.description;
        oldItem.teacherId = this.courseView.editedItem.teacherId;
        oldItem.teacherName = this.roleView.teacherDisplayList
            .find(x => x.id === oldItem.teacherId)
            .name;
      }

      this.courseView.dialog.editDialog = false;
    },
    deleteCourse: async function (item) {
      const resp = await requestHelper.delete(`/course/${item.id}`);

      if (resp['status'] >= 300) {
        alert("删除失败" + JSON.stringify(resp))
      }

      this.courseView.courseDisplayList.splice(
          this.courseView.courseDisplayList.indexOf(item), 1);
    },
    addStudentToCourse: async function () {
      const studentId = this.courseView.editedStudentSelect;

      console.log(this.courseView.editedStudentList.find(x => x.id == studentId))
      if (this.courseView.editedStudentList.findIndex(x => x.id == studentId) === -1) {
        // 无重复
        const resp = await requestHelper.post(`/course/${this.courseView.editedItem.id}/student?studentId=${studentId}`);
        if (resp['status'] >= 300) {
          alert("添加错误" + JSON.stringify(resp));
        } else {
          const student = {
            id: studentId,
            name: this.findStudentNameById(studentId),
          };

          this.courseView.editedStudentList.push(student);
        }
      }
    },
    openAddRole: function (role) {
      this.roleView.dialog.addDialog = true;
      this.roleView.newItem = {
        role: role,
        idNumber: "",
        name: "",
        phone: "",
        password: ""
      };
    },
    addRole: async function () {
      if (this.roleView.newItem.role === "老师") {
        const resp = await requestHelper.post("/teacher", this.roleView.newItem);
        if (resp['status'] >= 300) {
          alert("新建错误" + JSON.stringify(resp));
        } else {
          this.roleView.teacherDisplayList.push(resp['data']);
        }
      } else if (this.roleView.newItem.role === "学生") {
        const resp = await requestHelper.post("/student", this.roleView.newItem);
        if (resp['status'] >= 300) {
          alert("新建错误" + JSON.stringify(resp));
        } else {
          this.roleView.studentDisplayList.push(resp['data']);
        }
      }

      this.roleView.dialog.addDialog = false;
    },
    removeTeacher: async function (id) {
      const resp = await requestHelper.delete(`/teacher/${id}`);
      if (resp['status'] >= 300) {
        alert("删除失败" + JSON.stringify(resp));
      } else {
        this.roleView.teacherDisplayList.splice(
            this.roleView.teacherDisplayList.findIndex(x => x.id === id), 1);
      }
    },
    removeStudent: async function (id) {
      const resp = await requestHelper.delete(`/student/${id}`);
      if (resp['status'] >= 300) {
        alert("删除失败" + JSON.stringify(resp));
      } else {
        this.roleView.studentDisplayList.splice(
            this.roleView.studentDisplayList.findIndex(x => x.id === id), 1);
      }
    },
    findStudentNameById: function (id) {
      for (const student of this.roleView.studentDisplayList) {
        if (student.id == id) {
          return student.name;
        }
      }
    }
  },
  async mounted() {
    await this.fetchAdmin();
    this.fetchTeacher()
        .then((resp) => {
          console.log(resp);
          this.fetchCourse()
        });
    await this.fetchStudent();
  }
}
</script>
