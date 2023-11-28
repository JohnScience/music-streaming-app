package com.music_streaming_app;


import com.music_streaming_app.repository.RepositoryAudioRecordings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {

  @Autowired
  RepositoryAudioRecordings repoAudioRecordings;


  // TODO: добавить мок-данные и переписать тесты для автоматического тестирования

  @Test
  public void shouldReturnFeaturedAudioRecordings() {

    System.out.println(repoAudioRecordings.findFeatured());

  }


}
