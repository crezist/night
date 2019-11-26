<template>
	<div>
		<span>总价:￥{{sum}}</span><span @click="maker()">结算</span>
		<table class="table table-hover table-striped">
		   <tr>
		   	<td>编号</td>
		   	<td>图片</td>
		   	<td>名称</td>
		   	<td>尺寸</td>
		   	<td>价格</td>
		   	<td>数量</td>
		   	<td>单个总价</td>
		   	<td>操作</td>
		   	<td><input type="checkbox"  v-model="checkall" >全选  选中</td>
		   </tr>
		   <tr v-for="(cart,index) in cartlist">
		   	<td>{{index+1}}</td>
		   	<td>
		   		<div class="back" :style="cart.backurl"></div>
		   	</td>
		   	<td>{{cart.gdname}}</td>
		   	<td>{{cart.gstext}}</td>
		   	<td>￥{{cart.price}}</td>
		    <td>
		    	<div>
		    		<button @click="changecount(cart.ctid,cart.gdcount-1,index)">-</button>
		    	{{cart.gdcount}}
		    	    <button @click="changecount(cart.ctid,cart.gdcount+1,index)">+</button>
		    	</div>
		    </td>
		    <td>{{cart.gdcount*cart.price}}</td>
		    <td><button @click="changecount(ctid,0,index)">delete</button></td>
		    <td><input type="checkbox" :value="index" v-model="temp"/></td>
		   </tr>
		</table>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				sum:0,
				cartlist:[],
				temp:[],
				ctids:[],
				checkall:false,
				
			}
		},
		watch:{
			temp(){
			this.dosum();
			this.toctids();
			if(this.temp.length!=this.cartlist.length){
				this.checkall=false;
			}else{
				this.checkall=true;
			}

			},
			checkall(){
				console.log(this.checkall);
				if(this.checkall){
					this.temp=[];
					for(var i in this.cartlist)
					this.temp.push(i);
				}else{
					if(this.temp.length==this.cartlist.length){
						this.temp=[];
				 }else{
				 	return;
			  }
				}
			},
		},
		methods:{
			maker(){
				this.$router.push({name:"makeorder",query:{ctids:this.ctids}})
			},
			toctids(){
				this.ctids=[];
				for(var i in this.temp){
				this.ctids.push(this.cartlist[this.temp[i]].ctid);	
				}	
			},
			dosum(){
			   this.sum=0;
			   for(var i in this.temp){
			   	 this.sum+=this.cartlist[this.temp[i]].gdcount*this.cartlist[this.temp[i]].price;
			   }
			},
			changecount(ctid,gdcount,index){
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/changecount"
				$.ajax(url,{
					data:{ctid:ctid,gdcount:gdcount},
					dataType:"text",
					xhrFields:{"withCredentials":true},
					success(result){
						if(result){
						   if(gdcount<=0){
						   	ob.cartlist.splice(index,1);
						    var tempindex=$.inArray(index,ob.temp);
						   	ob.temp.splice(tempindex,1);
						   	for(var i in ob.temp){
						   		if(ob.temp[i]>index){
						   			ob.temp[i]=ob.temp[i]-1;
						   		}
						   	}
						   }else{
						   	 ob.cartlist[index].gdcount=gdcount;
						   }
						 ob.dosum(); 
					  }else{
					  	return;
					  }
					}
				   })	
				
			},
			getcart(){
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/browsercart"
				$.ajax(url,{
					dataType:"json",
					xhrFields:{"withCredentials":true},
					success(result){
						if(result){
						  
						   for(var i in result){
						   	result[i].backurl={"background-image":"url('http://192.168.1.29:8809/xm/tp/"+result[i].gimgurl+"')"}
						   }
						  ob.cartlist=result;
					  }		
					}
				   })	
		        },
	   },
	   mounted(){
	   	this.getcart();
	   }
	}
	
</script>

<style>
	.back{
		width: 100px;
		height: 100px;
		background-size:100%;
		margin: 2px;
		margin: auto;
	}
</style>