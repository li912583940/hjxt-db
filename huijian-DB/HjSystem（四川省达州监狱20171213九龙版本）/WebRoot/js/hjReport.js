function enterSubmit(src,e){
    var keyPressed;
    if(window.event){
   	 keyPressed = window.event.keyCode; // IE
    }else{
       keyPressed = e.which; // Firefox
    }
    if(keyPressed==13){ 
   	    chaxun();                     
        return false;
    }
}
function chaxun(){
	document.forms[0].action="hjReport.do?method=search";
	document.forms[0].submit();
}
function goCountMap(){
	document.forms[0].action="hjReport.do?method=goCountMap";
	document.forms[0].submit();
}
function whjfrMap(){
	document.forms[0].action="hjReport.do?method=whjfrMap";
	document.forms[0].submit();
}