var DATA_TYPE_JSON="1";
var DATA_TYPE_XML="2";
var DATA_TYPE_IMG="3";
var DATA_TYPE_APK="4";
var DATA_TYPE_FILE="5";

jQuery.each(["FastGet","FastPost"],function(index,method){
    //data:请求参数，会一并放入requestHeader中
    //url:请求连接
    //callback：回调函数
    //type:数据类型
    jQuery[method]=function(url,data,callback,type){
        if(type==null || type== undefined || type == ""){
            alert("error data type equal null");
            return;
        }
        return jQuery.ajax({
            url: url,
            type: (method == "FastGet" ? "get" : "post"),
            dataType: type,
            beforeSend:function(request){
                for(var a in data){
                    request.setRequestHeader(a,data[a]);
                }
                switch(type.toUpperCase()){
                    case "JSON":
                        request.setRequestHeader("dataType",DATA_TYPE_JSON);
                        break;
                    case "XML":
                        request.setRequestHeader("dataType",DATA_TYPE_XML);
                        requestFile(url,data);
                        this.abort();
                        break;
                    case "IMG":
                        request.setRequestHeader("dataType",DATA_TYPE_IMG);
                        requestFile(url,data);
                        this.abort();
                        break;
                    case "APK":
                        request.setRequestHeader("dataType",DATA_TYPE_APK);
                        requestFile(url,data);
                        this.abort();
                        break;
                    case "FILE":
                        request.setRequestHeader("dataType",DATA_TYPE_FILE);
                        requestFile(url,data);
                        this.abort();
                        break;
                    default :
                        alert("error data type："+type);
                        this.abort();
                }

            },
            data: data,
            success: callback
        });

        //不跳转页面的文件下载
        function requestFile(url,data){
            var form=$("<form>");//定义一个form表单
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("method","post");
            form.attr("action",url);
            for(var d in data){
                var input=$("<input>");
                input.attr("type","hidden");
                input.attr("name",d);
                input.val(data[d]);
                form.append(input);
            }
            var input1=$("<input>");
            input1.attr("type","hidden");
            input1.attr("name","file");
            input1.attr("value",(new Date()).getMilliseconds());
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();
        }
    }
});
