<template>
  <div>
    <div style="height: 160px;">
      <topup></topup>
      <topshop></topshop>
    </div>
  <input v-model="stname" placeholder="请输入店名"/>
  <input v-model="stnum" placeholder="电话"/>
  <select v-model="stareaid">
    <option v-for="area in areainfo" :value="area.aid" >{{area.aname}}</option>
  </select>
  <input v-model="staddress" placeholder="详细地址"/>

  <button @click="insert()">提交</button>
</div>

</template>

<script>import topshop from '@/components/topshop.vue';
   import topup from '@/components/topup.vue';
   export default {
     components:{
       topshop,
       topup,
     },
    data(){
      return{
        stname:"",
        stnum:"",
        staddress:"",
        stareaid:1,
        areainfo:[],
      }
    },
    methods:{
      //添加商户并跳转到商铺页面
        insert(){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/StoreuserController/doonestore"
        $.ajax(	url,{
          data:{
            stname:ob.stname,
            stnum:ob.stnum,
            staddress:ob.staddress,
            stareaid:ob.stareaid,
          },
        	dataType:"json",
        	xhrFields: {"withCredentials": true},
        	success:function(result){
            if(result!=null){
              ob.$router.push({
                "name":"myshop",
                query:{
                  "stid":result
                }
              })
            }
          }
        });
      },
      getallarea(){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/StoreuserController/selectareainfo"
        $.ajax(	url,{
        	dataType:"json",
        	xhrFields: {"withCredentials": true},
        	success:function(result){
            console.log(result)
            ob.areainfo=result;
          }
        });
      }
    },
    mounted() {
    this. getallarea();
    },
  }

</script>

<style>
</style>
