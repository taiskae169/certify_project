/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.height = 400; 
	config.toolbarCanCollapse = true; 
	config.font_names = '나눔바른고딕/NanumBarunGothic;맑은 고딕/Malgun Gothic;굴림/Gulim;돋움/Dotum;바탕/Batang;궁서/Gungsuh;' + config.font_names; 
	
	config.filebrowserBrowserUrl = "/TeamProject/ckfinder/ckfinder.html";
	config.filebrowserFlashBrowserUrl = "/TeamProject/ckfinder/ckfinder.html?type=Flash";
	config.filebrowserUploadUrl = "/TeamProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files";
	config.filebrowserImageUploadUrl = "/TeamProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images";
	config.filebrowserFlashUploadUrl = "/TeamProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash";
	config.filebrowserFileploadUrl = "/TeamProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files";
};