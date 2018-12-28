$(function(){
    initDialog();
    loadPage(1);
    $("#search").click(function () {
        loadPage(1);
    });
    $("#importWord").click(importWord);//点击导入可以导入expense
});

function importWord(){
//检验导入的文件是否为Excel文件
    var uploadFile =$("#uploadFile").val();
    if(uploadFile == null || uploadFile == ""){//判断是否已经选择文件
        alert("please select the file");
        return false;
    }else{
        $('#myModal').modal('show');//loading框显示
        var fileExtend = uploadFile.substring(uploadFile.lastIndexOf('.')).toLowerCase();
        if(fileExtend == '.xls'||fileExtend == '.xlsx'){//文件后缀名是否符合标准
            var formData = new FormData();
            formData.append('uploadFile', $("#uploadFile")[0].files[0]);//文件
            $.ajax({
                url: "/importWord",
                type: "POST",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                data: formData,
                success: function (data) {
                    $('#myModal').modal('hide');//loading框显示
                    if(data.code==200) {//如果成功
                        alert("import success");
                        window.location.reload();
                    }else{
                        var errorHtml="";
                        errorHtml+=data.extend.errMsg+"<br/>";
                        if (data.extend.words){
                            var existWords="";
                            $.each(data.extend.words,function(i,word){
                                existWords+=word.word+",";
                            });
                            if(data.extend.words.length>1){
                                existWords=existWords.substring(0,existWords.length-1);
                            }
                            errorHtml+=existWords;
                        }
                        $("#importError").html(errorHtml);
                        $("#importError").show();
                    }
                }
            });
        }else{
            alert("file's suffix must be xls or xlsx");
            return false;
        }
    }
}
/**
 * 加载word
 * @param pageNum页数
 */
function loadPage(pageNum){
    $("#importError").hide();
    $('#myModal').modal('show');//loading框显示
    var pn=1;//默认为第1页
    if(pageNum){
        pn=pageNum;
    }
    var param={
        pn:pn
    };
    if($("#searchWord").val()){
        param.word=$("#searchWord").val().toLowerCase().trim();
    }
    $.ajax({
        url:"/word/page",
        type:"POST",
        dataType:"JSON",
        data:param,
        success:function(data){
            $('#myModal').modal('hide');//loading框关闭
            if(data.code==200){//如果成功
                var datas=data.extend.pageInfo.list;//得到数据
                var datahtml="";
                $.each(datas,function (i,word) {
                    datahtml+="<tr>";
                    datahtml+="<td>";
                    datahtml+=word.word;
                    datahtml+="</td>";
                    datahtml+="<td>";
                    datahtml+=word.root;
                    datahtml+="</td>";
                    datahtml+="<td>";
                    datahtml+=word.chinese;
                    datahtml+="</td>";
                    datahtml+="<td>";
                    datahtml+="<button type='button' class='btn btn-default' ";
                    datahtml+="onclick=\"toEdit("+word.id+")\">";
                    datahtml+="<span class='glyphicon glyphicon-pencil'>Edit</span></button>"
                    datahtml+="&nbsp;&nbsp;";
                    datahtml+="<button type='button' class='btn btn-default' ";
                    datahtml+="onclick=\"del("+word.id+")\">";
                    datahtml+="<span class='glyphicon glyphicon-remove'>Del</span></button>";
                    datahtml+="</td>";
                    datahtml+="</tr>";
                });
                $("#wordtbody").html(datahtml);//加载到tbody中
                //page
                if(data.extend.pageInfo.list.length>0){//如果有值
                    var pagehtml=drawPageDiv(data.extend.pageInfo,"loadPage");//渲染分页
                    $("#wordpage").html(pagehtml);
                }else{
                    $("#wordpage").html("");
                }
            }else{
                alert(data.extend.errMsg);
            }
        }
    });
}
function toEdit(id){
    $.ajax({
            url: "/word/" + id,
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                var worddata = data.extend.word;
                if (data.code == 200) {
                    $("#dialog-form").dialog("open");
                    $("#word").val(worddata.word);
                    $("#root").val(worddata.root);
                    $("#coreWord").val(worddata.coreword);
                    $("#note").val(worddata.note);
                    $("#chinese").val(worddata.chinese);
                    $("#sentence").val(worddata.sentence);
                    $("#wordId").val(id);
                }else{
                    alert(data.extend.errMsg);
                }
            }
        });
}
/**
 * 初始化添加/更新DIV
 */
function initDialog() {
    $("#dialog-form").dialog({
        autoOpen: false,
        height: 700,
        width: 800,
        modal: true,
        buttons: {
            "OK": function () {//OK按钮
                edit();
                $(this).dialog("close");
            },
            Cancel: function () {//关闭按钮
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}
function edit(){
    if($("#word").val()==""){
        alert("word is empty");
        return false;
    }
    var param={
        chinese:$("#chinese").val(),
        sentence:$("#sentence").val(),
        root:$("#root").val(),
        coreword:$("#coreWord").val(),
        note:$("#note").val(),
        _method:"PUT"
    };
    $.ajax({
        url:"/word/"+$("#wordId").val(),
        type:"POST",
        dataType:"JSON",
        data:param,
        success:function(data){
            if(data.code==200){//如果成功，重新加载。由于加载时loading框会关闭，所以这里不用关闭
                $("#dialog-form").dialog("close");
                loadPage(1);
            }else{
                $('#myModal').modal('show');
                alert(data.extend.errMsg);
            }
        }
    });
}
/** delete
 * 需要删除的id
 * @param id
 */
function del(id){
    if(confirm('do you confirm to delete?')){//确认是否删除
        $('#myModal').modal('show');//loading框显示
        var param={
            _method:"DELETE",
            id:id
        };
        $.ajax({
            url:"/word/"+id,
            type:"POST",
            dataType:"JSON",
            data:param,
            success:function(data){
                if(data.code==200){
                    loadPage(1);
                }else{
                    $('#myModal').modal('hide');
                    alert(data.extend.errMsg);
                }
            }
        });
    }
}
