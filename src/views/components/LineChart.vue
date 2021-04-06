<script>
  //Importing Line class from the vue-chartjs wrapper
  import { Line } from 'vue-chartjs'
  import Axios from 'axios';  

  //Exporting this so it can be used in other components
  export default {
    extends: Line,
    created() {
      this.$store.commit("dayBefore",0);
      this.requestEndDateOption = this.$store.state.dateOption; // 조회조건에서 현재 날짜 
      this.$store.commit("dayBefore",7);
      this.requestStartDateOption = this.$store.state.dateOption; // 조회조건이서 현재보다 -7 일

      this.search();
    },
    data () {
      return {
        datacollection: {
          //Data to be represented on x-axis
          labels: [],
          datasets: [
            {
              label: '발주수량', // 그래프가 어떤 내용을 뜻하는지
              backgroundColor: '#E6A23C', // 그래프 안의 배경색
              pointBackgroundColor: 'white', // 각 점의 색
              borderWidth: 1,
              pointBorderColor: '#249EBF',
              //Data to be represented on y-axis
              data: []
            }
          ]
        },
        //Chart.js options that controls the appearance of the chart
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true // 차트 y축 0부터 시작
              },
              gridLines: {
                display: true // 수치마다 가로선 유무
              }
            }],
            xAxes: [ {
              gridLines: {
                display: false // 수치마다 세로선 유무
              }
            }]
          },
          legend: {
            display: false
          },
          responsive: true, // 그래프넓이
          maintainAspectRatio: false
        },
        requestEndDateOption:'',
        requestStartDateOption:'',
      }
    },
    // mounted () {
    //   //renderChart function renders the chart with the datacollection and options object.
    //   this.renderChart(this.datacollection, this.options) // datacollection 과 options 객체를 파라미터로 보내서 차트를 render 하는 renderChart()함수를 부릅니다
    // },
    methods: {
      async search(){

        this.$store.commit("dayBefore",0);
        this.requestEndDateOption = this.$store.state.dateOption; // 조회조건에서 현재 날짜 
        this.$store.commit("dayBefore",10);
        this.requestStartDateOption = this.$store.state.dateOption; // 조회조건이서 현재보다 -7 일

        let url = this.$url + '/rfidInfo/dailyValueWIn';
        var reqJson = new Object();
        reqJson.w_in_start_date = this.requestStartDateOption;
        reqJson.w_in_end_date = this.requestEndDateOption;

        await Axios.post(url, reqJson , this.$store.state.config  ).then(res => { 

          var resDataJson = res.data.rfidInfoDTO;
              
          if (res.data.resultCode){
 
            for(var resData in resDataJson){
              this.datacollection.labels[resData] = resDataJson[resData]['w_in_date']
              this.datacollection.datasets[0].data[resData] =  resDataJson[resData]['amount']
            }

            this.renderChart(this.datacollection, this.options) // datacollection 과 options 객체를 파라미터로 보내서 차트를 render 하는 renderChart()함수를 부릅니다
           
          }else{
            this.$message.error(this.$store.state.dataNotFound);
          }
        })//await Axios.post
        .catch(error => {
          console.log(error.response)
          this.$message.error(this.$store.state.serverErrorMessage);
          this.currentPage = 1; 
          this.loading = false;
        }); // await Axios.post
      }
    },
  }
</script>
