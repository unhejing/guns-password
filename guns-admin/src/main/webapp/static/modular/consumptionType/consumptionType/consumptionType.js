/**
 * 账单类型管理初始化
 */
var ConsumptionType = {
    id: "ConsumptionTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ConsumptionType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '类型名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '关联用户', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '支付状态(0:支出，1：收入)', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ConsumptionType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ConsumptionType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加账单类型
 */
ConsumptionType.openAddConsumptionType = function () {
    var index = layer.open({
        type: 2,
        title: '添加账单类型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/consumptionType/consumptionType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看账单类型详情
 */
ConsumptionType.openConsumptionTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账单类型详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/consumptionType/consumptionType_update/' + ConsumptionType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除账单类型
 */
ConsumptionType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/consumptionType/delete", function (data) {
            Feng.success("删除成功!");
            ConsumptionType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("consumptionTypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询账单类型列表
 */
ConsumptionType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ConsumptionType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ConsumptionType.initColumn();
    var table = new BSTable(ConsumptionType.id, "/consumptionType/list", defaultColunms);
    table.setPaginationType("client");
    ConsumptionType.table = table.init();
});
