# Welcome to FIT5046 LAB03_03

哈囉大家好，以下是注意事項。


## git
1. 記得先創建自己任務的分支`git branch <task-name>`
2. 然後移動到自己分支後進行工作`git checkout <task-name>`
3. 確認自己所在分支`git branch` **建議每次工作時都確認**
4. 新創建分支後，若要push上遠端，需要用在該分支下`git push --set-upstream origin <task-name>`，在遠端創立該分支名並與本地進行連結。
5. 完成步驟4後，之後就可以只使用`git push`就推上遠端．

- 時常`git add.` + `git commit -m "<做了什麼>"`是好習慣。
- `git log` + `git checkout <log 流水號>`，可回復作業階段。

## android studio

- test user_name, user_password: email@monash.edu, 123456
- 應該只要都做fragment就行。
- 各task的java在各package(資料夾)裡，方便管理。
- 文字可以硬code，但寫在res/value/strings後呼叫，是好習慣。（我的framework大部分都硬code，反省）
- 同一介面id都要不一樣，不同可一樣。但大家一起工作，可a能還是取名成類似`<map-address-cofirm-button>`保險？

## 其他資源

- [如何在Android Studio設計並套用自己的Theme](https://medium.com/@maggie.kuo/%E5%A6%82%E4%BD%95%E5%9C%A8android-studio%E8%A8%AD%E8%A8%88%E4%B8%A6%E5%A5%97%E7%94%A8%E8%87%AA%E5%B7%B1%E7%9A%84theme-973f7a2f14a6)


