var wxCharts = require('../../utils/wxcharts.js');   //引入wxChart文件
var app = getApp();
var pieChart = null;
var lineChart = null;
Page({
  data: {
    TabCur: 0,
    scrollLeft: 0,
    pieTitle: "饼图",//饼图名称
    lineTitle: "折线图",//折线图名称

    pieSeries: [{   //具体饼图
      name: '男',  //名字
      data: 60 //数据值
    }
      , {
      name: '女',
      data: 40
    }
    ],
    lineSeries: [
      {   //具体坐标数据
        name: '当日报名人数',  //名字
        data: [10, 20, 5, 0, 20, 11, 3],  //数据点
        format: function (val, name) {  //点击显示的数据注释
          return val + '人';
        }
      },
      {
        name: '总人数',
        data: [10, 30, 35, 35, 55, 66, 69],
        format: function (val, name) {
          return val + '人';
        }
      }
    ],
    categories: ['2018-6-13', '2018-6-14', '2018-6-15', '2018-6-16', '2018-6-17', '2018-6-18', '2018-6-19']//折现统计图的横坐标
  },
  touchHandler: function (e) {
    lineChart.showToolTip(e, {
      format: function (item, category) {
        return category + ' ' + item.name + ':' + item.data
      }
    });
  },
  updateData: function (e) {
    var series = [{


    }];
    lineChart.updateData({

    });
  },
  /**
   * tab切换
   */
  tabSelect(e) {
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      scrollLeft: (e.currentTarget.dataset.id - 1) * 60
    })
  },
  /**
   * 审核报名表页面
   */
  getAuditTheForm: function (e) {
    wx.navigateTo({
      url: '../AuditTheForm/AuditTheForm',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    var windowWidth = '', windowHeight = '';    //定义宽高
    try {
      var res = wx.getSystemInfoSync();    //试图获取屏幕宽高数据
      windowWidth = res.windowWidth;   //以设计图750为主进行比例算换
      windowHeight = res.windowWidth / 750 * 480    //以设计图750为主进行比例算换
    } catch (e) {
      console.error('getSystemInfoSync failed!');   //如果获取失败
    }
    pieChart = new wxCharts({     //定义一个wxCharts 饼图 实例
      background: '#ffffff',
      canvasId: 'pieCanvas',     //输入wxml中canvas的id
      type: 'pie',       //图标展示的类型有:'line','pie','column','area','ring','radar'
      series: this.data.pieSeries,

      width: windowWidth,  //图表展示内容宽度
      height: windowHeight,  //图表展示内容高度
    });
    lineChart = new wxCharts({     //定义一个wxCharts 折线图 实例
      background: '#ffffff',
      canvasId: 'lineCanvas',
      type: 'line',
      categories: this.data.categories,    //模拟的x轴横坐标参数
      series: this.data.lineSeries,
      dataLabel: false,
      width: windowWidth,
      height: windowHeight,
    });
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

  },
})