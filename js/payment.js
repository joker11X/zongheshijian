//window.onload = fuction(){
//	
////获取显示隐藏按钮DOM对象
//Let showBtn = document .getElementById('showBtn');
////获取订单明细DOM对象
//let detailetBox = document.getElementById( 'detailetBox');
//
//
//showBtn.onclick = function(){
//	//判断订单明细DOM对象是否隐藏，如果是，就显示，否则就隐藏
//if(detailetBox.style .display=='none')
//{
//	(detailetBox.style .display='block';
//}
//else{
//detailetBox.style .display='none';
//}
// }
//}
window.onload = function(){
	let showBtn = document.getElementById('showBtn');
	let detailetBox = document.getElementById('detailetBox');
	detailetBox.style.display='none';
	showBtn.onclick = function(){
		if(detailetBox.style.display=='none'){
			detailetBox.style.display='block';
		}else{
			detailetBox.style.display='none';
		}
	}
}
