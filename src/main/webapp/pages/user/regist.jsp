<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%--	静态包含css样式，js路径，绝对路径base--%>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
	<script type="text/javascript">
		$(function () {//判断输入是否正确的函数
			$("#yzm").click(function(){
				//在事件响应的function函数中有一个this对象。这个this对象，是当前正在响应的dom对象
				//而在这里src属性表示img的src属性，就是图片的路径，它可读可写。
				this.src="${base}kaptcha.jpg?d="+new Date();
			})
			//绑定提交注册单击事件
			$("#sub_btn").click(function () {
				//创建正则判断语句
				var UPpatt=/^\w{5,12}$/
				var EmailPatt= /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
				//获得用户名输入内容
				var username=$("#username").val()
				var password=$("#password").val()
				var repwd=$("#repwd").val()
				var email=$("#email").val()
				var code=$("#code").val()
				if(!UPpatt.test(username)){
					$(".errorMsg").text("用户名不合法")
					return false//阻止跳转页面
				}
				 $(".errorMsg").text("")
				if(!UPpatt.test(password)){
					$(".errorMsg").text("密码不合法")
					return false
				}
				$(".errorMsg").text("")
				if(password!==repwd){
					$(".errorMsg").text("两次密码不一样")
					return false
				}
				$(".errorMsg").text("")
				if(!EmailPatt.test(email)){
					$(".errorMsg").text("邮箱格式不正确")
					return false
				}
				$(".errorMsg").text("")
				//去掉验证码里面的前后空格
				code=$.trim(code)
				if(code===""||code==null){
					$(".errorMsg").text("验证码不能为空")
					return false
				}
				//等用户名合法了删除提示并且提交内容

			})
		})
	</script>

</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${empty requestScope.msg ?"":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet?action=regist" method="post">
									<label>用户名称：</label>
									<%--隐藏的标签，用于判断在userServlet中是注册还是登--%>
									<input type="hidden" name="action" value="regist">
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入5-12位的密码" autocomplete="off" tabindex="1" name="password" id="password"
										   value="${requestScope.password}"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd"
										   value="${requestScope.repwd}"/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" id="code" name="code"/>
									<img id="yzm" alt="验证码找不到了" src="kaptcha.jpg" style="float: right; margin-right: 40px;height: 40px;width: 100px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--		静态包含共有的页脚信息--%>
		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>