<template>
  <div class="containers">
  <div class="top_up_frist">
    <div><a href="http://127.0.0.1:8088" style="color: #8d8d8d;margin-left:-10px ;">首页</a></div>
    <div>|</div>
    <div >商城</div>
    <div>|</div>
    <div>
      <button @click="topayfor()" class="btn btn-link" style="font-size: 13px; color: #8d8d8d;position: relative;left: -40px;">我的订单</button>
      </div>
      <div>|</div>
      <!-- 商家信息 -->
      <div><button @click="tostoreuser()" class="btn btn-link" style="font-size: 13px; color: #8d8d8d;position: relative;left: -40px;">商家系统</button></div>
    <div>|</div>
    <div>关于我们</div>

  </div><hr style="margin-top:3px;"/>
  </div>
</template>

<script>
  export default{
    methods:{
      topayfor(){
        // this.$router.push({"name":"payfor"})
        let resoles= this.$router.resolve({
         "name":"payfor"
        });
        window.open(resoles.href,'_blank');
      },
      tostoreuser(){
        let resoles= this.$router.resolve({
         "name":"toinsertstoreuser"
        });
        window.open(resoles.href,'_blank');
      }
    }
  }
</script>

<style>
  .top_up_frist{
    width: 100%;
    height: 33px;
    line-height: 33px;
    /* background-color: pink; */
  }
  .top_up_frist :first-child{
    color: white;
    margin-left: 60%;
  }
   .top_up_frist div{
    width: 10px;
    color: #8D8D8D;
    float:left;
    margin-right:10px;
  }
  .top_up_frist div:nth-child(2n-1){
    width: 60px;
    color: #8D8D8D;
    float:left;
    margin-right:30px;
  }
.containers{
  width: 100%;
  padding: 0px;
}
</style>
