import typescript from '@src/assets/typescript.png';
import svg from '@src/assets/svg.png';
import spring from '@src/assets/spring.png';
import react from '@src/assets/react-logo.png';

const playgroundData = {
	content: [
		{
			id: '1',
			title: 'TypeScript Making',
			date: '2021.01.09',
			src: typescript,
			position: '주니어',
			personnel: '1/4',
			language: ['React', 'TypeScript', 'Redux'],
		},
		{
			id: '2',
			title: 'SVG Making',
			date: '2021.01.17',
			src: svg,
			position: '시니어',
			personnel: '1/4',
			language: ['SVG'],
		},
		{
			id: '3',
			title: 'Spring Core',
			date: '2021.01.27',
			src: spring,
			position: '주니어',
			personnel: '1/4',
			language: ['Spring', 'Mysql'],
		},
		{
			id: '4',
			title: 'React',
			date: '2021.02.28',
			src: react,
			position: '주니어',
			personnel: '2/3',
			language: ['React'],
		},
	],
};
export default playgroundData;
