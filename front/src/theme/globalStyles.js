import { createGlobalStyle } from 'styled-components'

const GlobalStyle = createGlobalStyle`
  body {
    margin: 0;
    padding: 0;
     height: 100%;
    background:#111111;
    font-family: Open-Sans, Helvetica, Sans-Serif;
  }

  main{
    display:flex;
    justify-content:center;
  padding-top:20px; 
   min-height: calc(100vh - 223px);
  }
`

export default GlobalStyle
