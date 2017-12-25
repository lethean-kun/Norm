<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("norm", request.getContextPath()) ;
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="常模添加">
    <meta name="author" content="高庆佳">
    <meta name="keyword" content="常模更新">
	<link rel="shortcut icon" href="${norm}/img/normicon.png">
    <title>常模添加</title>

    <!-- Bootstrap CSS -->    
    <link href="${norm}/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${norm}/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${norm}/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="${norm}/css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${norm}/css/style.css" rel="stylesheet"> 

    <%@include file="common/header.jsp"%>
  </head>

  <body>
 
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i>常模添加</h3>
					<ol class="breadcrumb">
<!-- 						<li><i class="fa fa-home"></i><a href="norm_list_page.jsp">主页</a></li> -->
						<li><i class="fa fa-table"></i>常模添加</li>
					</ol>
				</div>
			</div>
              <!-- page start-->

              <div class="row">
                  <form action="#">
                      <div class="col-sm-12">
                          <section class="panel">
                              <table class="table table-bordered">
                                  <tbody>
                                  <tr>
                                      <td>常模名称：</td>
                                      <td>
                                          <input type="text" name="normname" id="normname" required="required"  onblur="checkLength(this,30)">
                                      </td>
                                  </tr>
                                  <tr>
                                      <td>版本：</td>
                                      <td>
                                          <input type="text" name="version" id="version" required="required"  onblur="checkLength(this,20)">
                                      </td>
                                  </tr>
                                  <tr>
                                      <td>描述：</td>
                                      <td>
                                      <textarea rows="3" cols="50" name="description" id="description"></textarea>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td>计算项：</td>
                                      <td id="SelectedModule">
                                          	  <input type="hidden" value="" id="SelectedModuleIds" name="moduleIds">
                                          	  <textarea type="text" value="" id="SelectedModuleNames" name="moduleNames"  readonly="readonly" style="border:none;overflow-x:hidden;width:60%" required="required"></textarea>
                                              <button type="button" class="btn btn-danger" style="float: right" data-toggle="modal" data-target="#myModal" >计算项选择</button>
                                          <!--modal begin-->
                                          <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                              <div class="modal-dialog" role="document" style="width:60%">
                                                  <div class="modal-content">
                                                      <div class="modal-header">
                                                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                              <span aria-hidden="true">×</span>
                                                          </button>
                                                          <h4 class="modal-title" id="myModalLabel">计算项选择
                                                          </h4>
                                                      </div>
                                                      <div class="modal-body">
                                                          <div class="row">
                                                              <div class="col-sm-12">
                                                                  <section class="panel">
                                                                      <table class="table table-bordered" id="moduleTable">
                                                                          <tbody>
                                                                         <!--  <tr>
                                                                              <td>产品类型：</td>
                                                                              <td>
                                                                                  <select name="productType" id="productType">
                                                                                  </select>
                                                                              </td>

                                                                          </tr>  -->
                                                                          <tr>
                                                                              <td>产品名称：</td>
                                                                              <td>
                                                                                  <select name="productName" id="productName">
                                                                                  </select>
                                                                              </td>
                                                                          </tr>
                                                                          <tr>
                                                                              <td>计算项：</td>
                                                                              <td id ="figureTerm">
       
                                                                              </td>
                                                                          </tr>
                                                                          </tbody>
                                                                      </table>
                                                                  </section>
                                                              </div>
                                                          </div>
                                                      </div>
                                                      <div class="modal-footer">
                                                          <button type="button" class="btn btn-primary" onclick="selectModule()" data-dismiss="modal">确定</button>
                                                          <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>

                                                      </div>
                                                  </div>
                                              </div>
                                          </div>
                                          <!--modal end-->
                                      </td>
                                  </tr>
                                  <tr style="text-align: center">
                                      <td colspan="2">
                                          <a onclick="smtForm()" class="btn btn-primary">保存并导入数据</a>
                                          <a href="${norm}/menu/switch/1" class="btn btn-primary">返回</a>
                                      </td>
                                  </tr>
                                  </tbody>
                              </table>




                          </section>
                      </div>
                  </form>


              </div>



              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
  </section>
	<!-- javascripts -->
    <script src="${norm}/js/jquery-3.2.1.min.js"></script>
    <script src="${norm}/js/jquery-2.1.1.js"></script>
    <script src="${norm}/js/bootstrap.min.js"></script>
    <script src="${norm}/js/bootstrap.js"></script>
    <script src="${norm}/js/layer.js"></script>
  </body>
  <script type="text/javascript">
  
  	function checkLength(e,length){
  		var val = $(e).val();
  		console.log(val+"<-->"+length);
  		if(length == 30){//常模名称
			if(val.length > length){
				$(e).val("");
				layer.alert("常模名称字符长度不能超过"+length);
			}
  		}else if(length == 20){//常模版本	
			if(val.length > length){
				$(e).val("");
				layer.alert("常模版本字符长度不能超过"+length);
			}
  		}else{
  			return;
  		}
  	}
    //表单提交
  	function smtForm(){
  		var productName =$("select#productName").val();//产品名
  		var normName = $("#normname").val();//常模名
  		var version = $("#version").val();//常模版本
  		var moduleIds = $("#SelectedModuleIds").val();//维度ids
  		var description = $("#description").val();//描述
  		if(normName == null || normName == "" ){
  			layer.alert("常模名称不能为空,请填写！");
  			return;
  		}
  		if(version == null || version == "" ){
  			layer.alert("常模版本不能为空,请填写！");
  			return;
  		}
  		if(moduleIds == null || moduleIds == "" ){
  			layer.alert("计算项不能为空,请选择！");
  			return;
  		}
  		if(description == null || description == "" ){
  			description = null;
  		}
  	
  		$.ajax({
  			url:"${norm}/norm/addnorm",
			data:{productName:productName,normName:normName,moduleIds:moduleIds,version:version,description:description},
			type:"post",
			dataType:"json",
			success:function(data){
				console.log(data.status);
				if(data.status==0){
					console.log("常模信息保存成功！");
					layer.confirm('常模信息保存成功,是否导入数据？', {
					  btn: ['导入数据','返回常模列表'] //按钮
					}, function(){
					  window.location.href="${norm}/norm/rtInPage/"+data.data;
					}, function(){
					  window.location.href="${norm}/menu/switch/1";
					});
				}else{
					layer.alert(data.message);
				}
			},
			error:function(){
				layer.alert("服务器开小差中0.0!!");
			}
  		})
  	}
  	//选中维度
  	function selectModule(){
  		var chks=$("input:checked");//获取页面所有选中的checkbox
  		console.log(chks.length);
  		var moduleIds = "";
  		var moduleNames = "";
  		for(var i = 0;i < chks.length;i++){
  			if(chks[i].value != "level"){
	  			moduleIds += chks[i].value+",";
	  			moduleNames += chks[i].nextSibling.nodeValue+",";
	  		}
  		}
  		moduleIds = moduleIds.substring(0,moduleIds.length-1);
  		moduleNames = moduleNames.substring(0,moduleNames.length-1);
  		$("#SelectedModuleIds").val(moduleIds);
        $("#SelectedModuleNames").val(moduleNames);
  	}
  
  	//多选框全部选中
    function selectAll(checkBox){
    	//选中级别的时候，全部选中
    	console.log(checkBox.checked);
    	if(checkBox.checked == true){
    		var table = $(checkBox).parent().parent().parent().parent();
    		console.log(table.find("tr"));
    		 for (var i = 0; i < table.find("tr").length; i++) {    //遍历Table的所有Row
		        for (var j = 0; j < table.find("tr")[i].cells.length; j++) {   //遍历Row中的每一列
		            $(table.find("tr")[i].cells[j]).find("input").prop("checked",true);   //获取Table中单元格的内容
		        }
		    }
    	}else{
    		var table = $(checkBox).parent().parent().parent().parent();
    		console.log(table.find("tr"));
    		 for (var i = 0; i < table.find("tr").length; i++) {    //遍历Table的所有Row
		        for (var j = 0; j < table.find("tr")[i].cells.length; j++) {   //遍历Row中的每一列
		            $(table.find("tr")[i].cells[j]).find("input").prop("checked",false);   //获取Table中单元格的内容
		        }
		    }
    	}
    }
    //切换产品，实现联动
	$("select#productName").change(function(){
	     var proid = $(this).val();
	     console.log(proid);
	     selectedPro(proid);
	});
	//已经选择的产品
	function selectedPro(id){
		$.ajax({
			url:"${norm}/product/proModules",
			data:{proid:id},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.status == 0){
					var map = data.data;
					var fterm="";
					for(var key in map){
						fterm += "<table border='0' width='100%'><tr bgcolor='#E0E0E0'><td colspan='6'><input value='level' type='checkbox' onclick='selectAll(this)'/>"+key+"</td></tr><tr>";
						map[key].forEach(function(val,index,list){
							fterm +="<td><input type='checkbox' value="+val.moduleId+" />"+val.modulename+"</td>";
							if((index+1)%6 == 0){
								fterm += "</tr>";
							}
						})
						fterm += "</table>";
					}
					$("#figureTerm").html(fterm);
				}
			},
			error:function(){
				layer.alert("服务器开小差中0.0!!");
			}
			
		})
	}
	//用户负责的产品列表
	function getUserProducts(){
		$.ajax({
  			url:"${norm}/product/userPros",
  			success:function(data){
  				if(data.status == 0){
  					var pName="";
  					var firstProId = data.data[0].productId;
  					data.data.forEach(function(val,index,arr){
  					  	pName += "<option value="+val.productId+">"+val.pro.name+"</option>"
  					})
  					$("#productName").html(pName);
  					selectedPro(firstProId);
  				}else if(data.status == 1){
  					window.location.href="${norm}/login.jsp";
  				}else{
  					layer.alert(data.message);
  				}
  			},
  			error:function(){
  				layer.alert("服务器开小差中0.0!!")
  			}
  		})
	}
	//获取所有的产品类型
	function getAllProductType(){
		$.ajax({
  			url:"${norm}/product/allType",
  			success:function(data){
  				if(data.status == 0){
  					var pType="";
  					data.data.forEach(function(val,index,arr){
  					  	pType += "<option value="+val.id+">"+val.name+"</option>"
  					})
  					$("#productType").html(pType);
  				}else{
  					console.log(data.message);
  				}
  			},
  			error:function(){
  				layer.open("服务器开小差中0.0")
  			}
  		})
	}
	
	function initModule(){
		var proId = $("#productName option:first").val();
		console.log(proId);
	}
	
	$(document).ready(function(){
  	/* 	getAllProductType(); */
  		getUserProducts();
	});
	
  </script>
</html>
