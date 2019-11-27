<template>
	<transition name="slide-fade">
		<div class="toolbar2" :style="style1" v-if="toolbar2">
			<!-- <input v-model="abc" style="background-color: darkgoldenrod;"> -->
		{{abc}}
			<div class="left">
			<img width="90px" height="90px" src="../../static/imgs/timg.jpg" style="margin-right:30px;margin-top: 5px;" />
			<font size="4" color="#FF33CC" >目录</font>
			</div>
			<div class="search" >
				<input type="text" class="input" @input="search($event)" v-model="keyword" @keyup.down="searchex(1)" @keyup.up="searchex(-1)" />
				<button class="butt"><i class="fa fa-search fa-2x"></i></button>
				<div class="list" >
					<div v-for="(good,index) in goods" @mouseover="mousein($event)" @mouseout="mouseout()" :class="{'bg-danger':show==index}"  >{{good.gdname}}</div>
				</div>
			</div>
			<div class="right" style="background-color: green;">
				<div class="rightsub">
					<i class="fa fa-comment-o fa-2x"></i><br />消息
				</div>
				<div class="rightsub">
				<i class="fa fa-star-o  fa-2x" @click="tall()"></i><br />收藏
				</div>
				<div class="rightsub" @click="browsercart()">
				<i class="fa fa-shopping-cart  fa-2x"></i><br />购物车
				</div >
				<div class="rightsub">
				<i class="fa fa-qrcode  fa-2x"></i><br />下载app
				</div >
				<div style="width: 20px;margin-left: 10px;background-color: red;">
			    <i style="display:inline-block;margin-top: 8px;" class="fa fa-laptop fa-2x"></i>
				</div>
				<div style="width:56px" class="redisetlogin" v-if="logdisplay">
				<span><router-link to="login" style="text-decoration: none;">注册</router-link></span>
				<span @click="loginin()" style="cursor: pointer;">登录</span>
				</div>
				<div class="redisetlogin" v-if="!logdisplay" >
					<span>{{user.logname}}</span>
					<span @click="exit()">退出</span>
				</div>
			</div>
		</div>
		</transition>
</template>

<script>
	export default{
		props:["abc"],
		data(){
			return{
				goods:[],
				keyword:"",
				show:-1,
				style1:{position:"relative"},
				toolbar2:true,
				lunbo1:{"background-color":"#FF6666"},
				logdisplay:true,
				user:null,
				test:"生成文档",
			}
		},
		watch:{
			abc(){
				this.$parent.xiao=this.abc;
			},
		},
			methods:{
				browsercart(){
					this.online();
					if(this.user==null){
						this.$router.push({name:"loginin"});
					}else{
					this.$router.push({name:"cart"});
					}

				},
				exit(){
				var ob=this;
		    var url="http://127.0.0.1:8809/xm/Goodsinfoctl/logout";
			    $.ajax(url,{
			    xhrFields:{"withCredentials":true},
				success(result){
				    ob.user=null;
					ob.logdisplay=true;

				}
			})
				},
				online(){
				var ob=this;
			    var url="http://127.0.0.1:8809/xm/Goodsinfoctl/useronline";
			    $.ajax(url,{
			    resultType:"json",
			    xhrFields:{"withCredentials":true},
				success(result){
				if(result){
					ob.user=result;
					ob.logdisplay=false;
				}else{
					ob.user=null;
					ob.logdisplay=true;
				}
				}
			})
				},
				loginin(){
					this.$router.push({name:"loginin"})
				},
				mouseout(){
					this.show=-1;
				},
			searchex(ke){
				this.show+=ke;
				if(this.show==this.goods.length){
					this.show=0;
				}
				if(this.show<0){
					this.show=this.goods.length-1;
				}
				this.keyword=this.goods[this.show].gdname;
			},
			search(event){
				if(this.keyword==''){
					this.goods=[];
					return;
				}
				if(event.keyCode==38||event.keyCode==40){
					return;
				}
			var ob=this;
			var url="http://127.0.0.1:8809/xm/Goodsinfoctl/like";
			$.ajax(url,{
			    data:{keyword:ob.keyword},
			    resultType:"json",
			    xhrFields:{"withCredentials":true},
				success(result){
					ob.goods=result;

				}
			});
			}
			},
      tall(){
        if(this.user==null){
          if(window.confirm("请登录后再操作")){
            this.$router.push({"name":"loginin"});
          }
        }else{
          this.$router.push({"name":"goodscollection",query:{"userid":this.user.userid}});
        }
      },
		mounted(){
			this.online();
			var ob=this;
			$(window).scroll(function(){
				if($(window).scrollTop()>=300){
					if(ob.style1['position']=="fixed"){
						return;
					}
					ob.toolbar2=false;
					window.setTimeout(function(){
						ob.toolbar2=true;
					},300);
				   ob.style1={position:"fixed",top:"0px",boxShadow:"0px 6px 6px #FFFFFF"}
				}else{
				   ob.style1={position:"relative"}

				}
			})
		}
	}
</script>

<style>
		.toolbar2{
		width: 1200px;
		min-height:100px;
		background-color: #FFFFFF;
	}
	.toolbar2 .left{
		float:left;
		margin-left:60px;

	}
	.toolbar2 .right{
    width:330px;
    height: 100px;
		text-align:left;
		line-height: 100px;
    float: right;
		position: relative;
    left: 30%;
	}
	.toolbar2 .right div{
		float: left;
		width: 46px;
		line-height: 20px;
		margin-top: 30px;
		text-align: center;
		margin-right: 10px;
		font-size: 12px;
	}
	.toolbar2 .right div i{
		color: #FF99CC;
	}
	.search{
		width: 550px;
		height: 48px;
		background-color:#BBBBBB;
		position:absolute;
		top:26px;
		left:22%;
		margin: 0px;
		padding: 0px;
		border: 0px;

	}
	.input{
		margin:0px;
		padding: 0px;
		border:0px;
		height: 100%;
		width: 492px;
		background-color:transparent;
		outline: none;
		font-size:20px;
		color: #FFFFF6;
	}
	.butt{
		height: 100%;
		margin: 0px;
		padding: 0px;
		width: 54px;
		background-color:#FF99CC;
		border:0px;

	}
	.list{
		margin-top: -4px;
		width: 496px;
		border:1px solid grey;
		border:0px;
		position: relative;
		opacity:1;
		background-color: #CCCCCC;
		z-index:9;
		color: #FFFFF6;
	}
	.list div{
		width: 100%;
		height:26px;
		line-height: 26px;
		text-align: left;
		padding-top:2px;
		padding-left: 10px;
		font-size:20px;
	}
	.redisetlogin{
		background-color: yellow;
		padding: 0px;
		width: 100px;
		height: 40px;
	}



</style>
