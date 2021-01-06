import React from 'react';
import * as StyledComponent from './style';

const LoginPage = () =>{

  return(
    <>
    <StyledComponent.GlobalStyle/>
    <StyledComponent.LoginContainer>
      <StyledComponent.LoginForm>
          <StyledComponent.LoginLogo/>
      </StyledComponent.LoginForm>
    </StyledComponent.LoginContainer>
    </>

  )
}

export default LoginPage;