		var BXLService = function() {
		};

		BXLService.prototype.getClaimed = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getClaimed" ]);
		};

		BXLService.prototype.getDeviceEnabled = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getDeviceEnabled" ]);
		};

		BXLService.prototype.setDeviceEnabled = function(successCallback,
				errorCallback, deviceEnabled) {
			cordova
					.exec(successCallback, errorCallback, "BXLService",
							"executePrinter", [ "setDeviceEnabled",
									deviceEnabled ]);
		};

		BXLService.prototype.getOutputID = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getOutputID" ]);
		};

		BXLService.prototype.getPowerState = function(successCallback,
				errorCallback) {
			return cordova.exec(successCallback, errorCallback,
					"BXLService", "executePrinter", [ "getPowerState" ]);
		};

		BXLService.prototype.getState = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getState" ]);
		};

		BXLService.prototype.getDeviceServiceDescription = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getDeviceServiceDescription" ]);
		};

		BXLService.prototype.getDeviceServiceVersion = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getDeviceServiceVersion" ]);
		};

		BXLService.prototype.getPhysicalDeviceDescription = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getPhysicalDeviceDescription" ]);
		};

		BXLService.prototype.getPhysicalDeviceName = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getPhysicalDeviceName" ]);
		};

		BXLService.prototype.getCapRecNearEndSensor = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getCapRecNearEndSensor" ]);
		};

		BXLService.prototype.getCapRecPapercut = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getCapRecPapercut" ]);
		};

		BXLService.prototype.getCapRecMarkFeed = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getCapRecMarkFeed" ]);
		};

		BXLService.prototype.getAsyncMode = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getAsyncMode" ]);
		};

		BXLService.prototype.setAsyncMode = function(successCallback,
				errorCallback, asyncMode) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "setAsyncMode", asyncMode ]);
		};

		BXLService.prototype.getCharacterSet = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getCharacterSet" ]);
		};

		BXLService.prototype.setCharacterSet = function(successCallback,
				errorCallback, characterSet) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "setCharacterSet", characterSet ]);
		};

		BXLService.prototype.setCharacterSet = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "FontTypefaceList" ]);
		};

		BXLService.prototype.getPageModeArea = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getPageModeArea" ]);
		};

		BXLService.prototype.getPageModeHorizontalPosition = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getPageModeHorizontalPosition" ]);
		};

		BXLService.prototype.setPageModeHorizontalPosition = function(
				successCallback, errorCallback, position) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "setPageModeHorizontalPosition",
							position ]);
		};

		BXLService.prototype.getPageModePrintArea = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getPageModePrintArea" ]);
		};

		BXLService.prototype.setPageModePrintArea = function(
				successCallback, errorCallback, area) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "setPageModePrintArea", area ]);
		};

		BXLService.prototype.getPageModePrintDirection = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getPageModePrintDirection" ]);
		};

		BXLService.prototype.setPageModePrintDirection = function(
				successCallback, errorCallback, direction) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "setPageModePrintDirection",
							direction ]);
		};

		BXLService.prototype.getPageModeVerticalPosition = function(
				successCallback, errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getPageModeVerticalPosition" ]);
		};

		BXLService.prototype.setPageModeVerticalPosition = function(
				successCallback, errorCallback, position) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "setPageModeVerticalPosition",
							position ]);
		};

		BXLService.prototype.getRecEmpty = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getRecEmpty" ]);
		};

		BXLService.prototype.getRecNearEnd = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "getRecNearEnd" ]);
		};

		BXLService.prototype.open = function(successCallback,
				errorCallback, logicalDeviceName) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "open", logicalDeviceName ]);
		};

		BXLService.prototype.close = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "close" ]);
		};

		BXLService.prototype.claim = function(successCallback,
				errorCallback, timeout) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "claim", timeout ]);
		};

		BXLService.prototype.release = function(successCallback,
				errorCallback) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "release" ]);
		};

		BXLService.prototype.checkHealth = function(successCallback,
				errorCallback, level) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "checkHealth", level ]);
		};

		BXLService.prototype.cutPaper = function(successCallback,
				errorCallback, percentage) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "cutPaper", percentage ]);
		};

		BXLService.prototype.markFeed = function(successCallback,
				errorCallback, type) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "markFeed", type ]);
		};

		BXLService.prototype.pageModePrint = function(successCallback,
				errorCallback, control) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "pageModePrint", control ]);
		};

		BXLService.prototype.printBarCode = function(successCallback,
				errorCallback, station, data, symbology, height, width,
				alignment, textPosition) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "printBarCode", station, data,
							symbology, height, width, alignment,
							textPosition ]);
		};

		BXLService.prototype.printBitmap = function(successCallback,
				errorCallback, station, fileName, width, alignment) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "printBitmap", station, fileName,
							width, alignment ]);
		};

		BXLService.prototype.printBitmapWithURL = function(successCallback,
				errorCallback, station, imageURL, width, alignment) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "printBitmapWithURL", station, imageURL,
							width, alignment ]);
		};

		BXLService.prototype.printBitmapWithBase64 = function(successCallback,
				errorCallback, station, base64Data, width, alignment) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "printBitmapWithBase64", station, base64Data,
							width, alignment ]);
		};


		BXLService.prototype.printNormal = function(successCallback,
				errorCallback, station, data) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "printNormal", station, data ]);
		};

		BXLService.prototype.transactionPrint = function(successCallback,
				errorCallback, station, control) {
			cordova.exec(successCallback, errorCallback, "BXLService",
					"executePrinter", [ "transactionPrint", station,
							control ]);
		};


		BXLService.prototype.addEntry = function(successCallback, errorCallback, productName, ifType, bluetoothAddress)
		{
		    cordova.exec(successCallback, errorCallback, "BXLService",
        					"executePrinter", [ "addEntry", productName, ifType, bluetoothAddress]);

		};

		module.exports = new BXLService();

