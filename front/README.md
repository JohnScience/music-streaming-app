# Фронтенд для музыкального сервиса

## Разработка

Делаете изменения в коде, запускаете фронтенд локально:

```console
npm run dev
```

## Сборка с помощью Docker

```console
docker build -t music-service-front . && docker create --name music-service-front music-service-front sleep infinity && docker export music-service-front > music-service-front.tar
```

На выходе у вас должен получиться `.tar` архив. Внутри него по пути `/dist` будет находиться собранный фронтенд.
