/**
 *  日期类型格式转换
 *  //将一个 Date 格式化为日期/时间字符串。
 *  alert(  DateFormat.format(new Date(),'yyyy年MM月dd日')  );
 *  DateFormat.format(new Date(),'yyyy-MM-dd hh:mm:ss');
 *  //从给定字符串的开始分析文本，以生成一个日期。
 *  alert(  DateFormat.parse('2010-03-17','yyyy-MM-dd')  );
 *
 */
function format(value,patten){
	if (value != null) {
		return DateFormat.format(value,patten);
	}
	return value;
}
DateFormat = (function(){
    var SIGN_REGEXP = /([yMdhsm])(\1*)/g;
    var DEFAULT_PATTERN = 'yyyy-MM-dd';
    function padding(s, len){
        var len = len - (s + '').length;
        for (var i = 0; i < len; i++) {
            s = '0' + s;
        }
        return s;
    };
    return ({
        format: function(value, pattern){
            var date = new Date();
            date.setTime(value);
            pattern = pattern || DEFAULT_PATTERN;
            return pattern.replace(SIGN_REGEXP, function($0){
                switch ($0.charAt(0)) {
                    case 'y':
                        return padding(date.getFullYear(), $0.length);
                    case 'M':
                        return padding(date.getMonth() + 1, $0.length);
                    case 'd':
                        return padding(date.getDate(), $0.length);
                    case 'w':
                        return date.getDay() + 1;
                    case 'h':
                        return padding(date.getHours(), $0.length);
                    case 'm':
                        return padding(date.getMinutes(), $0.length);
                    case 's':
                        return padding(date.getSeconds(), $0.length);
                }
            });
        },
        parse: function(dateString, pattern){
            var matchs1 = pattern.match(SIGN_REGEXP);
            var matchs2 = dateString.match(/(\d)+/g);
            if (matchs1.length == matchs2.length) {
                var _date = new Date(1970, 0, 1);
                for (var i = 0; i < matchs1.length; i++) {
                    var _int = parseInt(matchs2[i]);
                    var sign = matchs1[i];
                    switch (sign.charAt(0)) {
                        case 'y':
                            _date.setFullYear(_int);
                            break;
                        case 'M':
                            _date.setMonth(_int - 1);
                            break;
                        case 'd':
                            _date.setDate(_int);
                            break;
                        case 'h':
                            _date.setHours(_int);
                            break;
                        case 'm':
                            _date.setMinutes(_int);
                            break;
                        case 's':
                            _date.setSeconds(_int);
                            break;
                    }
                }
                return _date;
            }
            return null;
        }
    });
})();
