
@echo off
:begin
echo ==========================请选择你需要打包的项目===============================
echo 0 退出系统
echo 1 ccx-cloud-register-center
set/p a=请选择对应的数字:
if "%a%"=="1" goto :FILES
if "%a%"=="0" goto :end
echo\
echo 输入的值有误，请重新输入
echo\ 
goto :begin
:FILES
echo 打包ccx-cloud-register-center工程
call mvn clean package -Dmaven.test.skip & explorer target
pause
goto :begin

 
:end
exit
