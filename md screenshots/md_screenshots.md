Запустить с первого раза предсказуемо не получилось. Вместо кириллицы что-то нечитаемое:
![first try](https://github.com/zrdpsh/boop/blob/main/md%20screenshots/1.PNG?raw=true)

Попытки по советам из сети менять переменную при запуске виртуальной машины или внутри самой программы не помогли:
![first solution](https://github.com/zrdpsh/boop/blob/main/md%20screenshots/stackoverflow.PNG?raw=true)
![second solution](https://github.com/zrdpsh/boop/blob/main/md%20screenshots/stackoverflow2.PNG?raw=true)

...видимо, потому, что менять кодировку надо ещё **на этапе компиляции**
![third solution](https://github.com/zrdpsh/boop/blob/main/md%20screenshots/solution.PNG?raw=true)

После этого стало работать как надо:
![third solution](https://github.com/zrdpsh/boop/blob/main/md%20screenshots/cmder.PNG?raw=true)
