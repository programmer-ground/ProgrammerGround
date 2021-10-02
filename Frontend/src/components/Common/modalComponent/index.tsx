import React, { useEffect, useState } from 'react';
import * as StyledComponent from './style';
import useShow from '@src/hooks/useShow';
import { repositoryModalMode } from '@src/store/modules/modal';
import { getNoticeLeaderList } from '@src/lib/axios/playground';;

interface modalProps {
  title: string;
  itemList?: []
  state?: boolean,
}

const ModalComponent:React.FC<modalProps> = ({title, itemList, state}) => {
  const [show, dispatch] = useShow();
  const [noticeItem, setNoticeItem] = useState([]);

  const onClose = (e: React.MouseEvent<HTMLButtonElement>) => {
    dispatch(repositoryModalMode(!state));
  }

  useEffect(() => {
    const getData = async () => {
      const noticeData = await getNoticeLeaderList();
      setNoticeItem(noticeData.user_notice);
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
              {noticeItem.map((v,i)=> {
                return (
                  <>
                    <StyledComponent.ModalContainerContent key={i}>
                      <StyledComponent.ModalContentData>{v.playground_title}</StyledComponent.ModalContentData>
                      <StyledComponent.ModalContentData>{v.date.toString().slice(0, 10)}</StyledComponent.ModalContentData>
                      <StyledComponent.ModalContainerRepositoryCreateButton>생성</StyledComponent.ModalContainerRepositoryCreateButton>
                    </StyledComponent.ModalContainerContent>
                  </>
                )
              })}
              </StyledComponent.ModalBodyContainer>
           </StyledComponent.ModalBody>
           <StyledComponent.ModalSubmitButton>{title}</StyledComponent.ModalSubmitButton>
         </StyledComponent.ModalContent>
       </StyledComponent.ModalContainer>
    </>
  );
}

export default ModalComponent;


