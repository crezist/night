<style>
.shitu{
  width: 80px;
  height: 120px;
  background-size:100% ;
  float: left;
  position: relative;
  left: 80px;
}

</style>

<template>
  <div>
    <toolbar1></toolbar1>
		<toolbar2></toolbar2>
   <div>
     <table class="table table-hover " >
       <tr>
         <td>编号</td>
         <td>图片</td>
         <td>名字</td>
         <td>价格</td>
         <td>操作</td>
         <td>详情</td>
       </tr>
      <tr v-for="(con,i) in collection" class="xijie">
        <td>{{i+1}}</td>
        <td ><div :style="con.img" class="shitu"></div></td>
        <td>{{con.gdname}}</td>
        <td>{{con.price}}</td>
        <td><button class="btn btn-danger" @click="selectcollction(con.gdid)">删除</button></td>
        <td><button class="btn btn-primary" @click="tiao(con.gdid)">详情</button></td>
      </tr>
     </table>
   </div>
 </div>
</template>

<script>
  import toolbar1 from "@/components/toolbar1"
  import toolbar2 from "@/components/toolbar2"

  export default{
    components:{
      toolbar1,
      toolbar2,
    },
    data:function(){
      return{
        userid:0,
        collection:[],
      }
    },
    mounted(){
      this.userid=this.$route.query.userid;
      this.getAllcollection();
    },
    methods:{
      getAllcollection(){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/Ajax_LidongxuController/selectAllcollection"
        $.ajax(url,{
        	data:{"userid":ob.userid},
        	dataType:"json",
        	xhrFields:{"withCredentials":true},
        	success(result){
             ob.collection=result;
             for(var i in result){
               ob.collection[i].img={
								"background-image":"url('http://127.0.0.1:8809/xm/tp/"+result[i].gimgurl+"')",
              }
             }
        	}
        	}
          )
      },
      selectcollction(gdid,userid){
        var ob=this;
        var url="http://127.0.0.1:8809/xm/Ajax_LidongxuController/selectGoodscollection"
        $.ajax(url,{
        	data:{"gdid":gdid,"userid":ob.userid},
        	dataType:"json",
        	xhrFields:{"withCredentials":true},
        	success(result){
               if(window.confirm("您确定删除这个收藏么？")){
                 ob.delectgoodskey(gdid);
               }
        	}
        	}
          )
      },
      delectgoodskey(gdid,userid){
        var ob=this;
      	var url="http://127.0.0.1:8809/xm/Ajax_LidongxuController/delectgoodskey"
      	$.ajax(url,{
      		data:{"gdid":gdid,"userid":ob.userid},
      		dataType:"json",
      		xhrFields:{"withCredentials":true},
      		success(result){
             if(result){
                  alert("您的操作成功");
                  ob.getAllcollection();
             }
      		}
      		}
          )
      },
      tiao(gdid){
      	this.$router.push({
      		name:"detail",
      		query:{gdid:gdid},
      	})
      },

    }


  }
</script>
