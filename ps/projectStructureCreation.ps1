# Create subfolders for project structure
mkdir src/model
mkdir src/manager
mkdir src/utils
mkdir bin

# Create the necessary Java files
New-Item -Path src/Main.java -ItemType File
New-Item -Path src/model/Member.java -ItemType File
New-Item -Path src/manager/TeamManager.java -ItemType File
New-Item -Path src/utils/AutoSaveTask.java -ItemType File

# Create .gitignore and README.md
New-Item -Path .gitignore -ItemType File
New-Item -Path README.md -ItemType File