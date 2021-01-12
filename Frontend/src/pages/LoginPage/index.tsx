/* eslint-disable react/jsx-filename-extension */
/* eslint-disable no-use-before-define */
/* eslint-disable import/extensions */
import React from 'react';
import * as StyledComponent from './style';

const LoginPage = () => {
  return (
    <>
      <StyledComponent.GlobalStyle />
      <StyledComponent.LoginContainer>
        <StyledComponent.LoginLogo />
        <StyledComponent.LoginButtonContainer>
          <StyledComponent.LoginButton>
            Sign In With GitHub
            <StyledComponent.GithubLogo />
          </StyledComponent.LoginButton>
        </StyledComponent.LoginButtonContainer>
      </StyledComponent.LoginContainer>
    </>
  );
};

export default LoginPage;
