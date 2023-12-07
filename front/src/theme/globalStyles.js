import { createGlobalStyle } from 'styled-components'

const GlobalStyle = createGlobalStyle`
  body {
    margin: 0;
    padding: 0;
     min-height: 100vh;
    background:#111111;
    font-family: Open-Sans, Helvetica, Sans-Serif;
  }
  #root{
    margin:auto;
    max-width:1440px;
    display:grid;
    grid-template-columns: 275px 1fr 348px ;
    grid-template-rows: 1fr auto;
    gap:30px;
    justify-content:center;
  }
  
  main{
  padding-top:20px; 
  }
  
`

export default GlobalStyle
