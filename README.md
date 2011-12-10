NoteDict
========

NoteDict provides a way of working with different notation styles in SuperCollider. 
Each note name in the dictionary returns either a MIDI note number or a frequency (Hz). 
Both flat (b) and sharp (#) notations are supported.

The following styles are available:
* Traditional (German)
* Helmholtz
* MIDI

Basic Usage
-----------

create a new dictionary
    n= NoteDict();

accessing
    n['c1']; // get one note
    n[[ 'c', 'e', 'g' ]]; // get an array of notes
more examples in the help file!

Installation 
------------

download or clone the files into your SuperCollider Extensions directory 

    `cd ~/Library/Application\ Support/SuperCollider/Extensions/`
    `git clone git@github.com:davidgranstrom/NoteDict.git`

