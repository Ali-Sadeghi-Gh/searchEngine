![LOGO](https://github.com/omid-hdr/Digital_System_Design/blob/main/Document/image/Untitled.jpg)
# InvertedIndex SearchEngine
in this project we create a library that can search text very fast.
it has three parts
constructor and search and addData.
this is very general that you can search widely and can customize search and text .

## Tools
- Java
- Regex


## Implementation Details
![LOGO](https://github.com/omid-hdr/Digital_System_Design/blob/main/Document/image/Screenshot%202024-06-28%20234248.png)

برای چنین پروژه ای از سه فایل استفاده کردم. اولین فایل همان بالابر است و آدرس و وضعیت آن را اپدیت میکندد. دومی هم فایل کنترلر است و اصل منطق حرکت و توقف و جهت حرکت آسانسور و همچنین پاسخگویی به درخواست ها را بر عهده دارد.
سومی برای تست کردن که همان فایل TESTBENCH.v است.


## How to Run
you can import searchEngine library and use this to search in texts. at first you should create a SearchEngine Object with call it's constructor.
parameters are filters and decoder and tokenizer.
filter is for filtering text. it is a interface that filters data. it has default filter that filter numbers or 
tokenizer is for split words, it can be by space or , and etc... . it is an interface that if you want to customize you should create this class and implement it interface 
decoder is for handle input query
some words must be in text(compulsory), some words can be in text (optional) and some words shouldn't in text(forbidden).

now we should add our data with addDate function. this parameter is a hashmap<K, String>.
you can add data every time you want.

also you can use search method for searching text.


## Results
![LOGO](https://github.com/omid-hdr/Digital_System_Design/blob/main/Document/image/Screenshot%202024-06-29%20064855.png)
چنین است که میتوان یک سناریو را شبیه سازی کرد و سپس مورد بررسی قرار داد که چگونه عمل میکند.همچنین دو مدل خروجی داریم یکی موج که در شکل بالا مشاهده میکنید و یکی هم خروجی متنی که خودمان در تست بنچ نوشتیم.


![LOGO](https://github.com/omid-hdr/Digital_System_Design/blob/main/Document/image/Screenshot%202024-06-29%20011317.png)



## Related Links
 - https://www.geeksforgeeks.org/inverted-index
 - https://www.dideo.ir/v/yt/CeGtqouT8eA/how-google-searches-one-document-among-billions-of-documents-quickly%3F

## Authors
- [Omid](https://github.com/omid-hdr)
- [Ali](https://github.com/Ali-Sadeghi-Gh)

## Date
summer 2024
