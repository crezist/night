<template>
			<div class="toolbar1">
			<div class="right">
				<a><i class="fa fa-home" style="color: #FF0000;"></i>&nbsp;首页</a>
				<span>|</span>
				<a><i class="fa fa-shopping-bag" style="color: #FF0000;"></i>&nbsp;商城</a>
        <span>|</span>
         <div style="float: right;"><button @click=" isstoreuser()" class="btn btn-link" style="font-size: 13px; color:red;position: relative;left: 0px;">商家系统</button></div>
				<span>|</span>
				<span @click="toorder()">我的订单</span>
				<span>|</span>
				<a>关于我们</a>
			</div>


		</div>
</template>

<script>
	export default{
		data(){
			return{
				user:null,
        suser:"",
			}
		},
		methods:{
			toorder(){
				if(this.user==null){
					this.online;
				}else{
				this.$router.push({name:"order"})
				}
			},
			online(){
				var ob=this;
			    var url="http://127.0.0.1:8809/xm/Goodsinfoctl/useronline";
			    $.ajax(url,{
			    async:false,
			    resultType:"json",
			    xhrFields:{"withCredentials":true},
				success(result){
				if(result){
					ob.user=result;
				}else{
					ob.user=null;
				}
				}
			})
				},
        //注册商户
      tostoreuser(){
        let resoles= this.$router.resolve({
          "name":"toinsertstoreuser"
        });
        window.open(resoles.href,'_blank');
      },
      //判断是否为商户
      isstoreuser(){
        var ob=this;
            var url="http://127.0.0.1:8809/xm/StoreuserController/userisstoreuser";
            $.ajax(url,{
            async:false,
            resultType:"json",
            xhrFields:{"withCredentials":true},
        	success(result){
        	if(result){
            ob.tostoreuser()
        	}else{
            ob.suser=result
        		ob.tomyshop()

        	}
        	}
        })
      },
      //跳转商户页面
      tomyshop(){
        var ob=this;
        this.$router.push({
          "name":"myshop",
          query:{
            "stid":ob.suser
          }
        })
      }
		},
		mounted(){
			this.online();
		}
	}
</script>

<style>
	.toolbar1{
		width: 1200px;
		min-height:33px;
		color: #999999;
		border-bottom: #F0F0F0 1px solid;
		box-shadow: 0px 0px 10px #ffffff;
		background-color: #404040;
	}
	.toolbar1 .right{
		float:right;
		text-align:center;
		line-height:33px ;
	}
	.toolbar1 .right a{
		padding:10px;
		color: #ffffff;
		text-decoration:none;
		cursor: pointer;
	}
</style>
