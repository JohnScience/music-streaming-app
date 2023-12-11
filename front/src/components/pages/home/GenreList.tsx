import React from 'react'
import img from './GenreList.png'
import styled from 'styled-components'

const GenreList: React.FC = () => {
  return (
    <MainGenreList>
      <img src={img} alt='' />
    </MainGenreList>
  )
}

export default GenreList

const MainGenreList = styled.div`
  margin-top: 100px;
`
