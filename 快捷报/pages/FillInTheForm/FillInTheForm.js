//协会id
var assid

//获取应用实例
const app = getApp()
Page({
  data: {
    index: null,
    imgList: [],
    fields: []
  },


  /**
   * 报名表提交
   */
  signUp: function(e) {
    var fieldList = this.data.fields
    var data = e.detail.value

    

    //必填标识符
    var flag = true
    //校验姓名，性别，联系方式必填项
    var name = e.detail.value.name
    var gender = e.detail.value.gender
    var tel = e.detail.value.tel
    if(name==''||gender==''||tel==''){
      flag = false
    }

    //校验自定义必填
    for (var i = 0; i < fieldList.length; i++) {
      //判断是否必填
      if (fieldList[i].required) { //必填项的字段名
        var fieldName = fieldList[i].fieldName
        if (data[fieldName] == '') { //必填项未填
          flag = false
        }
      }
    }

    //console.log(e.detail.value)
    var openid = wx.getStorageSync("openid")
    //console.log(openid)
    if (flag) {
      wx.showLoading({
        title: '正在提交',
      })
      var timeNumber = setTimeout(() => {
        wx.hideLoading()
        if (openid != '') {
          this.saveMebmer(data, openid,assid)
          wx.redirectTo({
            url: '../SignUpSuccessful/SignUpSuccessful',
          })
        } else {
          //Promise
          app.checkOpenId(openid).then((res) => {
            openid = wx.getStorageSync("openid")
            console.log("openid-checkAfter:" + openid)
            this.saveMebmer(data, openid,assid)
            wx.redirectTo({
              url: '../SignUpSuccessful/SignUpSuccessful',
            })
          })
        }
      }, 1000)
    } else {
      wx.showModal({
        title: '温馨提示',
        content: '存在未填写的必填内容',
      })
    }
  },

  saveMebmer: function(data, openid,assid) {
    var jsonStr = JSON.stringify(data)
    wx.request({
      url: 'http://localhost:8082/member/applyAss',
      data: {
        tempData: jsonStr,
        openid: openid,
        assid:assid
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: res => {
        console.log(res)
      }
    })
  },

  /**
   * 图片选择
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
   * 图片预览
   */
  ViewImage(e) {
    wx.previewImage({
      urls: this.data.imgList,
      current: e.currentTarget.dataset.url
    });
  },
  /**
   * 图片删除
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
  /**
   * 根据openid查询用户
   */
  findUser:function(openid){
    wx.request({
      url: 'http://localhost:8082/user/getUserInfo',
      data: {
        openid: openid
      },
      success: res => {
        console.log(res.data)
        this.setData({
            name:res.data.data.name,
            tel:res.data.data.telephone
        })
        if(res.data.data.gender=='1'){//男
            this.setData({
              boyFlag:true,
              grilFlag:false
            })
        }
        if(res.data.data.gender=='2'){//女
            this.setData({
              boyFlag:false,
              grilFlag:true
            })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    var openid = wx.getStorageSync("openid")
    if (openid != '') {
      this.findUser(openid)
    } else {
      //Promise
      app.checkOpenId(openid).then((res) => {
        openid = wx.getStorageSync("openid")
        console.log("openid-checkAfter:" + openid)
        this.findUser(openid)
      })
    }

    //console.log(options)
    assid = options.assid
    console.log(assid)
    /**
     * 从数据库中查询模板 通过扫二维码到此页
     * 根据协会id查询协会基本信息
     */
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
          //绑定到页面中
          this.setData({
            fields: fields,
            assname: res.data.data.assname,
            slogan: res.data.data.slogan
          })
        } else {
          console.log("数据库中无模板没模板")
        }
      }
    })
  }
})