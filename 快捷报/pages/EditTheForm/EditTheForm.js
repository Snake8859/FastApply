var assid //协会id
var openid //openid
Page({
  data: {
    index: null,
    imgList: [],
  },
  /**
   * 根据openid查询用户
   */
  findUser: function (openid) {
    wx.request({
      url: 'http://localhost:8082/user/getUserInfo',
      data: {
        openid: openid
      },
      success: res => {
        console.log(res.data)
        this.setData({
          name: res.data.data.name,
          tel: res.data.data.telephone
        })
        if (res.data.data.gender == '1') {//男
          this.setData({
            boyFlag: true,
            grilFlag: false
          })
        }
        if (res.data.data.gender == '2') {//女
          this.setData({
            boyFlag: false,
            grilFlag: true
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:function(options){

    openid = wx.getStorageSync("openid")
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
    //console.log(assid)
    /**
     * 从数据库中查询模板 通过查看报名表页面到此页面
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
          var template = JSON.parse(res.data.data.templatecontent)
          //绑定到页面中
          this.setData({
            //fields: fields,
            assname: res.data.data.assname,
            slogan: res.data.data.slogan
          })
          wx.request({
            url: 'http://localhost:8082/member/getApplyFormData',
            data: {
              openid: openid,
              assid: assid
            },
            success: res => {
              console.log(res.data)
              //取出templatedata的json字符串
              var jsonStr = res.data.data.templatedata
              //转成json对象
              var templateData = JSON.parse(jsonStr)
              //console.log(data)
              //将模板数据填充给模板中
              for (var i = 0; i < template.length;i++){
                //取出字段名
                var fieldName = template[i].fieldName
                if(template[i].type!='checkbox'){//若不为多选
                //从模板数据中找到对应字段名的数据
                var value = templateData[fieldName]
                template[i].value = value
                }
                if(template[i].type=='checkbox'){//若为多选
                  //从模板数据中找到对应字段名的数据
                  var value = templateData[fieldName]
                  for(var j = 0;j<value.length;j++){
                     for(var z= 0;z<template[i].options.length;z++){
                       if(value[j]==template[i].options[z].itemValue){
                         template[i].options[z].checked = true
                       }
                     }
                  }
                }
              }
              console.log(template)
              this.setData({
                fields:template
              })
            }
          })

        } else {
          console.log("数据库中无模板没模板")
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onShow:function(){
      
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
            imgList: this.data.imgList
          })
        }
      }
    })
  },
})