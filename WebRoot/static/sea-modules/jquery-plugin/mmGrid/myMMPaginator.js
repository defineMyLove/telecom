/**
*	author：gengzi
*/
define(function(require, exports, module) {
var $=jQuery= require('$');
(function($) {
	var MyPaginator = function(element, options) {
		this.$el = $(element);
		this.opts = options;
	};

	MyPaginator.prototype = {
		_initLayout : function() {
			var that = this;
			var $el = this.$el;
			var opts = this.opts;

			$el.addClass("mmPaginator");
			var pgHtmls = [ '<div class="totalCountLabel"></div>',
					'<ul class="pageList"></ul>',
					];
			//如果limitList只有一项，隐藏分页大小select
			if(opts.limitList.length==1){
				pgHtmls.push('<div class="limit" style="display:none;"><select></select></div>');
			}else{
				pgHtmls.push('<div class="limit"><select></select></div>');
			}
			$el.append($(pgHtmls.join('')));

			this.$totalCountLabel = $el.find('.totalCountLabel');
			this.$pageList = $el.find('.pageList');
			this.$limitList = $el.find('.limit select');

			var $limitList = this.$limitList
			$.each(opts.limitList, function() {
				var $option = $('<option></option>').prop('value', this).text(
						that.formatString(opts.limitLabel, [ this ]));
				$limitList.append($option);
			});
			
			$limitList.on('change', function() {
				//如果改变分页大小，从第一页开始查询
				$el.data('page', 1);
				that.reload();
			});

			// 拦截查询按钮，查询操作执行前清空页码标志
			var searchB = $("#search_dg");
			if (searchB) {
				var searchB_onclick = searchB.attr("onclick");
				searchB.attr("onclick", null);
				searchB.click(function() {
					$el.data('page', 1);
					eval(searchB_onclick);
				});
			}
		},
		_plain : function(page, totalCount, limit) {
			var that = this;
			var $el = this.$el;
			var $pageList = this.$pageList;

			var totalPage = totalCount % limit === 0 ? parseInt(totalCount
					/ limit) : parseInt(totalCount / limit) + 1;
			totalPage = totalPage ? totalPage : 0;
			if (totalPage === 0) {
				page = 1;
			} else if (page > totalPage) {
				page = totalPage;
			} else if (page < 1 && totalPage != 0) {
				page = 1;
			}

			var $prev = $('<li class="prev"><a>上一页</a></li>');
			if (page <= 1) {
				$prev.addClass('disable');
			} else {
				$prev.find('a').on('click', function() {
					$el.data('page', page - 1);
					that.reload();
				});
			}
			$pageList.append($prev);

			var list = [ 1 ];
			if (page > 4) {
				list.push('...');
			}
			for (var i = 0; i < 5; i++) {
				var no = page - 2 + i;
				if (no > 1 && no <= totalPage - 1) {
					list.push(no);
				}
			}
			if (page + 1 < totalPage - 1) {
				list.push('...');
			}
			if (totalPage > 1) {
				list.push(totalPage);
			}
			$.each(list, function(index, item) {
				var $li = $('<li><a></a></li>');
				if (item === '...') {
					$li.addClass('moreLess').html('...');
				} else if (item === page) {
					$li.addClass('active').find('a').text(item);
				} else {
					$li.find('a').text(item).prop('title', '第' + item + '页')
							.on('click', function(e) {
								$el.data('page', item);
								that.reload();
							});
				}
				if(that.opts.fullPageStyle){//是否完整显示分页
					$pageList.append($li);
				}else{
					$pageList.append($li.hide());
				}
			});

			var $next = $('<li class="next"><a title="下一页">下一页</a></li>');
			if (page >= totalPage) {
				$next.addClass('disable');
			} else {
				$next.find('a').on('click', function() {
					$el.data('page', page + 1);
					that.reload();
				});
			}
			$pageList.append($next);
		},
		_search : function(page, totalCount, limit) {
		},
		/**
		 * 
		 * @param firstPage	是否从第一页重新加载true|false，影响分页参数
		 * @param otherData	其他数据，会合并分页参数
		 */
		reload : function(firstPage,otherData) {
			var $el = this.$el;
			if(firstPage){
				$el.data('page',1);
			}
			var opts = this.opts;
			var param = this.params();
			if(otherData){
				param=$.extend(false, {}, param, otherData);
			}
			
			if (opts.loadFunction) {
				for (var i = 0; i < opts.loadFunction.length; i++) {
					opts.loadFunction[i](param);
				}
			}
		},
		load : function(params) {
			var $el = this.$el;
			var $limitList = this.$limitList;
			var opts = this.opts;

			var page = params[opts.pageParamName];
			if (page === undefined) {
				page = $el.data('page');
			}
			$el.data('page', page);

			var totalCount = params[opts.totalCountName];
			if (totalCount === undefined) {
				totalCount = 0;
			}
			$el.data('totalCount', totalCount);

			var limit = params[opts.limitParamName];
			if (limit === undefined) {
				limit = $limitList.val();
			}
			this.$limitList.val(limit);

			this.$totalCountLabel.html(this.formatString(opts.totalCountLabel,
					[ totalCount ]));
			this.$pageList.empty();

			this._plain(page, totalCount, this.$limitList.val());
		},
		formatString : function(text, args) {
			return text.replace(/{(\d+)}/g, function(match, number) {
				return typeof args[number] != 'undefined' ? args[number]
						: match;
			});
		},
		params : function() {
			var opts = this.opts;
			var $el = this.$el;
			var $limitList = this.$limitList;

			var params = {};
			params[opts.pageParamName] = $el.data('page');
			params[opts.limitParamName] = $limitList.val();
			return params;
		},
		init : function($grid) {
			var that = this;
			var opts = that.opts;
			this.$mmGrid = $grid;
			this._initLayout();
			this.$mmGrid.on('loadSuccess', function(e, data) {
				if (data === undefined) {
					data = {};
				}
				//data：本地加载data是本地数据，远程加载data是返回数据
				that.load(data);
				//支持兼容mmgrid的loadSuccess事件选项opts
				//add:#rengeng,20140429#加载本地数据，数据在data.records
				if(that.$mmGrid.opts.loadSuccess){
					that.$mmGrid.opts.loadSuccess(data);
				}
			});
			this.$mmGrid.on('cellSelected', function(e, item, rowIndex, colIndex) {
				//支持兼容mmgrid的cellSelected事件选项opts
				if(that.$mmGrid.opts.cellSelected){
					that.$mmGrid.opts.cellSelected(e, item, rowIndex, colIndex);
				}
			});

			var params = {};
			params[opts.totalCountName] = 0;
			params[opts.pageParamName] = opts.page;
			params[opts.limitParamName] = opts.limit;
			this.load(params);

			if ($grid.opts.indexCol) {
				var indexCol = $grid.opts.cols[0];
				indexCol.renderer = function(val, item, rowIndex) {
					var params = that.params();
					return '<label class="mmg-index">'
							+ (rowIndex + 1 + ((params[opts.pageParamName] - 1) * params[opts.limitParamName]))
							+ '</label>';
				};
			}
		}
	};

	$.fn.myMMPaginator = function() {
		if (arguments.length === 0 || typeof arguments[0] === 'object') {
			var option = arguments[0], data = this.data('myMMPaginator'), options = $
					.extend(false, {}, $.fn.myMMPaginator.defaults, option);
			if (!data) {
				options.limit=options.limitList[0];
				data = new MyPaginator(this[0], options);
				this.data('myMMPaginator', data);
			}
			return $.extend(true, this, data);
		}
		if (typeof arguments[0] === 'string') {
			var data = this.data('myMMPaginator');
			var fn = data[arguments[0]];
			if (fn) {
				var args = Array.prototype.slice.call(arguments);
				return fn.apply(data, args.slice(1));
			}
		}
	};

	$.fn.myMMPaginator.defaults = {
		style : 'plain',
		totalCountName : 'totalRow',
		page : 1,
		pageParamName : 'pageNumber',
		limitParamName : 'pageSize',
		limitLabel : '每页{0}条',
		totalCountLabel : '共<span>{0}</span>条记录',
		limit : 10,
		limitList : [ 10,20,30 ],
		fullPageStyle:true////分页展示方式：完整展示|只展示上下页
	// , loadFunction:[]
	};

	$.fn.myMMPaginator.Constructor = MyPaginator;

})(jQuery); return $;});