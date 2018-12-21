/**
 * 初始化账单明细详情对话框
 */
var BillingDetailInfoDlg = {
    billingDetailInfoData : {}
};

/**
 * 清除数据
 */
BillingDetailInfoDlg.clearData = function() {
    this.billingDetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BillingDetailInfoDlg.set = function(key, val) {
    this.billingDetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BillingDetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BillingDetailInfoDlg.close = function() {
    parent.layer.close(window.parent.BillingDetail.layerIndex);
}

/**
 * 收集数据
 */
BillingDetailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('expenseAmount')
    .set('status')
    .set('typeId')
    .set('description')
    .set('screenshot')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
BillingDetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/billingDetail/add", function(data){
        Feng.success("添加成功!");
        window.parent.BillingDetail.table.refresh();
        BillingDetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.billingDetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BillingDetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/billingDetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.BillingDetail.table.refresh();
        BillingDetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.billingDetailInfoData);
    ajax.start();
}

$(function() {

});
