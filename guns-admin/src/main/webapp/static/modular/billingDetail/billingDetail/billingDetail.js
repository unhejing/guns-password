/**
 * 账单明细管理初始化
 */
var BillingDetail = {
    id: "BillingDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BillingDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '消费金额', field: 'expenseAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '消费状态（0：支出，1：收入）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '消费类型（关联类型表）', field: 'typeId', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '截图', field: 'screenshot', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BillingDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BillingDetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加账单明细
 */
BillingDetail.openAddBillingDetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加账单明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/billingDetail/billingDetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看账单明细详情
 */
BillingDetail.openBillingDetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账单明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/billingDetail/billingDetail_update/' + BillingDetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除账单明细
 */
BillingDetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/billingDetail/delete", function (data) {
            Feng.success("删除成功!");
            BillingDetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("billingDetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询账单明细列表
 */
BillingDetail.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BillingDetail.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BillingDetail.initColumn();
    var table = new BSTable(BillingDetail.id, "/billingDetail/list", defaultColunms);
    table.setPaginationType("client");
    BillingDetail.table = table.init();
});
