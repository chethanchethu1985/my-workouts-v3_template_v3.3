# Apply only permissions needed by the app. Media-folder access uses the Android
# Storage Access Framework and persisted URI grants, not broad file-management access.
p = "android/app/src/main/AndroidManifest.xml"
s = open(p, encoding="utf-8").read()
if "android.permission.VIBRATE" not in s:
    assert "<application" in s
    s = s.replace("<application", '    <uses-permission android:name="android.permission.VIBRATE" />\n    <application', 1)
open(p, "w", encoding="utf-8").write(s)
print("Manifest patched with minimal permissions")
