import React from 'react'
import img from './PlayList.png'
import styled from 'styled-components'
const PlayList: React.FC = () => {
  return (
    <MainPlayList>
      <img src={img} alt='' />
    </MainPlayList>
  )
}

export default PlayList

const MainPlayList = styled.div`
  margin-top: 100px;
`
