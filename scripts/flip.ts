/*
 * This script inverts all comment lines in a set of files that end with
 * '// FLIP: TechnoLibLocal' comment (The "TechnoLibLocal" is configurable).
 *
 * For TechnoLibLocal, this lets you add a subdirectory "TechnoLib" (as a git
 * subtree, submodule, or just a copy) that contains TechnoLib instead of
 * pulling down the latest release from jitpack.io through Maven or whatever
 * that remote repo is.
 *
 * For BUILD ALL BOTS, it toggles including not just the competition bots, but
 * the ForTeaching, RoadRunner Quick Start and any other 'bot' subdirs that we
 * may not want to build in the heat of competition work.
 *
 * "bun libflip" will, from a normal repo, enable you to build & link with
 * a local copy of TechnoLib in <root>\TechnoLib. Once you're done, run
 * "bun libflip" again, and it will restore the dependency on the publicly
 * released copy of TechnoLib
 */

import { promises } from 'fs';
import { argv } from 'process';

const { readFile, writeFile } = promises;

type FileList = {
  key: string;
  files: string[];
};

const technoLib: FileList = {
  key: 'TechnoLibLocal',
  files: [
    'LearnBot/build.gradle',
    'Sixteen750/build.gradle',
    'Ptechnodactyl/build.gradle',
    'Hoops/build.gradle',
    'Twenty403/build.gradle',
    'build.dependencies.gradle',
    'settings.gradle',
  ],
};
const botList: FileList = {
  key: 'BUILD ALL BOTS',
  files: ['settings.gradle', 'build.gradle'],
};
const meepList: FileList = {
  key: 'MeepMeepLocal',
  files: ['MeepMeepTesting/build.gradle', 'settings.gradle'],
};
// This is a map of keys (the argument to call flip.js with) to objects that
// are a name (the tag at the end of the comment) and an array of files to
// scan & process
const fileList = new Map<string, FileList>([
  ['lib', technoLib],
  ['bot', botList],
  ['meepmeep', meepList],
  ['meep', meepList],
]);

// For any line that ends with '// FLIP: id',
// toggle the line comment 'status'
function toggleLine(lineFull: string, id: string) {
  const commentMarker = '// FLIP: ' + id;
  const line = lineFull.trimEnd();
  // If the line doesn't end with the comment marker, don't change it at all
  if (!line.endsWith(commentMarker)) {
    return line;
  }
  if (line.trimStart().startsWith('//')) {
    // If the line starts with a comment,
    // remove the comment at the beginning of the line
    return line.replace(/^( *)\/\/ */, '$1');
  } else {
    // If the line does *not* start with a comment,
    // add the comment at the beginning of the line
    return line.replace(/^( *)([^ ])/, '$1// $2');
  }
}

// Read the file, flip the comments for lines with markers, the write it back
async function toggleFile(file: string, key: string) {
  try {
    const contents = await readFile(file, 'utf-8');
    const resultArray = contents.split('\n');
    const toggled = resultArray.map((elem) => toggleLine(elem, key));
    await writeFile(file, toggled.join('\n'));
  } catch (e) {
    // Some file access problem :(
    console.error('Problem with dealing with file:', file);
    console.error(e);
  }
}

async function toggleLinesWithComments(arg: string) {
  const elem: FileList | undefined = fileList.get(arg);
  if (elem === undefined) {
    throw Error(
      'This script only understands :' + [...fileList.keys()].join(', '),
    );
  }
  let { key, files } = elem;
  for (let filename of files) {
    await toggleFile(filename, key);
  }
}

// Call our script...
toggleLinesWithComments(argv[2])
  // Reporting errors
  .catch((err) => console.error(err))
  .finally(() => console.log('Processing complete'));
