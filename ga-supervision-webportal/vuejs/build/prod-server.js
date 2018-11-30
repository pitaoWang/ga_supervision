// https://github.com/shelljs/shelljs
require('./check-versions')()

process.env.NODE_ENV = 'production'

var ora = require('ora')
var path = require('path')
var chalk = require('chalk')
var shell = require('shelljs')
var webpack = require('webpack')
var config = require('../config')
var webpackConfig = require('./webpack.prod.conf')
var express = require('express')
var port = process.env.PORT || config.prod.port

var spinner = ora('Building ...')
spinner.start()

var assetsPath = path.join(config.prod.assetsRoot, config.prod.assetsSubDirectory)
shell.rm('-rf', assetsPath)
shell.mkdir('-p', assetsPath)
shell.config.silent = true
shell.cp('-R', 'static/*', assetsPath)
shell.config.silent = false

webpack(webpackConfig, function (err, stats) {
    spinner.stop()
    if (err) throw err
    process.stdout.write(stats.toString({
        colors: true,
        modules: false,
        children: false,
        chunks: false,
        chunkModules: false
    }) + '\n\n')

    console.log(chalk.cyan('Build complete.\n\n'))

    var app = express()

    // handle fallback for HTML5 history API
    app.use(require('connect-history-api-fallback')())

    // serve pure static assets
    //var staticPath = path.posix.join(config.prod.assetsRoot, config.prod.assetsSubDirectory)
    app.use(express.static(config.prod.assetsRoot))

    app.listen(port, function (err) {
        if (err) {
            console.log(err)
            return
        }

        console.log(chalk.green('------------------------------------\n' +
            'Running server at [' + port + ']...\n' +
            '------------------------------------\n'
        ))
    })
})
