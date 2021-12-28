module.exports = {
	parser: '@typescript-eslint/parser',
	plugins: ['@typescript-eslint', 'react-hooks'],
	extends: [
		'airbnb', // or airbnb-base
		'plugin:react/recommended',
		'plugin:jsx-a11y/recommended', // 설치 한경우
		'plugin:import/errors', // 설치한 경우
		'plugin:import/warnings', // 설치한 경우
		'plugin:@typescript-eslint/recommended',
		'plugin:prettier/recommended',
	],
	rules: {
		'react/jsx-indent': 0,
		'react/jsx-filename-extension': [
			1,
			{ extensions: ['.js', '.jsx', '.ts', '.tsx'] },
		],
		'import/no-unresolved': 'off',
		'no-use-before-define': 'off',
		'import/no-extraneous-dependencies': 'off',
		'import/extensions': 'off',
		'no-restricted-globals': 'off',
		'@typescript-eslint/no-unused-vars': 'off',
		'@typescript-eslint/explicit-module-boundary-types': 'off',
	},
	env: {
		browser: true,
	},
};
