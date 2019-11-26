<style>
	.container2{
		margin-top: 20px;
		width: 1200px;
		background-color: #F5F5F5;
		
	}
	
	.box{
		column-count:4; 
		column-gap:14px;
	}
   .picture{
   		height:220px;
			margin:0px;
			break-inside:avoid;
			background-size: 100%;
			background-repeat:no-repeat ;
			border-radius: 6px;
			margin-top: 12px;
   }
   .picture:first-child{
   	margin-top:0px;
   }
   .pic1{
   	height:290px;
   }
   .goodsinfo{
   	width:100%;
   	height:100%;
   	display: none;
   }
   .goodsinfo button{
   	width:40px;
   	height:40px;
   	border-radius:90px;
   	border:0px;
   	float: right;
   	margin-top:10px;
   	margin-right: 10px;
   	background-color: #122B40;
   }
   
   .picture:hover .goodsinfo{
   	display: block;
   	background-color: rgba(50,50,50,0.6);
   	color: white;
   }

   .buttom{
   	clear: both;
   	font-size: 20px;
   	color:#456389234;
   	margin: 10px,auto;
   	
   }
   
</style>

<template>
	<div class="container2">
	<div class="box">
	<div  v-for="(good,i) in goods" class="picture" :class="{pic1:(i%3==0||i%7==0)}"  :style="good.sty">
		<div class="goodsinfo" @click="todetail(good.gdid)">
		<button> <i class="fa fa-thumbs-o-up"></i></button>	
		<br /><br /><br />
		<button> <i class="fa fa-star-o"></i></button>
		<br /><br /><br />
		<hr style="width: 90%;margin:auto;border:0px;border-bottom:1px solid gray;" />
		<br /><br />
		{{good.gdname}}
		</div>
		
	</div>
	</div>
    <div class="buttom">{{tex}}</div>
    
	</div>
</template>

<script>
	export default{
		data:function(){
			return{
				goods:[],
				pages:0,
				num:1,
				lock:false,
				tex:"",
			}
		},
		methods:{
			todetail(gdid){
				this.$router.push({
					name:"detail",
					query:{gdid:gdid},
				})
			},
			getgoodsinfo(current){
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/goods"
				$.ajax(url,{
					data:{current:current},
					dataType:"json",
					xhrFields:{"withCredentials":true},
					success(result){
						ob.pages=result.pages;
						ob.goods=ob.goods.concat(result.goodsinfo);
						for(var i in ob.goods){
							var good=ob.goods[i];
							good.sty={
								"background-image":"url('http://192.168.1.29:8809/xm/tp/"+ob.goods[i].gimgurl+"')",
							}
						}
					}
					}
				)
				
		}

		},
		mounted:function(){
			 var ob=this;
			this.getgoodsinfo(1);
			$(window).scroll(function(){
				if(window.scrollY+window.outerHeight>=document.body.scrollHeight){
					if(!ob.lock){
						ob.lock=true;
						if(ob.num>ob.pages){
						ob.tex="到底了";
					}else{
						ob.getgoodsinfo(++ob.num);
						ob.lock=false;
					}
					}
					
				}
				
						
				
		})  
		},
		
	}
</script>

