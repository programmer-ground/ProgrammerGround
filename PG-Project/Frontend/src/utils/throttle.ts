/* eslint-disable prefer-rest-params */
// eslint-disable-next-line import/prefer-default-export

export const throttling = () => {
	let throttleCheck: any;

	return {
		throttle(callback: any, milliseconds: number) {
			if (!throttleCheck) {
				throttleCheck = setTimeout(() => {
					callback();
					throttleCheck = false;
				}, milliseconds);
			}
		},
	};
};
