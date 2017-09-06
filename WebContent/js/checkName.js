/**
 * 
 */
window.onload=function(){
	document.querySelector("#img").onclick =function(){
		this.src="CodeServlet.do?id="+Math.random();
	}
	document.querySelector("#checkuser").onclick=function(){
		
		var uname=document.querySelector("#uname").value;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", "checkname.do?uname="+uname, true);
		xmlhttp.send(null);
		xmlhttp.onreadystatechange=function(){
			
			if(xmlhttp.readyState==4){
				
				if(xmlhttp.status==200){
					var data = xmlhttp.responseText;
					var sp = document.querySelector("#sp");
					if(data=="1"){
						sp.innerHTML="账号已被注册";
						sp.style.color="RED";
					}
					if(data=="0"){
						sp.innerHTML="恭喜你，账号可用";
						sp.style.color="GREEN";
					}
					
				}
			}
		}
		
		
	}
}
