<template>
			<div class="toolbar1">
			<div class="right">
				<a><i class="fa fa-home" style="color: #FF0000;"></i>&nbsp;首页</a>
				<span>|</span>
				<a><i class="fa fa-shopping-bag" style="color: #FF0000;"></i>&nbsp;商城</a>
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