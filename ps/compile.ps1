# Compile all Java source files and output to /bin
javac -d bin `
    src\model\Member.java `
    src\manager\TeamManager.java `
    src\utils\AutoSaveTask.java `
    src\utils\DBConnection.java `
    src\dao\MemberDAO.java `
    src\Main.java

#Uses .Net just for fun to do the same thing as above, however its doesnt follow orders - Members need to be compiled first. 
#javac -d bin $(Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })