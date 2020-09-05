Page({

  /**
   * 页面的初始数据
   */
  data: {
    assList:[]
  },
  /**
    * 报名表填写页面
    */
  getFillInTheForm(e) {
    //console.log(e);
    //获取自定义绑定数据
    var assid = e.currentTarget.dataset.assid
    wx.navigateTo({
      url: '../EditTheForm/EditTheForm?assid=' + assid,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var openid = wx.getStorageSync("openid")
    if(openid!=''){
      wx.request({
        url: 'http://localhost:8082/member/getApplyedAssInfo',
        data:{
          openid:openid
        },
        success:res=>{
          console.log(res.data)
          this.setData({
            assList:res.data.data
          })
        }
      })
    }
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