/**
 * 密码管理管理初始化
 */
var BizPassword = {
    id: "BizPasswordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BizPassword.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '密码', field: 'password', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'image', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createName', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BizPassword.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BizPassword.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加密码管理
 */
BizPassword.openAddBizPassword = function () {
    var index = layer.open({
        type: 2,
        title: '添加密码管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bizPassword/bizPassword_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看密码管理详情
 */
BizPassword.openBizPasswordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '密码管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bizPassword/bizPassword_update/' + BizPassword.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除密码管理
 */
BizPassword.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bizPassword/delete", function (data) {
            Feng.success("删除成功!");
            BizPassword.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bizPasswordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询密码管理列表
 */
BizPassword.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BizPassword.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BizPassword.initColumn();
    var table = new BSTable(BizPassword.id, "/bizPassword/list", defaultColunms);
    table.setPaginationType("client");
    BizPassword.table = table.init();
});
