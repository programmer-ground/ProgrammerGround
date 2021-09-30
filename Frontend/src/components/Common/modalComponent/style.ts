import styled from 'styled-components';

export const ModalContainer = styled.div`
  position: fixed;
  top: 0; 
  right: 0;
  bottom: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.7);
`;

export const ModalHead = styled.div`
  display: flex;
  padding: 15px;
`;

export const ModalContent = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  border-radius: 1px;
`;

export const ModalTitle = styled.strong`
    flex: 1 1 auto;
		font-size: 20px;
		line-height: 35px;
	  font-weight: normal;

		&:not(:last-child) {
			margin-right: 10px;
		}
`;

export const ModalBody = styled.div`
    &:not(:first-child) {
      margin-top: 20px;
    }
    padding: 0 15px 30px;
`;

export const ModalButton = styled.button.attrs((props) => {
  type: 'button'
})`
  flex: 1 1 auto;
  padding: 10px;
  border: none; 
  background-color: transparent;
  cursor: pointer;
`;

export const ModalSubmitButton = styled.button`
  background-color: #4c94e8;
  width: 100%;
  text-align: center;
  color: #fff;
  font-weight: 700;
  height: 45px;
  line-height: 45px;
  border: 0;
  cursor: pointer; 
`;
