<#
Set the MONGODB_URI environment variable persistently for the current user.
Usage:
  .\set-mongodb-uri.ps1 -Uri "mongodb+srv://user:pass@cluster.mongodb.net/votaya?retryWrites=true&w=majority"
If you omit `-Uri` the script will prompt you.
#>

param(
  [string]$Uri
)

if (-not $Uri) {
  $Uri = Read-Host "MongoDB connection string (mongodb+srv://... or mongodb://... )"
}

if (-not $Uri) {
  Write-Error "No URI provided. Aborting."
  exit 1
}

Write-Host "Setting MONGODB_URI for current user..."
setx MONGODB_URI $Uri | Out-Null
Write-Host "MONGODB_URI set. You may need to start a new terminal session to see it." 
