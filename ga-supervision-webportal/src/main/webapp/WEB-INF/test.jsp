<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>iview example</title>
    <link rel="stylesheet" type="text/css" href="http://unpkg.com/iview/dist/styles/iview.css">
    <script type="text/javascript" src="http://vuejs.org/js/vue.min.js"></script>
    <script type="text/javascript" src="http://unpkg.com/iview/dist/iview.min.js"></script>
</head>
<body>
<div id="app">
    <i-button @click="show">Click me!</i-button>
    <Modal v-model="visible" title="Welcome">welcom iView</Modal>
    
    <Upload action="http://10.5.3.137:28000/app/api/upload/avatar" name="file" >
        <Button type="ghost" icon="ios-cloud-upload-outline">Upload</Button>
    </Upload>
    
    <form method="post" action="http://10.5.3.137:28000/app/api/upload/avatar" enctype="multipart/form-data">
		choose:
		<input type="file" name="file" />
		<br/><br/>
		<input type="submit" value="upload" />
	</form>
</div>
<script>
    new Vue({
        el: '#app',
        data: {
            visible: false
        },
        methods: {
            show: function () {
                this.visible = true;
            }
        }
    })
  </script>
</body>
</html>