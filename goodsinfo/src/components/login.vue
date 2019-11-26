<template>
	<div class="register" >
		<div class="name">
			<span  class="stl" >用户名:</span>
			<input type="text" v-model="name"  @input="nameisok()" />
			<span class="str" :class="{'text-success':namecolor,'text-danger':!namecolor}">{{nameok}}</span>
		</div>
			<div class="name"  >
			<span class="stl">密码:</span>
			<input type="password" v-model="password_one" @blur="passwordchange()" />
		</div>
		<div class="name">
			<span  class="stl">确认密码:</span>
			<input type="password" v-model="password_two" @blur="passwordissame()"  />
			<span class="str text-danger">{{passworderror}}</span>
		</div>
		<div class="vaild">
			<span  class="stl">验证码:</span>
			<input type="text" v-model="vaildcode"  />
			<span class="str text-danger">{{vaildcodeerror}}</span>
		</div>		
		<img id="yanzheng" @click="changeimage($event)" src="http://192.168.1.29:8809/xm/vaildCode" ></src>
		<button class="btn btn-class" @click="submit()">确认</button>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				name:"",
				namecolor:true,
				nameok:"",
				password_one:"",
				password_two:"",
				passworderror:"",
				passwordok:false,
				vaildcode:"",
				vaildcodeerror:"",
			}
		},
		methods:{
			
			
			submit(){
				if(this.submitready()){
					console.log(1);
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/register"
				$.ajax(url,{
					data:{logname:ob.name,password:ob.password_one},
					xhrFields:{"withCredentials":true},
					success(result){
				      if(result){
				       	if($(window)[0].confirm("直接登录吗？")){
				       	    ob.$router.push({name:"loginin",query:{reg:"reg"}});	
				       	}else{
				       		ob.$router.back(-1);
				       	}
					  }else{
					  	console.log("注册失败");
					}
			    }
				}
				)
				}else{
					console.log(2);
				}},
			
			submitready(){
				if(this.name==""){
					this.nameok="名字输入为空"
					return false
				}else if(!this.namecolor){
					this.nameok="名字已经被占用";
					return false;
				}else{
					this.nameok="";
				}
				if(this.password_one==""){
					this.passworderror="密码为空";
					return false;
				}else if(this.password_one.length<6){
					this.passworderror="密码不足6位";
				    return false;
				}else if(this.password_two==""){
					this.passworderror="确认密码为空";
					return false;
				}else if(!this.passwordok){
					this.passworderror="密码不一致";
					return false;
				}else{
						this.passworderror="";
				}
                 return this.vaild();
			},
			vaild(){
				var kk=false;
				var ob=this;
				var url="http://192.168.1.29:8809/xm/vaildCodeisok"
				$.ajax(url,{
					data:{vaild:ob.vaildcode},
					xhrFields:{"withCredentials":true},
					async:false,
					success(result){
						if(result){
						ob.vaildcodeerror="";
						console.log("验证通过")
						kk=true;
						}else{
						ob.vaildcodeerror="验证码错误";
						$("#yanzheng")[0].src="http://192.168.1.29:8809/xm/vaildCode";
						kk=false;
						}
					}
					}
				)
				return kk;
			},
			passwordchange(){
				if(this.password_one.length<6){
					this.passworderror="密码不足6位";
					this.passwordok=false;
				}else if(this.password_two==""){
					return;
				}else if(this.password_one==this.password_two){
					this.passworderror="";
					this.passwordok=true;
				}else{
					this.passworderror="密码不一致";
					this.passwordok=false;
				}
				
			},
			passwordissame(){
				if(this.passworderror=="密码不足6位"){
					return;
				}else if(this.password_one==this.password_two){
					this.passworderror="";
					this.passwordok=true;
				}else{
					this.passworderror="密码不一致";
					this.passwordok=false;
				}
			},
			nameisok(){
				var ob=this;
				var url="http://192.168.1.29:8809/xm/Goodsinfoctl/lognameisok"
				$.ajax(url,{
					data:{logname:ob.name},
					xhrFields:{"withCredentials":true},
					success(result){
						
						if(result){
						ob.nameok="可以使用";
						ob.namecolor=true;
						}else{
						ob.nameok="被占用";
						ob.namecolor=false;
						}
						
					}
					}
				)
			},
			changeimage(event){
				event.target.src="http://192.168.1.29:8809/xm/vaildCode";
			},
		}
	}
</script>

<style>
	.register{
		padding-top:1px;
		width: 400px;
		height: 250px;
		background-color: pink;
		margin: auto;
	}
	.register > div{
		position: relative;
		margin: auto;
		width: 400px;
		height: 40px;
		line-height: 40px;
		margin-top:10px;    
	}
	.register div .stl{
		width: 90px;
		position: absolute;
		left: 0px;
		top:0px;
		text-align: right;
	}
    .register div .str{
		width: 100px;
		position: absolute;
		left: 300px;
		top:0px;
		text-align: left;
	}
	.register > div input{
		margin: auto;
		width: 190px;
		height: 40px;  
	}
	
	
</style>