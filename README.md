# JAVA_JDBC6
学习JDBC连接MySQL时产生的代码
本次上传只是为了自己验证是否可以上传成功


第一次失败原因：没有配置git用户名和邮箱
1. 打开 git bash here
Git 用户名和邮箱配置_01_git
2. 用户名和邮箱配置
git config --global user.name "用户名"
git config --global user.email "邮箱"
git config --list

第二次失败报错：Failed to connect to github.com port 443 after 21098 ms: Timed out
解决：
设置代理：
git config --global https.proxy
取消代理：
git config --global --unset https.proxy
很奇怪为什么设置完再取消就可以解决了，但是真的就解决了，哈哈哈
