/* eslint-disable @typescript-eslint/no-var-requires */
const path = require('path');
const HtmlWebPackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const webpack = require('webpack');

module.exports = {
	entry: {
		app: ['@babel/polyfill', './src/index'],
	},
	mode: 'development',
	resolve: {
		extensions: ['.js', '.jsx', '.ts', '.tsx'],
		alias: {
			'@src': path.resolve(__dirname, 'src'),
		},
	},
	module: {
		rules: [
			{
				test: /\.s[ac]ss$/i,
				use: ['style-loader', 'css-loader', 'sass-loader'],
				exclude: /node_modules/,
			},
			{
				test: /\.jsx?$/i,
				use: ['babel-loader'],
				exclude: /node_modules/,
			},
			{
				test: /\.tsx?$/i,
				use: ['ts-loader'],
				exclude: /node_modules/,
			},
			{
				test: /\.(png|jpg|svg)$/,
				use: {
					loader: 'file-loader',
					options: {
						name: '[name].[ext]',
						outputPath: 'assets',
						publicPath: '../assets/',
					},
				},
				exclude: /node_modules/,
			},
		],
	},
	devServer: {
		contentBase: path.join(__dirname, 'dist'),
		host: 'localhost',
		publicPath: '/',
		overlay: true,
		stats: 'errors-only',
		port: 3000,
		historyApiFallback: true,
		proxy: {
			'/api': {
				target: 'http://localhost:8080',
				changeOrigin: true,
				pathRewrite: { '^/api': '' },
			},
		},
		headers: {
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Headers':
				'Origin, X-Requested-With, Content-Type, Accept',
			'Access-Control-Allow-Credentials': 'true',
			'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
		},
	},
	plugins: [
		new HtmlWebPackPlugin({
			template: './public/index.html',
		}),
		new CleanWebpackPlugin(),
		new webpack.BannerPlugin({
			banner: '배너내용입니다.',
		}),
	],
	output: {
		filename: '[name].[chunkhash].js',
		path: path.resolve(__dirname, 'dist'),
		publicPath: '/',
	},
};
