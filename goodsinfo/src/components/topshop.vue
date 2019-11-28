<template>
  <div class="top_up_last_all" style="background-color: white;z-index: 99;" :style="style_1">
    <a href="http://192.168.1.42:8089">
      <div style="width: 150px;height: 100px;float: left;line-height: 100px;"> <!-- {{stname}} -->商店</div>
    </a>
       <div class="poto">
         <label style="font-size: 15px;"><i class="fa fa-bars" style="width: 20px;"></i>目录</label>
        </div>
    <div class="top_up_last">
        <input type="text" @keyup="getgoodsname($event)" @keyup.up="upanddown(-1)" @keyup.down="upanddown(1)" v-model="values" />
        <div class="button">
          <i class="fa fa-search"></i>
        </div>
        <div class="downlodes" v-if="sob">
            <div v-for="(ob,i) in lode"  :class="{'bg-warning':i==length}" class="aso">{{ob.gdname}}</div>
        </div>
         </div>
         <div>
        <div class="massige"><i class="fa fa-commenting fa-2x"></i><br>消息</div>
        <div class="massige"><i class="fa fa-tasks  fa-2x"></i><br>订单</div>
        <div class="massige"><i class="fa fa-cog  fa-2x"></i><br>编辑</div>

        <div class="massige"><i class="glyphicon glyphicon-qrcode" style="font-size: 22px;margin-top: 4px;"></i><br>二维码</div>
        <div class="massige"><img src="/../static/imgs/181016_143l3ehl4ebad6c2326gjk6d4h41g_48x48.png" style="width: 30px;height:30px;margin-top: 10px;" />
          <router-link to="logup">
            <span v-if="dl">
              登陆
            </span>

          </router-link>
          <span v-if="!dl" style="text-align: left;">
              {{user}}<button class="btn btn-link" @click="logdown()">退出</button>
            </span>
        </div>
        </div>



  </div>
</template>

<script>
  export default{
    data () {
      return {
        values:"",
        lode:[],
        sob:false,
        length:-1,
        style_1:{position: "absolute"},
        dl:true,
        user:"",
        stname:""
      }
    },
    methods:{
      //查询店家名称（logo）
      getlogo(){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/StoreinfoController/selectstorename"
        $.ajax(	url,{
          data:{
            stid:ob.stid,
          },
        xhrFields: {"withCredentials": true},
        success:function(result){
         console.log(result)
        }
        })
      },
      //退出账号
      logdown(){
        if(confirm("确认退出账号？")){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/Goodsinfoctl/logout"
        $.ajax(	url,{
        dataType:"json",
        xhrFields: {"withCredentials": true},
        success:function(result){
          if(result){
            ob.dl=true;
              ob.user="";
              ob.$router.push({"name":"main"})
          }
        }
        })
        }
      },
      //查询是否在线
      userisint(){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/Goodsinfoctl/useronline"
        $.ajax(	url,{
        dataType:"json",
        xhrFields: {"withCredentials": true},
        success:function(result){
            if(result!=""){
              ob.dl=false;
              ob.user=result.logname;
            }else{
              ob.dl=true;
              ob.user="";
            }
            }
        })
      }
      ,
      //上下选择
      upanddown(num){
        // var va=this.values;
        this.length+=num;
        // console.log(this.length)
        if(this.length>this.lode.length-1){
          this.length=0;
        }else if(this.length<0){
          this.length=this.lode.length-1;
        }
        this.values=this.lode[this.length].gdname;
      },
      //搜索框
      getgoodsname(event){
        // console.log(event.keyCode)
        if(event.keyCode==38||event.keyCode==40){
          return;
        }
        var ob=this;
        if(this.values==""){
         this.sob=false;
         return ;
       }
       this.sob=true;
        var url="http://127.0.0.1:8809/xm/Goodsinfoctl/like"
        $.ajax(	url,{
          data:{
             gdname:ob.values,
          },

        dataType:"json",
        xhrFields: {"withCredentials": true},
        success:function(result){
            ob.lode=result;


            }
        })
      }
    },
    watch:{
      user(){

      }
    },
    mounted(){
      alert(this.$parent.toser())
      this.userisint();
     // this.getgoodsname()
     var ob =this;
     $(window).scroll(function(){
       if($(window).scrollTop()>300){
         if(ob.style_1=={position: "fixed"}){
           return;
         }
          ob.style_1={position:"fixed",top:"0px"}
       }else{
         ob.style_1={position: "absolute"}
       }
     })
    }
  }
</script>

<style>
  .top_up_last_all{
    width: 100%;
    height: 100px;
     /* background-color: pink; */
    }
    .top_up_last_all img{
      width: 140px;
      height: 100px;
      float: left;
  margin-left: 3%;
    }
    .top_up_last{
      width: 535px;
      height: 48px;
      float: left;
      position: relative;
      top:25px ;
      left: 3%;
    }
   .top_up_last .button{
      width: 58px;
      height: 48px;
      background-color: #8D8D8D;
      font-size: 20px;
      line-height: 48px;
      border-radius: 5px;
      color: #FFFFFF;
      float: right;
    }
   .top_up_last input{
      width:475px;
      height: 48px;
      background-color:#F7F7F7 ;
      border: 0px;
      outline: none;
      padding: 0px 20px ;
      /* float: left; */
    }
    .poto{
      line-height: 48px;
      color: #8D8D8D;
      width: 80px;
      height: 48px;
      float: left;
      margin-top: 25px;
      margin-left: 20px;

    }
    .massige{
      width: 50px;
      height: 48px;
      color: #999999;
      float: left;
      margin-right:5px;
      position: relative;
      top: 24px;
      left:3%;
    }
    .massige:last-child{
      min-width: 160px;
      line-height: 48px;
    }
     .downlodes{
      width:650px;
      height: 450px;
      background-color:white;
      position: relative;
      top: 0px;
    }
  .aso{
      width:100%;
      height: 40px;
      line-height: 40px;
      text-align:left;
      position: relative;
      margin-down:5px;
      text-indent:20px
    }
    .inputs{
      width:600px ;
      height: 48px;
      background-color: darkgray;
    }
</style>
