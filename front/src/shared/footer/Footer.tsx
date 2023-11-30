import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Footer: React.FC = () => {
  return (
<<<<<<< Updated upstream
    <StyledFooter>
      <div className="container">
        <StyledList>
          <StyledItem>
            <Link to="/">Правообладателям</Link>
          </StyledItem>
          <StyledItem>
            <Link to="/">Пользовательское соглашение</Link>
          </StyledItem>
          <StyledItem>
            <Link to="/">Правила рекомендаций</Link>
          </StyledItem>
          <StyledItem>
            <Link to="/">Справка</Link>
          </StyledItem>
        </StyledList>
      </div>
    </StyledFooter>
=======
    <MainFooter>
      <div className="container">Footer</div>
    </MainFooter>
>>>>>>> Stashed changes
  );
};

export default Footer;

<<<<<<< Updated upstream
const StyledFooter = styled.footer`
  background-color: #f0f0f0;
  padding: 20px;
`;

const StyledList = styled.ul`
  list-style: none;
  padding: 0;
  display: flex;
  column-gap: 20px;
`;

const StyledItem = styled.li`
  margin-bottom: 10px;
=======
const MainFooter = styled.footer`
  position: fixed;
  z-index: 2;
  bottom: 0;
  width: 100%;
  padding-top: 20px;
  padding-bottom: 20px;
  background: #111;
>>>>>>> Stashed changes
`;
