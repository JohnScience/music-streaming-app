# Фронтенд для музыкального сервиса

## Разработка

Делаете изменения в коде, запускаете фронтенд локально:

```console
npm run dev
```

## Добавление локальных изменений в репозиторий

Запускаете линтер:

```console
npm run lint
```

Если есть проблемы, фиксите. Если нет, то добавляете изменения в репозиторий. Из `front` запускаете:

```console
git add .
git commit -m "<feat|fix|docs|refactor|style|test>: Your commit message here"
git pull
git push
```

И запомните, никогда, НИКОГДА не используйте `git push --fоrсе`. [Ссылка](https://qna.habr.com/q/625016).

## Сборка с помощью Docker

```console
docker build -t music-service-front . && docker create --name music-service-front music-service-front sleep infinity && docker export music-service-front > music-service-front.tar
```

На выходе у вас должен получиться `.tar` архив. Внутри него по пути `/dist` будет находиться собранный фронтенд.
