const fs = require('fs');
const html = fs.readFileSync('www/index.html', 'utf8');
const pkg = JSON.parse(fs.readFileSync('package.json', 'utf8'));
const workflow = fs.readFileSync('.github/workflows/build-apk.yml', 'utf8');
const plugin = fs.readFileSync('android-overrides/GifFolderPlugin.java', 'utf8');
const manifestPatch = fs.readFileSync('patch_manifest.py', 'utf8');
const checks = [
  ['Workout Library CRUD controls', /id="libadd"/.test(html) && /function saveLibraryDefinition/.test(html)],
  ['Calendar date editing and empty-date creation', /function renderCal/.test(html) && /data-caledit/.test(html)],
  ['Calendar-date workbook mapping and selected-day import', /Calendar Date \(YYYY-MM-DD\)/.test(html) && /csvselectedonly/.test(html)],
  ['Plan day copy and reorder controls', /id="pcopy"/.test(html) && /data-mv/.test(html)],
  ['Plan editor and library picker', /id="planlibpick"/.test(html) && /function renderPlan/.test(html)],
  ['Yoga/Cardio selection controls', /data-sessionselect/.test(html) && /function startSession/.test(html)],
  ['XLSX import/export source', /XLSX\.utils\.book_new/.test(html) && /XLSX\.read/.test(html)],
  ['XLSX dependency and offline bundle step', Boolean(pkg.dependencies && pkg.dependencies.xlsx) && /node_modules\/xlsx\/dist\/xlsx\.full\.min\.js/.test(workflow)],
  ['History filters/export and progress trend', /id="histresults"/.test(html) && /id="histexport"/.test(html) && /Weekly activity trend/.test(html)],
  ['Encrypted Health Tracker and inactivity lock', /AES-GCM/.test(html) && /refreshHealthLock/.test(html)],
  ['Android SAF folder picker implementation', /ACTION_OPEN_DOCUMENT_TREE/.test(plugin) && /takePersistableUriPermission/.test(plugin)],
  ['Broad file-management permission removed from manifest patch', !/MANAGE_EXTERNAL_STORAGE/.test(manifestPatch)]
];
const failed = checks.filter(([, ok]) => !ok);
checks.forEach(([name, ok]) => console.log(`${ok ? 'PASS' : 'FAIL'}: ${name}`));
if (failed.length) process.exit(1);
console.log(`PASS: ${checks.length} static structure checks. These do not prove runtime/device behavior.`);
