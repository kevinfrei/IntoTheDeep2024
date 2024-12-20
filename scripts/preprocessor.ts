import * as fs from 'fs/promises';
import * as path from 'path';
import { removeComments } from './helpers/removeComments.js';
import { MakeAutoConstantsTransformer } from './helpers/AutoConstTransformer.js';

/*** BEGIN CONFIGURATION STUFF ***/

// This is the package name that we're going to use for our generated code.
const packageDir = ['com', 'robotcode', 'shared'];

/*** END CONFIGURATION STUFF ***/

// The first two command line arguments are the bun binary and this script
const [, , outDir, className, ...filesAsString] = process.argv;
const outputLocation = path.join(outDir, ...packageDir);

// We're only finding files that include "auto" and "const" in their paths.
const constantsFiles = filesAsString.filter(
  (val) =>
    val.toLocaleLowerCase().indexOf('auto') >= 0 &&
    val.toLocaleLowerCase().indexOf('const') >= 0 &&
    val.toLocaleLowerCase().indexOf('meepmeep') < 0,
);
const drivebaseFile = filesAsString.find(
  (name) => name.toLocaleLowerCase().indexOf('drivebase') >= 0,
);

async function main(): Promise<void> {
  // Make the output directory structure
  try {
    await fs.mkdir(outputLocation, { recursive: true });
  } catch (e) {
    console.error(e);
  }

  // TODO: Extract the drivebase specs/RR setup code from the bot,
  // then pass those values into the Constants Transformer

  const transformer = MakeAutoConstantsTransformer();
  // Remove the comments then parse the file
  for (const file of constantsFiles) {
    const origFileContents = await fs.readFile(file, 'utf8');
    const contents = removeComments(origFileContents);
    transformer.transformFile(contents);
  }

  let output = `package ${packageDir.join('.')};
/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!!!!!!!!!WARNING!!!!!!!!!!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! DO NOT EDIT THIS FILE !!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!!!!!!!!!WARNING!!!!!!!!!!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * Any edits you make will get obliterated when you build.
 * Instead, make edits to th${constantsFiles.length > 1 ? 'ese files' : 'is file'}:
 *
 * ${constantsFiles.join('\n * ')}
 *
 * and your changes will be reflected in here when you next build.
 *
 * If you're struggling to get things to compile properly, know that you should
 * not be using "fully qualified" typenames, but should import the whole
 * types, and the build will fix them up to work for MeepMeep.
 *
 * If you're not using fully qualified names, and things are still messed up
 * you should reach out to Kevin to figure out what's going on.
 *
 */
`;

  /* You can move this before the ` to see the raw input to the script:

   * ${process.argv.join("\n * ")};
  
   */

  output += transformer.collectImports();

  output += `\n\npublic class ${className} {\n`;
  output += transformer.getTransformedCode().join('\n  ');
  output += '\n}\n';
  await fs.writeFile(path.join(outputLocation, `${className}.java`), output);
}

main().catch(console.error);
