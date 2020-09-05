// pages/custom2/custom2.js
var app=getApp();
var type
var index = 3
var assname //协会名称
var slogan  //协会口号
var assid   //协会id
var fields = [] //自定义模板
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //一般字段，包括单行，多行，数字，分别代表的type为:text,textarea,number
    generalField: { 
    label: '',  //显示名称
    fieldName:'', //字段名称
    type: '',   //字段类型
    required: false, //字段是否必要
    description:'', //字段描述
    },
    //选择字段,包括单选字段,多选字段，分布代表的type为:radio,checkbox
    chooseField:{
      label: '',  //显示名称
      fieldName: '', //字段名称
      type: '',   //字段类型
      required: false, //字段是否必要
      description: '', //字段描述
      options:[
        {
          itemValue:1,
          itemName:"选项1"
        }, 
        {
          itemValue: 2,
          itemName: "选项2"
        }
        ]//选项
    },
    //用于显示
    options: [{
      itemValue: 1,
      itemName: "选项1"
    },
      {
        itemValue: 2,
        itemName: "选项2"
    }],

    //图片字段,包括图片上传，type为:image
    imageField:{
      label: '',  //显示名称
      fieldName: '', //字段名称
      type: '',   //字段类型
      required: false, //字段是否必要
      description: '', //字段描述
      imagenum:0//图片数量
    }
  },
  submit: function (e) {
    // var jsonstr = '{' + 'label:' + e.detail.value.label + ','
    //   + 'textarea:' + e.detail.value.textarea + ',' + 'type:' + this.data.type + '}'
    // this.data.field = JSON.parse(jsonstr);

    var lable = e.detail.value.label  //显示名称
    var description = e.detail.value.description //字段描述
    var fieldName = e.detail.value.fieldName; //
    var required = e.detail.value.switch

    // console.log(lable)
    // console.log(description)
    // console.log(fieldName)
    // console.log(required)
    
    if(lable!=''&&fieldName!=''){//显示名称和字段名称非空判断
      var reg = new RegExp('^[A-Za-z]+$')
      if (reg.test(fieldName)){//字段名称正则
        //若是单行文本，多行文本和数组
        if (type == 'text' || type == 'textarea' || type == 'number') {
          this.data.generalField.label = lable;
          this.data.generalField.description = description;
          this.data.generalField.fieldName = fieldName
          this.data.generalField.type = type;
          this.data.generalField.required = required
          console.log(this.data.generalField)
          //取出缓存
          fields = wx.getStorageSync(assid)
          //放入模板元素
          fields.push(this.data.generalField)
          //刷新缓存
          wx.setStorageSync(assid, fields)
          //var jsonStr = JSON.stringify(this.data.generalField)
          //重定向
          // wx.redirectTo({
          //   url: '../custom/custom?field=' + jsonStr +'&assid='+assid+ '&assname=' + assname + '&slogan=' + slogan+'&flag=right',
          // })
          wx.navigateBack({
            
          })
          //app.globalData.field.push(this.data.generalField)
        }
        //若为单选或者多选
        if (type == 'radio' || type == 'checkbox') {
          this.data.chooseField.label = e.detail.value.label;
          this.data.chooseField.description = e.detail.value.description;
          this.data.chooseField.fieldName = e.detail.value.fieldName;
          this.data.chooseField.type = type;
          this.data.chooseField.required = e.detail.value.switch
          // for (var i = 0; i < this.data.radios.length; i++) {
          //   this.data.radios[i].name = e.detail.value.radios
          // }
          console.log(this.data.chooseField)
          //取出缓存
          fields = wx.getStorageSync(assid)
          //放入模板元素
          fields.push(this.data.chooseField)
          //刷新缓存
          wx.setStorageSync(assid, fields)
          //var jsonStr = JSON.stringify(this.data.chooseField)
          //重定向
          // wx.redirectTo({
          //   url: '../custom/custom?field=' + jsonStr + '&assid=' + assid + '&assname=' + assname + '&slogan=' + slogan + '&flag=right',
          // })
          wx.navigateBack({
            
          })
          //app.globalData.field.push(this.data.chooseField)
        }
        //若为图片
        if (type == 'image') {
          this.data.imageField.label = e.detail.value.label;
          this.data.imageField.description = e.detail.value.description;
          this.data.imageField.fieldName = e.detail.value.fieldName;
          this.data.imageField.type = type;
          this.data.imageField.required = e.detail.value.switch
          this.data.imageField.imagenum = e.detail.value.imagenum
          console.log(this.data.imageField)
          //取出缓存
          fields = wx.getStorageSync(assid)
          //放入模板元素
          fields.push(this.data.imageField)
          //刷新缓存
          wx.setStorageSync(assid, fields)
          //var jsonStr = JSON.stringify(this.data.imageField)
          //重定向
          // wx.redirectTo({
          //   url: '../custom/custom?field=' + jsonStr + '&assid=' + assid + '&assname=' + assname + '&slogan=' + slogan + '&flag=right',
          // })
          //app.globalData.field.push(this.data.imageField)
          wx.navigateBack({
            
          })
        }
        // wx.navigateBack({
        // })
      
      }else{
        wx.showModal({
          title: '温馨提示',
          content: '字段名称不能包含中文,数字，下划线',
        })
      }
    }else{
        wx.showModal({
          title: '温馨提示',
          content: '显示名称或者字段名称不能为空',
        })
    }

    
  
  },

  /**
   * 增加选项
   */
  addoption() {
    this.data.chooseField.options.push({ itemValue: index  ,itemName: "选项"+index})
    console.log(this.data.chooseField.options)
    this.setData({ 
      options: this.data.chooseField.options
    });
    index++
  },
  
  /**
   * 修改选项名
   */
  changeName(e) {
    //console.log(e.currentTarget.dataset.index)
    var i = e.currentTarget.dataset.index
    this.data.chooseField.options[i-1].itemName = e.detail.value
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      console.log(options)
      this.setData({
        value: options.value
      });
      type = options.type
      index=3;
      //协会名称,协会口号和协会id
      // assname = options.assname
      // slogan = options.slogan
      assid =options.assid

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