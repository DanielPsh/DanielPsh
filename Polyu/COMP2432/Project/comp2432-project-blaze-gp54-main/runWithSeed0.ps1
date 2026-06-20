$ts = Get-Date -Format "yyyyMMdd_HHmmss"
New-Item -ItemType Directory -Force -Path .\log | Out-Null
$logPath = ".\log\$ts.log"

$utf8NoBom = New-Object System.Text.UTF8Encoding($false)
$writer = New-Object System.IO.StreamWriter($logPath, $false, $utf8NoBom)

try {
	cmd /c "cargo run -- --seed=0 2>&1" | ForEach-Object {
		$_
		$writer.WriteLine($_)
	}
}
finally {
	$writer.Dispose()
}

if ($LASTEXITCODE -ne 0) {
	exit $LASTEXITCODE
}