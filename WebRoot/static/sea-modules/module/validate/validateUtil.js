/**
 * 表单校验
 * @author gengzi 350049339@qq.com
 */
define(function (require, exports, module) {
    require('./validate.css');
    var $ = jQuery = require('$');
    require('module/jquery-mask');
    require('jquery-plugin/poshytip-1.1/jquery.poshytip.min.js');
    require('jquery-plugin/jquery-validation-1.8.1/jquery.validate.min.js');
    jQuery.extend(jQuery.validator.messages, {
        required: "必须填写",
        remote: "请修正该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入拥有合法后缀名的字符串",
        maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
        minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
        rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
        range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
        min: jQuery.validator.format("请输入一个最小为 {0} 的值")
    });
    var validateUtil = {};
    //大于#param
    jQuery.validator.addMethod('greatThan', function (value, element, param) {
        if (this.optional(element)) {
            return true;
        }
        if (value == 0 && target.val() == 0) {
            return true;
        }
        var target = $(param).unbind(".validate-greatThan").bind("blur.validate-greatThan", function () {
            $(element).valid();
        });

        return value > target.val();
    }, "");
    //小于#param
    jQuery.validator.addMethod('lessThan', function (value, element, param) {
        if (this.optional(element)) {
            return true;
        }
        if (value == 0 && target.val() == 0) {
            return true;
        }
        var target = $(param).unbind(".validate-lessThan").bind("blur.validate-lessThan", function () {
            $(element).valid();
        });
        return value < target.val();
    }, "");
    //正整数
    jQuery.validator.addMethod("positiveInteger", function (value, element, param) {
        if (this.optional(element)) {
            return true;
        }
        value = jQuery.trim(value);
        return/^[0-9]*[1-9][0-9]*$/.test(value);
    }, "请输入大于0的整数！");
    //固定长度
    jQuery.validator.addMethod("fixlength", function (value, element, param) {
        if (this.optional(element)) {
            return true;
        }
        value = jQuery.trim(value);
        return value.length == param;
    }, "请输入{0}位字符！");
    //验证自定义方法
    // number精度验证（默认精度为9,4,true(true表示为正数，false允许是0)）
    jQuery.validator.addMethod("appNumber", function (value, element, param) {
        if (this.optional(element)) {
            return true;
        }
        var intPart = 9;
        var decimalPart = 4;
        var positive = true;//正数
        if (param.constructor == Array) {
            intPart = param[0] ? param[0] : intPart;
            decimalPart = param[1] ? param[1] : decimalPart;
            positive = param[2] === undefined ? positive : param[2];
        }
        value = jQuery.trim(value);
        if (!/^(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value)) {
            return false;
        }
        if (positive && value * 1 <= 0) {
            return false;
        }
        var splitValue = value.split(".");
        if (splitValue[0].length <= intPart) {
            if (splitValue[1]) {
                return splitValue[1].length <= decimalPart;
            }
            return true;
        } else {
            return false;
        }
    }, "\u8bf7\u8f93\u5165\u6570\u5b57(\u6574\u6570\u6700\u591a9\u4f4d\uff0c\u5c0f\u6570\u6700\u591a4\u4f4d)");

    jQuery.validator.addMethod("userId", function (value, element) {
        return this.optional(element) || /^[a-zA-Z]\w{5,15}$/.test(value);
    }, "英文字母开头、可包含数字和下划线,6~16位");
    jQuery.validator.addMethod("compCode", function (value, element) {
        return this.optional(element) || /^[a-zA-Z]\w{5,15}$/.test(value);
    }, "英文字母开头、可包含数字和下划线,6~16位");
    //手机号码验证
    jQuery.validator.addMethod("isPhone", function (value, element) {
        var length = value.length;
        return this.optional(element) ||
            //验证手机    13*,15*,18*,17*开头
            (/^((13\d)|(15\d)|(17\d)|(18\d))\d{8}$/.test(value));
    }, "请正确填写手机号码");
// 电话验证   
    jQuery.validator.addMethod("isTel", function (value, element) {
        var length = value.length;
        return this.optional(element) ||
            //验证手机    13*,158,159 开头
            (/^((13\d)|(15\d)|(17\d)|(18\d))\d{8}$/.test(value)) ||
            //验证电话        "兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
            (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(value));
    }, "\u8bf7\u6b63\u786e\u586b\u5199\u7535\u8bdd\u6216\u624b\u673a\u53f7\u7801");
//身份证验证
    jQuery.validator.addMethod("isIDCard", function (value, element) {
        return this.optional(element) || checkIDCard(value);
    }, "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u8eab\u4efd\u8bc1\u53f7");
//案件上传附件后缀格式验证
    jQuery.validator.addMethod("isUploadFileExt", function (value, element) {
        return this.optional(element) || isUploadFileExt(value);
    }, "\u4e0d\u5141\u8bb8\u7684\u6587\u4ef6\u683c\u5f0f,\u53ea\u5141\u8bb8\u4e0a\u4f20'.doc','.xls','.ppt','.pdf','.txt'");

//上传文件文件名长度验证
    jQuery.validator.addMethod("uploadFileLength", function (value, element, param) {
        return this.optional(element) || checkResouceFile(value, param);
    }, "\u65e0\u6548\u7684\u6587\u4ef6\u8def\u5f84");
//案件添加时的证件编号验证(根据所选择的证件编号类型，而决定不同验证方法)
    jQuery.validator.addMethod("baseTo", function (value, element, param) {
        var typeValue = jQuery(param).val();
        if (typeValue == 1) {
            return this.optional(element) || checkIDCard(value);
        }
        if (typeValue == 2) {
            return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
        }
        return true;  //如果不是1,也不是2就返回true
    }, "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u8bc1\u4ef6\u7f16\u53f7");
    function isUploadFileExt(value) {
        var extArray = ["doc", "xls", "ppt", "pdf", "txt"];
        var point = value.lastIndexOf(".");
        var type = value.substr(point + 1);
        for (var i = 0; i < extArray.length; i++) {
            if (extArray[i] === type) {
                return true;
            }
        }
        return false;
    }

    function checkResouceFile(value, param) {
        var temp = value.split("\\");
        var fileName = temp[temp.length - 1];
        if (fileName.length > param) {
            return false;
        }
        return true;
    }

    function checkIDCard(idCard) {

//判断身份证号码格式函数
//公民身份号码是特征组合码，
//排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码

//身份证号码长度判断
        if (idCard.length < 15 || idCard.length == 16 || idCard.length == 17 || idCard.length > 18) {
            return false;
        }

//身份证号码最后一位可能是超过100岁老年人的X
//所以排除掉最后一位数字进行数字格式测试
//全部换算成17位数字格式
        var Ai;
        if (idCard.length == 18) {
//将身份证中的大写字母转换为小写字母
            idCard = idCard.toLowerCase();
            Ai = idCard.substr(0, 17);
        } else {
            Ai = idCard.substr(0, 6) + "19" + idCard.substr(6, 9);
        }
        if (isNumeric(Ai) == false) {
            return false;
        }
        var strYear, strMonth, strDay, strBirthDay;
        strYear = Ai.substr(6, 4);
        strMonth = Ai.substr(10, 2);
        strDay = Ai.substr(12, 2);
        if (isValidDate(strYear, strMonth, strDay) == false) {
            return false;
        }
        var arrVerifyCode = new Array("1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2");
        var Wi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var TotalmulAiWi = 0;
        for (var loop = 0; loop < Ai.length; loop++) {
            TotalmulAiWi += parseInt(Ai.substr(loop, 1)) * Wi[loop];
        }
        var modValue = parseInt(TotalmulAiWi, 10) % 11;
        var strVerifyCode = arrVerifyCode[modValue];
        Ai = Ai + strVerifyCode;
        if ((idCard.length == 18) && (idCard != Ai)) {
            return false;
        }
        return true;
    }

    function getBithdayAndSexFromIds(ids) {
        var year, month, day, birthday, sexNumber, sex;
        //********************************************身份证为15位
        if (ids.length == 15) {
            year = ids.substr(6, 2);
            month = ids.substr(8, 2);
            day = ids.substr(10, 2);
            birthday = "19" + year + "-" + month + "-" + day;
            sexNumber = ids.substr(13, 1);
            if (sexNumber % 2 == 0) {
                sex = "\u5973";
            } else {
                sex = "\u7537";
            }
        }
        //********************************************身份证为18位
        if (ids.length == 18) {
            year = ids.substr(6, 4);
            month = ids.substr(10, 2);
            day = ids.substr(12, 2);
            birthday = year + "-" + month + "-" + day;
            sexNumber = ids.substr(16, 1);
            if (sexNumber % 2 == 0) {
                sex = "\u5973";
            } else {
                sex = "\u7537";
            }
        }
        var bithdayAndSexArray = new Array(birthday, sex);
        return bithdayAndSexArray;
    }

    function isNumeric(oNum) {
        if (!oNum) {
            return false;
        }
        var strP = /^\d+(.\d+)?$/;
        if (!strP.test(oNum)) {
            return false;
        }
        try {
            if (parseFloat(oNum) != oNum) {
                return false;
            }
        }
        catch (ex) {
            return false;
        }
        return true;
    }

    function isValidDate(sYear, sMonth, sDay) {
        var nYear = parseInt(sYear, 10);
        var nMonth = parseInt(sMonth, 10);
        var nDay = parseInt(sDay, 10);
        if (sYear == "" && sMonth == "" && sDay == "") {
            return true;
        }
        if (sYear == "" || sMonth == "" || sDay == "") {
            return false;
        }
        if (nMonth < 1 || 12 < nMonth) {
            return false;
        }
        if (nDay < 1 || 31 < nDay) {
            return false;
        }
        if (nMonth == 2) {
            if ((nYear % 400 == 0) || (nYear % 4 == 0) && (nYear % 100 != 0)) {
                if ((nDay < 1) || (nDay > 29)) {
                    return false;
                }
            } else {
                if ((nDay < 1) || (nDay > 28)) {
                    return false;
                }
            }
        } else {
            if ((nMonth == 1) || (nMonth == 3) || (nMonth == 5) || (nMonth == 7) || (nMonth == 8) || (nMonth == 10) || (nMonth == 12)) {
                if ((nDay < 1) || (31 < nDay)) {
                    return false;
                }
            } else {
                if ((nDay < 1) || (30 < nDay)) {
                    return false;
                }
            }
        }
        return true;
    }

// -------------------------身份证验证函数结束------------------//
    /**
     *封装jquery.validate校验框架 options{debug,form,tipStyle(poshytip样式),rules,messages,submitHandler(form),mask}
     *依赖jquery-validation-1.8.1、poshytip-1.1，jquery-mask
     *
     */
    validateUtil.formValidate = function (_options) {
        validateUtil.tipStyle = _options.tipStyle;
        var _rules = _options.rules;
        var _messages = _options.messages;
        var _debug = _options.debug ? _options.debug : false;
        var _formSelector = "#" + _options.form;

        //默认显示mask效果
        var _mask = true;
        var _maskMsg = "\u6b63\u5728\u6267\u884c\u64cd\u4f5c...";
        if (_options.mask == false) {
            _mask = false;
        } else {
            if (typeof _options.mask == "string") {
                _maskMsg = _options.mask;
            }
        }
        //自定义错误回调函数 _invalidHandler
        var _invalidHandler = false;
        //自定义提交事件
        var _submitHandler = false;
        if (typeof _options.submitHandler == "function") {
            if (_mask) {
                _submitHandler = function (form) {
                    if ((_options.submitHandler)(form) !== false) {
                        $(_formSelector).mask(_maskMsg);
                    }
                };
            } else {
                _submitHandler = _options.submitHandler;
            }
        } else {
            if (_mask) {
                _submitHandler = function (form) {
                    $(_formSelector).mask(_maskMsg);
                    form.submit();
                };
            }
        }
        var MySetting = {debug: _debug, /*onfocusout:true, onkeyup:true, onclick:true, */errorPlacement: validateUtil.errorPlacement, success: validateUtil.success, rules: _rules, messages: _messages};
        if (_submitHandler) {
            MySetting.submitHandler = _submitHandler;
        }
        if (_invalidHandler) {
            MySetting.invalidHandler = _invalidHandler;
        }
        var validator = jQuery(_formSelector).validate(MySetting);
        return validator;
    };
    validateUtil.success = function (label, a) {
        var targetElement = label.data("targetElement");
        if (targetElement) {
            targetElement.data("poshytipEd", null);
            targetElement.poshytip("destroy");
            if (targetElement.hasClass("tipForSelect__")) {//删除select框或file的提示信息，释放内存
                var selectElement = targetElement.data("tipFor");
                selectElement.unbind("focus", selectElement.data("tipFocusFn")).unbind("blur", selectElement.data("tipblurFn"));
                selectElement.data("focus", null);
                selectElement.data("blur", null);
                targetElement.data("tipFor", null);
                targetElement.remove();
            }
            //此处理放在最后，防止位置的错误
            label.data("targetElement", null);
            targetElement.data('errorEl', null);
        }
    };
    validateUtil.errorPlacement = function (error, element) {
        var tipOpt = {content: error, /* className:"tip-yellowsimple",*/ alignTo: "target", alignX: "right", alignY: "center", offsetX: 5};
        $.extend(tipOpt, validateUtil.tipStyle || {});
        var targetElement = element;
        if (targetElement.is("select") || targetElement.is(":file")) {//如果是下拉框或file，将校验提示放在下拉框的后面的span上，防止下拉框在现实提示信息的时候不能进行下拉选择
            targetElement = element.next(".tipForSelect__");
            if (targetElement.size() == 0) {
                //element.after("<span class=\"tipForSelect__\">&nbsp;</span>");//空span，但是sapn内必须有内容（&nbsp;）
                element.after("<span class=\"tipForSelect__\"></span>");//空span，但是sapn内必须有内容（&nbsp;）
                targetElement = element.next(".tipForSelect__");
                var focus = function () {
                    targetElement.poshytip("show");
                };
                var blur = function () {
                    targetElement.poshytip("hide");
                };
                element.bind({focus: focus, blur: blur});
                element.data("tipFocusFn", focus);
                element.data("tipblurFn", blur);
            }
            targetElement.data("tipFor", element);
        } else {
            tipOpt.showOn = "focus";
        }
        if (targetElement.data("poshytipEd")) {
            targetElement.poshytip("update", error);
        } else {
            targetElement.poshytip("destroy");
            targetElement.data("poshytipEd", "1");
            targetElement.poshytip(tipOpt);
        }
        if (!error.data("targetElement")) {
            error.data("targetElement", targetElement);
            targetElement.data('errorEl', error);
        }
    };
    //清空表单校验错误信息的显示，并默认重置表单（form.reset()）
    validateUtil.resetForm = function (form, resetForm) {
        var formObj = document.getElementById(form);
        if (formObj) {
            for (var i = 0; i < formObj.elements.length; i++) {
                var el = formObj.elements[i];
                var tagName = el.tagName.toLowerCase();
                if (tagName != 'object') {
                    var $el = $(el);
                    //销毁错误提示tip
                    var errorEl = $el.data('errorEl');
                    if (errorEl) {
                        validateUtil.success(errorEl);
                    }
                    $el.removeClass('error');
                }
            }
        }
        if (resetForm === undefined || resetForm === true) {
            formObj.reset();
        }
    }
    jQuery(function () {
        /**给按钮增加效果，这段代码可重构
         //1、处理正常按钮
         jQuery("input:button[class='btn_small'],input:reset[class='btn_small'],input:submit[class='btn_small'],button[class='btn_small']").mouseover(function(){
		jQuery(this).attr("class","btn_small_onmouseover");
	});
         jQuery("input:button[class='btn_small'],input:reset[class='btn_small'],input:submit[class='btn_small'],button[class='btn_small']").mouseout(function(){
		jQuery(this).attr("class","btn_small");
	});
         //2、处理大一点的按钮
         jQuery("input:button[class='btn_big'],input:reset[class='btn_big'],input:submit[class='btn_big'],button[class='btn_big']").mouseover(function(){
		jQuery(this).attr("class","btn_big_onmouseover");
	});
         jQuery("input:button[class='btn_big'],input:reset[class='btn_big'],input:submit[class='btn_big'],button[class='btn_big']").mouseout(function(){
		jQuery(this).attr("class","btn_big");
	});
         */

        //组件提示信息
        if (jQuery.Poshytip) {
            jQuery("[poshytip]").poshytip({className: "tip-yellowsimple", showOn: "focus", alignTo: "target", alignX: "right", alignY: "center", offsetX: 5});
        }
        /*公用ajax操作等待提醒
         var ajaxBusy = jQuery("#ajaxBusy");
         if(!ajaxBusy || ajaxBusy.attr('id')!='ajaxBusy'){
         jQuery("body").append('<div id="ajaxBusy" align="center">正在执行请求...<img src="'+top.APP_PATH+'/resources/images/loadinfo_net.gif"></div>');
         }
         jQuery('#ajaxBusy').css( {
         display : "none",
         margin : "0px",
         position : "absolute",
         right : "10px",
         top : "5px",
         width:'220px',
         border: 'none',
         padding:'0px',
         '-webkit-border-radius': '10px',
         '-moz-border-radius': '10px',
         opacity: .8,
         background: '#F0F0F0',
         "z-index":99999
         });
         // Ajax activity indicator bound
         // to ajax start/stop document events
         jQuery(document).ajaxStart(function(evt, request, settings) {
         jQuery('#ajaxBusy').show();
         //tar=evt.target;
         //alert(jQuery(tar).text());
         }).ajaxStop(function(evt, request, settings) {
         jQuery('#ajaxBusy').hide();
         });

         //ajax 全局配置
         jQuery.ajaxSetup({
         cache: false,
         type: "POST",
         error:function (XMLHttpRequest, textStatus, errorThrown) {
         // 通常 textStatus 和 errorThrown 之中
         // 只有一个会包含信息
         //alert(this); // 调用本次AJAX请求时传递的options参数
         //alert(textStatus);
         }
         });
         */
        //必填样式添加
        jQuery('td.required').append('<span class="required">*</span>');
    });
    return validateUtil;
});

