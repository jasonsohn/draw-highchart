function getTime() {
	var d = new Date();

	var s = leadingZeros(d.getFullYear(), 4) + '-'
			+ leadingZeros(d.getMonth() + 1, 2) + '-'
			+ leadingZeros(d.getDate(), 2) + ' ' +

			leadingZeros(d.getHours(), 2) + ':'
			+ leadingZeros(d.getMinutes(), 2);

	return s;
}

function jsonParsing() {
	var jsonLocation = 'test.json';
	// var jsonLocation = 'usage2.json';
	$.getJSON(jsonLocation, function(data) {
		console.log("success");

		var arr = [];

		$.each(data, function(i, item) {
			arr.push(item.date);
			// arr.push(item.computerSystem.manufacturer);
			arr.push(item.memory.use);
		});
	})
}

function readTextFile(file, callback) {
	var rawFile = new XMLHttpRequest();
	rawFile.overrideMimeType("application/json");
	rawFile.open("GET", file, true);
	rawFile.onreadystatechange = function() {
		if (rawFile.readyState === 4 && rawFile.status == "200") {
			callback(rawFile.responseText);
		}
	}
	rawFile.send(null);
}

function readParsing() {
	// usage:
	var file = 'test.json';
//	var file = 'file:///D:/workspace/openiot/usageAgent/log/usage.json';
//	var file = 'usage2.json';
	
	readTextFile(file, function(text) {
		var data = JSON.parse(text);
		console.log(data);
		
		console.log(data.date);
		console.log(data.memory.use);
		console.log(data.CPU["CPU load"]);
		console.log(data.CPU["CPU load per"][1]);
		console.log(data.CPU["CPU load per"].length);
		
	});
}

var cpuChart = new Highcharts.Chart('container', {
	title: {
        text: 'CPU 이용률'
    },
    xAxis : { // x축
		categories : [ '05-07']
	},

	yAxis : { // y축
		title : {
			text : '이용률(%)'
		},

		plotLines : [ { // 선
			value : 0,
			width : 1,
			color : '#808080'
		} ]
	},

	tooltip : {
		valueSuffix : '%'
	},

	legend : { // 범례
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle',
		borderWidth : 0
	},

	series : [ { // 값
		name : 'CPU',
		data : [ 0.0 ]
	} ]
});


document.addEventListener("click", function() {
//	jsonParsing();
	readParsing();
});