const fs = require('fs');
const vm = require('vm');
const html = fs.readFileSync('www/index.html', 'utf8');
const scripts = [...html.matchAll(/<script\b([^>]*)>([\s\S]*?)<\/script>/gi)];
let checked = 0;
for (const [, attrs, source] of scripts) {
  if (/\bsrc\s*=/.test(attrs) || !source.trim()) continue;
  new vm.Script(source, { filename: `www/index.html:inline-script-${++checked}` });
}
if (!checked) throw new Error('No inline JavaScript blocks were found.');
console.log(`PASS: parsed ${checked} inline JavaScript block(s).`);
