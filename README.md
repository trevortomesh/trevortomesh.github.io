Website for Dr. Trevor M. Tomesh
================================

Overview
--------
- Static site built with HTML, CSS, and JavaScript only.
- Homepage (`index.html`) renders content from `cards.json` and supports tag filtering + search.
- A new Projects hub (`projects.html`) consolidates links to major projects, tools, and writing.

Key Paths
--------
- `index.html`: Homepage with cards + sidebar.
- `projects.html`: Consolidated list of projects and tools.
- `cards.json`: Card data used to render homepage content.
- `styles.css` / `script.js`: Site styles and client-side behavior.
- `archive/`: Archived versions of older pages.
- `assembler/`, `cids-graph/`, `TheWayPoint/`, `pwudgen/`: Project sub-sites.

Recent Cleanup
-------------
- Added `projects.html` and a “Projects” card for easier discovery.
- Added sidebar navigation links to Home, Projects, Archive, The Way Point, Toy Assembler, and CIDS Graph.
- Switched the JSON Writer card link to use the local `json-writer.html`.

Local Development
-----------------
This site is static. Open `index.html` in a browser, or serve locally with any static file server.

Housekeeping
------------
- `.gitignore` added to ignore macOS and IDE files.

