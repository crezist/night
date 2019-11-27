<template>
  <div style="width: 100%;min-height:500px ;">
<div style="height:150px;">
  <topup></topup>
  <topshop></topshop>
</div>
  <div style="width: 100%;min-height: 500px;background-color: #000080;">
    <h1 v-if="ts!=''">{{ts}}</h1>
    <div></div>
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
       }
     },
     methods:{
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
            console.log(result)
        	if(result=[]){
            console.log("hah")
            ob.ts="您的店铺是空的，快添加商品把"
          }else{
            ob.goods=result;
          }
        	}
        })
       }
     },
     mounted() {
       // console.log(this.$route.query.stid)
       this.stid=this.$route.query.stid;
      this.selectgoodsbysuer()
     },

   }
</script>

<style>
</style>
