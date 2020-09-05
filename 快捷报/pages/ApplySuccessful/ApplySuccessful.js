Page({

  data:{

  },
  /**
   * 返回个人页面
   */
  returnPP:function(){
    wx.switchTab({
      url: '../PersonalPage/PersonalPage',
    })
  }


})