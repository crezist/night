<template>
	<div>
		<div>
			{{ctids}}
			收件地址:<input v-model="address" />
			收件人:<input  v-model="recipient"/>
			联系电话:<input v-model="contactnumber"/>
			<button @click="makeorder()">提交订单</button>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
			  address:"山东省青岛市香港中路",
			  recipient:"周",
			  contactnumber:"12345678",
			  ctids:[],
			}
		},
		methods:{
			makeorder(){
			    var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/makeorder"
				$.ajax(url,{
					data:{address:ob.address,recipient:ob.recipient,contactnumber:ob.contactnumber,ctids:ob.ctids},
					traditional:true,
					xhrFields:{"withCredentials":true},
					success(result){
				      if(result){
				      	ob.$router.push({name:"order"})
				      }
					}
					}
				)	
			}
			   
		},
		mounted(){
			this.ctids=this.$route.query.ctids;
		}
		
	}
</script>

<style>
</style>