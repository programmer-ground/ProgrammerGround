import React from 'react';
import styled, {createGlobalStyle} from 'styled-components';
export const GlobalStyle = createGlobalStyle`
  body{
    background-color:#e9e9e9;
    margin:0;
    padding:0;
  }
`;
export const LoginContainer=styled.div`
  display:flex;
  justify-content:center;
  align-items:center;
  width:50%;
  height:500px;
  border:1px solid black;
`;
