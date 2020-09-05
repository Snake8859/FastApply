var flag; 
Page({
  data: {
    index: null,
    imgList: [],
  },
  /**
   * 返回个人中心
   */
  returnPP(){
      wx.switchTab({
        url: '../PersonalPage/PersonalPage',
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
  onLoad(options){
    console.log(options)
    //从路径取出flag
    flag = options.flag
  
    //flag进行判断，如果是查看报名表
    if(flag=="view"){
        console.log("view")
    }

    //flag进行判断，如果是编辑报名表
    if(flag=="edit"){
      console.log("edit")
    }

    //flag进行判断，如果是审核报名表
    if(flag=="audit"){
      console.log("audit")
    }
  },
  onShow(){
    this.setData({
      flag: flag
    })
  }
})