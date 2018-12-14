$(function() {
    loadWord();
    $("#rootSpan").click(function () {
        $("#root").toggle();
    });
    $("#coreWordSpan").click(function () {
        $("#coreWord").toggle();
        $("#note").toggle();
    });
    $("#chineseSpan").click(function () {
        $("#chinese").toggle();
        $("#sentence").toggle();
    });
    $("#nextSpan").click(loadWord);
});
/**
 * 加载word
 */
function loadWord(){
    $(".wordExplain").hide();
    $.ajax({
        url:"/randomWord",
        type:"GET",
        dataType:"JSON",
        success:function(data){
            if(data.code==200){//如果成功
                var worddata=data.extend.word;//得到数据
                $("#word").html("<h1>"+worddata.word+worddata.phonetic+"</h1>");
                $("#root").html(worddata.root);//词根
                $("#coreWord").html(worddata.coreword);//核心词
                var note="";
                if(worddata.core){//如果存在核心词对象
                    note+="---------核心词----------<br/>";
                    note+=worddata.core.note+"<br/>";
                    note+=getWordImgHtml(worddata.core.word);
                    note+="-------------------<br/>";
                }
                note+=worddata.note;//解释
                note+="<br/>";
                var word=worddata.word;//单词
                note+=getWordImgHtml(word);
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
function getWordImgHtml(word){
    var imageFullPath="/images/"+word+".png";//图片以imageDir//单词名.png来命名
    if(isHasImg(imageFullPath)){
        return "<img src='"+imageFullPath+"'/><br/>";
    }else{
        return "";
    }
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