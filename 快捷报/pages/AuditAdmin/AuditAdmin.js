// pages/AdminApplyList/AdminApplyList.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: null,
    imgList: [],
  },
  /**
   * 审核通过
   */
  auditAss: function(e) {
    console.log(e)
    var that = this
    var status = e.currentTarget.dataset.status
    /**
     * pass - 审核通过
     * reject - 审核不通过
     */
    if (status == 'pass') {
      wx.showModal({
        title: '温馨提示',
        content: '确定该协会审核通过',
        success: res => {
          /**
           * 用户点击确定
           */
          if (res.confirm) {
            //系统管理员审核通过
            wx.request({
              url: 'http://localhost:8082/sys/passAss',
              method: 'POST',
              header: {
                'content-type': 'application/x-www-form-urlencoded'
              },
              data: {
                assid: that.data.assInfo.assid,
                creatorid: that.data.assInfo.creatorid
              },
              success: res => {
                console.log(res.data)
                wx.redirectTo({
                  url: '../AuditSuccessful/AuditSuccessful',
                })
              }
            })
          }
          /**
           * 用户点击取消
           */
          else if (res.cancel) {
            console.log("管理员点击了取消")
          }
        }
      })
    } else if (status == 'reject') {
      wx.showModal({
        title: '温馨提示',
        content: '拒绝该协会审核通过',
        success: res => {
          /**
           * 用户点击确定
           */
          if (res.confirm) {
            //系统管理员审核通过
            wx.request({
              url: 'http://localhost:8082/sys/rejectAss',
              method: 'POST',
              header: {
                'content-type': 'application/x-www-form-urlencoded'
              },
              data: {
                assid: that.data.assInfo.assid,
                creatorid: that.data.assInfo.creatorid
              },
              success: res => {
                console.log(res.data)
                wx.showLoading({
                  title: '正在执行',
                })
                if (res.data.status == '200') {
                  setTimeout(function() {
                    wx.hideLoading()
                    wx.showToast({
                      title: '拒绝成功',
                    })
                  }, 1000)
                }
              }
            })
          }
          /**
           * 用户点击取消
           */
          else if (res.cancel) {
            console.log("管理员点击了取消")
          }
        }
      })
    }

  },
  /**
   * 选择图片
   */
  ChooseImage() {
    wx.chooseImage({
      count: 4, //默认9
      sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album'], //从相册选择
      success: (res) => {
        if (this.data.imgList.length != 0) {
          this.setData({
            imgList: this.data.imgList.concat(res.tempFilePaths)
          })
        } else {
          this.setData({
            imgList: res.tempFilePaths
          })
        }
      }
    });
  },
  /**
   * 预览图片
   */
  ViewImage(e) {
    wx.previewImage({
      urls: this.data.imgList,
      current: e.currentTarget.dataset.url
    });
  },
  /**
   * 删除图片
   */
  DelImg(e) {
    wx.showModal({
      title: '提示',
      content: '确定要删除这张照片吗？',
      cancelText: '取消',
      confirmText: '确定',
      success: res => {
        if (res.confirm) {
          this.data.imgList.splice(e.currentTarget.dataset.index, 1);
          this.setData({
            imgList: this.data.imgList,
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    //console.log(options)
    /**
     * 根据协会id查询协会信息
     */
    var assid = options.assid
    if (assid != '') {
      wx.request({
        url: 'http://localhost:8082/ass/getAssInfo',
        data: {
          assid: assid
        },
        success: res => {
          console.log(res.data)
          this.setData({
            assInfo: res.data.data,
            imgList: [res.data.data.logo]
          })
        }
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})