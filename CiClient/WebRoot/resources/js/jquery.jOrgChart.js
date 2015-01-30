/**
 * jQuery org-chart/tree plugin.
 *
 * Author: Héctor Vela
 * http://twitter.com/vellonce
 *
 * Original PlugIn Author: Wes Nolte
 * http://twitter.com/wesnolte
 *
 * Based on the work of Mark Lee
 * http://www.capricasoftware.co.uk
 *
 * Copyright (c) 2011 Wesley Nolte
 * Dual licensed under the MIT and GPL licenses.
 *
 */
(function($) {
	var cx = 0;
    $.fn.jOrgChart = function(options) {
        var opts = $.extend({}, $.fn.jOrgChart.defaults, options);
        var $appendTo = $(opts.chartElement);

        // build the tree
        $this = $(this);
		
        var $container = $("<div class='" + opts.chartClass + "'/>");
        if($this.is("ul")) {
		
		    //add color to row wise 
		    $this.find("li").each(function(){
				classList = $(this).attr('class').split(/\s+/);
				$.each(classList, function(index,item) {
                    if(item != "temp" && item != "node" && item != "child" && item != "ui-draggable" && item != "ui-droppable" && !/^unic\d+$/i.test(item)){
                        re = item;
                    }
                });
				if(re != null){
					$(this).removeClass(re)
				}
				var col = $(this).parents('li').length;
				if (col == 0){
					$(this).addClass("nrow");
				}
				else if (col == 1){
					$(this).addClass("firow");
				}
				else if (col == 2){
					$(this).addClass("serow");
				}
				else if (col == 3){
					$(this).addClass("throw");
				}
				else if (col == 4){
					$(this).addClass("forow");
				}
				else if (col == 5){
					$(this).addClass("firow");
				}
				else if (col == 6){
					$(this).addClass("sirow");
				}
				else {
					$(this).addClass("norow");
				}
			});
			$this.find("li.root").each(function(){
				buildNode($(this), $container, 0, opts);
			})
        }
        else if($this.is("li")) {
            buildNode($this, $container, 0, opts);
        }
        $appendTo.append($container);

        // add drag and drop if enabled
        if(opts.dragAndDrop){
            var $divNode = $('.node:not(.temp)').not(".disabled, #org .node");
            var $nodeParts = $divNode.not(".child");
            $divNode.draggable({
                cursor      : 'move',
                distance    : 40,
                helper      : 'clone',
                opacity     : 0.8,
                revert      : 'invalid',
                revertDuration : 100,
                snap        : 'div.node.expanded',
                snapMode    : 'inner',
                stack       : 'div.node'
            });

			
            $nodeParts.droppable({
                accept      : '.node',
                activeClass : 'drag-active',
                hoverClass  : 'drop-hover'
            });


             var $nodeCU = $("div.node.child");
             $nodeCU.droppable({
				 accept      : '.child',
				 activeClass : 'drag-active',
				 hoverClass  : 'drop-hover'
             });

            // Drag start event handler for nodes
			
            $divNode.bind("dragstart", function handleDragStart( event, ui ){
				var start_event = ui.helper
				var sourceNode = $(this);
				if(start_event.is("div")){
					sourceNode.parentsUntil('.node-container').find('*').filter('.node').droppable('disable');
				}	
				else if(start_event.is("li")){
					sourceNode.find('*').filter('.node').droppable('disable');
				}
            });
			
			
	        // Drag stop event handler for nodes
            $divNode.bind("dragstop", function handleDragStop( event, ui ){
				//refresh side bar
				var sideLi = $("#upload-chart").html();
				$("#upload-chart").empty();			
				$("#upload-chart").append(sideLi);
				if($("#upload-chart li:last-child").hasClass("ui-draggable-dragging")){
				    $("#upload-chart li:last-child").remove();
				}
			    
				//remove list from side bar when added to org-chart
				if(removeside_node!=""){
					$("#upload-chart #"+removeside_node).remove();
					removeside_node = "";
				}
				
				/* reload the plugin */
				$(opts.chartElement).children().remove();
                $this.jOrgChart(opts);
				
			});
         
      
            // Drop event handler for nodes
			var removeside_node = ""
            $divNode.bind("drop", function handleDropEvent( event, ui ) {
            	
    			var targetID = $(this).data("tree-node");
                var targetLi = $this.find("li").filter(function() { return $(this).data("tree-node") === targetID; } );
                var targetUl = targetLi.children('ul');
                var sourceID = ui.draggable.data("tree-node");
				if(sourceID == null){
				   var lilength = $this.find("li").length;
				   var sourceLi =  ui.draggable.clone().addClass('unic'+(lilength+1));
				   removeside_node = ui.draggable.attr("id");
				   sourceLi.addClass("item");

				}
				else{
                  var sourceLi = $this.find("li").filter(function() { return $(this).data("tree-node") === sourceID; } );
                  var sourceUl = sourceLi.parent('ul');
				   //Removes any empty lists
				   if (sourceUl.children().length === 0){
                    sourceUl.remove();
                   }
				}
				
			    sourceLi.removeClass("node").removeClass("ui-draggable")
			    var programid=$("#programid").attr("value");
                var pageid;
                var parentpagename;
                var childs="";
                var childid=0;
                var pagename=sourceLi.find("> .label_node:eq(0) > a").html();
                var classli=sourceLi.find("li");
                $.each(classli, function(index,item) {
                	var classList=sourceLi.find("li:eq("+index+")").attr('class').split(/\s+/);
                	$.each(classList, function(index,item) {
                    	if(item.indexOf('unic')== 0){
                            childid=item.substring(4,item.length);
                            childs+=childid+",";
                           }
                       })
                   });
                if (targetUl.length > 0){
                	parentpagename=targetUl.parent().find("> .label_node:eq(0) > a").html();
                    targetUl.append(sourceLi);
                } else {
                    targetLi.append("<ul></ul>");
                    targetLi.children('ul').append(sourceLi);
                    parentpagename=targetLi.find("> .label_node:eq(0) > a").html();
                }
            	if(confirm("确定要拖动吗？")){
            		$.fancybox('<div class=\"span12\" id=\"add_nodo\">\
        					<p class=\"notice span3\">输入页面信息</p>\
        					<div class=\"inputBox\" id=\"searchType\">\
        						<label for=\"classtype\">元素类型:</label>\
        						<input type=\"text\" name=\"classtype\" id=\"type\" class=\"span6\" onkeyup=\"queryData(this)\"/>\
        						<ul class=\"classtypeData\">\
        						</ul>\
        						</div>\
        					<div class=\"inputBox\">\
        						<label> 元素文本:</label>\
        						<input	type=\"text\" name=\"text\" id=\"elementText\" class=\"span6\" />\
        					</div>\
        					<div class=\"inputBox\">\
        						<label for=\"elementindex\">同类元素排序位置:</label>\
        						<input type=\"text\" name=\"elementindex\" id=\"index\" class=\"span6\" />\
        					</div>	\
        					<div class=\"inputBox\">\
        						<label for=\"content\"> 操作:</label>\
        						<input type=\"text\" name=\"content\" id=\"operational\" class=\"span6\" onkeyup=\"queryContentData(this)\"/>\
        						</div>\
        					<div class=\"submitBox\">\
        						<input type=\"button\" id=\"addElement\" value=\"确定\" class=\"ok\"></input>\
        					</div>\
        				</div>'
	                       );
            		$("#addElement").click(function(){
            			var classtype=$("#type").val();
            			var elementText=$("#elementText").val();
            			var elementindex=$("#index").val();
            			var operational=$("#operational").val();
                        $.ajax({
        			    	type:"post",
        			    	url:"updateProgramPage?programid="+programid+"&pagename="+pagename+"&parentpagename="+parentpagename+"&childs="+childs+"&classtype="+classtype+"&elementText="+elementText+"&operational="+operational+"&elementindex="+elementindex,
        			    	data:{},
        			    	async: false,
        			    	dataType:"html",
        			        success: function (data) {
        			    		$.fancybox.close();
        			    		window.location.reload();
        			          }
        	  		  });
                        
            		});
            		$("#index").keyup(function() {
            			if(""!=this.value){
            			var str = this.value.replace(/(^\s*)|(\s*$)/g, "");
            			if(this.value != str )
            			this.value = str;
            			}
            			if( isNaN(Number(this.value)))
            			this.value = this.value.replace(/[\D]/,'');
            			}); 
            	}
            }); // handleDropEvent

        } // Drag and drop
    };

    // Option defaults
    $.fn.jOrgChart.defaults = {
        chartElement : 'body',
        depth      : -1,
        chartClass : "jOrgChart",
        dragAndDrop: false
    };

    var nodeCount = 0;

    function removeNode($node, opts, $nodeDiv){
        if($nodeDiv.hasClass("temp")){
            if(click_flag){
                $node.remove();
                click_flag = true;
                $(opts.chartElement).children().remove();
                $this.jOrgChart(opts);
            }

        }
    }

    // Method that recursively builds the tree
    function buildNode($node, $appendTo, level, opts) {
        var $table = $("<table cellpadding='0' cellspacing='0' border='0'/>");
        var $tbody = $("<tbody/>");

        // Construct the node container(s)
        var $nodeRow = $("<tr/>").addClass("node-cells");
        var $nodeCell = $("<td/>").addClass("node-cell").attr("colspan", 2);
        var $childNodes = $node.children("ul:first").children("li");
        var $nodeDiv;

        if($childNodes.length > 1) {
            $nodeCell.attr("colspan", $childNodes.length * 2);
        }
        // Draw the node
        // Get the contents - any markup except li and ul allowed
        var $nodeContent = $node.clone()
            .children("ul,li")
            .remove()
            .end()
            .html();

        //Increaments the node count which is used to link the source list and the org chart
        nodeCount++;

        $node.data("tree-node", nodeCount);
        $nodeDiv = $("<div>").addClass("node")
            .data("tree-node", nodeCount)
            .append($nodeContent);


        $nodeDiv.append(
                "<div class='opciones'>" +
                    "</div>")
            .mouseenter(function(){
                if($(this).find("> .details > span").length == 0){
                   var duplicate = $(this).find("> span.label_node").clone();
                   $(this).find("> .details").prepend(duplicate);
                }
                $(this).find(".details").toggle().parent().css("z-index","999");
            }).mouseleave(function(){
                $(this).find(".details").toggle().parent().removeAttr("style");
            });

        var append_text = "<li class='temp'></li>";
        var $list_element = $node.clone()
            .children("ul,li")
            .remove()
            .end();

			
	    // Expand and contract nodes
		if ($childNodes.length > 0) {
		   $nodeDiv.find(".opciones:eq(0)").closest(".node").append("<span class='exp-col'></span>");
		   $nodeDiv.find(".exp-col").click(function() {
			  var $this = $(this);
			  var $tr = $this.closest("tr");
			  if($tr.hasClass('contracted')){
				$tr.removeClass('contracted').addClass('expanded');
				$tr.nextAll("tr").css('visibility', '');
				$node.removeClass('collapsed');
				}else{
				  $tr.removeClass('expanded').addClass('contracted');
				  $tr.nextAll("tr").css('visibility', 'hidden');
				  $node.addClass('collapsed');
				  }
			});
		}


        $nodeCell.append($nodeDiv);
        $nodeRow.append($nodeCell);
        $tbody.append($nodeRow);

        if($childNodes.length > 0) {
            // if it can be expanded then change the cursor
            //$nodeDiv.css('cursor','n-resize');

            // recurse until leaves found (-1) or to the level specified
            if(opts.depth == -1 || (level+1 < opts.depth)) {
                var $downLineRow = $("<tr/>");
                var $downLineCell = $("<td/>").attr("colspan", $childNodes.length*2);
                $downLineRow.append($downLineCell);

                // draw the connecting line from the parent node to the horizontal line
                $downLine = $("<div></div>").addClass("line down");
                $downLineCell.append($downLine);
                $tbody.append($downLineRow);

                // Draw the horizontal lines
                var $linesRow = $("<tr/>");
                $childNodes.each(function() {
                    var $left = $("<td>&nbsp;</td>").addClass("line left top");
                    var $right = $("<td>&nbsp;</td>").addClass("line right top");
                    $linesRow.append($left).append($right);
                });

                // horizontal line shouldn't extend beyond the first and last child branches
                $linesRow.find("td:first")
                    .removeClass("top")
                    .end()
                    .find("td:last")
                    .removeClass("top");

                $tbody.append($linesRow);
                var $childNodesRow = $("<tr/>");
                $childNodes.each(function() {
                    var $td = $("<td class='node-container'/>");
                    $td.attr("colspan", 2);
                    // recurse through children lists and items
                    buildNode($(this), $td, level+1, opts);
                    $childNodesRow.append($td);
                });

            }
            $tbody.append($childNodesRow);
        }

        // any classes on the LI element get copied to the relevant node in the tree
        // apart from the special 'collapsed' class, which collapses the sub-tree at this point

        if ($node.attr('class') != undefined) {
            var classList = $node.attr('class').split(/\s+/);
            $.each(classList, function(index,item) {
                if (item == 'collapsed') {

                    $nodeRow.nextAll('tr').css('visibility', 'hidden');
                    $nodeRow.removeClass('expanded');
                    $nodeRow.addClass('contracted');
                } else {
                    $nodeDiv.addClass(item);
                }
            });
        }
        if(!$nodeDiv.hasClass("temp")){
            $nodeDiv.find(".opciones:eq(0)").append("<span class='edit' href='#fancy_edit'></span>");
			$nodeDiv.find(".opciones:eq(0)").append("<span class='add' href='#fancy'></span>");
            if($nodeDiv.hasClass("child")){
                $nodeDiv.find(".opciones:eq(0)").append("<span class='del'></span>");
            }
        }else{
            $nodeDiv.find(".opciones:eq(0)").append("<span class='add' href='#fancy'></span><span class='del'></span>");
        }

        $table.append($tbody);
        $appendTo.append($table);

        /* Prevent trees collapsing if a link inside a node is clicked */
        $nodeDiv.children('a, span').click(function(e){
            e.stopPropagation();
       });
    }

})(jQuery);

function queryData(element){
    var uri="GetElementClasstype?classtype="+$(element).val();
    $.ajax({
      type: "post",
      //查询参数
      data:{},
      url: uri,
      dataType:"json",
      success: function(data,status) {
        var objs=eval(data);
        var obj=objs[0];
        var elementList=obj.elementList;
        if(elementList.length==0){
        	$('.inputBox>ul.classtypeData').fadeOut();
        }else{
        	$(element).parent().find('ul.classtypeData').fadeIn("fast");
            $(element).parent().find('ul.classtypeData').children().remove();
            for(var item in elementList){
                var _i=$("<li>"+elementList[item].classtype+"</li>");
                $(element).parent().find('ul.classtypeData').append(_i);
            }
        }
        $('.inputBox>ul.classtypeData>li').each(function(index,element){
            $(this).click(function(){
                $(element).parent().parent().find("input[type='text']").val($(element).text());
                // 清理缓存
                // $(element).parent().parent().find("ul.contentTip").children().remove();
                $('.inputBox>ul.classtypeData').fadeOut();
            })
   	      });
   	      document.onclick = hideClasstype;
      },
      error:function(data,status,e){
        alert("请求失败");
      }
    });
}
function hideClasstype(){
 	$('.inputBox>ul.classtypeData').fadeOut();
 }
function queryContentData(element){
    var uri="GetOperContent?content="+$(element).val();
    $.ajax({
      type: "post",
      //查询参数
      data:{},
      url: uri,
      dataType:"json",
      success: function(data,status) {
        var objs=eval(data);
        var obj=objs[0];
        var operList=obj.operList;
        if(operList.length==0){
        	$('.inputBox>ul.contentData').fadeOut();
        }else{
        	$(element).parent().find('ul.contentData').fadeIn("fast");
            $(element).parent().find('ul.contentData').children().remove();
            for(var item in operList){
                var _i=$("<li>"+operList[item].content+"</li>");
                $(element).parent().find('ul.contentData').append(_i);
            }
        }
        $('.inputBox>ul.contentData>li').each(function(index,element){
            $(this).click(function(){
                $(element).parent().parent().find("input[type='text']").val($(element).text());
                $('.inputBox>ul.contentData').fadeOut();
            })
   	      });
   	       document.onclick = hideContent;
      },
      error:function(data,status,e){
        alert("请求失败");
      }
    });
}
function hideContent(){
 	$('.inputBox>ul.contentData').fadeOut();
 }

