$(function(){
    $("#saveBtn").click(function(){
        addObj.save();
    });
});
var addObj = {
    save:function(){
        var name = $("#name").val();
        name = $.trim(name);
        if(name==''){
            alert("姓名不能为空！");
            return;
        }
        var groupName = $("#groupName").val();
        var yesterday = $("#yesterdayC").val();
        var today = $("#todayC").val();
        $.ajax({
            url:"./record/add",
            type:"post",
            data:{
                "name":name,
                "groupName":groupName,
                "yesterdayC":yesterday,
                "todayC":today
            },
            success:function(data){
                if(data.code=='0'){
                    window.location.href="./index";
                }else{
                    alert(data.desc);
                }
            },
            error:function(request,status,e){
                alert("保存异常！");
            }
        });
    }
}