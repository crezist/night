<template>
	<div class="container">
        <toolbar1></toolbar1>
		<toolbar2></toolbar2>
		<carousel></carousel>
		<goodsinfo2></goodsinfo2>
		<retunrntop></retunrntop>

	</div>
</template>

<script>
	import carousel from "@/components/carousel"
	import goodsinfo2 from "@/components/goodsinfo2"
	import retunrntop from "@/components/retunrntop"
	import toolbar1 from "@/components/toolbar1"
	import toolbar2 from "@/components/toolbar2"

	export default{
		components:{
			carousel,
			goodsinfo2,
			retunrntop,
			toolbar1,
			toolbar2,
		},
		data:function(){
			return{
				test:"",
				xiao:"小心"
			}
		},
		watch:{
		  xiao(){
		  	this.$children[1].abc=this.xiao
		  }
		},
		methods:{

		},
		mounted(){
			this.test=this.$children[1].test;
		},
	}
</script>

<style>
	*{
		padding: 0px;
		margin: 0px;
		border: 0px;
	}
	.container{
		width:1200px;
		min-height:600px;

	}
</style>
