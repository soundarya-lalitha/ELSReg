ControlFocus("File Upload","","Edit1")
Sleep(2000)
$pm1=$cmdLine[1]
$pathmod=StringReplace($cmdLine[1],"\\", "\")
ControlSetText("File Upload","","Edit1",$pathmod)
Sleep(2000)
ControlClick("File Upload","","Button1")

