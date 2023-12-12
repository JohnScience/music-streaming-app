import React from 'react'
import styled from 'styled-components'

const Information: React.FC = () => {
  return (
    <div>
      <List>
        <Item>
          <Text> Документация по проекту в Google </Text>
          <Link href='https://drive.google.com/drive/folders/1LMDWBQJvWTMkXAgWi2CtlUQaBiRl8ZKY'>
            https://drive.google.com/drive/folders/1LMDWBQJvWTMkXAgWi2CtlUQaBiRl8ZKY
          </Link>
        </Item>
        <Item>
          <Text>Документация по проекту в ClickUp </Text>
          <Link href='https://app.clickup.com/9005175548/v/dc/8cc04qw-288/8cc04qw-308'>
            https://app.clickup.com/9005175548/v/dc/8cc04qw-288/8cc04qw-308
          </Link>
        </Item>
        <Item>
          <Text> Макет: </Text>
          <Link href=' https://www.figma.com/file/m9mPk1duxN3yVPc8EinbOu/Project-Music-player?type=design&node-id=0%3A1&mode=design&t=M3kiGVCFLk3BlFYN-1'>
            https://www.figma.com/file/m9mPk1duxN3yVPc8EinbOu/Project-Music-player?type=design&node-id=0%3A1&mode=design&t=M3kiGVCFLk3BlFYN-1
          </Link>
        </Item>
        <Item>
          <Text> Репозиторий проекта </Text>
          <Link href='https://github.com/JohnScience/music-streaming-app'>
            https://github.com/JohnScience/music-streaming-app
          </Link>
        </Item>
        <Item>
          <Text>Таск Менеджер проекта CLickUp </Text>
          <Link href='https://app.clickup.com/9005175548/home'>https://app.clickup.com/9005175548/home</Link>
        </Item>
        <Item>
          <Text>Документация по API (требуется запустить приложение) </Text>
          <Link href=' http://localhost:8080/swagger-ui/index.html'> http://localhost:8080/swagger-ui/index.html</Link>
        </Item>
        <Item>
          <Text> Важно! Прочитайте Устав проекта.</Text>
        </Item>
      </List>
    </div>
  )
}

export default Information

const List = styled.ul`
  color: white;
`
const Item = styled.li``

const Text = styled.span``

const Link = styled.a``
