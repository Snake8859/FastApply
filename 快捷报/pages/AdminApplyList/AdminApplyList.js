// pages/AuditAdmin/AuditAdmin.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  /**
   * 审核协会管理员具体详情页面
   */
  getAuditAdmin: function (e) {
    //console.log(e)
    wx.navigateTo({
      url: '../AuditAdmin/AuditAdmin?assid=' + e.currentTarget.dataset.assid,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      /**
       * 查询所有待审核的协会管理员列表
       */
      wx.request({
        url: 'http://localhost:8082/sys/getAssInfoList',
        success:res=>{
          console.log(res.data);
          this.setData({
            assList:res.data.data
          })
        }
      })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})