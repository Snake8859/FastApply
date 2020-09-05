// pages/beforeIndex/beforeIndex.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  /**
   * 微信登录，获取微信基本信息
   */
  wxLogin: function () {
    var that = this
    //加载中
    wx.showLoading({
      title: '正在登录',
    })
    //定时1.5秒，加载结束
    setTimeout(function () {
      wx.hideLoading()
      //从缓存中取出openid
      var openid = wx.getStorageSync("openid")
      //判断是否存在
      if (openid != "") {//若存在
        //调用获取用户信息
        that.getUserInfo(openid)
      } else {
        //Promise
        app.checkOpenId(openid).then((res) => {
          openid = wx.getStorageSync("openid")
          console.log("openid-checkAfter:" + openid)
          //调用获取用户信息
          that.getUserInfo(openid)
        })
      }
    }, 1500)
  },

  /**
   * 获得用户信息
   */
  getUserInfo: function (openid) {
    wx.getUserInfo({
      success: res => {
        console.log(res);
        wx.request({
          url: 'http://localhost:8082/user/updateUserWxInfo',
          data: {
            openid: openid,
            nickname: res.userInfo.nickName,
            picture: res.userInfo.avatarUrl,
            province: res.userInfo.province,
            city: res.userInfo.city
          },
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: res => {
            console.log(res.data);
            //设置登录状态
            wx.setStorageSync("loginStatus", res.data.data)
            //跳转到首页
            wx.switchTab({
              url: '../index/index',
            })
          }
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '',
    })
    setTimeout(function(){
      wx.hideLoading()
        //缓存中取出登录状态
        var loginStatus = wx.getStorageSync("loginStatus")
        console.log(loginStatus)
        //判断登录状态
        if(loginStatus!= ""){//若已登录
        wx.switchTab({
        url: '../index/index',
        })
    }
  }, 500)
      
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