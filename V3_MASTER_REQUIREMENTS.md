> **Scope decision for this development pass (user-confirmed):** Implement the rest of this master scope. Defer (a) Google Fit / Health Connect / Fastrack Smart World provider integration and connected-health sync KPIs, and (b) the extended data-integrity/migration/backup/restore and production-release assurance workstream. The existing GitHub Actions debug-APK build workflow remains in scope. Device acceptance testing will be performed by the user after installation. Deferred items are not to be marked PASS.

---

# WORKOUT APPLICATION V3 — FINAL MASTER DEVELOPMENT REQUIREMENTS
## Existing Code Enhancement, UI Redesign, Functional Completion and Android Release

### 1. Role and objective

Act as a Senior Product Owner, Business Analyst, Mobile Application Architect, Senior Android/Capacitor Developer, UI/UX Designer, QA Engineer, and Release Manager.

I already have an existing V2 application source code and have created a separate V3 project in GitHub. The initial V3 code is also prepared.

Your task is to **continue development from the existing V3 codebase**, using the original V2 source code and the attached V3 requirements document as references where available.

The main objective of V3 is to improve the existing user interface, simplify workout planning and execution, and implement the additional features specified below.

This is not a request to start another project from scratch, generate another mock-up, or merely write a development plan.

Inspect the existing V3 source code, understand what has already been implemented, identify the gaps, and modify the existing V3 project accordingly.

### 2. Mandatory rules

1. Continue from the existing V3 code. Do not recreate the application unnecessarily.
2. Keep V2 completely unchanged. Never overwrite, modify, or break the original V2 project.
3. V3 must have a distinct Android application ID and be independently installable alongside V2.
4. Preserve working V3 functionality unless a requirement explicitly changes it.
5. Reuse the existing technology stack and architecture wherever practical.
6. Do not replace functioning code simply to achieve a different implementation style.
7. Do not claim that code is implemented, integrated, secure, built, or tested without evidence.
8. Never fabricate API access, health measurements, media, test results, or successful builds.
9. Do not embed passwords, OTPs, access tokens, refresh tokens, private keys, or client secrets in the source code or GitHub repository.
10. Implement the actual functionality, not just the UI or placeholder buttons.
11. If a requirement depends on an unavailable API, permission, credential, device, or external service, implement the supported portion and clearly document the dependency.
12. Maintain project checkpoints and update the progress and acceptance-test documents as development proceeds.

### 3. First action: inspect the existing V3 implementation

Before changing code:

- Inspect the complete V3 repository and build configuration.
- Review HTML, CSS, JavaScript, Capacitor configuration, Android overrides, media-access code, dependencies, and GitHub Actions workflows.
- Review the existing navigation and screens.
- Identify the existing implementations of Calendar, Plan, Workout Library, Yoga, Cardio, Progress, workout execution, history, and health tracking.
- Check the existing data models, storage mechanisms, and media references.
- Identify which requirements are complete, partially complete, missing, or broken.
- Run available syntax checks and tests before making changes.
- Preserve the existing code and build configuration as a recoverable checkpoint.

Create or update:

- `V3_MASTER_REQUIREMENTS.md`
- `V3_PROGRESS.md`
- `V3_ACCEPTANCE_TESTS.md`
- `V3_CHANGELOG.md`
- `V3_KNOWN_LIMITATIONS.md`
- `V3_BUILD_AND_RELEASE.md`

Do not stop after producing these documents. Continue with implementation.

---

### 4. Application branding and UI redesign

Redesign the application to provide a polished, consistent, mobile-first Android experience.

Requirements:

- Maintain consistent typography, colours, spacing, icons, cards, buttons, and form controls.
- Make screens readable and usable on supported Android screen sizes.
- Provide clear empty, loading, success, and error states.
- Ensure all buttons and navigation controls perform their intended actions.
- Avoid unnecessary duplicate screens and confusing navigation.
- Preserve existing functionality unless explicitly replaced below.

Display the application name prominently and add **“Designed by CB” directly beneath it**.

Use the previously supplied photography logo as visual inspiration. Create a minimalist, professional design without inserting the entire screenshot or its phone interface. If the reference image is unavailable, do not invent a replacement logo.

### 5. Workout Library

Rename “GIF & Video Library” to **“Workout Library”**.

The Workout Library must be the central repository for workout definitions and associated media.

Support these main categories:

- Chest
- Back
- Shoulders
- Legs
- Arms
- Core
- Yoga
- Cardio
- Dance
- Step-Board
- Stretching
- Breathing

Each workout should support:

- A unique, stable workout ID.
- Main category and workout name.
- Subcategory where applicable.
- Description and instructions.
- Exercise type: repetitions, timed, or another supported type.
- Default sets and repetitions.
- Repetitions per side where applicable.
- Default duration and duration unit.
- Rest interval and rest unit.
- Notes.
- One or more associated videos and GIFs.
- YouTube URL references.
- Media preview, where supported.

Allow users to add, edit, remove, search, filter, and reuse workouts.

The library must remain separate from daily workout plans. Changes to a daily plan must not unintentionally modify the original library definition.

New workouts must become available for selection immediately.

### 6. Calendar tab

When the user selects a calendar date, display the actual workout plan assigned to that date.

Show:

- Selected date.
- Main category.
- Subcategory/workout name.
- Sets.
- Repetitions per set.
- Repetitions per side where applicable.
- Exercise duration and unit where applicable.
- Rest interval.
- Notes.
- Planned or completed status where available.

Use a readable table or responsive mobile cards.

Example:

| Main Category | Workout | Sets | Reps per Set | Rest |
|---|---|---:|---:|---|
| Chest | Dumbbell Bench Press | 3 | 12 | 45 sec |
| Chest | Dumbbell Fly | 3 | 10 | 45 sec |
| Core | Side Plank | 3 | Timed | 30 sec |

These are examples, not hard-coded workout values.

The Calendar must read the saved plan for the selected date. It must not display sample data as though it were the user's actual schedule.

Support navigation between months, dates with scheduled workouts, empty dates, and access to workout details or execution.

A date-specific edit must not unexpectedly change other dates.

### 7. Plan tab — structured planning only

The Plan tab must be dedicated to creating, editing, saving, and organising daily workout plans.

Move bulk Excel import, template download, and schedule import/export functionality to the Workout Library.

Replace the current edit-based workout selection with structured fields:

1. Main Category.
2. Subcategory/Workout.
3. Day.
4. Calendar date where applicable.
5. Sets.
6. Repetitions per set.
7. Repetitions per side.
8. Duration.
9. Duration unit.
10. Rest interval.
11. Rest unit.
12. Notes.

Populate the workout dropdown dynamically based on the selected main category.

Allow users to:

- Select multiple exercises.
- Add, reorder, edit, remove, and save exercises.
- Save and reuse daily plans.
- Assign plans to dates.
- Preview the selected sequence.
- Execute a saved plan.
- Customise session parameters without modifying library defaults.

Use repetitions for repetition-based exercises and duration for timed exercises.

Keep all default values configurable. Validate numeric fields and permit blanks when a field does not apply.

When a plan is saved, preserve its session parameters. A later library edit must not silently overwrite existing plan settings.

### 8. Workout Library — Excel template and schedule import/export

Implement genuine `.xlsx` template creation, import, preview, validation, and export under the Workout Library tab.

Provide:

- Download Excel Template.
- Share Excel Template.
- Upload Weekly Workout Plan.
- Preview Imported Plan.
- Export Current Schedule.

The workbook must include:

1. Day of the Week.
2. Main Category.
3. Subcategory / Workout Name.
4. Sets.
5. Repetitions per Set.
6. Repetitions per Side.
7. Duration.
8. Duration Unit.
9. Rest Interval.
10. Rest Unit.
11. Media Reference.
12. Notes.

Include sample rows and instructions. Support Monday through Sunday and multiple workouts per day.

Generate a real `.xlsx` file, not CSV renamed to `.xlsx`.

Support native Android file saving and sharing where available, with a working download fallback.

For uploaded workbooks:

- Validate headers, days, categories, exercise names, numeric values, units, and applicable required fields.
- Identify missing values, invalid values, duplicates, unknown exercises, and missing media references.
- Match existing exercises using stable IDs or reliable matching fields.
- Preview the proposed import before applying changes.
- Allow correction or creation of missing library entries.
- Allow selected-day import.
- Offer Merge and Replace.
- Require confirmation before replacing an existing plan.
- Preserve the original workbook and library definitions.
- Prevent duplicate entries during repeated imports.
- Display the actual number of imported, skipped, and rejected rows.

Imported workouts must appear in the Plan and Calendar screens and execute using the imported parameters.

Support a date-mapping mechanism when applying weekday schedules to actual calendar dates. Do not assume a weekday alone uniquely identifies a date.

### 9. Yoga and Cardio tabs

Replace the existing Yoga and Cardio interfaces with clean, selection-driven screens.

The Yoga screen should support:

- Yoga poses.
- Stretching and flexibility.
- Breathing exercises.
- Recovery and relaxation routines.

The Cardio screen should support:

- Cardio exercises.
- Dance.
- Step-Board.
- Other suitable aerobic activities.

Both screens must use the shared Workout Library.

Provide dropdowns, checkboxes, or equivalent selection controls and allow configuration of timing, repetitions, rest, and order where applicable.

Users must be able to select exactly which exercises they want to perform.

Only the selected exercises should run. Do not automatically execute every exercise in the library or require completion of unrelated exercises.

Support adding, removing, reordering, previewing, starting, pausing, resuming, restarting, and exiting a session where applicable.

Support imported schedules and independent completion history.

### 10. Direct workout execution, timers, and breathing

Allow any exercise to be started directly without completing previous workouts.

Display the selected sequence before execution.

Correct timer behaviour:

- Rest intervals such as 45 or 90 seconds are rest periods, not exercise durations.
- Repetition-based exercises must not show an unnecessary exercise-duration countdown.
- Timed exercises must use their configured duration.
- Rest between sets, rest between exercises, transition time, and exercise duration must be distinct.
- Use the actual settings saved in the selected session.

During the 10-second transition in pre-workout and post-workout routines, display the upcoming movement and available media preview.

For side-specific exercises, track left and right execution separately. Example: Left Side — 12 Reps, followed by Right Side — 12 Reps.

Provide breathing voice cues for “Inhale,” “Hold,” “Exhale,” and “Hold”, synchronised with the configured breathing durations. Prevent overlapping cues and provide appropriate audio controls.

### 11. Local media and YouTube

Fix the existing `workout_gif` folder synchronisation and media-access issues.

Support permitted Android file and folder selection using native pickers or the Storage Access Framework where appropriate.

- Support multiple file selection and folder access where permitted.
- Preserve access permissions where supported.
- Avoid unnecessary copying of large files.
- Do not impose arbitrary application-level size limits.
- Handle memory, storage, permission, and unsupported-format limitations.
- Display clear errors and retry options.
- Distinguish local media from online references.
- Preview and play associated videos/GIFs where supported.
- Warn when a referenced file is unavailable.

Allow users to associate YouTube URLs with exercises. Open the original video or use authorised playback mechanisms.

Respect copyright, YouTube terms, and Android restrictions. Do not claim a video has been generated or downloaded when it has not.

Where feasible, integrate authorised demonstration videos showing controlled movement, posture, start/end positions, and primary muscle groups. Otherwise, use available authorised media and document the limitation.

### 12. Workout History and Progress KPIs

Store workout history locally.

Where applicable, record:

- Workout ID, name, and category.
- Date and time.
- Planned and completed exercises.
- Sets and repetitions.
- Left/right completion.
- Duration and rest intervals.
- Total workout duration.
- Completion status.
- Notes.

Provide historical summaries, date/category/status filters, weekly/monthly activity, progress charts where sufficient data exists, and local export/backup.

Redesign the Progress tab to show:

- Workouts completed and planned.
- Completion percentage.
- Weekly/monthly activity.
- Total workout time.
- Weight and weight trend.
- BMI.
- Latest glucose.
- Latest blood pressure.
- HbA1c where recorded.
- Body fat and muscle mass where recorded.
- Connected health and activity KPIs.

Show the latest valid health reading with its timestamp and source. For glucose, show whether the reading is fasting, random, or post-meal. For blood pressure, display systolic and diastolic values.

Display “Not recorded” or “No data available” where appropriate. Never invent values.

Do not estimate body fat or muscle mass from BMI alone.

### 13. Personal Health Tracker and privacy

Support recording:

- Weight.
- Height.
- Blood pressure.
- Blood glucose.
- Measurement date/time.
- Glucose type and time after eating where relevant.
- HbA1c.
- Body fat percentage.
- Muscle mass percentage.
- Measurement source/device where available.

Calculate BMI using recorded height and weight. Interpret health measurements using appropriate recognised guidance and correct units.

Do not apply fasting glucose thresholds to random or post-meal measurements. Do not present a single reading as a confirmed diagnosis.

Do not apply universal body fat or muscle mass ranges without considering the relevant method, device, age, sex, and reference data.

Protect health records with appropriate authentication and encryption. Never store passwords in plain text. Provide manual lock/logout and inactivity locking where feasible.

Keep health records local by default. Do not transmit them to unrelated external servers, analytics, AI services, or third-party plugins.

### 14. Google Fit, Health Connect, and Fastrack Smart World

#### 14.1 Confirmed user setup

The user has confirmed:

- Google Fit is installed and used.
- Health Connect is available, and both Google Fit and Fastrack Smart World were reported as visible in the relevant permissions area.
- Fastrack smartwatch model: 38202.
- Companion app: Fastrack Smart World.
- Fastrack app version: 6.41.0.279927.
- Watch firmware: V1.42.
- Target platform: Android.

The presence of the apps does not prove that all requested data is accessible. Verify the actual permissions and records.

#### 14.2 Fastrack data requested

Investigate and integrate, where supported:

- Steps.
- Heart rate.
- Sleep.
- Calories burned.
- Blood oxygen saturation (SpO₂).
- Weight and body measurements, if available.
- Workout sessions and duration.

#### 14.3 Google Fit data requested

Investigate and integrate, where supported:

- Steps.
- Heart rate.
- Activity and workout sessions.
- Calories burned.
- Sleep, if available.
- Weight, if available.

Do not assume all data types are exposed by both providers. Determine the actual source for each metric.

#### 14.4 Integration implementation

Before coding:

1. Inspect the available Health Connect permissions and data types.
2. Verify whether Fastrack Smart World writes the required records to Health Connect.
3. Determine the currently supported Google health-data integration path for new Android development.
4. Verify API availability, permission requirements, and provider restrictions.
5. Implement the supported authorised integration path.
6. Request only necessary permissions and let the user approve or revoke access.
7. Never request the user's Google or Fastrack account password in the app or this conversation.
8. Never embed credentials, tokens, or client secrets in source code or GitHub.
9. Use appropriate secure storage for tokens where required.
10. If a metric or provider is unsupported, document it and provide manual entry or another supported fallback where practical.

Do not rely on undocumented APIs, scrape private application storage, or fabricate endpoints.

#### 14.5 Connected Health & Activity dashboard

Add a section to Progress showing these metrics when real records are available:

- Steps today.
- Latest heart rate.
- Sleep duration.
- Calories burned.
- SpO₂.
- Latest weight.
- Workout sessions and duration.

For every KPI, show the value, unit, source where known, and record timestamp or reporting period.

Clearly distinguish a daily total from the latest individual measurement.

Provide separate connection-status panels for Google health data and Fastrack/Health Connect data. Display:

- Connection/authorisation status.
- Available permissions.
- Last successful sync.
- Latest record timestamp.
- Sync outcome.
- Errors and missing-data status.
- Manual Sync Now/Retry where supported.
- Disconnect/revoke-access option.

Supported states must include Not Connected, Awaiting Permission, Connected, Syncing, Synced Successfully, No Data Available, Permission Denied, Sync Failed, and Unsupported/Blocked.

Do not display “Connected” simply because an app is installed. Do not display “Synced Successfully” until an actual read operation has succeeded.

#### 14.6 Privacy and duplicate handling

Keep health records local by default. Respect permission revocation, protect cached records, avoid duplicate imports, and retain source/timestamp metadata.

Do not send measurements to unrelated external servers or services. Explain the data being accessed before requesting permission.

#### 14.7 Integration acceptance tests

Verify that:

- Authorisation and revocation work.
- Each supported data type can be read from the actual source.
- Values, units, timestamps, and source labels are accurate.
- Unsupported and missing data are handled clearly.
- Repeated sync does not create duplicates.
- Sync failures and permission denial are handled.
- No unauthorised external transmission occurs.

Do not claim that Google Fit, Health Connect, or Fastrack synchronisation works until an end-to-end test succeeds on the target Android environment.

### 15. Data integrity and storage

Maintain separate, consistent data structures for:

- Workout library definitions.
- Workout media references.
- Weekly plan templates.
- Daily plans and calendar dates.
- Session-specific parameters.
- Workout execution and side-specific completion.
- Workout history.
- Health measurements.
- External data-source permissions and sync metadata.

Use stable IDs and reliable references.

When executing a workout, preserve the selected session configuration. Completed workout history must retain a snapshot of the relevant parameters so future library edits do not rewrite history.

Initialise or migrate V3 data safely without changing V2 data.

### 16. Android build and GitHub workflow

Use the existing V3 repository and workflow.

- Verify the V3 application ID differs from V2.
- Preserve required Capacitor configuration and Android overrides.
- Review Android permissions.
- Confirm dependency and build-version compatibility.
- Keep secrets out of the repository.
- Build the debug APK using GitHub Actions.
- Save the APK as a downloadable artifact if the build succeeds.
- Document release APK/AAB generation if required.
- Verify V2 and V3 can be installed independently.

Do not claim that an APK was successfully built unless the actual workflow succeeds.

### 17. Required acceptance testing

Test and report the following:

1. Branding and navigation.
2. Calendar date selection and accurate summaries.
3. Structured Plan dropdowns and dynamic categories.
4. Multi-exercise planning, editing, reordering, and saving.
5. Library CRUD and media association.
6. Genuine XLSX template creation, sharing, importing, preview, validation, merge, replace, and export.
7. Imported plan execution with correct parameters.
8. Independent Yoga and Cardio execution.
9. Direct workout selection.
10. Exercise, rest, transition, and breathing timers.
11. Left/right tracking.
12. Local media access and playback on Android.
13. Workout history persistence.
14. Progress KPIs and latest measurement handling.
15. Health Tracker privacy and calculations.
16. Google Fit/Health Connect/Fastrack integration, where supported.
17. Duplicate prevention and sync failure handling.
18. Successful Android build.
19. Independent V2/V3 installation.
20. Regression testing of retained functionality.

Use these statuses:

- PASS — verified successfully.
- FAIL — tested and unsuccessful.
- BLOCKED — requires unavailable access, credentials, APIs, or hardware.
- NOT TESTED — not yet verified.
- NOT IMPLEMENTED — not delivered.

Code presence alone is not proof of completion.

### 18. Development tracking

Maintain these repository documents:

- `V3_MASTER_REQUIREMENTS.md`
- `V3_PROGRESS.md`
- `V3_ACCEPTANCE_TESTS.md`
- `V3_CHANGELOG.md`
- `V3_KNOWN_LIMITATIONS.md`
- `V3_BUILD_AND_RELEASE.md`

Update them after each meaningful implementation checkpoint.

If development is interrupted, preserve the current state and document completed work, pending work, blockers, modified files, and test results. Resume from the latest checkpoint rather than restarting.

### 19. Scope exclusion

Do not implement the PDF user manual in this release. Defer it to a future version.

### 20. Final delivery requirements

Deliver:

1. Updated V3 source code in the existing V3 project.
2. Working implementation of the specified UI and functional changes.
3. Updated GitHub Actions build workflow.
4. Downloadable APK artifact if successfully built.
5. Genuine Excel template and functional schedule import/export.
6. Updated project documentation and acceptance tests.
7. Completed, incomplete, blocked, and untested feature summary.
8. Known limitations and integration dependencies.
9. Android build and installation instructions.
10. Confirmation that V2 was not modified.

### Final instruction

Begin by inspecting the current V3 code and identifying the actual implementation gaps.

Then modify the existing source code in small, testable phases. Prioritise the redesigned UI, Calendar, Plan, Workout Library, Excel integration, Yoga/Cardio selection, workout execution, Progress KPIs, and data integrity. Complete the media, health-security, and authorised health-data integrations to the extent supported by the environment.

Do not stop after producing a plan or mock-up. Implement the code, run available checks, build the Android APK where possible, and report evidence-based results.

**The objective is a complete, independently buildable V3 Android application based on the existing V2/V3 code, with verified functionality—not a new project, a UI-only prototype, or an untested package.**

## Development scope addendum

> **Scope decision for this development pass (user-confirmed):** Implement the rest of this master scope. Defer (a) Google Fit / Health Connect / Fastrack Smart World provider integration and connected-health sync KPIs, and (b) the extended data-integrity/migration/backup/restore and production-release assurance workstream. The existing GitHub Actions debug-APK build workflow remains in scope. Device acceptance testing will be performed by the user after installation. Deferred items are not to be marked PASS.

---
