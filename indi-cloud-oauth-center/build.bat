
@echo off
:begin
echo ==========================��ѡ������Ҫ�������Ŀ===============================
echo 0 �˳�ϵͳ
echo 1 ccx-cloud-oauth-center
set/p a=��ѡ���Ӧ������:
if "%a%"=="1" goto :OAUTH
if "%a%"=="0" goto :end
echo\
echo �����ֵ��������������
echo\ 
goto :begin
:OAUTH
echo ���ccx-cloud-oauth-center
call mvn clean package -Dmaven.test.skip & explorer target
pause
goto :begin

 
:end
exit
