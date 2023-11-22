import React from 'react';
import styled from 'styled-components';
const StyledHero = styled.section`
  margin-top: 100px;
`;
const Hero: React.FC = () => {
  return (
    <StyledHero>
      <div className="container">
        <h1>Hero Section</h1>
      </div>
    </StyledHero>
  );
};

export default Hero;
