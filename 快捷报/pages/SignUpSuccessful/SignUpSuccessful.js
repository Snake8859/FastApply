Page({
  /**
   * 返回个人列表页面
   */
  getPersonalRegistrationList:function(e){
    wx.navigateBack({
      delta:1
    })
  },
  getPersonalPage:function(e){
    wx.switchTab({
      url: '../PersonalPage/PersonalPage',
    })
  }
})