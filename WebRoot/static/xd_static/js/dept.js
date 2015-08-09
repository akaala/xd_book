$(function(){
	getDeptList();
});
//获取部门列表
function getDeptList(){
	$.ajax({
		url:"dept.it?action=getDeptList"
	}).done(function(data){
		var len=data.length;
		var html="";
		for(var i=0;i<len;i++){
			var index=i+1;
			html+="<tr>";
			html+="<td>"+index+"</td>"
			html+="<td>";
			html+="<span class='deptspan'>"+data[i].name+"</span>";
			html+="<span class='deptinput'><input type='text' value="+data[i].name+" onblur='saveDept("+data[i].id+",$(this))'/></span>";
			html+="</td>";
			html+="<td>";
			html+="<input type='button' value='删除' onclick='deleteDept("+data[i].id+")' class='pure-button pure-button-primary' /> ";
			html+="<input type='button' value='编辑' onclick='editDept($(this))' class='button-success pure-button' />";
			html+="</td>"
			html+="</tr>";
		}
		$(".deptBody").html(html);
		
	}).fail(function(){
		alert("服务器连接失败!")
	});
}
//删除部门
function deleteDept(id,obj){
	if(!obj){//服务器删除
		$.ajax({
			url:"dept.it?action=deleteDept&id="+id
		}).done(function(data){
			alert(data.msg);
			if(data.status=="success"){
				location.reload(true);
			}
		});
	}else{//界面删除
		obj.closest("tr").remove();
	}		
}
//添加部门
function addDept(){
	var index=$(".deptBody tr").length+1;
	var html="";
	html+="<tr>";
	html+="<td>"+index+"</td>"
	html+="<td>";
	html+="<span class='deptinput' style='display:block'><input type='text' onblur='saveDept(-1,$(this))' plachholder='部门名称' /></span>";
	html+="</td>";
	html+="<td>";
	html+="<input type='button' value='删除' onclick='deleteDept(-1,$(this))' class='pure-button pure-button-primary' /> ";
	html+="</td>"
	html+="</tr>";
	$(".deptBody").append(html);
}
//编辑部门
function editDept(obj){
	obj.closest("tr").find(".deptspan").hide();
	obj.closest("tr").find(".deptinput").show();
}
//保存部门
function saveDept(id,obj){
	var name=obj.val();
	if(!name)
		return;
	$.ajax({
		url:"dept.it?action=saveDept&id="+id+"&name="+name
	}).done(function(data){
		if(data.status=="success"){
			getDeptList();
		}else{
			alert("保存失败!");
		}
	}).fail(function(){
		alert("服务器连接失败!")
	})
}

