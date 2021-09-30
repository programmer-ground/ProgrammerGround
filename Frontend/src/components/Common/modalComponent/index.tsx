import React from 'react';
import * as StyledComponent from './style';
import useShow from '@src/hooks/useShow';
import { repositoryModalMode } from '@src/store/modules/modal';

interface modalProps {
  title: string;
  itemList?: []
  state?: boolean,
}

const ModalComponent:React.FC<modalProps> = ({title, itemList, state}) => {
  const [show, dispatch] = useShow();

  const onClose = (e: React.MouseEvent<HTMLButtonElement>) => {
    dispatch(repositoryModalMode(!state));
  }

  return (
    <>
       <StyledComponent.ModalContainer>
         <StyledComponent.ModalContent>
           <StyledComponent.ModalHead>
             <StyledComponent.ModalTitle>{title}</StyledComponent.ModalTitle>
             <StyledComponent.ModalButton onClick={onClose}>X</StyledComponent.ModalButton>
           </StyledComponent.ModalHead>
           <StyledComponent.ModalSubmitButton>{title}</StyledComponent.ModalSubmitButton>
         </StyledComponent.ModalContent>
       </StyledComponent.ModalContainer>
    </>
  );
}

export default ModalComponent;


