// pages/custom/custom.js
var app = getApp();
var assid //协会id
var field //基本模板
var fields = [] //自定义模板
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgList: [],
    index: null,
    fields: [],
    checked: -1,
    chooseList: [{
      value: 0,
      name: '单行文字',
      type: "text",
      checked: false,
      hot: false,
    }, {
      value: 1,
      name: '多行文字',
      type: 'textarea',
      checked: false,
      hot: false,
    }, {
      value: 2,
      name: '单项选择',
      type: 'radio',
      checked: false,
      hot: true,
    }, {
      value: 3,
      name: '多项选择',
      type: 'checkbox',
      checked: false,
      hot: true,
    }, {
      value: 4,
      name: '数字',
      type: 'number',
      checked: false,
      hot: false,
    }, {
      value: 5,
      name: '图片上传',
      type: 'image',
      checked: false,
      hot: false,
    }]
  },
  /**
   * 模板保存
   */
  saveForm() {
    //console.log(this.data.fields)
    var jsonstr = JSON.stringify(this.data.fields)
    console.log(jsonstr)

    /**
     * 保存协会自定义模板
     */
    wx.request({
      url: 'http://localhost:8082/ass/createTemplate',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        assid: assid,
        templatecontent: jsonstr
      },
      success: res => {
        console.log(res.data)
         wx.redirectTo({
           url: '/pages/CreateSuccessful/CreateSuccessful',
         })
      }
    })

  },

  /**
   * 图片的选择，预览，以及删除
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
  ViewImage(e) {
    wx.previewImage({
      urls: this.data.imgList,
      current: e.currentTarget.dataset.url
    });
  },
  DelImg(e) {
    wx.showModal({
      title: '提示',
      content: '确定要删除此图片吗？',
      confirmText: '确定',
      cancelText: '放弃',
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
  showModal(e) {
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    let items = this.data.chooseList;
    //console.log(items)
    // let value = this.data.chooseList;
    // console.log(value)
    let value = this.data.checked

    if (e.currentTarget.dataset.value == 1) {

      wx.navigateTo({
        url: '/pages/custom2/custom2?value=' + value + '&type=' + items[value].type+'&assid='+assid,
      })

      // wx.redirectTo({
      //   //传递自定义项目类型编号，类型名称；协会名称和协会口号
      //   url: '/pages/custom2/custom2?value=' + value + '&type=' + items[value].type + '&assname=' + this.data.assname + '&slogan=' + this.data.slogan+'&assid='+assid,
      //   success: function (res) { },
      //   fail: function (res) { },
      //   complete: function (res) { },
      // })
    }

    this.setData({
      modalName: null,

    })
  },

  ChooseCheckbox(e) {
    let items = this.data.chooseList;
    let value = e.currentTarget.dataset.value;
    for (let i = 0, lenI = items.length; i < lenI; ++i) {
      if (items[i].value == this.data.checked) {
        items[i].checked = false;
        this.data.checked = -1;
      }
    }
    for (let i = 0, lenI = items.length; i < lenI; ++i) {
      if (items[i].value == value && this.data.checked != value) {
        this.data.checked = i;
        items[i].checked = true;
      }
    }
    this.setData({
      chooseList: items
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //console.log(options)
    assid = options.assid

    //左右页面标识符
    var flag = options.flag

    this.setData({
      assname: options.assname,
      slogan: options.slogan
    })

    console.log(assid)
    /**
     * 从数据库中查询模板 从列表页跳转到此页
     * 根据协会id查询协会基本信息
     */
    if (flag=='left') {
      wx.request({
        url: 'http://localhost:8082/ass/getAssInfo',
        data: {
          assid: assid
        },
        success: res => {
          console.log(res.data)
          if (res.data.data.templatecontent != null) {
            console.log("有模板")
            var fields = JSON.parse(res.data.data.templatecontent)
            wx.setStorageSync(assid, fields)
            //保存到全局field中
            //app.globalData.field = fields
            //绑定到页面中
            this.setData({
              fields: fields
            })
          } else {
            console.log("数据库中无模板没模板")
          }
        }
      })
    } 
    // else if(flag=='right') {
    //   /**
    //    * 从自定义模板页面1跳转此页
    //    */
    //   field = options.field
    //   var field_json = JSON.parse(field)
    //   console.log(field_json)
    //   //缓存中取自定义模板
    //   fields = wx.getStorageSync(assid)
    //   fields.push(field_json)
    //   //再次存入缓存中
    //   wx.setStorageSync(assid, fields)
    //   this.setData({
    //     fields: fields
    //   })
    // }
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


    fields = wx.getStorageSync(assid)
    this.setData({
         fields: fields
    })
    // if (app.globalData.field==null)
    // return;
    // var field = app.globalData.field;
    // this.data.fields.push(field);
    // this.setData({ fields: this.data.fields});
    // console.log(field)
    // console.log(this.data.fields)
    // if (app.globalData.field.length != 0) {
    //   console.log("globalData:" + app.globalData.field)
    //   this.setData({
    //     fields: app.globalData.field
    //   })
    // }
    // for (var i = 0; i < app.globalData.field.length;i++){
    //   console.log(app.globalData.field[i])
    // }
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