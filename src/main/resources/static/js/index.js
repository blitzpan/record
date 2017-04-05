$(function(){
    indexObj.query();
    $("#queryBtn").click(function(){
        indexObj.query();
    });
    $("#groupName").change(function(){
        indexObj.query();
    });
    $("#exportBtn").click(function(){
        indexObj.export();
    });
});
var indexObj = {
    query:function(){
        var groupName = $("#groupName").val();
        $.ajax({
            url:"./record/listToday",
            type:"post",
            data:{
                "groupName":groupName
            },
            success:function(data){
                console.log(data)
                if(data.code=='0'){
                    $("#tableData").empty();
                    var innerHtml = "<tr><th>姓名</th><th>昨天</th><th>今天</th></tr>";
                    for(i=0; i<data.obj.length; i++){
                        innerHtml += "<tr><td>"+data.obj[i]['name']+"</td>" +
                                         "<td>"+data.obj[i]['yesterdayC']+"</td>" +
                                         "<td>"+data.obj[i]['todayC']+"</td></tr>";
                    }
                    $("#tableData").append(innerHtml);
                }else{
                    alert(data.desc);
                }
            },
            error:function(request,status,e){
                alert("查询今日记录异常！");
            }
        });
    },
    export:function(){
        var url = "./record/download";
        var groupName = $("#groupName").val();
        url = url + "?groupName=" + groupName + "&exFileName" + "=" + $("#groupName").find("option:selected").text();
        url = encodeURI(encodeURI(url));
        try{
            var elemIF = document.createElement("iframe");
            elemIF.src = url;
            elemIF.style.display = "none";
            document.body.appendChild(elemIF);
        }catch(e){
            alert("下载出现异常！");
        }
    }
}