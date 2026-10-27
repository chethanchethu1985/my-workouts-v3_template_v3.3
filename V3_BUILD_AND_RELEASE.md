# V3 Build and Release

## Build prerequisites
- Node.js 20
- Java 17
- GitHub Actions enabled in the private `my-workouts-v3` repository.

## Local static checks
```bash
npm install
npm run check
```

The check command parses inline JavaScript and validates the presence of key UI/build wiring. It does not replace device acceptance tests.

## GitHub Actions
1. Upload the contents of this `workout-app-v3` directory to the root of the separate V3 repository.
2. Commit to `main` or `master`.
3. Run **Build Android APK** from Actions, or push a commit.
4. The workflow installs dependencies, copies `node_modules/xlsx/dist/xlsx.full.min.js` to `www/vendor/xlsx.full.min.js`, creates/syncs the Capacitor Android project and runs `assembleDebug`.
5. Download `My-Workouts-V3-APK` only if the workflow succeeds.
6. Install the debug APK and test on Android.

## Notes
- The app ID is `com.myworkout.v3`, intended to coexist with V2.
- The Android media folder picker uses Storage Access Framework and retained URI permission; select the `Workout_gif` folder.
- Health provider sync and the broader migration/backup/production-release assurance workstream are deferred.
- Do not commit passwords, access tokens, private keys or signing secrets.
- No APK is claimed built until a successful workflow result exists.
