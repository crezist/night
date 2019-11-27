<template>
	<div>
		<table class="table table-hover">
		<tr>
		<td>订单编号</td>
        <td>商品图片</td>
		<td>商品名称</td>
		<td>商品价格</td>
		<td>商品数量</td>
		<td>商品型号</td>
		<td>下单日期</td>
		<td>订单状态</td>
		<td>操作</td>
		</tr>

		<tr v-for="(order,index) in orderlist" >
		<td :rowspan="ja[order.ofid]" v-if="order.ofid!=null">{{order.ofid}}</td>
		<td><div :style="order.back" class="ab"></div></td>
		<td>{{order.gdname}}</td>

		<td>{{order.price}}</td>
		<td>{{order.gdcount}}</td>
		<td >{{order.gstext}}</td>
		<td >{{order.ofdate}}</td>
		<td>{{order.ofstate}}</td>

		<td>
			<button @click="topay(order.ofid)">结算</button>
		</td>

		</tr>
		</table>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				orderlist:"",
				ja:{},
				tem:"",
				count:""
			}
		},
		methods:{
			getorder(){
				var ob=this;

				var url="http://127.0.0.1:8809/xm/Goodsinfoctl/getorderdetails"
				$.ajax(url,{
					dataType:"json",
					xhrFields:{"withCredentials":true},
					success(result){
						for(var i in result){

							result[i].back={"background-image":"url('http://127.0.0.1:8809/xm/tp/"+result[i].gimgurl+"')"};
						}




						for (var i in result) {
							if(ob.tem!=result[i].ofid){
								ob.tem=result[i].ofid;
								ob.count=1;
								ob.ja[result[i].ofid]=ob.count;

							}else{
								ob.count+=1;
								ob.ja[result[i].ofid]=ob.count;
								result[i].ofid=null;
							}
						}
						ob.orderlist=result;
						console.log(ob.ja);
					}
					}
				)
			},
			topay(ofid){

				window.open("http://127.0.0.1:8809/xm/ali?ofid="+ofid)
			},

		},
		mounted(){
			this.getorder();
		}
	}
</script>

<style>
	.ab{
		width: 40px;
		height: 40px;
		background-size: 100%;
		margin: auto;
	}
</style>
