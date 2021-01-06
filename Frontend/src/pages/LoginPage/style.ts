import React from 'react';
import styled, {createGlobalStyle} from 'styled-components';
import logo from '../../assets/logo.svg';
export const GlobalStyle = createGlobalStyle`
  body{
    background-color:#e9e9e9;
    margin:0;
    padding:0;
  }
`;
export const LoginContainer=styled.div`
  position:relative;
  display:flex;
  width:40%;
  height:400px;
  border:1px solid black;
  background-color:#fff;
  margin:200px auto;
`;

export const LoginForm = styled.div`
  display:flex;
  justify-content:center;
  align-items:center;
`;
export const LoginLogo = styled.img.attrs({
  src:logo,
})`
  width:300px;
  height:300px;
`;
