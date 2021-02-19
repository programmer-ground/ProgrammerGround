module.exports = {
	roots: ['<rootDir>/src'],
	testMatch: [
		'**/__tests__/**/*.+(ts|tsx|js)',
		'**/?(*.)+(spec|test).+(ts|tsx|js)',
	],
	transform: {
		'^.+\\.(ts|tsx)$': 'ts-jest',
	},
	collectCoverageFrom: ['src/**/*.{js,jsx}'],
	moduleNameMapper: {
		'^.+\\.(svg|png|ico)$': 'ts-jest',
	},
};
