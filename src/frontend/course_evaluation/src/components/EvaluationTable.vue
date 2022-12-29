<template>
  <v-data-table
      :headers="resultHeader"
      :items="resultDisplayList"
      :items-per-page="5"
      :search="keyword"
      sort-by="id"
  ></v-data-table>
</template>

<script>
import requestHelper from "@/request";

export default {
  name: "EvaluationTable",
  props: {
    courseId: {
      type: Number
    }
  },
  data: () => ({
    keyword: "",
    resultHeader: [
      {
        text: "序号",
        value: "id"
      },
      {
        text: "课程名称",
        value: "courseName"
      },
      {
        text: "评语",
        value: "praise"
      },
      {
        text: "问题",
        value: "questionDisplay"
      },
      {
        text: "总分",
        value: "totalScore"
      },
      {
        text: "平均分",
        value: "avgScore"
      },
      {
        text: "学生",
        value: "studentName"
      }
    ],
    resultDisplayList: [{
      id: 1,
      courseName: "数值分析",
      praise: "老师很好",
      questionDisplay: "问题id2: 99\n问题xxx",
      studentName: "康萱琪",
      avgScore: 99,
      totalScore: 990,
    }],
    resultList: [{
      id: 1,
      praise: {
        id: 1,
        content: "老师很好"
      },
      questions: [{
        id: 2,
        content: "问题id2",
        score: 99
      }, {}, {}],
      student: {},
      course: {
        // 不使用
        title: "数值分析"
      }
    }]
  }),
  mounted() {
    this.fetchResult();
  },
  methods: {
    fetchResult: async function () {
      const id = this.courseId;

      const resp = await requestHelper.get(`/evaluation/course/${id}`);
      if (resp['status'] >= 300) {
        alert("请求错误" + JSON.stringify(resp))
      } else {
        this.resultList = resp['data'];

        this.resultDisplayList = this.resultList
            .map(e => {
              const newElement = {
                id: e.id,
                courseName: e.course.title,
                praise: e.praise.content,
                questionDisplay: "",
                studentName: e.student.name,
                avgScore: 0,
                totalScore: 0,
              };

              // 分数计算
              newElement.totalScore = e.questions
                  .map(question => question.score)
                  .reduce((prev, item) => prev + item);
              newElement.avgScore = newElement.totalScore / e.questions.length;

              // 问题显示
              newElement.questionDisplay = e.questions
                  .map(question => `${question.content}: ${question.score}`)
                  .join("\n");

              console.log(newElement)
              return newElement;
            })
      }
    }
  }
}
</script>

<style scoped>

</style>
