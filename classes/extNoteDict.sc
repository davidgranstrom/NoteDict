
+ SimpleNumber {
    asNote{|style=\traditional|
        ^NoteDict.numberToNote(this, style)
    }
}

+ Symbol {
    asNote{|style=\traditional|
        ^NoteDict.symbolToNote(this, style)
    }
}

+ SequenceableCollection {
    asNote{|style=\traditional|
        ^this.collect{|x| x.asNote(style) }
    }
}
