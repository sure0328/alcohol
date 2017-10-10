$.fn.setForm = function(jsonValue) {
    var obj=this;
    $.each(jsonValue, function (name, ival) {
    	//alert(name);
    	//var $oinput = obj.find("input:[name=" + name + "]"); 
    	var ss= "#"+name;
    	var $oinput=$(ss);
    	
    	if($oinput!=undefined){
    		if ($oinput.attr("type")== "radio" || $oinput.attr("type")== "checkbox"){
       		 $oinput.each(function(){
                    if(Object.prototype.toString.apply(ival) == '[object Array]'){//是复选框，并且是数组
                         for(var i=0;i<ival.length;i++){
                             if($(this).val()==ival[i])
                                $(this).attr("checked", "checked");
                         }
       	 		 }else{
                        if($(this).val()==ival)
                           $(this).attr("checked", "checked");
                    }
                });
	       	}else if($oinput.attr("type")== "textarea"){//多行文本框
	       		
	       		obj.find("[name="+name+"]").html(ival);
	       	}else{
	       		if(name=="quarter") {
			       	  if(ival=="03") {
			       		  ival="第一季度";
		           	  } else if(ival=="06") {
		           		  ival="第二季度";
		           	  } else if(ival=="09") {
		           		  ival="第三季度";
		           	  } else if(ival=="12") {
		           		  ival="第四季度";
		           	  }
	       		}
	       		$oinput.val(ival);
	    		
           }
      }
    });
};
/**  
 * 将数值四舍五入(保留2位小数)后格式化成金额形式  
 *  
 * @param num 数值(Number或者String)  
 * @return 金额格式的字符串,如'1,234,567.45'  
 * @type String  
 */    
function formatCurrency(num) {    
	if(num==undefined) {
		return "0.0";
	}
    num = num.toString().replace(/\$|\,/g,'');    
    if(isNaN(num))    
    num = "0";    
    sign = (num == (num = Math.abs(num)));    
    num = Math.floor(num*100+0.50000000001);    
    cents = num%100;    
    num = Math.floor(num/100).toString();    
    if(cents<10)    
    cents = "0" + cents;    
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)    
    num = num.substring(0,num.length-(4*i+3))+','+    
    num.substring(num.length-(4*i+3));    
    return (((sign)?'':'-') + num + '.' + cents);    
}  
