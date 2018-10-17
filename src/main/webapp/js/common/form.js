$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


var myForm = function () {
    return{
        /**
         * 获取页面所有的表单数据，并转换成json对象
         * @param formId
         * @returns {*|jQuery}
         */
        serializeObject:function (formId) {
            var jsonuserinfo = $('#' + formId).serializeObject();
            return jsonuserinfo;
        },
        /**
         * 清空所有表单数据
         * @param formId
         */
        clear:function (formId) {
            $("#" + formId).find('input, textarea').each(function () {
                $(this).val("");
            });
            $("#" + formId).find('select').each(function () {
                $(this).val("").trigger("change");
            });
        },

        /**
         * 表单验证
         * @param formId
         */
        validation:function (formId) {
            $("#"+formId).on("blur", function () {
                if ($(this).val() === "" || $(this).val() == null || $(this).val() === undefined) {
                    $(this).css("border-color", "red");
                    $(this).prop("placeholder", "不能为空！");
                } else {
                    $(this).css("border-color", "");
                }
            })
        },
        ajaxRequest: function (url,reqParam,callback) {
            $.ajax({
                type: 'POST',
                url: url,
                data: reqParam,
                cache: false,
                success: callback
            });
        },
        ajaxGetRequest: function (url,data,callback) {
            $.ajax({
                type: 'GET',
                data:data,
                url: url,
                cache: false,
                success: callback
            });
        }
    }
}();