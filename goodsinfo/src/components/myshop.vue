<style>
  .tps{
    width: 220px;
    height: 340px;
    background-color: #006600;
    margin: 7px;
    float: left;
  }
</style>

<template>
  <div style="width: 100%;min-height:500px ;">
<div style="height:150px;">
  <topup></topup>
  <topshop></topshop>
</div>
  <div style="min-height: 500px;background-color: #000080;" class="container">
    <h1 v-if="ts!=''">{{ts}}</h1>
    <div class="tps" v-for="gs in imgs" :style="gs.srcs" ></div>
  </div>
  </div>
</template>

<script>
  import topshop from '@/components/topshop.vue';
   import topup from '@/components/topup.vue';
   export default {
     components:{
       topshop,
       topup,

     },
     data(){
       return{
         goods:[],
         ts:"",
         stid:0,
         pagenum:1,
         imgs:[]
       }
     },
     methods:{
       //测试
       toser(){
         return this.stid;
       },
       //查询店铺商品
       selectgoodsbysuer(){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/StoreinfoController/selectgoodsbystore";
        $.ajax(url,{
          data:{
            "stid":ob.stid,
            "pagenums":ob.pagenum
          },
          xhrFields:{"withCredentials":true},
        	success(result){
        	if(result==[]){
            ob.ts="您的店铺是空的，快添加商品把"
          }else{
              ob.ts=""
            ob.goods=result;
            for (var i in result) {
            	result[i].srcs={
            		"background-image": "url('http://127.0.0.1:8809/xm/tp/"+result[i].gimgurl+"')"
            		}
            	ob.imgs.push(result[i])
            }
          }
        	}
        })
       }
     },
     mounted() {
       // console.log(this.$route.query.stid)
       this.stid=this.$route.query.stid;
       // console.log(this.stid)
      this.selectgoodsbysuer();
     },

   }
</script>

<style>
</style>
