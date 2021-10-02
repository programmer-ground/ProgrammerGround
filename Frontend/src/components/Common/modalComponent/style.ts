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
    padding-bottom: 30px;
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

export const ModalBodyContainer = styled.div`
    border-top: 1px solid #e9e9e9;
`;

export const ModalContainerContent = styled.div`
  display: flex;
  padding: 15px;

  &:not(:first-child) {
    border-top: 1px solid #e9e9e9;
  }

`;

export const ModalContentData = styled.span`
  flex: 1 1 auto;

  &:first-child {
    font-size: 15px;
  }

  &:not(:first-child) {
    display: inline-block;
    font-size: 10px;
    line-height: 22px;
  }

  &:not(:last-child) {
    margin-right: 10px;
  }

`;

export const ModalContainerRepositoryCreateButton = styled.button.attrs((props) => {
  type:'button'
})`
  border: 1px solid #e9e9e9;
  font-size: 13px;
  line-height: 28px;
  color: #333;
  cursor: pointer;
  background-color: #fff;

  &:hover {
    background-color: #78ffe0;
    color: #fff;
  }
`;

