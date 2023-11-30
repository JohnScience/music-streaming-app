import React from 'react';
import styled from 'styled-components';

import search from '../../images/search.svg';

const Filter = () => {
  return (
    <SearchWrapper>
      <Input type="text" placeholder="Search Music, Artist, Genre" />
      <SearchImage src={search} alt="" loading="eager" aria-hidden="true" />
    </SearchWrapper>
  );
};

export default Filter;

const SearchWrapper = styled.div`
  max-width: 630px;
  position: relative;
`;
const Input = styled.input`
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 66px;
  font-size: 16px;
  padding-right: 40px;
  width: 400px;
  border-radius: 36px;
  background: rgba(65, 65, 65, 0.65);
  backdrop-filter: blur(88px);
  color: #b8b8b8;
  transition: 0.3s;
  border: none;
  &:focus-visible {
    outline: none;
  }
  &::placeholder {
    color: #b8b8b8;
    font-size: 16px;
    letter-spacing: 1px;
  }
`;

const SearchImage = styled.img`
  position: absolute;
  top: 15px;
  left: 15px;
`;
