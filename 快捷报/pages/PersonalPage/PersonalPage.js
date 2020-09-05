Page({

  data: {

  },
  /**
   * 跳转查看已填写报名表页面
   */
  getPersonalRegistrationListPage: function () {
    wx.navigateTo({
      url: '../PersonalRegistrationList/PersonalRegistrationList',
    })
  },

  /**
   * 跳转申请协会管理员页面
   */
  getApplyAssociationAdministratorPage: function (e) {
    wx.navigateTo({
      url: '../ApplyAssociationAdministrator/ApplyAssociationAdministrator?flag=apply',
    })
  },

  /**
  * 查看审核情况
  */
  getCheckAuditPage: function (e) {
    wx.navigateTo({
      url: '../CheckAudit/CheckAudit',
    })
  },
  /**
   * 创建（查看）协会报名表
   */
  getManagementAssociationRegistrationForm: function (e) {
    wx.navigateTo({
      url: '../ManagementAssociationRegistrationForm/ManagementAssociationRegistrationForm'
    })
  },

  /**
   * 显示查看协会报名情况
   */
  getAssociationRegistrationListPage: function (e) {
    wx.navigateTo({
      url: '../AssociationRegistrationList/AssociationRegistrationList',
    })
  },

  /** 
   * 跳转查看协会管理员审核情况页
   */
  getCheckAuditPage: function () {
    wx.navigateTo({
      url: '../CheckAudit/CheckAudit',
    })
  },
  /**
   * 跳转查看协会定制报名表页面
   */
  getManagementAssociationRegistrationFormPage: function () {
    wx.navigateTo({
      url: '../ManagementAssociationRegistrationForm/ManagementAssociationRegistrationForm',
    })
  },
  /**
   * 跳转查看协会报名情况页面
   */
  getAssociationRegistrationListPage: function (e) {
    wx.navigateTo({
      url: '../AssociationRegistrationList/AssociationRegistrationList',
    })
  },
  /**
   * 跳转审核协会管理员页面
   */
  getAuditAdmin:function(e){
    wx.navigateTo({
      url: '../AdminApplyList/AdminApplyList',
    })
  },
  /**
   * 初始化页面
   */
  onLoad(optnions) {

  },
  /**
   * 每次页面跳转时
   */
  onShow() {

    var that = this
    //1.从缓冲中取出openid 【解决异步前提下】
    var openid = wx.getStorageSync("openid")
    //2.初始化权限判断
    wx.request({
      url: 'http://localhost:8082/user/checkPermissons',
      data: {
        openid: openid
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: res => {
        /**
         *  identity:
         *    0 - 普通用户
         *    1 - 系统管理员
         * 
         *  isAssHead:
         *    0 - 不为协会管理员
         *    3 - 审核通过为协会管理员
         */
        console.log(res.data)

        //设置权限标识
        that.setData({
          identity: res.data.data.identity,
          isAssHead: res.data.data.isAssHead
        })

      }
    })
  }
})