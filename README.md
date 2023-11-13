# Музыкальный сервис

В разработке. Подробности в [Telegram канале](https://t.me/projectmusicapp).

* `front` - фронтенд, пишущийся на React + JavaScript и собираемый бандлером Vite.
* `back` - бэкенд, пишущийся на Spring Boot + Java.

Так как разработка находится на раннем этапе, выбор технологий может меняться.

## Установка

Для клонирования репозитория напишите

```
git clone --depth 1 https://github.com/JohnScience/music-streaming-app
```

В директории, в которую вы хотите установить директорию с проектом с помощью [git](https://git-scm.com/downloads).

## Запуск

1. Убедитесь, что у вас установлен Docker на вашей машине. Если нет, вы можете скачать его с [официального сайта Docker](https://www.docker.com/products/docker-desktop).

2. Перейдите в директорию `back` проекта

```
cd music-streaming-app/back
```

3. Чтобы очистить и собрать JAR файл вашего проекта с помощью Maven выполните команду

```
mvn clean package
```

4. Для запуска docker контейнера введите комманду 

``` 
docker-compose up 
```


