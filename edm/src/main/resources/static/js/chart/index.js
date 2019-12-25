$(document).ready(function() {
	

	var chartTop = Highcharts.chart('top', {
		chart: {
			type: 'column'
		},
		title: {
			text: '各机组设备总量'
		},
		exporting: {
	        enabled: false   // 关闭导出按钮
	 },
		credits:{
		    enabled:false // 禁用版权信息
		},

		xAxis: {
			type: 'category',
			labels: {
				rotation: -45  // 设置轴标签旋转角度
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: ''
			}
		},
		legend: {
			enabled: false
		},
		tooltip: {
			pointFormat: '设备总量: <b>{point.y:.0f} </b>'
		},
		series: [{
			name: '总量',
			data: [
				['1号机组', 24],
				['2号机组', 23],
				['3号机组', 21],
				['4号机组', 16],
				['5号机组', 16]
			],
			dataLabels: {
				enabled: true,
				color: '#FFFFFF',
				align: 'right',
				format: '{point.y:.0f}'// :.1f 为保留 1 位小数
			}
		}]
	});
	
	var chartMiddle = Highcharts.chart('middle',{
		chart: {
			type: 'column'
		},
		exporting: {
	        enabled: false   // 关闭导出按钮
	 },
		credits:{
		    enabled:false // 禁用版权信息
		},
		title: {
			text: '设备异常情况'
		},
		xAxis: {
			categories: [
				'1号机组','2号机组','3号机组','4号机组','5号机组'
			],
			crosshair: true
		},
		yAxis: {
			min: 0,
			title: {
				text: ''
			}
		},
		tooltip: {
			// head + 每个 point + footer 拼接成完整的 table
			headerFormat: '<span style="font-size:10px"><font>{point.key}</font></span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0"><font>{series.name}:</font> </td>' +
			'<td style="padding:0"><font>{point.y:.0f}</font> </td></tr>',
			footerFormat: '</table>',
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				borderWidth: 0
			}
		},
		series: [{
			name: '异动',
			data: [49.9, 71.5, 106.4, 129.2, 144.0]
		}, {
			name: '故障',
			data: [83.6, 78.8, 98.5, 93.4, 106.0]
		}, {
			name: '检修',
			data: [48.9, 38.8, 39.3, 41.4, 47.0]
		}]
	});


	var chart1 = Highcharts.chart('bottom', {
		chart: {
			type: 'bar'
		},
		title: {
			text: '设备评级结果'
		},
		xAxis: {
			categories: ['1号机组', '2号机组', '3号机组', '4号机组', '5号机组']
		},
		yAxis: {
			min: 0,
			title: {
				text: '各等级数量'
			}
		},
		exporting: {
	        enabled: false   // 关闭导出按钮
		},
		credits:{
		    enabled:false // 禁用版权信息
		},
		legend: {
			/* 图例显示顺序反转
	         * 这是因为堆叠的顺序默认是反转的，可以设置 
	         * yAxis.reversedStacks = false 来达到类似的效果 
	         */
			reversed: true 
		},
		plotOptions: {
			series: {
				stacking: 'normal'
			}
		},
		series: [{
			name: '一级设备',
			data: [5, 3, 4, 7, 2]
		}, {
			name: '二级设备',
			data: [2, 2, 3, 2, 1]
		}, {
			name: '三级设备',
			data: [3, 4, 4, 2, 5]
		}]
	});
});