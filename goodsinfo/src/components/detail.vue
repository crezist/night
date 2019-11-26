<template>
	<div class="container">
		<toolbar1></toolbar1>
		<toolbar2></toolbar2>
		<div class="shop_detail">
		  <div class="info_box">
		   <div class="BigImg">
		     <img :src="imgurl">
		   </div>
		   <div class="info">
		   	<div class="name">
		   		{{gdname}}
		   	</div>
		   	<div class="price">
		   		<div style="float: left;">
		   			价格：
		   		</div  style="float: left;">
		   		
		   		<div style="float: left;font-size:40px;color: red;">
		   			￥{{price}}
		   		</div>
		   		
		   	</div>
		   	<div class="record">
		   		<div  style="float: left;margin-top: 20px;">
		   			尺寸:
		   		</div>
		   		<div  class="size" v-for="(size,index) in sizes" :class="{size_selected:index+1==gsid}" @click="changegsid(index)">{{size.gstext}}</div>
		   		<div style="clear:left;float: left;" >
		   			数量：
		   		</div>
		   		<div class="minus_plus" style="float:left">
		   			<button @click="docount(-1)"><i class="fa fa-minus"></i></button>
		   			<input type="text" v-model="count" readonly unselectable="on" />
		   			<button @click="docount(1)"><i class="fa fa-plus"></i></button>
		   		</div>
		   	</div>
		   	<div class="button_buy">
		   		<button class="btn btn-danger">立即购买</button>
		   		<button class="btn btn-default" @click="tocart()">加入购物车</button>
		   		<div class="block" v-if="block"></div>
		   		<div class="tocartdisplay" v-if="tocartdisplay">+{{count}}</div>
		   	</div>
		   	<div class="note">
		   		<div class="speak">说明:</div>
		   		<div class="say" v-for="v in note"><i class="fa fa-check-circle-o fa-2x"></i>{{v}}</div>
		   	</div>
		   	<div class="pay">
		   		<div class="zhi">支付:</div>
		   		<div class="zhifubao"></div>
		   		<div class="wechat"></div>
		   	</div>
		   	
		   </div>
		  </div>
		</div>
        <righttoolbar></righttoolbar>
        <blank></blank>
	</div>
	
</template>

<script>
	import toolbar2 from "@/components/toolbar2"
	import toolbar1 from "@/components/toolbar1"
	import righttoolbar from "@/components/righttoolbar"
    import blank from "@/components/blank" 

	export default{
		
		components:{toolbar1,toolbar2,righttoolbar,blank,},
		data(){
			return{
				gdid:"",
				imgurl:"",
				gdname:"",
				price:"",
				sizes:"",
				count:1,
				note:["72小时发货","7天无理由退货","延误必赔","退货补运费","全国包邮"],
				user:null,
				gsid:1,
				tocartdisplay:false,
				block:false,
			}
		},
		methods:{
			changegsid(i){
				this.gsid=i+1;
			},
			tocart(){
				if(this.tocartdisplay){
					return;
				}
				this.online();
				if(this.user==null){
					this.$router.push({name:"loginin"})
					return;
				}
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/tocart"
				$.ajax(url,{
					data:{gdid:ob.gdid,gdcount:ob.count,gsid:ob.gsid},
					dataType:"json",
					xhrFields:{"withCredentials":true},
					success(result){
						if(result){
						ob.tocartdisplay=true;
						ob.block=true;
						window.setTimeout(function(){
						ob.tocartdisplay=false;	
						ob.block=false;
						},500);
						
						}else{
						ob.tocartdisplay=false;		
						}
					}
					}
				)	
			},
			online(){
				var ob=this;
			    var url="http://192.168.1.29:8809/xm/Goodsinfoctl/useronline";
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
			docount(c){
				this.count+=c;
				if(this.count<1){
					this.count=1;
				}
			},
			
			getallsize(){
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/getallsize"
				$.ajax(url,{
					dataType:"json",
					xhrFields:{"withCredentials":true},
					success(result){
						ob.sizes=result;
					}
					}
				)
			},
			getdetail(){
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/getdetails"
				$.ajax(url,{
					data:{gdid:ob.gdid},
					dataType:"json",
					xhrFields:{"withCredentials":true},
					success(result){
						ob.imgurl="http://192.168.1.29:8809/xm/tp/"+result.gimgurl;
						ob.gdname=result.gdname;
						ob.price=result.price;
					}
					}
				)
			}
			
		},
		mounted(){
			this.gdid=this.$route.query.gdid;
			this.getdetail();
			this.getallsize();
		}
		
	}
</script>

<style>
.shop_detail{
	float:left;
	width: 970px;
	min-height:600px ;
	
}
.info_box{
	width: 1000px;
	height: 600px;

}
.BigImg{
	width: 400px;
	height: 600px;
	float: left;
}
.BigImg img{
	width: 100%;
	height: 100%;
}
.info{
	width:600px;
	float: left;
	
}
.info>div{
	width: 80%;
	height: 100px;
	margin:auto;
	text-align: left;
	
}
.info .name{
	font-size: 30px;
	line-height:100px;
}
.info .price{
	background-color:rgba(56,56,56,0.2);
	line-height:100px;
}

.info .record .size{
	float:left;
	min-width:24px;
	height:24px;
	margin-top: 20px;
	margin-bottom: 20px;
	margin-left: 10px;
	line-height: 24px;
	text-align: center;
	box-shadow: 0px 0px 1px 1px #999999;
}
.info .record .size:hover{
    box-shadow: 0px 0px 0px 2px #262626;	
}
.info .record .size_selected{
    background-color: greenyellow;
    color: white;	
}
.minus_plus{
	width:104px;
	height: 26px;
	border:1px solid #999999;
	outline: none;
}
.minus_plus button{
	margin-top: 0.8px;
	float: left;
	height:23px;
	width: 26px;
	border:0px;
	outline: none;
}
.minus_plus input{
	margin-top: 0.8px;
	text-align: center;
	float: left;
    height:23px;
    width: 50px;
    outline: none;
    border: 0px;
    border-left:1px solid #999999;
    border-right:1px solid #999999;
}
.button_buy{
	line-height:100px;
	text-indent: 40px;
	position: relative;
}

.button_buy button{
	width: 200px;
	height:50px;
}
.button_buy .block{
	background-color: transparent;
	width:200px;
	height:50px;
	position: absolute;
	top:26px;
	left:244px;
}
.button_buy .tocartdisplay{
	font-size: 30px;
	color: red;
	position: absolute;
	top:-20px;
	left:300px;
	
}
.pay{
	
}
.pay .zhi{
	float: left;
}
.pay .zhifubao{
	margin-left:4px;
	width: 24px;
	height: 24px;
	background-image: url(../../static/imgs/icons.png);
	background-position:-36px -294px;
	float: left;
}
.pay .wechat{
	margin-left:4px;
	width: 24px;
	height: 24px;
	background-image: url(../../static/imgs/icons.png);
	background-position:-64px -294px;
	float: left;
}
.note{
	
	
}
.note .speak{
	padding-bottom: 70px;
	margin-top:6px;
	float: left;
}
.note .say{
	min-width:80px;
	height: 30px;
	margin-left: 6px;
	float: left;
	
}
.note .say i{
	vertical-align:bottom;
    color:red;
    
}
	
</style>