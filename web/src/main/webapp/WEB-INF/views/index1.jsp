<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/layui/css/layui.css">
	<link rel="stylesheet" href="css/followfansmoment.css">
	<link rel="stylesheet" href="css/userinfo.css">
	<link rel="stylesheet" href="css/usercontent.css">
	<link rel="stylesheet" href="css/longpic.css">
	<link rel="stylesheet" href="css/userrelation.css">
	<link rel="stylesheet" href="css/mynav.css">
	<link rel="stylesheet" href="http://at.alicdn.com/t/font_1963893_2cwb6qthxh5.css">
	<script src="css/layui/layui.js"></script>
</head>
<style>

</style>
<body>
<div class="bodycontent" style="width: 1024px;height: auto; margin: 0 auto;">

<div class="nav">
	<ul class="mynav">
	    <li class="mynavli"><a class="active" href="#home">主页</a></li>
	    <li class="mynavli"><a href="#news">新闻111</a></li>
	    <li class="mynavli"><a href="#contact">联系</a></li>
	    <li class="mynavli"><a href="#about">关于</a></li>
		<li class="mynavlibtn"><a href="http://www.baidu.com"> <i class="iconfont iconxieweibo"></i></a></li>
		<li class="mynavliinput"><input type="text" name="" id="" value="" class="searchsmt" placeholder="发现新鲜事"/></li>
	</ul>
</div>


<div class="weiboheader">
	<img class="weiboheaderpic" src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=14356876,365809084&fm=26&gp=0.jpg" />
	<div class="headONlongpicPosition">
		<img class="headONlongpic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg">
	</div>
	<div class="operaONlongpicPosition">
		<button class="layui-btn layui-btn-danger"><i class="iconfont iconfabu2"></i> 关注</button>
		<button class="layui-btn layui-btn-normal"><i class="iconfont iconfabu5"></i> 私信</button>
	</div>
</div>



<div class="leftpart">
	<div class="firstpart">
		<table class="one">
			<tr>
				<td class="two">
					<div class="three1">关注</div>
					<div class="three2" id="followcount">123</div>
				</td>
				<td class="two">
					<div class="three1">粉丝</div>
					<div class="three2" id="fanscount">234</div>
				</td>
				<td class="two">
					<div class="three1">微博</div>
					<div class="three2" id="momentcount">155</div>
				</td>
			</tr>
		</table>
	</div>
	
	<div class="maininfoblock">
		<ul class="info">
			<li class="unitinfo">
				<span class="first-info"><i class="iconfont iconqq"></i></span>
				<span class="second-info" id="usermail">123@qq.com</span>
			</li>
			<li class="unitinfo">
				<span class="first-info"><i class="iconfont iconweixin"></i></span>
				<span class="second-info" id="username">spaceX</span>
			</li>
			<li class="unitinfo">
				<span class="first-info"><i class="iconfont iconshouji54"></i></span>
				<span class="second-info" id="usertelnum">18974509345</span>
			</li>
			<li class="unitinfo">
				<span class="first-info"><i class="iconfont iconxinxi"></i></span>
				<span class="second-info" id="usermobilebrand">中国移动</span>
			</li>
			<li class="unitinfo">
				<span class="first-info"><i class="iconfont icondashujukeshihuaico-"></i></span>
				<span class="second-info" id="userwebsite">www.google.com</span>
			</li>
		</ul>
	</div>
	
	<div class="userrelation">
		<div class="userrelationheader">
			微关系
		</div>
		<div class="userfollow">
			<div class="userfollowheader">
				<span>TA的关注(</span>
				<span id="followcount">123</span>
				<span>)</span>
			</div>
			<div class="userfollowcontent">
				<ul class="userfollowfriendlsit">
					<li class="userfollowfriend">
						<span class="userfollowfriendhead">
							<img class="userfollowfriendpic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg"/>
						</span>
						<span class="userfollowfriendname">
							秦始皇
						</span>
					</li>
					<li class="userfollowfriend">
						<span class="userfollowfriendhead">
							<img class="userfollowfriendpic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg"/>
						</span>
						<span class="userfollowfriendname">
							秦始皇
						</span>
					</li>
					<li class="userfollowfriend">
						<span class="userfollowfriendhead">
							<img class="userfollowfriendpic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg"/>
						</span>
						<span class="userfollowfriendname">
							秦始皇
						</span>
					</li>
				</ul>
			</div>
		</div>

		<div class="userfollow">
			<div class="userfollowheader">
				<span>TA的粉丝(</span>
				<span id="fanscount">234</span>
				<span>)</span>
			</div>
			<div class="userfollowcontent">
				<ul class="userfollowfriendlsit">
					<li class="userfollowfriend">
						<span class="userfollowfriendhead">
							<img class="userfollowfriendpic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg"/>
						</span>
						<span class="userfollowfriendname">
							秦始皇
						</span>
					</li>
					<li class="userfollowfriend">
						<span class="userfollowfriendhead">
							<img class="userfollowfriendpic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg"/>
						</span>
						<span class="userfollowfriendname">
							秦始皇
						</span>
					</li>
					<li class="userfollowfriend">
						<span class="userfollowfriendhead">
							<img class="userfollowfriendpic" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg"/>
						</span>
						<span class="userfollowfriendname">
							秦始皇
						</span>
					</li>
				</ul>
			</div>
		</div>	
		<div class="userrelationfooter">
			查看更多  >
		</div>
	</div>
</div>
	
	

		<div class="maincontent">
			<div class="headiconblock">
				<img class="headicon" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg" alt="Paris">
			</div>
			<div class="usercontent">
				<div class="username">
					齐天大圣
				</div>
				<div class="usernormalinfo">
					<div class="usersendtime">
						9月初8
					</div>
					<div class="userdevice">
						苹果11pro
					</div>
					<div class="usermonmentstatus">
						已编辑
					</div>
				</div>
			</div>
			<div class="MomentInfo">
				<div class="MomentText">
					生活不止眼前的苟且，还有诗和远方。
				</div>
				<div class="MomentImgBlock">
					<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
					<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
					<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
				</div>
				
				<div class="useroperation">
					<ul class="useroperationul">
						<li class="useroperationli">
							<span><i class="iconfont iconshoucang"></i>收藏</span>
							<span class="collectcount">111</span>
						</li>
						<li class="useroperationli">
							<span><i class="iconfont iconicon--"></i>转发</span>
							<span class="sharecount">222</span>
						</li>
						<li class="useroperationli">
							<span><i class="iconfont iconpinglun"></i>评论</span>
							<span class="commentcount">333</span>
						</li>
						<li class="useroperationli">
							<span><i class="iconfont iconweibiaoti-"></i></span>
							<span class="likecount">555</span>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="maincontent">
			<div class="headiconblock">
				<img class="headicon" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg" alt="Paris">
			</div>
			<div class="usercontent">
				<div class="username">
					齐天大圣
				</div>
				<div class="usernormalinfo">
					<div class="usersendtime">
						9月初8
					</div>
					<div class="userdevice">
						苹果11pro
					</div>
					<div class="usermonmentstatus">
						已编辑
					</div>
				</div>
			</div>
			<div class="MomentInfo">
				<div class="MomentText">
					生活不止眼前的苟且，还有诗和远方。
				</div>
				<div class="MomentImgBlock">
					<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
					<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
					<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
					
				</div>
				
				<div class="useroperation">
					<ul class="useroperationul">
						<li class="useroperationli">
							<span><i class="iconfont iconshoucang"></i>收藏</span>
							<span class="collectcount">111</span>
						</li>
						<li class="useroperationli">
							<span><i class="iconfont iconicon--"></i>转发</span>
							<span class="sharecount">222</span>
						</li>
						<li class="useroperationli">
							<span><i class="iconfont iconpinglun"></i>评论</span>
							<span class="commentcount">333</span>
						</li>
						<li class="useroperationli">
							<span><i class="iconfont iconweibiaoti-"></i></span>
							<span class="likecount">555</span>
						</li>
					</ul>
				</div>
			</div>
		</div>

        <div class="maincontent">
        	<div class="headiconblock">
        		<img class="headicon" src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1418315404,2308746069&fm=26&gp=0.jpg" alt="Paris">
        	</div>
        	<div class="usercontent">
        		<div class="username">
        			齐天大圣
        		</div>
        		<div class="usernormalinfo">
        			<div class="usersendtime">
        				9月初8
        			</div>
        			<div class="userdevice">
        				苹果11pro
        			</div>
        			<div class="usermonmentstatus">
        				已编辑
        			</div>
        		</div>
        	</div>
        	<div class="MomentInfo">
        		<div class="MomentText">
        			生活不止眼前的苟且，还有诗和远方。
        		</div>
        		<div class="MomentImgBlock">
        			<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
        			<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
        			<img class="MomentImg" src="https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=345252579,3981969551&fm=15&gp=0.jpg"/>
        			
        		</div>
        		
        		<div class="useroperation">
        			<ul class="useroperationul">
        				<li class="useroperationli">
        					<span><i class="iconfont iconshoucang"></i>收藏</span>
        					<span class="collectcount">111</span>
        				</li>
        				<li class="useroperationli">
        					<span><i class="iconfont iconicon--"></i>转发</span>
        					<span class="sharecount">222</span>
        				</li>
        				<li class="useroperationli">
        					<span><i class="iconfont iconpinglun"></i>评论</span>
        					<span class="commentcount">333</span>
        				</li>
        				<li class="useroperationli">
        					<span><i class="iconfont iconweibiaoti-"></i></span>
        					<span class="likecount">555</span>
        				</li>
        			</ul>
        		</div>
        	</div>
        </div>



</div>
</body>
</html>