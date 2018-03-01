

//==身份证号码验证    
jQuery.validator.addMethod("isIdCardNo", function(value, element) {    
	return this.optional(element) || isIdCardNo(value);    
});
  
//==用户名输入规则验证    
jQuery.validator.addMethod("userName", function(value, element) {    
	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);    
});

//邮箱前缀校验
jQuery.validator.addMethod("mailPrefix", function(value, element) {    
	return this.optional(element) || /^\w+([\.-]?\w+)*$/i.test(value);
});

//检查IPHONE字典是否合法
jQuery.validator.addMethod("idir", function(value, element) {    
	return this.optional(element) || /^\w+[:]\w+([\.-]?\w+)*$/i.test(value);
});

//==手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element){
    var length = value.length;
    return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
});

//==电话号码验证    
jQuery.validator.addMethod("isPhone", function(value, element){
    var tel = /^(((\+86)|(86)|(086))\-?)?((0[1-9]\d{1,2}[-]?)|(\(0[1-9]\d{1,2}\)?))?([1-9]\d{6,7})(\-\d{1,4})?$/;
    return this.optional(element) || (tel.test(value));
});

//==邮政编码验证    
jQuery.validator.addMethod("isZipCode", function(value, element){
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
});

jQuery.extend(jQuery.validator.messages, {
    required: "<label style=\"color: #ff0000\"> 必填</label>",
    remote: "<label style=\"color: #ff0000\"> <br/>请修正该字段</label>",
    email: "<label style=\"color: #ff0000\"> <br/>电子邮件格式非法</label>",
    url: "<label style=\"color: #ff0000\"> <br/>请输入合法的网址</label>",
    date: "<label style=\"color: #ff0000\"> <br/>请输入合法的日期</label>",
    dateISO: "<label style=\"color: #ff0000\"> <br/>请输入合法的日期 (ISO).</label>",
    number: "<label style=\"color: #ff0000\"> <br/>请输入合法的数字</label>",
    digits: "<label style=\"color: #ff0000\"> <br/>只能输入整数</label>",
    creditcard: "<label style=\"color: #ff0000\"> <br/>请输入合法的信用卡号</label>",
    equalTo: "<label style=\"color: #ff0000\"> <br/>请再次输入相同的值</label>",
    accept: "<label style=\"color: #ff0000\"> <br/>请输入拥有合法后缀名的字符串</label>",
    maxlength: jQuery.format("<label style=\"color: #ff0000\"> <br/>长度最多是 {0} 位的字符串</label>"),
    minlength: jQuery.format("<label style=\"color: #ff0000\"> <br/>长度最少是 {0} 位的字符串</label>"),
    rangelength: jQuery.format("<label style=\"color: #ff0000\"> <br/>长度应介于 {0} 和 {1} 之间的字符串</label>"),
    range: jQuery.format("<label style=\"color: #ff0000\"> <br/>请输入一个介于 {0} 和 {1} 之间的值</label>"),
    max: jQuery.format("<label style=\"color: #ff0000\"> <br/>请输入一个最大为 {0} 的值</label>"),
    min: jQuery.format("<label style=\"color: #ff0000\"> <br/>请输入一个最小为 {0} 的值</label>"),
	isIdCardNo:"<label style=\"color: #ff0000\"> <br/>请输入正确的身份证号码</label>",
	userName:"<label style=\"color: #ff0000\"> <br/>只能输入汉字、英文字母、数字和下划线</label>",
	isMobile:"<label style=\"color: #ff0000\"> <br/>请输入正确的手机号码</label>",
	isPhone:"<label style=\"color: #ff0000\"> <br/>请输入正确的电话号码</label>",
	isZipCode:"<label style=\"color: #ff0000\"> <br/>请输入正确的邮政编码</label>",
	mailPrefix:"<label style=\"color: #ff0000\"> <br/>邮箱前缀不合符</label>",
	idir:"<label style=\"color: #ff0000\"> <br/>格式不合法（例：a:b）</label>"
});
