
/**
 * 标识符说明
 *  flag1 非空标识符
 *  flag2 编辑或者申请标识符
 *  flag3 跳转申请或者编辑成功页面标识符
 */

var flag1 = true
Page({
  data: {
    index: null,
    imgList: [],
    saveImage: ''
  },
  /**
   * 申请成功
   */
  apply: function (e) {
    console.log(e)
    //获取协会名称
    var assName = e.detail.value.assName
    //获取协会口号
    var assLogo = e.detail.value.assLogo
    //获取协会联系方式
    var assTel = e.detail.value.assTel
    //获取openid
    var openid = wx.getStorageSync("openid")
    //非空判断
    if (assName == '' || assTel == '') {
      flag1 = false
    }
    if (flag1) {

      if (this.data.flag2 == 'apply') {
        /**
         * 申请数据提交
         */
        wx.request({
          url: 'http://localhost:8082/ass/applyAssAdmin',
          data: {
            assname: assName,
            slogan: assLogo,
            contact: assTel,
            logo: this.data.saveImage,
            creatorid: openid
          },
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: res => {
            console.log(res)
            wx.redirectTo({
              url: '../ApplySuccessful/ApplySuccessful?flag3=apply',
            })
          }
        })
      }
      if (this.data.flag2 == 'edit') {
        /**
         * 编辑数据提交
         */
        wx.request({
          url: 'http://localhost:8082/ass/editAssAdmin',
          data: {
            assid: this.data.assid,
            assname: assName,
            slogan: assLogo,
            contact: assTel,
            logo: this.data.saveImage,
          },
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          success: res => {
            console.log(res.data)
            wx.redirectTo({
              url: '../ApplySuccessful/ApplySuccessful?flag3=edit',
            })
          }
        })
      }
    } else {
      wx.showModal({
        title: '温馨提示',
        content: '协会名称或者联系方式未填',
        showCancel: false
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
        //console.log(res.tempFilePaths)
        /**
         * 图片上传
         */
        wx.uploadFile({
          url: 'http://localhost:8082/common/uploadPic',
          filePath: res.tempFilePaths[res.tempFilePaths.length - 1],
          name: 'logoPic',
          success: res => {
            var result = JSON.parse(res.data);
            console.log(result)
            this.setData({
              saveImage: result.data
            })
          }
        })
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
            imgList: this.data.imgList
          })
        }
      }
    })
  },
  onLoad: function (options) {
    console.log(options)
    this.setData({
      flag2: options.flag
    })
    if (options.flag == 'edit') {
      this.setData({
        assid: options.assid
      })
      /**
       * 编辑数据查询
       */
      wx.request({
        url: 'http://localhost:8082/ass/getAssInfo',
        data: {
          assid: options.assid
        },
        success: res => {
          console.log(res.data)
          this.setData({
            assname: res.data.data.assname,
            slogan: res.data.data.slogan,
            contact: res.data.data.contact,
            imgList: [res.data.data.logo],
            saveImage: res.data.data.logo
          })
        }
      })
    }
  }
})