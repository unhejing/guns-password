/**
 * 初始化账单类型详情对话框
 */
var ConsumptionTypeInfoDlg = {
    consumptionTypeInfoData : {}
};

/**
 * 清除数据
 */
ConsumptionTypeInfoDlg.clearData = function() {
    this.consumptionTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumptionTypeInfoDlg.set = function(key, val) {
    this.consumptionTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsumptionTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ConsumptionTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.ConsumptionType.layerIndex);
}

/**
 * 收集数据
 */
ConsumptionTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('userId')
    .set('sort')
    .set('status');
}

/**
 * 提交添加
 */
ConsumptionTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumptionType/add", function(data){
        Feng.success("添加成功!");
        window.parent.ConsumptionType.table.refresh();
        ConsumptionTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumptionTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ConsumptionTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consumptionType/update", function(data){
        Feng.success("修改成功!");
        window.parent.ConsumptionType.table.refresh();
        ConsumptionTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consumptionTypeInfoData);
    ajax.start();
}

$(function() {

});
