<template>
  <div>
    <v-container>
      <v-card>
        <v-card-title>我的评教</v-card-title>
        <v-card-text>
          <div v-if="taskView.taskDisplayList && taskView.taskDisplayList.length !== 0">
            <v-list-item-group>
              <v-list-item
                  v-for="taskItem in taskView.taskDisplayList"
                  :key="taskItem.id"
              >
                <v-list-item-avatar>
                  <v-icon
                      class="primary"
                      dark
                  >mdi-notebook-edit
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title v-text="taskItem.title"></v-list-item-title>
                  <v-list-item-subtitle v-text="`共 ${taskItem.questionCount} 个问题`"></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn @click="openDialog(taskItem)" icon>
                    <v-icon color="success">mdi-arrow-right-bold-box-outline</v-icon>
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list-item-group>
          </div>
          <div v-else>
            太好了，你已经完成了全部评教任务
          </div>
        </v-card-text>
      </v-card>
    </v-container>
    <v-dialog v-if="taskView.dialogView.selectTask" v-model="taskView.dialog" width="600">
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          {{ taskView.dialogView.selectTask.course.title }}
        </v-card-title>

        <v-card-text>
          <v-list>
            <v-row v-for="(question, i) in taskView.form.questionList"
                   :key="i">
              <v-col cols="4">
                <v-subheader>{{ question.content }}</v-subheader>
              </v-col>
              <v-col cols="8">
                <v-text-field
                    label="评分"
                    :rules="[taskView.form.rules.scoreRule]"
                    v-model="question.score"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">
                <v-textarea
                    label="评语"
                    :rules="[taskView.form.rules.praiseRule]"
                    v-model="taskView.form.praise.content"
                ></v-textarea>
              </v-col>
            </v-row>
          </v-list>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="primary"
              @click="submitTask"
              text
          >保存
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import storage from "@/storage";
import requestHelper from "@/request";
import {loginPage} from "@/router";

export default {
  name: "StudentPage",
  data: () => ({
    taskView: {
      taskDisplayList: [
        // {
        //   id: 1,
        //   title: "数值分析 评教任务",
        //   questionCount: 10,
        // }
      ],
      taskList: [
        // {
        //   id: 1,
        //   praise: {
        //     id: 1,
        //     content: "老师很好"
        //   },
        //   questions: [{
        //     id: 2,
        //     content: "问题id2",
        //     score: 99
        //   }, {}, {}],
        //   student: {},
        //   course: {
        //     // 不使用
        //     title: "数值分析"
        //   }
        // }
      ],
      form: {
        rules: {
          scoreRule: value => (value >= 0 && value <= 100),
          praiseRule: value => (value)
        },
        questionList: [
          {content: "PPT 的设计与制作", score: 0}, {content: "教学工具", score: 0},
          {content: "教材及参考资料", score: 0}, {content: "教案", score: 0},
          {content: "PPT的使用与板书", score: 0}, {content: "为人师表", score: 0},
          {content: "学生出勤与课堂纪律", score: 0}, {content: "对课堂的把控能力", score: 0},
          {content: "教学方法", score: 0}, {content: "案例教学或习题课", score: 0},
          {content: "讲授内容", score: 0}, {content: "讲授进度", score: 0},
          {content: "语言表达", score: 0}, {content: "课程思政", score: 0},
          {content: "持续改进", score: 0}, {content: "辅导答疑", score: 0},
          {content: "作业内容", score: 0}, {content: "作业布置", score: 0},
          {content: "作业批改", score: 0}, {content: "考核内容", score: 0},
          {content: "考核方式", score: 0}],
        praise: {
          content: ""
        }
      },

      dialog: false,
      dialogView: {
        selectTask: null,
      }
    }
  }),
  methods: {
    openDialog: function (item) {
      this.taskView.dialogView.selectTask = this.taskView.taskList
          .find(x => x.id === item.id);
      this.taskView.dialog = true;
    },
    fetchTasks: async function (studentId) {
      console.log(studentId);
      const resp = await requestHelper.get(`/evaluation/student/${studentId}`);
      this.taskView.taskList = resp['data'];

      for (const task of this.taskView.taskList) {
        if (task.praise) {
          continue;
        }

        this.taskView.taskDisplayList.push({
          id: task.id,
          title: `${task.course.title} 评教任务`,
          questionCount: this.taskView.form.questionList.length
        });
      }
    },
    submitTask: async function () {
      const selectEvaluationId = this.taskView.dialogView.selectTask.id;

      // 提交所有问题
      for (const question of this.taskView.form.questionList) {
        await requestHelper.post("/question", {
          content: question.content,
          score: question.score,
          evaluationId: selectEvaluationId
        });
      }

      // 提交评语
      await requestHelper.post("/praise", {
        content: this.taskView.form.praise.content,
        evaluationId: selectEvaluationId
      });

      this.taskView.dialog = false;
      // 重置项
      this.taskView.dialogView.selectTask = null;
      this.taskView.form.questionList.forEach(e => e.score = 0);
      this.taskView.form.praise = "";

      this.taskView.taskList.splice(
          this.taskView.taskList.findIndex(x => x.id === this.taskView.dialogView.selectTask.id), 1
      )
    }
  },
  mounted() {
    if (storage['role'] === "admin") {
      this.$router.push({name: loginPage.name});
    }
    this.fetchTasks(storage['info']['id']);
  }
}
</script>
