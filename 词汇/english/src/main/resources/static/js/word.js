$(function() {
    loadWord(null);
    $("#rootSpan").click(function () {
        $("#root").toggle();
    });
    $("#coreWordSpan").click(function () {
        $("#coreWord").toggle();
        $("#note").toggle();
        $("#rootTable").toggle();
    });
    $("#chineseSpan").click(function () {
        $("#chinese").toggle();
        $("#sentence").toggle();
    });
    $("#nextSpan").click(function () {
        loadWord(null);
    });
    $("#search").click(function(){
        loadWord($("#searchWord").val());
    })
});
/**
 * 加载word
 */
function loadWord(word){
    var url="";
    var param={};
    if(word){
        param={
            word:word.trim().toLowerCase()
        };
        url="/getDetailByWord";
    }else{
        url="/randomWord";
        $(".wordExplain").hide();
    }
    $(".wordExplain").not("#rootTable").html("");
    $("#wordtbody").html("");
    $.ajax({
        url:url,
        dataType:"JSON",
        data:param,
        success:function(data){
            if(data.code==200){//如果成功
                var worddata=data.extend.word;//得到数据
                $("#word").html("<h1><span id='currentWord'>"+worddata.word+"</span>"+worddata.phonetic+"</h1>");
                $("#root").html(worddata.root);//词根
                var note=""
                if(worddata.coreword){
                    loadWordsByCoreWord(worddata.coreword);//加载核心词相关单词
                }
                if(worddata.core){//如果存在核心词对象
                    $("#coreWord").html("<b>核心词："+worddata.core.word+worddata.core.phonetic+"</b>");//核心词
                    note+="---------核心词解析----------<br/>";
                    note+=worddata.core.note+"<br/><div id='corewordimgdiv'></div>";
                    note+="-------------------<br/>";
                    getWordImgHtml(worddata.core.word,"corewordimgdiv");//这个单词核心词的图片
                }
                note+=worddata.note;//解释
                note+="<br/>";
                var word=worddata.word;//单词
                note+="<div id='wordimgdiv'></div>";
                getWordImgHtml(word,"wordimgdiv");//这个单词的图片
                $("#note").html(note);
                $("#chinese").html(worddata.chinese);
                if(worddata.coreword){
                    $("#sentence").html(worddata.sentence);
                }

            }else{
                alert(data.extend.errMsg);
            }
        }
    });
}
function getWordImgHtml(word,divid){
    var imageFullPath="/images/"+word+".png";//图片以imageDir//单词名.png来命名
    $.ajax({
        url:imageFullPath,
        success:function(){
            $("#"+divid).html("<img src='"+imageFullPath+"'/><br/>");
        }
    });
    // if(isHasImg(imageFullPath)){
    //     return "<img src='"+imageFullPath+"'/><br/>";
    // }else{
    //     return "";
    // }
}
/**
 * 判断是否存在图片
 * @param pathImg
 * @returns {boolean}
 */
function isHasImg(pathImg){
    var ImgObj=new Image();
    ImgObj.src= pathImg;
    if(ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0))
    {
        return true;
    } else {
        return false;
    }
}
/**
 * 加载word
 * @param pageNum页数
 */
function loadWordsByCoreWord(coreWord){
    var param={
        coreWord:coreWord
    };
    $.ajax({
        url:"/getWordsByCoreWord",
        dataType:"JSON",
        data:param,
        success:function(data){
            if(data.code==200){//如果成功
                var datas=data.extend.words;//得到数据
                var datahtml="";
                $.each(datas,function (i,word) {
                    datahtml+="<tr";
                    if(word.word==$("#currentWord").text()){
                        datahtml+=" style='color:red'";
                    }
                    datahtml+=">";
                    datahtml+="<td>";
                    datahtml+=word.word+word.phonetic;
                    datahtml+="<button onclick=\"loadWord('"+word.word+"')\"><span class='glyphicon glyphicon-eye-open'></span></button>";
                    datahtml+="</td>";
                    datahtml+="<td>";
                    datahtml+=word.chinese;
                    datahtml+="</td>";
                    datahtml+="</tr>";
                });
                $("#wordtbody").html(datahtml);//加载到tbody中
            }else{
                alert(data.extend.errMsg);
            }
        }
    });
}