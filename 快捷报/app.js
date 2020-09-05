App({

  /**
   * 当小程序初始化完成时，会触发 onLaunch（全局只触发一次）
   */
  onLaunch: function () {
    /**
     * 初始化用户信息操作
     */
    //1.从缓冲中取出openid
    var openid = wx.getStorageSync("openid")
    //2.判断是否openid是否存在
    if (openid == "") {//若不存在，进行登录
      wx.login({
        success: res => {
          wx.request({
            url: 'http://localhost:8082/user/initUser',
            data: {
              code: res.code
            },
            success: res => {
              console.log(res.data)
              //获取openid
              openid = res.data.data
              try {
                //存入缓存
                wx.setStorageSync("openid", openid)
              } catch (e) {
                console.log("存入缓存失败")
              }
            }
          })
        },
        fail: res => {//网络请求失败
          console.log(res)
        }
      })
    } else {
      //若存在，则跳过登录
      console.log("openid已存在，跳过登录")
    }
    //问题:需要解决异步
  },

  /**
   * 利用Promise，解决异步问题
   */
  checkOpenId: function (openid) {

    return new Promise(function (resolve, reject) {
      wx.login({
        success: res => {
          wx.request({
            url: 'http://localhost:8082/user/initUser',
            data: {
              code: res.code
            },
            success: res => {
              console.log(res.data)
              //获取openid
              openid = res.data.data
              try {
                //存入缓存
                wx.setStorageSync("openid", openid)
              } catch (e) {
                console.log("存入缓存失败")
              }
              resolve();
            }
          })
        },
        fail: res => {//网络请求失败
          console.log(res)
        }
      })
    })

  },
  globalData: {
    field: []
  }

})
