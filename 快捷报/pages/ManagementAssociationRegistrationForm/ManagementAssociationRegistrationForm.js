var fields =[]
//比例单位
const RADIO = wx.getSystemInfoSync().screenWidth / 375 
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  /**
   * 跳转自定义报名表
   */
  toCustom(e) {
    //console.log(e)
    var assid = e.currentTarget.dataset.assid
    var assname = e.currentTarget.dataset.assname
    var slogan = e.currentTarget.dataset.slogan
    //创建缓存自定义模板
    fields = wx.getStorageSync(assid)
    if (fields.length == 0) {//若没有自定义模板
      wx.setStorageSync(assid, [])
    }
    wx.navigateTo({
      url: '/pages/custom/custom?assid=' +assid+'&assname='+assname+'&slogan='+slogan+'&flag=left',
    })
  },
  /**
   * 报名表分享
   */
  shareForm:function(e){

      //console.log(e.currentTarget.dataset.id)
    var assid = e.currentTarget.dataset.id
    var path = "pages/FillInTheForm/FillInTheForm?assid="+assid
      wx.request({
        url: 'http://localhost:8082/common/createQR',
        data:{
            assid:assid,
            path:path,
            width:430
        },
        success:res=>{
          console.log(res.data)

          var context = wx.createContext()
          context.setFillStyle("#FF9900")
          
          //小三角1
          context.lineTo(0, 0)
          context.lineTo(15 * RADIO, 15 * RADIO)
          context.lineTo(30 * RADIO, 0)
    
          context.fill()
          //小三角2
          context.moveTo(270 * RADIO, 0)
          context.lineTo(285 * RADIO, 15 * RADIO)
          context.lineTo(300 * RADIO, 0)
          context.fill()


          //协会名称
          context.setTextAlign('center')
          context.setFillStyle('#000000')
          context.setFontSize(22)
          context.fillText(res.data.data.assName, 150 * RADIO, 50 * RADIO)

          //协会logo
          if(res.data.data.logo!=''){
            context.drawImage(res.data.data.logo, 100 * RADIO, 100 * RADIO, 100 * RADIO, 100 * RADIO)
          }
          //报名二维码
          context.drawImage(res.data.data.qrpath, 50 * RADIO, 250 * RADIO, 200 * RADIO, 200 * RADIO)

          wx.drawCanvas({
            canvasId: 'canvas',
            actions: context.getActions()
          })

          //弹出对话框
          this.setData({
            modalName: e.currentTarget.dataset.target,
            shareImage:res.data
          })
        }
      })



      //测试
      //获得token
      // wx.request({
      //   url: 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx3ada9faa41ff3299&secret=c0ac2837333fcafb3959abb04834c577',
      //   method:'POST',
      //   success:res=>{
      //     console.log(res)
      //     //获取小程序二维码
      //     wx.request({
      //       url: 'https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=' + res.data.access_token,
      //       data:{
      //           "path":"pages/CreateSuccessful/CreateSuccessful",
      //           "width":430
      //       },
      //       method: 'POST',
      //       header: {
      //         'content-type': 'application/json'
      //       },
      //       success:res=>{
      //         console.log(res)
      //       }
      //     })
      //   }
      // })
  },

  /**
   * 保存二维码到本地相册
   */
  saveQRCodeToPhotosAlbum:function(e){

    var tempImgPath;
    //canvas截取图片
    wx.canvasToTempFilePath({
      x: 0,
      y: 0,
      destWidth: 300 * RADIO * 4,
      destHeight: 450 * RADIO * 4,
      canvasId: 'canvas',
      fileType: 'jpg',
      quality: 1,
      success: function (res) {
        console.log(res)
        tempImgPath = res.tempFilePath
      },
      fail: function (res) { },
      complete: function (res) { },
    }, this)
      

    wx.getSetting({
        success:res=>{
          //用户未授权
          if (res.authSetting['scope.writePhotosAlbum']==false){
              wx.showModal({
                title: '提示',
                content: '是否授权将照片保存相册？',
                confirmColor:'#2ca3ed',
                success:res=>{
                  //用户确定开启授权
                  if(rers.confirm){
                    //开启相册授权
                    wx.openSetting({
                      success: res => {
                        setTimeout(()=>{
                          if (res.authSetting['scope.writePhotosAlbum']==true){
                            //将照片保存至相册
                            wx.saveImageToPhotosAlbum({
                              filePath: tempImgPath,
                              success: res => {
                                wx.showToast({
                                  title: '保存成功！',
                                  icon: 'success',
                                  mask: true
                                })
                                this.setData({
                                  modalName: null
                                })
                              },
                              fail: err => {
                                wx.showToast({
                                  title: '保存失败！',
                                  icon: 'none',
                                  mask: true
                                })
                                this.setData({
                                  modalName: null
                                })
                              }
                            })
                          }else{
                            wx.showToast({
                              title: '保存失败！',
                              icon: 'none',
                              mask: true
                            })
                            this.setData({
                              modalName: null
                            })
                          }
                        },500)
                      }
                    })
                  }
                }
              })
          }else{
            wx.saveImageToPhotosAlbum({
              filePath: tempImgPath,
              success: res => {
                wx.showToast({
                  title: '保存成功！',
                  icon: 'success',
                  mask: true
                })

                this.setData({
                  modalName: null
                })
              }
            })
          }
        }
      })
  },
  hideModal(e) {
  
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取openid
    var openid = wx.getStorageSync("openid")
    if (openid != '') {
      //获得协会列表
      wx.request({
        url: 'http://localhost:8082/ass/getPassedAssList',
        data: {
          openid: openid
        },
        success: res => {
          console.log(res.data)
          this.setData({
            assPassList: res.data.data
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