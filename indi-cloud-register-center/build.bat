
@echo off
:begin
echo ==========================��ѡ������Ҫ�������Ŀ===============================
echo 0 �˳�ϵͳ
echo 1 ccx-cloud-register-center
set/p a=��ѡ���Ӧ������:
if "%a%"=="1" goto :FILES
if "%a%"=="0" goto :end
echo\
echo �����ֵ��������������
echo\ 
goto :begin
:FILES
echo ���ccx-cloud-register-center����
call mvn clean package -Dmaven.test.skip & explorer target
pause
goto :begin

 
:end
exit
