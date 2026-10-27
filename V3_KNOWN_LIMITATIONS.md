# V3 Known Limitations

- This source is reconstructed from the V3 APK's embedded web UI plus V2 scaffolding because the original complete V3 source ZIP was not available.
- Code-level implementation and static checks do not establish end-to-end functionality.
- The XLSX library is installed as an npm dependency and copied into `www/vendor` by GitHub Actions. If the build workflow is bypassed and that file is absent, XLSX actions will report that the library is unavailable.
- The Android Storage Access Framework plugin and `content://` media paths have not been compiled or tested on the target device. Providers may expose only direct children and playback support depends on Android/WebView formats.
- Large media files can still be constrained by device memory, browser/WebView format support, and the content provider.
- Google Fit, Health Connect and Fastrack Smart World integrations are deferred; no connected health values should be displayed as synced.
- The extended migration/restore, data-integrity and production-release assurance workstream is deferred. Keep separate backups before testing and do not treat a debug APK as a production release.
- Health CSV exports are plaintext; store exported files securely. Vault password recovery is not provided.
- No device-level security review or physical-device regression testing has been performed.
- The debug APK is for personal testing. Production signing and store-release compliance are not verified.
