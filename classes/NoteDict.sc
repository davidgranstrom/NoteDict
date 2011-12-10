// a dictionary for pitch notations
// david granstrom 25/11/2011

NoteDict {

    classvar <>instances;
    classvar small, big, numbered;
    classvar h_small, h_big;

    var <>style;
    var <>output;
    var <notes;

    *new{|style=\traditional, output=\MIDI|
        ^super.new.init.makeDict(style, output);
    }

    *create{|style|

        instances= instances ? ();

        if(instances[style].isNil, {
            instances.put(style, NoteDict(style));
        });

        ^instances[style]
    }

    *numberToNote{|num, style=\traditional|
        ^NoteDict.create(style).notes.findKeyForValue(num);
    }

    init{

        var smallnames, bignames;
        var smallnotes, bignotes;
        var hs, hb;

        notes= ();

        smallnames= [ 'c', 'd', 'e', 'f', 'g', 'a', 'b' ];
        bignames= [ 'C', 'D', 'E', 'F', 'G', 'A', 'B' ];

        hs= [ nil, '´', '´´', '´´´', '´´´´', '´´´´´', '´´´´´´' ];
        hb= [ nil, ',', ',,', ',,,' ];

        smallnotes= ['#', 'b'].collect{|mod| 
            smallnames.stutter.collect{|note, i|
                if(mod=='#', {
                    if(i.even, { note }, { (note++mod).asSymbol });
                }, {
                    if(i.odd, { note }, { (note++mod).asSymbol });
                });
            }.removeAll(['b#', 'e#', 'cb', 'fb'])
        };

        bignotes= ['#', 'b'].collect{|mod| 
            bignames.stutter.collect{|note, i|
                if(mod=='#', {
                    if(i.even, { note }, { (note++mod).asSymbol });
                }, {
                    if(i.odd, { note }, { (note++mod).asSymbol });
                });
            }.removeAll(['B#', 'E#', 'Cb', 'Fb'])
        };

        // small > sixth-lined octave
        small= 7.collect{|oct|
            smallnotes.collect{|row| 
                row.collect{|note|
                    if(oct!=0, { (note++oct).asSymbol }, { note });
                }
            }
        }.flatten;

        // great < subsubcontra octave
        big= 4.collect{|oct|
            bignotes.collect{|row| 
                row.collect{|note|
                    if(oct!=0, { (note++oct).asSymbol }, { note });
                }
            }
        }.flatten;

        // helmholtz notation
        // small > seventh-lined octave
        h_small= hs.collect{|oct|
            smallnotes.collect{|row| 
                row.collect{|note|
                    if(oct.notNil, { (note++oct).asSymbol }, { note });
                }
            }
        }.flatten;

        // helmholtz notation
        // great < subsubcontra octave
        h_big= hb.collect{|oct|
            bignotes.collect{|row| 
                row.collect{|note|
                    if(oct.notNil, { (note++oct).asSymbol }, { note });
                }
            }
        }.flatten;

        // MIDI octave numbers
        numbered= 11.collect{|oct| 
            bignotes.collect{|row| 
                row.collect{|note| ((note++(oct-1)).asSymbol) }
            } 
        }.flatten;
    }

    makeDict{|style, output|

        var s_oct= 0;
        var b_oct= 0;
        var n_oct= -1;

        switch(style)

        {\traditional} { 
            small.do{|row, x|
                var note;
                if(x.even, { s_oct= s_oct+1 });
                row.do{|name, num| 
                    note= (num+36)+(12*s_oct);
                    if(note<128, {
                        notes.put(name, note); 
                    })
                }
            };
            big.do{|row, x|
                var note;
                if(x.even, { b_oct= b_oct+1 });
                row.do{|name, num| 
                    note= (num+48)-(12*b_oct);
                    if(note<128, {
                        notes.put(name, note); 
                    })
                }
            };
        }
        {\helmholtz} { 
            h_small.do{|row, x|
                var note;
                if(x.even, { s_oct= s_oct+1 });
                row.do{|name, num| 
                    note= (num+36)+(12*s_oct);
                    if(note<128, {
                        notes.put(name, note); 
                    })
                }
            };
            h_big.do{|row, x|
                var note;
                if(x.even, { b_oct= b_oct+1 });
                row.do{|name, num| 
                    note= (num+48)-(12*b_oct);
                    if(note<128, {
                        notes.put(name, note); 
                    })
                }
            };
        }
        {\MIDI} { 
            numbered.do{|row, x|
                var note;
                if(x.even, { n_oct= n_oct+1 });
                row.do{|name, num| 
                    note= num+(12*n_oct); 
                    if(note<128, {
                        notes.put(name, note);
                    })
                }
            };
        }
        { "style does not exist".throw }
        ;

        // output style
        case
        {output==\freq} { notes= notes.collect{|num| num.midicps } }
        {output!=\MIDI and:{output!=\freq}}
        { "wrong output. supported formats are: \\MIDI or \\freq".throw }
        ;
    }

    at{|note|
        ^if(note.isArray.not, {
            this.notes[note]
        }, {
            this.notes.atAll(note)
        })
    }
}
