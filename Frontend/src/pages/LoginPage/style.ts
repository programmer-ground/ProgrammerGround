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
  display:relative;
  width:50%;
  height:400px;
  border:1px solid black;
  background-color:#fff;
  margin:200px auto;

`;
