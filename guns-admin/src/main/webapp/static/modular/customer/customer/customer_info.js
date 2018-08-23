/**
 * 初始化APP用户管理详情对话框
 */
var CustomerInfoDlg = {
    customerInfoData : {}
};

/**
 * 清除数据
 */
CustomerInfoDlg.clearData = function() {
    this.customerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CustomerInfoDlg.set = function(key, val) {
    this.customerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CustomerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CustomerInfoDlg.close = function() {
    parent.layer.close(window.parent.Customer.layerIndex);
}

/**
 * 收集数据
 */
CustomerInfoDlg.collectData = function() {
    this
    .set('id')
    .set('username')
    .set('password')
    .set('createTime');
}

/**
 * 提交添加
 */
CustomerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/customer/add", function(data){
        Feng.success("添加成功!");
        window.parent.Customer.table.refresh();
        CustomerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.customerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CustomerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/customer/update", function(data){
        Feng.success("修改成功!");
        window.parent.Customer.table.refresh();
        CustomerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.customerInfoData);
    ajax.start();
}

$(function() {

});
