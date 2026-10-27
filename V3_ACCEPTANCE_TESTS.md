# V3 Acceptance Tests

Statuses: PASS, FAIL, BLOCKED, NOT TESTED, NOT IMPLEMENTED, DEFERRED.
A source-level check is not proof of runtime/device success.

| Test | Status | Evidence / next action |
|---|---|---|
| Inline JavaScript syntax | PASS | `npm run check` parses inline scripts. |
| Critical UI/source structure | PASS (static) | `scripts/check-v3-structure.js` checks 12 expected controls, code paths and build wiring. |
| V3 application ID and storage namespace | PASS (source/config review) | `com.myworkout.v3`, `myWorkouts.v3`, media DB `wk3`; device coexistence remains untested. |
| V2 source left unchanged | PASS (workspace process) | V2 archive is used only as a reference; final repository diff must be checked by the user. |
| Workout Library CRUD and category filtering | NOT TESTED | Create/edit/delete, search, category filter, media and YouTube links on device. |
| Plan library selection and snapshot isolation | NOT TESTED | Add library definition, edit library later, verify saved plan remains unchanged. |
| Calendar date selection, empty dates and per-date overrides | NOT TESTED | Test date navigation, empty-date creation, copy/edit and date-specific isolation. |
| Genuine XLSX template and export | NOT TESTED | GitHub build bundles SheetJS; open generated files in Excel/LibreOffice. |
| XLSX/CSV import validation, preview, merge/replace, duplicate prevention | NOT TESTED | Test malformed headers, numeric values, duplicate rows, unknown categories and repeat imports. |
| Explicit calendar-date import mapping | NOT TESTED | Import `Calendar Date (YYYY-MM-DD)` rows and verify they appear only on mapped dates. |
| Import creates missing library definitions | NOT TESTED | Confirm prompt, saved definitions and imported parameters. |
| Yoga/Cardio selection and direct session execution | NOT TESTED | Verify only selected exercises run; test add/reorder/remove, pause/resume and independent history. |
| Exercise, rest, transition and breathing timers | NOT TESTED | Test timed and repetition-based exercises, configured rests, transition previews, voice cue order and pause/resume. |
| Left/right stages and exercise history | NOT TESTED | Verify both sides are logged separately and saved with timestamps. |
| Progress KPIs, activity trend and history export | NOT TESTED | Compare KPIs with saved records; filter and export CSV. |
| Health Tracker input validation, encryption and inactivity lock | NOT TESTED | Verify values, BMI, vault unlock/lock, inactivity timeout and local-only behavior. |
| Android SAF folder picker and persisted permission | NOT TESTED | Select `Workout_gif`, close/reopen app, sync files and test media playback. |
| Google Fit / Health Connect / Fastrack sync | DEFERRED | Explicitly deferred by user. |
| Full migration/restore assurance, production release signing and broader release verification | DEFERRED | Explicitly deferred by user; debug build workflow remains in scope for source delivery. |
| Android APK compilation | NOT TESTED | GitHub Actions must succeed; dependency install timed out in this environment. |
| Physical-device acceptance and V2/V3 side-by-side install | NOT TESTED | User will test after installation. |
