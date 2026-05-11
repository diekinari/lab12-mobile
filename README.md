# DBases — Базы данных SQLite

Android-приложение на **Java** с XML-разметками: загрузка и отображение данных из SQLite-базы в ListView.

**Практическое занятие №12**, Тема 4 — Мобильная разработка.

---

## Стек

| Компонент | Версия |
|-----------|--------|
| Язык | Java |
| UI | XML layouts |
| БД | SQLite (из assets) |
| AGP | 8.7.3 |
| Gradle | 8.9 |
| compileSdk | 34 |
| minSdk | 24 |

---

## Структура проекта

```
app/src/main/
├── assets/
│   └── it_geniuses.db           ← готовая SQLite-база
├── java/com/example/navigation10/
│   ├── MainActivity.java        ← загрузка данных из БД в ListView
│   └── DBHelper.java            ← копирование БД из assets, SQLiteOpenHelper
├── res/
│   ├── layout/
│   │   ├── activity_main.xml    ← кнопка + ListView
│   │   └── listview_item.xml    ← элемент списка (имя + достижение)
│   └── values/
│       ├── strings.xml
│       ├── colors.xml
│       └── themes.xml
└── AndroidManifest.xml
```

---

## Как собрать

1. Открой проект в Android Studio (Koala / Ladybug / Meerkat или новее)
2. **File → Sync Project with Gradle Files**
3. Запусти через **Run** или `Shift + F10`
