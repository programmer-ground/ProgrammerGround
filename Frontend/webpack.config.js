const path = require('path');
const HtmlWebPackPlugin = require('html-webpack-plugin');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');

module.exports = {
  entry:'./src/index',
  output:{
    filename:'bundle.js',
    path:path.resolve(__dirname, 'dist')
  },
  mode:'none',
  resolve:{
    extensions:['.js','.jsx'],
    alias:{
      '@src':path.resolve(__dirname, 'src')
    }
  },
  module:{
    rules:[
      {
        test:/\.css$/i,
        use:['style-loader', 'css-loader'],
      },
      {
        test:/\.jsx?$/i,
        use:['babel-loader']
      }
    ]
  },
  devServer:{
    contentBase:path.join(__dirname, "dist"),
    port:3000
  },
  plugins:[
    new HtmlWebPackPlugin({
      template:'./public/index.html'
    })
  ]
}