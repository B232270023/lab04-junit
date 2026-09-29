# Lab04 - JUnit 5 Unit Testing

## Оюутны мэдээлэл

- Нэр: О. Шинэбаяр
- Оюутны код: B232270023
- Хичээл: F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест
- Лаборатори: №4 — Нэгжийн тестийн эхлэл, JUnit 5

---

## Лабораторийн зорилго

Энэхүү лабораторийн ажлын зорилго нь Java хэл дээр JUnit 5 ашиглан нэгжийн тест бичих, ердийн болон хязгаарын утгуудыг шалгах, `assertThrows` ашиглан буруу оролтын exception-ийг тестлэх, мөн `@ParameterizedTest` ашиглан ижил логиктой олон өгөгдлийг нэг тестийн методоор шалгах дадлага эзэмшихэд оршино.

Төслийг Apache Maven ашиглан үүсгэж, JUnit 5 framework болон Maven Surefire Plugin ашиглан тестүүдийг ажиллуулсан.

---

## Ашигласан технологи

- Java: OpenJDK 17 буюу түүнээс дээш
- Build tool: Apache Maven
- Unit Testing Framework: JUnit Jupiter 5.10.2
- Maven Surefire Plugin: 3.2.5
- Operating System: Ubuntu Linux / WSL2
- Version Control: Git
- Repository: GitHub

### Java хувилбар

```bash
openjdk version "21.0.8" 2025-07-15
OpenJDK Runtime Environment (build 21.0.8+9-Ubuntu-0ubuntu124.04.1)
OpenJDK 64-Bit Server VM (build 21.0.8+9-Ubuntu-0ubuntu124.04.1, mixed mode, sharing)
```

### Maven хувилбар

```bash
Apache Maven 3.8.7
Maven home: /usr/share/maven
Java version: 21.0.8, vendor: Ubuntu, runtime: /usr/lib/jvm/java-21-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.15.167.4-microsoft-standard-wsl2", arch: "amd64", family: "unix"
```

---

## Төслийн бүтэц

```text
lab04-junit/
├── .gitignore
├── README.md
├── pom.xml
├── results/
│   ├── mvn-test.txt
│   └── mvn-test-mutant.txt
├── src/
│   ├── main/
│   │   └── java/
│   │       └── mn/
│   │           └── edu/
│   │               └── must/
│   │                   └── sqat/
│   │                       └── GradeCalculator.java
│   └── test/
│       └── java/
│           └── mn/
│               └── edu/
│                   └── must/
│                       └── sqat/
│                           └── GradeCalculatorTest.java
```

---

## GradeCalculator класс

`GradeCalculator` класс нь `letterGrade()` болон `totalScore()` гэсэн үндсэн хоёр методтой.

### letterGrade(double score)

Оюутны нийлбэр онооноос үсгэн дүн тооцно.

| Оноо | Үсгэн дүн |
|---|---|
| 90–100 | A |
| 80–89.99 | B |
| 70–79.99 | C |
| 60–69.99 | D |
| 0–59.99 | F |

Хэрэв оноо `0`-оос бага эсвэл `100`-аас их байвал `IllegalArgumentException` шиднэ.

### totalScore(...)

Оюутны дараах үнэлгээний хэсгүүдийн нийлбэр оноог тооцно.

- Ирц: 10 оноо
- Лаборатори болон бие даалт: 40 оноо
- Сорил 1: 10 оноо
- Сорил 2: 10 оноо
- Шалгалт: 30 оноо
- Нийт: 100 оноо

Аль нэг утга сөрөг эсвэл тухайн хэсгийн дээд хязгаараас их байвал `IllegalArgumentException` шиднэ.

---

## Нэгжийн тест

`GradeCalculatorTest` класс дотор нийт **10 тестийн метод** бичсэн.

Тестүүдийг Arrange–Act–Assert буюу AAA бүтцээр зохион байгуулсан бөгөөд тест бүрд `@DisplayName` ашиглан ойлгомжтой нэр өгсөн.

Шалгасан үндсэн тохиолдлууд:

- `95 → A`
- `85 → B`
- `75 → C`
- `65 → D`
- `30 → F`
- `90 → A`
- `89.99 → B`
- `60 → D`
- `59.99 → F`
- `0 → F`
- `100 → A`
- `-1 → IllegalArgumentException`
- `101 → IllegalArgumentException`
- `totalScore(10, 40, 10, 10, 30) → 100`
- Сөрөг attendance → `IllegalArgumentException`
- Lab = 41 → `IllegalArgumentException`
- Exam = 31 → `IllegalArgumentException`

`letterGrade` болон `totalScore` функцүүдэд `@ParameterizedTest` ашигласан.

---

## Parameterized Test

`letterGrade`-ийн ердийн утгуудыг parameterized тестээр шалгасан:

```text
95 → A
85 → B
75 → C
65 → D
30 → F
```

Мөн хязгаарын утгуудыг:

```text
90 → A
89.99 → B
60 → D
59.99 → F
0 → F
100 → A
```

гэж тусад нь шалгасан.

`totalScore` функцэд мөн parameterized тест ашиглаж:

```text
10, 40, 10, 10, 30 → 100
5, 20, 5, 5, 15 → 50
0, 0, 0, 0, 0 → 0
```

гэсэн өгөгдлүүдийг шалгасан.

---

## Тестийн үр дүн

Тестүүдийг дараах командаар ажиллуулсан:

```bash
mkdir -p results
mvn test 2>&1 | tee results/mvn-test.txt
```

JUnit-ийн parameterized тестийн өгөгдөл бүр тусдаа тестээр тоологдох тул нийт:

```text
Tests run: 21
```

тест ажиллана.

Амжилттай ажилласан эцсийн үр дүн:

```text
Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Тестийн бүрэн гаралтыг:

```text
results/mvn-test.txt
```

файлд хадгалсан.

---

## Exception тест

Хүчингүй оролтыг шалгахдаа JUnit 5-ийн `assertThrows()` assertion ашигласан.

`letterGrade()` метод дээр:

```text
-1
101
```

утгуудыг шалгасан.

`totalScore()` метод дээр:

```text
att = -5
lab = 41
exam = 31
```

зэрэг зөвшөөрөгдөх хязгаараас гарсан утгуудыг шалгасан.

Эдгээр тохиолдолд `IllegalArgumentException` шидэгдэж байгааг тестээр баталгаажуулсан.

---

## Mutation Test

Тестүүд алдааг үнэхээр илрүүлж чадаж байгаа эсэхийг шалгах зорилгоор mutation тест хийсэн.

`GradeCalculator.java` файл дахь:

```java
if (score >= 90)
```

нөхцөлийг зориуд:

```java
if (score > 90)
```

болгон өөрчилсөн.

Дараа нь:

```bash
mvn test 2>&1 | tee results/mvn-test-mutant.txt
```

командыг ажиллуулсан.

Mutation хийсний дараа `90 → A` гэсэн хязгаарын тест амжилтгүй болсон.

Учир нь `score > 90` нөхцөлд яг `90` оноо A нөхцөлд орохгүй болсон.

Mutation тестийн үед:

```text
Failures: 1
BUILD FAILURE
```

гарсан.

Mutation-ийн гаралтыг:

```text
results/mvn-test-mutant.txt
```

файлд хадгалсан.

Дараа нь өөрчлөлтийг буцааж:

```java
if (score >= 90)
```

болгон зассан.

Бүх тестийг дахин ажиллуулахад:

```text
Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

гарсан.

---

## Хамгийн сонирхолтой тест

Энэ лабораторийн хамгийн сонирхолтой тест нь `90` оноог яг `A` үсгэн дүн байх ёстой гэж шалгасан boundary тест байсан. Учир нь `95` зэрэг ердийн утгыг шалгахад `>= 90` нөхцөлийг `> 90` болгон өөрчилсөн алдаа илрэхгүй байсан ч яг `90` гэсэн хязгаарын утгыг шалгасан тест алдааг шууд илрүүлсэн. Энэ нь зөвхөн ердийн утгуудыг шалгах нь хангалтгүй болохыг харуулсан. Мөн `assertThrows` ашигласнаар буруу оролтын үед програм зөв exception шидэж байгаа эсэхийг шалгах боломжтой болсон. `@ParameterizedTest` болон `@CsvSource` ашигласнаар олон оролтыг давхардсан тестийн код бичихгүйгээр шалгах боломжтой байсан. AAA бүтэц ашигласнаар тестийн бэлтгэл, үйлдэл болон шалгалтын хэсгүүд илүү ойлгомжтой болсон. Mutation тест нь бичсэн тестүүд зөвхөн амжилттай ажиллахаас гадна кодын алдааг үнэхээр илрүүлэх чадвартай байх ёстойг харуулсан.

---

## Дүгнэлт

Энэхүү лабораторийн ажлаар JUnit 5 framework ашиглан Java програмд нэгжийн тест бичиж, ажиллуулж сурлаа. `GradeCalculator` классын үсгэн дүн болон нийт оноо тооцох функцүүдийг ердийн болон хязгаарын утгуудаар шалгасан. Буруу оролтууд дээр `assertThrows` ашиглан `IllegalArgumentException` зөв шидэгдэж байгааг баталгаажуулсан. Мөн `@ParameterizedTest` болон `@CsvSource` ашигласнаар олон тестийн өгөгдлийг нэг тестийн методоор үр дүнтэй шалгах боломжтой болсон. `90`, `89.99`, `60`, `59.99`, `0`, `100` зэрэг boundary утгуудыг тестлэх нь хязгаарын алдааг илрүүлэхэд чухал болохыг ойлгосон. Mutation тестээр `score >= 90` нөхцөлийг `score > 90` болгон өөрчлөхөд `90 → A` тест алдааг амжилттай илрүүлсэн. Кодыг зөв төлөвт буцааж зассаны дараа бүх тест `Failures: 0`, `Errors: 0` үр дүнтэй амжилттай ажилласан. Энэхүү лабораторийн ажил нь цаашдын coverage болон mutation testing-ийн суурь ойлголт, дадлагыг эзэмшихэд чухал ач холбогдолтой байлаа.# lab04-junit