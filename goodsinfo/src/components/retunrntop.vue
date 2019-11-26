<template>
	<div class="rollbacktop">
		<button @click="top()">Top</button>
	</div>
</template>

<script>
	export default{
		methods:{
			top(){
				$(window).scrollTop(0);
			}
		}
	}
</script>

<style>
	.rollbacktop{
		height:100%;
		width:40px;
	}
	.rollbacktop button{
		position: fixed;
		bottom: 80px;
		right:16px;
		width: 50px;
		height: 50px;
		border-radius: 90px;
		border:0px;
		outline: none;
		background-color:#FB609A;
		
	}
</style>