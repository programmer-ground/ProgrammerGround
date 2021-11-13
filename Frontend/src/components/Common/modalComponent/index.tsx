import React, { useEffect, useState } from 'react';
import * as StyledComponent from './style';
import useShow from '@src/hooks/useShow';
import { repositoryModalMode } from '@src/store/modules/modal';
import { getAllPlaygrounds, createRepository } from '@src/lib/axios/playground';;

interface modalProps {
  title: string;
  itemList?: []
  state?: boolean,
}

const ModalComponent:React.FC<modalProps> = ({title, itemList, state}) => {
  const [show, dispatch] = useShow();  
  const [playgroundItem, setPlaygroundItem] = useState([]);

  const onClose = (e: React.MouseEvent<HTMLButtonElement>) => {
    dispatch(repositoryModalMode(!state));
  }

  const createRepositoryHandler = async (title: string, playgroundId: number) => {
    const confirmData = confirm(`${title} 이름으로 레포지토리를 생성하시겠습니까?`);
    const githubRepoVo ={
      title
    }
    
    if (confirmData) {
      await createRepository(playgroundId, githubRepoVo);
      alert('성공하였습니다.');
    }
  }

  useEffect(() => {
    const getData = async () => {
      const playgroundData = await getAllPlaygrounds();
      setPlaygroundItem(playgroundData.playground_card);
    }
    getData();
  },[]);
  return (
    <>
       <StyledComponent.ModalContainer>
         <StyledComponent.ModalContent>
           <StyledComponent.ModalHead>
             <StyledComponent.ModalTitle>{title}</StyledComponent.ModalTitle>
             <StyledComponent.ModalButton onClick={onClose}>X</StyledComponent.ModalButton>
           </StyledComponent.ModalHead>
           <StyledComponent.ModalBody>
           <StyledComponent.ModalBodyContainer>
              {playgroundItem.map((v,i)=> {
                return (
                  <StyledComponent.ModalContainerContent key={i}>
                    <StyledComponent.ModalContentData>{v.title}</StyledComponent.ModalContentData>
                    <StyledComponent.ModalContentData>{v.created_date.toString().slice(0, 10)}</StyledComponent.ModalContentData>
                    <StyledComponent.ModalContainerRepositoryCreateButton onClick={() => createRepositoryHandler(v.title, v.playground_id)}>생성</StyledComponent.ModalContainerRepositoryCreateButton>
                  </StyledComponent.ModalContainerContent>
                )
              })}
              </StyledComponent.ModalBodyContainer>
           </StyledComponent.ModalBody>
         </StyledComponent.ModalContent>
       </StyledComponent.ModalContainer>
    </>
  );
}

export default ModalComponent;


