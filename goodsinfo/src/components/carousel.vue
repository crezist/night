<template>
<div class="div_carouseltheme">
 <div class="div_carousel" @mouseover="carstop()" @mouseout="car()">
 	<transition name="slide-fade" v-for="(car,index) in carousels" :key="index">
 	<div  :style="car" v-if="index==inde"></div>
 	</transition>
 
 	
 	
  	<div class="left_right"  > 
  	<button ><i class="fa fa-caret-left" @click="carclick(-1)"></i></button>
 	<button ><i class="fa fa-caret-right" @click="carclick(1)"></i></button>
 	</div>
 	
   
 </div>
 <div class="div_theme" v-for="the in theme">
 	<img   :src="the"/>
 </div>
</div>

</template>

<script>
	export default{
		data(){
			return{
				carousels:[],
				theme:[],
				inde:0,
				timmer:"",
			}
		},
		methods:{
				car(){
				var ob=this;
				ob.timmer=window.setTimeout(function(){
				ob.inde++;
				if(ob.inde==ob.carousels.length){
					ob.inde=0;
				}
				ob.car();
			},4000)	
			},
			carstop(){
				window.clearTimeout(this.timmer);
				
			},
			carclick(step){
				this.inde+=step;
				if(this.inde==this.carousels.length){
					this.inde=0;
				}
				if(this.inde<0){
					this.inde=this.carousels.length-1;
				}
			}
		},
			
		mounted:function(){
			this.carousels.push({"background-image":"url('../static/imgs/lun (1).jpg')"});
			this.carousels.push({"background-image":"url('../static/imgs/lun (2).jpg')"});
			this.carousels.push({"background-image":"url('../static/imgs/lun (3).jpg')"});
			this.carousels.push({"background-image":"url('../static/imgs/lun (1).jfif')"});
			this.theme.push("../../static/imgs/theme (1).jpg");
			this.theme.push("../../static/imgs/theme (2).jpg");
			this.theme.push("../../static/imgs/theme (3).jpg");
			this.theme.push("../../static/imgs/theme (4).jpg");
			this.car();
		    
	}
		}
</script>

<style>
	.div_carouseltheme{
		width:1200px;
		height:260px;
		
	}
	.div_carousel{
		width: 76%;
		height: 100%;
		float:left;
		position: relative;
	}

	.left_right{
		position: absolute;	
	}
	.left_right button{
		width: 100px;
		height: 100px;
		font-size:60px;
		margin-top:10%;
		border:0px;
		background-color: transparent;
		outline:none;
		float:left;
	}
	.left_right button:last-child{
		float:right;
	}
	

	.div_theme{
		width:20%;
		height:24%;
		float: right;
	    margin-bottom: 4px;
		border-radius: 6px;
		overflow: hidden;
		text-align: center;
		
	}
	.div_carousel div{
		width:100%;
		height: 100%;
		background-size: 100% 100%;
		position: absolute;
		border-radius: 10px;
	}
	.div_theme img{
		width:100%;
	}
	.div_theme img:last-child{
		margin-bottom: 0px;
		}
		/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
  transition: all .3s ;
}

.slide-fade-enter, 
/* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
.slide-fade-leave-to
{
  transform: translateX(-10px);
  opacity: 0;
}
</style>