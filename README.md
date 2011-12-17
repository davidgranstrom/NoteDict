NoteDict
========

NoteDict provides a way of working with different notation styles in SuperCollider.<br>
Each note name in the dictionary returns either a MIDI note number or a frequency (Hz).<br>
Both flat (b) and sharp (#) notations are supported.<br>

The following styles are available:

* `Traditional (German)`
* `Helmholtz`
* `MIDI`

Basic Usage
-----------

First create a new dictionary `n= NoteDict();`

<h4>Accessing</h4>

Get one note: `n['c1']` returns MIDI note number `60`<br>
Get an array of notes: `n[[ 'c', 'e', 'g' ]]` this will return `[ 48, 52, 55 ]`<br>
Get a note name from a MIDI note number `60.asNote` returns `c1`<br>

..check the help file for more examples and usage.

Installation 
------------

download the .zip or clone the files into your SuperCollider Extensions directory 

    $ cd ~/Library/Application\ Support/SuperCollider/Extensions/
    $ git clone git@github.com:davidgranstrom/NoteDict.git
                                                           
