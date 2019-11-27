<template>
	<div class="loginin" >
		<div class="name">
			<span  class="stl" >用户名:</span>
			<input type="text" v-model="name"  />
		</div>
			<div class="name">
			<span class="stl">密码:</span>
			<input type="password" v-model="password_one"/>
		</div>
		<div class="vaild">
			<span  class="stl">验证码:</span>
			<input type="text" v-model="vaildcode"  />
			<span class="str text-danger">{{vaildcodeerror}}</span>
		</div>		
		<img id="yanzheng" @click="changeimage()" src="http://127.0.0.1:8809/xm/vaildCode" ></src>
		<button class="btn btn-class" @click="submit()">确认</button>
	</div>
	
</template>

<script>
	export default{
		data(){
			return{
			 name:"",
			 password_one:"",
			 vaildcode:"",
			 vaildcodeerror:"",
			 error:["","用户名不存在","用户被禁用","用户名密码不匹配"]
			}
		},
		methods:{
			
			submit(){
				this.vaild();
				if(this.vaildcodeerror==""){
				var ob=this;

				var url="http://127.0.0.1:8809/xm/Goodsinfoctl/login"
				$.ajax(url,{
					data:{logname:ob.name,password:ob.password_one},
					xhrFields:{"withCredentials":true},
					success(result){
					  if(result==0){
					  	if(ob.$route.query.reg=="reg"){
					  		ob.$router.go(-2);
					  	}else{
					  	  ob.$router.back(-1);	
					  	}
					  }else{
					  	ob.vaildcodeerror=ob.error[result];
					  }
					}
					}
				)
					
				}else{
					return;
				}
				
				
			},
			vaild(){
				var ob=this;
				var url="http://127.0.0.1:8809/xm/vaildCodeisok"
				$.ajax(url,{
					data:{vaild:ob.vaildcode},
					xhrFields:{"withCredentials":true},
					async:false,
					success(result){
						if(result){
						ob.vaildcodeerror="";
						}else{
						ob.vaildcodeerror="验证码错误";
						ob.changeimage();
						}
					}
					}
				)
			},
			changeimage(){
				$("#yanzheng")[0].src="http://127.0.0.1:8809/xm/vaildCode";
			},
			my(){
				console.log(this.$route.query.reg); 
			}
		},
		mounted(){
			this.my();
			
		}
		
	}
	
</script>

<style>
	.loginin{
		padding-top:1px;
		width: 440px;
		height: 250px;
		background-color: pink;
		margin: auto;
	}
	.loginin > div{
		position: relative;
		margin: auto;
		width: 400px;
		height: 40px;
		line-height: 40px;
		margin-top:10px;    
	}
	.loginin div .stl{
		width: 90px;
		position: absolute;
		left: 0px;
		top:0px;
		text-align: right;
	}
    .loginin div .str{
		width: 120px;
		position: absolute;
		left: 300px;
		top:0px;
		text-align: left;
	}
	.loginin > div input{
		margin: auto;
		width: 190px;
		height: 40px;  
	}
	
</style>