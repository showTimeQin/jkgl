;(function($){var gl_show_ajax_indicator=false;var arrOptions=new Array();var strLastValue="";var bMadeRequest;var theTextBox;var objLastActive;var currentValueSelected=-1;var bNoResults=false;var isTiming=false;var $suggestList=$("#suggest_output");$(document).ready(function(){if(document.search_form){document.search_form.keyword.obj=SetProperties(document.search_form.keyword,null,true,true,true,true,"请点击查询按钮查找您想要的结果:)",false,null,1);};});function SetProperties(xElem,xHidden,xignoreCase,xmatchAnywhere,xmatchTextBoxWidth,xshowNoMatchMessage,xnoMatchingDataMessage,xuseTimeout,xtheVisibleTime,xToLink){var props={elem:xElem,hidden:xHidden,regExFlags:((xignoreCase)?"i":""),regExAny:((xmatchAnywhere)?"^":""),matchAnywhere:xmatchAnywhere,matchTextBoxWidth:xmatchTextBoxWidth,theVisibleTime:xtheVisibleTime,showNoMatchMessage:xshowNoMatchMessage,noMatchingDataMessage:xnoMatchingDataMessage,useTimeout:xuseTimeout,isToLink:xToLink};AddHandler(xElem);return props;}
var isOpera=(navigator.userAgent.toLowerCase().indexOf("opera")!=-1);var isIE=(navigator.userAgent.toLowerCase().indexOf("msie")!=-1)
function AddHandler(objText){objText.onkeyup=GiveOptions;if(isIE)objText.onkeypress=catch_enter;if(isOpera)objText.onkeypress=catch_enter;}
function catch_enter(){var intKey=-1;if(window.event){intKey=event.keyCode;theTextBox=event.srcElement;}
if(objLastActive==theTextBox){if(intKey==13){GrabHighlighted();theTextBox.blur();return false;}}}
function GiveOptions(e){var intKey=-1;if(window.event){intKey=event.keyCode;theTextBox=event.srcElement;}
else{intKey=e.which;theTextBox=e.target;}
if(theTextBox.obj.useTimeout){if(isTiming)EraseTimeout();StartTimeout();}
if(theTextBox.value.length==0&&!isOpera){arrOptions=new Array();HideTheBox();strLastValue="";return false;}
if(objLastActive==theTextBox){if(intKey==13){GrabHighlighted();theTextBox.blur();return false;}
else if(intKey==38){MoveHighlight(-1);return false;}
else if(intKey==40){MoveHighlight(1);return false;}
else if(intKey==37||intKey==39){return false;}}
if(objLastActive!=theTextBox||theTextBox.value.indexOf(strLastValue)!=0||((arrOptions.length==0||arrOptions.length==15)&&!bNoResults)||(theTextBox.value.length!=strLastValue.length)){objLastActive=theTextBox;bMadeRequest=true;TypeAhead(theTextBox.value.replace(/\s+/g,""));}
else if(!bMadeRequest){}
strLastValue=theTextBox.value.replace(/\s+/g,"");}
function TypeAhead(xStrText){$.post("/food/suggest_search",{keyword:xStrText},function(data){BuildChoices(data);});}
function BuildChoices(strText){arrOptions=strText.split(',');for(i=0;i<arrOptions.length;i++){arrOptions[i]=arrOptions[i].split(";");}
BuildList(strLastValue);bMadeRequest=false;}
function BuildList(theText){SetElementPosition(theTextBox);var theMatche=MakeMatches(theText);theMatches="<span id='be_replace'>"+theMatche.join().replace(/\,/gi,"")+"</span>";if(theMatche.length>0){$("#be_replace").replaceWith(theMatches);currentValueSelected=0;bNoResults=false;}
else{currentValueSelected=-1;bNoResults=true;if(theTextBox.obj.showNoMatchMessage){innerHTML="<span class = 'stress' id='be_replace'>"+theTextBox.obj.noMatchingDataMessage+"</span>";$("#be_replace").replaceWith(innerHTML);}
else
HideTheBox();}}
var countForId=0;function MakeMatches(xCompareStr){countForId=0;var matchArray=new Array();var regExp=new RegExp(xCompareStr,theTextBox.obj.regExFlags);for(i=0;i<arrOptions.length-1;i++){if(matchArray.length<10){var theMatch=arrOptions[i][0].match(regExp)||arrOptions[i][1].match(regExp);if(theMatch){matchArray[matchArray.length]=CreateUnderline(arrOptions[i][0],xCompareStr,i);}}
else break;}
return matchArray;}
function SetElementPosition(theTextBoxInt){$suggestList.css({"width":$(theTextBox.obj.elem).outerWidth(),"display":"block"});}
var undeStart="<em>";var undeEnd="</em>";var selectSpanStart="<li ";var selectSpanEnd="</li>";function CreateUnderline(xStr,xTextMatch,xVal){var selectSpanMid="data-value='"+xVal+"' "+"id='OptionsList_"+countForId+"' theArrayNumber='"+xVal+"'>";var regExp=new RegExp(xTextMatch,theTextBox.obj.regExFlags);var aStart=xStr.search(regExp);var matchedText=xStr.substring(aStart,aStart+xTextMatch.length);countForId++;return selectSpanStart+selectSpanMid+xStr.replace(regExp,undeStart+matchedText+undeEnd)+selectSpanEnd;}
function MoveHighlight(xDir){if(currentValueSelected>=0){newValue=parseInt(currentValueSelected)+parseInt(xDir);if(newValue>-1&&newValue<countForId){currentValueSelected=newValue;}
xVal=document.getElementById("OptionsList_"+currentValueSelected).getAttribute("theArrayNumber");theTextBox.value=arrOptions[xVal][0];}}
function SetText(xVal){theTextBox.value=arrOptions[xVal][0];$suggestList.css("display","none");currentValueSelected=-1;if(theTextBox.obj.isToLink==1){open("/shiwu/"+arrOptions[xVal][1]);}}
function GrabHighlighted(){if(currentValueSelected>=0){xVal=document.getElementById("OptionsList_"+currentValueSelected).getAttribute("theArrayNumber");SetText(xVal);HideTheBox();}}
function HideTheBox(){$suggestList.css("display","none");currentValueSelected=-1;}
$suggestList.on("click","li",function(e){var $target=$(e.target);SetText($target.data("value"));})
$(document).mouseup(function(e){var _con=$suggestList;if(!_con.is(e.target)&&_con.has(e.target).length===0){$suggestList.css({"display":"none"});}});})(jQuery);;(function($){$("#main").on("click",".more",function(e){var $target=$(e.target),$info=$("."+$target.data("info"));$target.text($info.hasClass("hide")?">> 简要":">> 详细");$info.toggleClass("hide");});$(document).ready(function(){var options={imageBtnClose:'/images/movement/book0/lightbox-btn-close.gif',imageBtnPrev:'/images/movement/book0/lightbox-btn-prev.gif',imageBtnNext:'/images/movement/book0/lightbox-btn-next.gif'};$('a.lightbox').lightBox(options);$('a.lightbox').lightBox(options);$('a.lightbox1').lightBox(options);$('a.lightbox2').lightBox(options);$('a.lightbox').lightBox(options);});}(jQuery));(function($){$.fn.lightBox=function(settings){settings=jQuery.extend({overlayBgColor:'#000',overlayOpacity:0.8,fixedNavigation:false,imageLoading:'/stylesheets/lightbox/lightbox-ico-loading.gif',imageBtnPrev:'/stylesheets/lightbox/lightbox-btn-prev.gif',imageBtnNext:'/stylesheets/lightbox/lightbox-btn-next.gif',imageBtnClose:'/stylesheets/lightbox/lightbox-btn-close.gif',imageBlank:'/stylesheets/lightbox/lightbox-blank.gif',containerBorderSize:10,containerResizeSpeed:400,txtImage:'Image',txtOf:'of',keyToClose:'c',keyToPrev:'p',keyToNext:'n',imageArray:[],activeImage:0},settings);var jQueryMatchedObj=this;function _initialize(){_start(this,jQueryMatchedObj);return false;}
function _start(objClicked,jQueryMatchedObj){$('embed, object, select').css({'visibility':'hidden'});_set_interface();settings.imageArray.length=0;settings.activeImage=0;if(jQueryMatchedObj.length==1){settings.imageArray.push(new Array(objClicked.getAttribute('href'),objClicked.getAttribute('title')));}else{for(var i=0;i<jQueryMatchedObj.length;i++){settings.imageArray.push(new Array(jQueryMatchedObj[i].getAttribute('href'),jQueryMatchedObj[i].getAttribute('title')));}}
while(settings.imageArray[settings.activeImage][0]!=objClicked.getAttribute('href')){settings.activeImage++;}
_set_image_to_view();}
function _set_interface(){$('body').append('<div id="jquery-overlay"></div><div id="jquery-lightbox"><div id="lightbox-container-image-box"><div id="lightbox-container-image"><img id="lightbox-image"><div style="" id="lightbox-nav"><a href="#" id="lightbox-nav-btnPrev"></a><a href="#" id="lightbox-nav-btnNext"></a></div><div id="lightbox-loading"><a href="#" id="lightbox-loading-link"><img src="'+settings.imageLoading+'"></a></div></div></div><div id="lightbox-container-image-data-box"><div id="lightbox-container-image-data"><div id="lightbox-image-details"><span id="lightbox-image-details-caption"></span><span id="lightbox-image-details-currentNumber"></span></div><div id="lightbox-secNav"><a href="#" id="lightbox-secNav-btnClose"><img src="'+settings.imageBtnClose+'"></a></div></div></div></div>');var arrPageSizes=___getPageSize();$('#jquery-overlay').css({backgroundColor:settings.overlayBgColor,opacity:settings.overlayOpacity,width:arrPageSizes[0],height:arrPageSizes[1]}).fadeIn();var arrPageScroll=___getPageScroll();$('#jquery-lightbox').css({top:arrPageScroll[1]+(arrPageSizes[3]/10),left:arrPageScroll[0]}).show();$('#jquery-overlay,#jquery-lightbox').click(function(){_finish();});$('#lightbox-loading-link,#lightbox-secNav-btnClose').click(function(){_finish();return false;});$(window).resize(function(){var arrPageSizes=___getPageSize();$('#jquery-overlay').css({width:arrPageSizes[0],height:arrPageSizes[1]});var arrPageScroll=___getPageScroll();$('#jquery-lightbox').css({top:arrPageScroll[1]+(arrPageSizes[3]/10),left:arrPageScroll[0]});});}
function _set_image_to_view(){$('#lightbox-loading').show();if(settings.fixedNavigation){$('#lightbox-image,#lightbox-container-image-data-box,#lightbox-image-details-currentNumber').hide();}else{$('#lightbox-image,#lightbox-nav,#lightbox-nav-btnPrev,#lightbox-nav-btnNext,#lightbox-container-image-data-box,#lightbox-image-details-currentNumber').hide();}
var objImagePreloader=new Image();objImagePreloader.onload=function(){$('#lightbox-image').attr('src',settings.imageArray[settings.activeImage][0]);_resize_container_image_box(objImagePreloader.width,objImagePreloader.height);objImagePreloader.onload=function(){};};objImagePreloader.src=settings.imageArray[settings.activeImage][0];};function _resize_container_image_box(intImageWidth,intImageHeight){var intCurrentWidth=$('#lightbox-container-image-box').width();var intCurrentHeight=$('#lightbox-container-image-box').height();var intWidth=(intImageWidth+(settings.containerBorderSize*2));var intHeight=(intImageHeight+(settings.containerBorderSize*2));var intDiffW=intCurrentWidth-intWidth;var intDiffH=intCurrentHeight-intHeight;$('#lightbox-container-image-box').animate({width:intWidth,height:intHeight},settings.containerResizeSpeed,function(){_show_image();});if((intDiffW==0)&&(intDiffH==0)){if($.browser.msie){___pause(250);}else{___pause(100);}}
$('#lightbox-container-image-data-box').css({width:intImageWidth});$('#lightbox-nav-btnPrev,#lightbox-nav-btnNext').css({height:intImageHeight+(settings.containerBorderSize*2)});};function _show_image(){$('#lightbox-loading').hide();$('#lightbox-image').fadeIn(function(){_show_image_data();_set_navigation();});_preload_neighbor_images();};function _show_image_data(){$('#lightbox-container-image-data-box').slideDown('fast');$('#lightbox-image-details-caption').hide();if(settings.imageArray[settings.activeImage][1]){$('#lightbox-image-details-caption').html(settings.imageArray[settings.activeImage][1]).show();}
if(settings.imageArray.length>1){$('#lightbox-image-details-currentNumber').html(settings.txtImage+' '+(settings.activeImage+1)+' '+settings.txtOf+' '+settings.imageArray.length).show();}}
function _set_navigation(){$('#lightbox-nav').show();$('#lightbox-nav-btnPrev,#lightbox-nav-btnNext').css({'background':'transparent url('+settings.imageBlank+') no-repeat'});if(settings.activeImage!=0){if(settings.fixedNavigation){$('#lightbox-nav-btnPrev').css({'background':'url('+settings.imageBtnPrev+') left 15% no-repeat'}).unbind().bind('click',function(){settings.activeImage=settings.activeImage-1;_set_image_to_view();return false;});}else{$('#lightbox-nav-btnPrev').unbind().hover(function(){$(this).css({'background':'url('+settings.imageBtnPrev+') left 15% no-repeat'});},function(){$(this).css({'background':'transparent url('+settings.imageBlank+') no-repeat'});}).show().bind('click',function(){settings.activeImage=settings.activeImage-1;_set_image_to_view();return false;});}}
if(settings.activeImage!=(settings.imageArray.length-1)){if(settings.fixedNavigation){$('#lightbox-nav-btnNext').css({'background':'url('+settings.imageBtnNext+') right 15% no-repeat'}).unbind().bind('click',function(){settings.activeImage=settings.activeImage+1;_set_image_to_view();return false;});}else{$('#lightbox-nav-btnNext').unbind().hover(function(){$(this).css({'background':'url('+settings.imageBtnNext+') right 15% no-repeat'});},function(){$(this).css({'background':'transparent url('+settings.imageBlank+') no-repeat'});}).show().bind('click',function(){settings.activeImage=settings.activeImage+1;_set_image_to_view();return false;});}}
_enable_keyboard_navigation();}
function _enable_keyboard_navigation(){$(document).keydown(function(objEvent){_keyboard_action(objEvent);});}
function _disable_keyboard_navigation(){$(document).unbind();}
function _keyboard_action(objEvent){if(objEvent==null){keycode=event.keyCode;escapeKey=27;}else{keycode=objEvent.keyCode;escapeKey=objEvent.DOM_VK_ESCAPE;}
key=String.fromCharCode(keycode).toLowerCase();if((key==settings.keyToClose)||(key=='x')||(keycode==escapeKey)){_finish();}
if((key==settings.keyToPrev)||(keycode==37)){if(settings.activeImage!=0){settings.activeImage=settings.activeImage-1;_set_image_to_view();_disable_keyboard_navigation();}}
if((key==settings.keyToNext)||(keycode==39)){if(settings.activeImage!=(settings.imageArray.length-1)){settings.activeImage=settings.activeImage+1;_set_image_to_view();_disable_keyboard_navigation();}}}
function _preload_neighbor_images(){if((settings.imageArray.length-1)>settings.activeImage){objNext=new Image();objNext.src=settings.imageArray[settings.activeImage+1][0];}
if(settings.activeImage>0){objPrev=new Image();objPrev.src=settings.imageArray[settings.activeImage-1][0];}}
function _finish(){$('#jquery-lightbox').remove();$('#jquery-overlay').fadeOut(function(){$('#jquery-overlay').remove();});$('embed, object, select').css({'visibility':'visible'});}
function ___getPageSize(){var xScroll,yScroll;if(window.innerHeight&&window.scrollMaxY){xScroll=window.innerWidth+window.scrollMaxX;yScroll=window.innerHeight+window.scrollMaxY;}else if(document.body.scrollHeight>document.body.offsetHeight){xScroll=document.body.scrollWidth;yScroll=document.body.scrollHeight;}else{xScroll=document.body.offsetWidth;yScroll=document.body.offsetHeight;}
var windowWidth,windowHeight;if(self.innerHeight){if(document.documentElement.clientWidth){windowWidth=document.documentElement.clientWidth;}else{windowWidth=self.innerWidth;}
windowHeight=self.innerHeight;}else if(document.documentElement&&document.documentElement.clientHeight){windowWidth=document.documentElement.clientWidth;windowHeight=document.documentElement.clientHeight;}else if(document.body){windowWidth=document.body.clientWidth;windowHeight=document.body.clientHeight;}
if(yScroll<windowHeight){pageHeight=windowHeight;}else{pageHeight=yScroll;}
if(xScroll<windowWidth){pageWidth=xScroll;}else{pageWidth=windowWidth;}
arrayPageSize=new Array(pageWidth,pageHeight,windowWidth,windowHeight);return arrayPageSize;};function ___getPageScroll(){var xScroll,yScroll;if(self.pageYOffset){yScroll=self.pageYOffset;xScroll=self.pageXOffset;}else if(document.documentElement&&document.documentElement.scrollTop){yScroll=document.documentElement.scrollTop;xScroll=document.documentElement.scrollLeft;}else if(document.body){yScroll=document.body.scrollTop;xScroll=document.body.scrollLeft;}
arrayPageScroll=new Array(xScroll,yScroll);return arrayPageScroll;};function ___pause(ms){var date=new Date();curDate=null;do{var curDate=new Date();}
while(curDate-date<ms);};return this.unbind('click').click(_initialize);};})(jQuery);