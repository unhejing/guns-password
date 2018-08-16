/**
 * 初始化密码管理详情对话框
 */
var BizPasswordInfoDlg = {
    bizPasswordInfoData : {}
};

/**
 * 清除数据
 */
BizPasswordInfoDlg.clearData = function() {
    this.bizPasswordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPasswordInfoDlg.set = function(key, val) {
    this.bizPasswordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizPasswordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizPasswordInfoDlg.close = function() {
    parent.layer.close(window.parent.BizPassword.layerIndex);
}

/**
 * 收集数据
 */
BizPasswordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('username')
    .set('password')
    .set('image')
    .set('createTime')
    .set('createName')
    .set('userId');
}

/**
 * 提交添加
 */
BizPasswordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassword/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizPassword.table.refresh();
        BizPasswordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPasswordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizPasswordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizPassword/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizPassword.table.refresh();
        BizPasswordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizPasswordInfoData);
    ajax.start();
}

$(function() {

});
