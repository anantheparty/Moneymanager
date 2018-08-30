<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh-CN">
  <head>
	<!-- Bootstrap Core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS  -->
	<link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet"> 
    <!-- DataTables CSS -->
    <link href="vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="SBadmin2/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <!-- DateTimepicker CSS -->
    <link href="vendor/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
	
	<!-- DateTimepicker CSS -->
	<link href="vendor/bootstrap-select-master/css/bootstrap-select.min.css" rel="stylesheet" />
	
    <title>记账</title>
  </head> 
  <body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">记账</a>
			</div>
			<!-- /.navbar-header -->
		</nav>
	
		<div id="page-wrapper" class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">stonepage的流水账</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
					<form class="form-inline" id="qry_form">
						<div class="form-group">
							<label for="#" class="control-label">从</label>
							   <div class="input-group date form_date" data-date="" 
								  data-date-format="yyyy-mm-dd" data-link-field="#" 
								  data-link-format="yyyy-mm-dd">
									<input class="form-control" id="qry_frDate" size="16" type="text" 
											value="" name="frDate" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							<input type="hidden" id="#" value="" />
						</div>
						<div class="form-group">
							<label for="#" class="control-label">到</label>
							   <div class="input-group date form_date" data-date="" 
								  data-date-format="yyyy-mm-dd" data-link-field="#" 
								  data-link-format="yyyy-mm-dd">
									<input class="form-control" id="qry_toDate" size="16" type="text" 
											value="" name="toDate" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							<input type="hidden" id="#" value="" />
						</div>
						<div class="form-group">
							<label for="#" class="control-label" >类别</label>
								   <select class="selectpicker" id="qry_type" name="type" multiple>    
								   </select>
						</div>
						<button type="button" class="btn btn-default" id="qry_btn">查询</button>
						<button type="button" class="btn btn-default" data-toggle="modal" data-target="#add_modal" id="add_modal_btn">添加</button>
					</form>				
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<table width="100%" class="table table-striped table-bordered table-hover" id="myTable">
							</table>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		<!-- /#page-wrapper -->
	<div id="container" style="min-width:400px;height:400px"></div>
	</div>
<!-- /#wrapper -->
<!-- /#wrapper -->
	<!-- add（Modal） -->
	<div class="modal fade bs-example-modal-lg" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						修改
					</h4>
				</div>
				<div class="modal-body">
					<form id="add_form" role="form" method="post" action="AccountItemServlet?method=addAccountItem">
						<div class="row">
  							<div class="form-group">
								<label for="add_id" class="col-sm-1 control-label">名称</label>
								<div class="col-sm-4">
	                                <input class="form-control" type="text" id="add_name" name="name"
	                                		onkeyup="this.value=this.value.replace(/\s+/g,'')" /> 
	                            </div>
								<label for="#" class="col-sm-1 control-label">日期</label>
	                            <div class="input-group date form_date col-md-4" data-date="" 
	                            	  data-date-format="yyyy-mm-dd" data-link-field="#" 
	                            	  data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" value="" id="add_date" name="date" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
								<input type="hidden"  value="" />
							</div>
   						</div>
   						<div class="row">
  							<div class="form-group">
	                            <label for="add_money" class="col-sm-1 control-label">金额</label>
	                            <div class="col-md-4">
	                                <input class="form-control" type="text" id="add_money" name="money"
	                                		placeholder="0.00" onkeyup="clearNoNum(this)"/>
	                            </div>
	                            <label for="#add_type" class="col-sm-1 control-label">类别</label>
	                            <div class="col-md-4">
									<div class="input-group">
										<input type="text" class="form-control" id="add_type" name="type"
												onkeyup="this.value=this.value.replace(/\s+/g,'')" >
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu" id="add_type_list">
												
											</ul>
										</div><!-- /btn-group -->
									</div><!-- /input-group -->
								</div><!-- /.col-lg-6 --><br>
								<div class="col-md-3">
                           	 	</div>
                        	</div>
   						</div>
   						<hr/>
   						<div class="row">
  							<div class="form-group">
								<label for="#add_details" class="col-md-1 control-label">详情</label>
	                            <div class="col-md-11">
	                            	<textarea class="form-control" rows="4" id="#add_details"
	                            				placeholder="详情" name="details"></textarea>
	                            </div>
							</div>
   						</div>
   					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="add_btn">	Add
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	
	<!-- mdf（Modal） -->
	<div class="modal fade bs-example-modal-lg" id="mdf_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						添加
					</h4>
				</div>
				<div class="modal-body">
					<form id="mdf_form" role="form" method="post" action="AccountItemServlet?method=mdfAccountItem">
						<input type="hidden"  id="mdf_id" name="id"/>
						<div class="row">
  							<div class="form-group">
								<label for="mdf_name" class="col-sm-1 control-label">名称</label>
								<div class="col-sm-4">
	                                <input class="form-control" type="text" id="mdf_name" name="name"
	                                		onkeyup="this.value=this.value.replace(/\s+/g,'')" /> 
	                            </div>
								<label for="#" class="col-sm-1 control-label">日期</label>
	                            <div class="input-group date form_date col-md-4" data-date="" 
	                            	  data-date-format="yyyy-mm-dd" data-link-field="#" 
	                            	  data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" value="" id="mdf_date" name="date" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
								<input type="hidden"  value="" />
							</div>
   						</div>
   						<div class="row">
  							<div class="form-group">
	                            <label for="mdf_money" class="col-sm-1 control-label">金额</label>
	                            <div class="col-md-4">
	                                <input class="form-control" type="text" id="mdf_money" name="money"
	                                		placeholder="0.00" onkeyup="clearNoNum(this)"/>
	                            </div>
	                            <label for="#mdf_type" class="col-sm-1 control-label">类别</label>
	                            <div class="col-md-4">
									<div class="input-group">
										<input type="text" class="form-control" id="mdf_type" name="type"
												onkeyup="this.value=this.value.replace(/\s+/g,'')" >
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu" id="mdf_type_list">
												
											</ul>
										</div><!-- /btn-group -->
									</div><!-- /input-group -->
								</div><!-- /.col-lg-6 --><br>
								<div class="col-md-3">
                           	 	</div>
                        	</div>
   						</div>
   						<hr/>
   						<div class="row">
  							<div class="form-group">
								<label for="#mdf_details" class="col-md-1 control-label">详情</label>
	                            <div class="col-md-11">
	                            	<textarea class="form-control" rows="4" id="#mdf_details"
	                            				placeholder="详情" name="details"></textarea>
	                            </div>
							</div>
   						</div>
   					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="mdf_btn">	修改
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
    <!-- jQuery -->
    <script src="jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script src="vendor/datatables-responsive/dataTables.responsive.js"></script>
	
	<script src="vendor/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="SBadmin2/js/sb-admin-2.js"></script>
	
	<!-- DateTimepicker JavaScript -->
	
	<script src="vendor/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    
	
	<script src="vendor/bootstrap-select-master/js/bootstrap-select.min.js"></script>
	<script src="vendor/bootstrap-select-master/js/i18n/defaults-zh_CN.min.js"></script>
	
	<script src="jquery/jquery.form.js"></script>
	
	<script type="text/javascript" src="vendor/highcharts.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script type="text/javascript">
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	</script>
    <script type="text/javascript">
    $(document).ready(function() {
    	$.ajax({
			url:"CategoryServlet?method=allCategory",
			type:"post",
			datatype:"json",
			success:function(data){
				arr = JSON.parse(data);
				for(var i = 0; i<arr.length; i++){
					var cur = arr[i];
					$("#qry_type").append("<option value=" + cur.name+">"+cur.name+"</option>" ); 
	//				<option value="string">string</option>
				}
				
			}
		});
        $('#myTable').DataTable({
            responsive: true,
            "aLengthMenu":[10,20,50,100],
	        "paging":true,
	        searching: false,
	        ajax:{
	    		type: "post",
	        	url: "AccountItemServlet?method=getAccountItemList",
	        	dataType: "json",
	        	traditional: true,
	        	data: function(){
		        	var formData = $("#qry_form").serialize();
		        	return formData;
				},
	        },
		    columns: [
//		            {title:"<input type='checkbox' class='checkboxAll'>全选",data:null,width:"6%" }, 
		            {title:"a",data:"id",className: "never"},
		            {title:"日期",data:"date"},
		            {title:"款项",data:"name"},
		            {title:"金额",data:"money" },
		            {title:"类型",data:"categoryName" },
		            {title:"详情",data:"details",width:"41%"},
		            {title:"操作",data:null,width:"9%"  }
		        ],
		  	columnDefs:[
		  		{
					targets:[0],
					"visible": false,
					orderable:false,
      			},	
				{
		         	orderable:false,
		        	targets:[6],
		        	render:function(data,type,row,meta){
		        		return '<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#mdf_modal" id="mdf_modal_btn">修改</button>'
		        		       +'&nbsp;&nbsp;'+'<button type="button" class="btn btn-default btn-sm" id="del_btn">删除</button>'
		        		       				
		        	}
		         },
		        ],
		        //国际化 
	        language: {
				"sProcessing": "处理中...",
				"sLengthMenu": "显示 _MENU_ 项结果",
				"sZeroRecords": "没有匹配结果",
				"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix": "",
				"sSearch": "搜索:",
				"sUrl": "",
				"sEmptyTable": "表中数据为空",
				"sLoadingRecords": "载入中...",
				"sInfoThousands": ",",
				"oPaginate": {
					"sFirst": "首页",
					"sPrevious": "上页",
					"sNext": "下页",
					"sLast": "末页"
				},
				"oAria": {
					"sSortAscending": ": 以升序排列此列",
					"sSortDescending": ": 以降序排列此列"
				}
			}
        });
    }); 
    
	$('#myTable').on("click","#mdf_modal_btn",function(){
		$.ajax({
			url:"CategoryServlet?method=allCategory",
			type:"post",
			datatype:"json",
			success:function(data){
				arr = JSON.parse(data);
				for(var i = 0; i<arr.length; i++){
					var cur = arr[i];
					$("#add_type_list").append(
						" <li><a href= \"javascript:void(0);\" onclick=\"add_type_value('" + cur.name
						+"' ) \" > "+cur.name+"</a></li>" ); //传入中文参数要有引号
	//				<li><a href="javascript:void(0);" onclick="add_type( 'string' )">string</a></li>
				}
				
			}
		});
		var trd = $('#myTable').DataTable().row($(this).closest("tr")).data();
		var id = trd.id;				$("#mdf_id").val(id);
		var type = trd.categoryName;	$("#mdf_type").val(type);	
		var name = trd.name;			$("#mdf_name").val(name);
		var money = trd.money;			$("#mdf_money").val(money);
		var date = trd.date;			$("#mdf_date").val(date);
		var details = trd.details;		$("#mdf_details").val(details);
	});
	function add_type_value( name ){
		document.getElementById("add_type").value = name;		
	}
	function clearNoNum(obj){ 
	    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
	    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
	    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
	        obj.value= parseFloat(obj.value); 
    } 
} 
	$("#mdf_btn").click(function(){
		var name = $("#mdf_name").val();
		var money = $("#mdf_money").val();
		var date = $("#mdf_date").val();
		var type = $("#mdf_type").val();
		if( name == null || name == ""  ){
	    	alert("名称不能为空");
			return false;
		}
		if( date == null || 	date == ""  ){
			alert("日期不能为空");
			return false;
		}
		if( money == null ||  money == ""  ){
			alert("金额不能为空");
			return false;
		}
		if( type == null ||  type == ""  ){
			alert("类别不能为空");
			return false;
		}
		$("#mdf_btn").disabled= "disabled";
		$("#mdf_form").submit();
		
	});
	$("#add_modal_btn").click(function(){
		$.ajax({
			url:"CategoryServlet?method=allCategory",
			type:"post",
			datatype:"json",
			success:function(data){
				arr = JSON.parse(data);
				for(var i = 0; i<arr.length; i++){
					var cur = arr[i];
					$("#add_type_list").append(
						" <li><a href= \"javascript:void(0);\" onclick=\"add_type_value('" + cur.name
						+"' ) \" > "+cur.name+"</a></li>" ); //传入中文参数要有引号
	//				<li><a href="javascript:void(0);" onclick="add_type( 'string' )">string</a></li>
				}
				
			}
		});
	
	});
	$("#add_btn").click(function(){
		var name = $("#add_name").val();
		var money = $("#add_money").val();
		var date = $("#add_date").val();
		var type = $("#add_type").val();
		if( name == null || name == ""  ){
	    	alert("名称不能为空");
			return false;
		}
		if( date == null || 	date == ""  ){
			alert("日期不能为空");
			return false;
		}
		if( money == null ||  money == ""  ){
			alert("金额不能为空");
			return false;
		}
		if( type == null ||  type == ""  ){
			alert("类别不能为空");
			return false;
		}
		$("#add_btn").disabled= "disabled";
		$("#add_form").submit();
	});
	$('#myTable').on("click","#del_btn",function(){
		var id = $('#myTable').DataTable().row($(this).closest("tr")).data().id;
		var type = $('#myTable').DataTable().row($(this).closest("tr")).data().categoryName;
		$.ajax({
			url:"AccountItemServlet?method=delAccountItem",
			type:"post",
			datatype:"json",
			data:{ "id":id,"cName":type },
		});
		$('#myTable').DataTable().ajax.reload(); 	
	});
	$("#qry_btn").click(function(){
		$('#myTable').DataTable().ajax.reload(); 
		var sum = 0.0;
		$.ajax({
			type: "post",
	        url: "AccountItemServlet?method=getAccountItemListInfo",
	        dataType: "json",
	        traditional: true,
	        data: function(){
		       	var formData = $("#qry_form").serialize();
		        return formData;
			},
			success:function(data){
				for(var i=0;i<data.length;i++) sum+=data[i].y;
				
				$('#container').highcharts({
					chart: {
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false,
						spacing : [100, 0 , 40, 0]
					},
					title: {
						floating:true,
						text: '合计'+sum+'元',
						style: {
							fontSize : '25px'
								}
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								format: '<b>{point.name}</b>: {point.y} 元',
								style: {
									color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
									fontSize : '18px'
								}
							},
						}
					},
					series: [{
						type: 'pie',
						innerSize: '80%',
						name: '花费',
						data:  data
		
					}]
				}, function(c) {
					// 环形图圆心
					var centerY = c.series[0].center[1],
						titleHeight = parseInt(c.title.styles.fontSize);
					c.setTitle({
						y:centerY + titleHeight/2
					});
					chart = c;
				});
			
			}
		});
		
	});
	</script>
	<div id='canvasDiv'></div>
  </body>
</html>
