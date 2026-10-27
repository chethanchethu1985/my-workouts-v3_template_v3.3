# V3 Changelog

## Development checkpoint — post master-prompt changes
- Added Workout Library CRUD fields and category filtering.
- Added selection of saved library definitions in Plan and category-specific Yoga/Cardio sessions.
- Added Calendar empty-date creation and date-specific plan visibility.
- Extended XLSX template/export/import with a Calendar Date column, validation, selected-plan-day import, missing-library-definition creation and date-specific override mapping.
- Added session completion records, timestamps, side-stage metadata, weekly/monthly progress KPIs, an 8-week activity trend, history filtering and CSV export.
- Added health measurement input-range checks, source/record timestamps and inactivity locking.
- Replaced broad Android storage access with a Storage Access Framework folder picker and persisted URI grants; removed broad file-management permission from the manifest patch.
- Kept connected health provider integration and the wider data-integrity/backup/production-release workstream deferred per user instruction.
- Added static structure checks and refreshed acceptance, progress, limitations and build documentation.
- No Android APK build or physical-device test is claimed.


## Uploaded workbook integration update (2026-10-27)
- Bundled the exact user-uploaded `Workout_V3_Weekly_Schedule_Template_with_Date.xlsx` as `www/workout-template.xlsx` without modifying the source workbook.
- Template download/share actions now use the bundled workbook rather than generating a different template.
- Excel import/export headers updated to the uploaded workbook schema: Date, Day, Main Category, Subcategory/Workout, Sets, Repetitions, Repetitions per Side, Duration, Duration Unit, Rest Interval, Rest Unit, Media Reference, Notes.
- Date-specific rows no longer require a Week column; blank dates use the currently selected plan week. Date-based schedule entries are included in export.
- Repetition ranges such as `10–12` are accepted and retained as a display/export label; added Biceps and Recovery category acceptance.
- GitHub Actions validates that the canonical workbook asset is present before Android project creation.
- Static JavaScript and V3 structure checks passed. Android build and device/runtime verification remain unconfirmed.
