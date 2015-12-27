/**
 * 常量
 */
constant={
	/**子系统domain配置参数*/
	DOMAIN:{"logon":"/logon?jsonCallBack=?",
			"main":"/main",
			"logonJsp":"/pages/login/default/login.jsp"},
	/**登陆成功*/
	LOGIN_STATE:{
		"SUCCESS":"1"/**登陆成功*/
	},
	/**登陆URL*/
	getLogonURL:function(domain){
		var logonURL="http://"+domain+this.DOMAIN.logon;
		return logonURL;
	},
	/**登陆URL jsp*/
	getLogonURLJsp:function(domain){
		var logonURLJsp="http://"+domain+this.DOMAIN.logonJsp;
		return logonURLJsp;
	},
	/**主页面URL*/
	getMainURL:function(domain){
		var mainURL="http://"+domain+this.DOMAIN.main;
		return mainURL;
	}
};
