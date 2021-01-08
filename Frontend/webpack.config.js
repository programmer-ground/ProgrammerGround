const path = require('path');
const HtmlWebPackPlugin = require('html-webpack-plugin');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');

module.exports = {
  entry:'./src/index.tsx',
  output:{
    filename:'bundle.js',
    path:path.resolve(__dirname, 'dist'),
    publicPath:'/'
  },
  mode:'none',
  resolve:{
    extensions:['.js','.jsx','.ts', '.tsx'],
    alias:{
      '@src':path.resolve(__dirname, 'src')
    }
  },
  module:{
    rules:[
      {
        test:/\.css$/i,
        use:['style-loader', 'css-loader'],
        exclude:/node_modules/,
      },
      {
        test:/\.jsx?$/i,
        use:['babel-loader'],
        exclude:/node_modules/
      },
      {
        test:/\.tsx?$/i,
        use:['ts-loader'],
        exclude:/node_modules/
      },
      {
        test:/\.(png|jpg|svg)$/,
        use:['file-loader'],
        exclude:/node_modules/
      }
    ]
  },
  devServer:{
    contentBase:path.join(__dirname, "dist"),
    host:'localhost',
    publicPath:'/',
    overlay:true,
    stats:'errors-only',
    port:3000,
    historyApiFallback:true
  },
  plugins:[
    new HtmlWebPackPlugin({
      template:'./public/index.html'
    }),
    new CleanWebpackPlugin(),
  ]
}